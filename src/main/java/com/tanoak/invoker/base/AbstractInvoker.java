package com.tanoak.invoker.base;

import com.tanoak.db.CustomDataSource;
import com.tanoak.entity.ColumnInfo;
import com.tanoak.task.base.AbstractTask;
import com.tanoak.utils.TaskQueue;
import freemarker.template.TemplateException;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author tanoak
 * @date 2019/12/25
 */
@Getter
@Setter
public abstract class AbstractInvoker implements Invoker {
    protected String tableName;
    protected String className;
    protected String parentTableName;
    protected String parentClassName;
    protected String foreignKey;
    protected String relationalTableName;
    protected String parentForeignKey;
    protected List<ColumnInfo> tableInfos;
    protected CustomDataSource customDataSource = CustomDataSource.getDataSource();
    protected TaskQueue taskQueue = new TaskQueue();
    /**
     * 手动创建线程池
     */
    private ExecutorService executorPool = new ThreadPoolExecutor(6, 6,
            2L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>(1024), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());

    private void initDataSource() {
        getTableInfos();
    }

    /**
     * 获取表信息
     */
    protected abstract void getTableInfos();

    /**
     * 初始化任务
     */
    protected abstract void initTasks();

    @Override
    public void execute() {
        try {
            initDataSource();
            initTasks();
            while (!taskQueue.isEmpty()) {
                AbstractTask task = taskQueue.poll();
                executorPool.execute(() -> {
                    try {
                        task.run();
                    } catch (IOException | TemplateException e) {
                        e.printStackTrace();
                    }
                });
            }
            executorPool.shutdown();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
