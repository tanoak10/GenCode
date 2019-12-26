package com.tanoak.utils;

import com.tanoak.entity.ColumnInfo;
import com.tanoak.task.*;
import com.tanoak.task.base.AbstractTask;

import java.util.LinkedList;
import java.util.List;

/**
 * 任务队列
 *
 * @author tanoak
 * @date 2019/12/25
 */
public class TaskQueue {

    private LinkedList<AbstractTask> taskQueue = new LinkedList<>();

    private void initCommonTasks(String className, List<ColumnInfo> tableInfos) {
        if (!StringUtil.isBlank(ConfigUtil.getConfiguration().getPath().getController())) {
            taskQueue.add(new ControllerTask(className,tableInfos));
        }
        if (!StringUtil.isBlank(ConfigUtil.getConfiguration().getPath().getServiceImpl())) {
            taskQueue.add(new ServiceImplTask(className,tableInfos));
        }
        if (!StringUtil.isBlank(ConfigUtil.getConfiguration().getPath().getService())) {
            taskQueue.add(new ServiceTask(className,tableInfos));
        }
        if (!StringUtil.isBlank(ConfigUtil.getConfiguration().getPath().getDao())) {
            taskQueue.add(new DaoTask(className, tableInfos));
        }
    }

    public void initSingleTasks(String className, String tableName, List<ColumnInfo> tableInfos) {
        initCommonTasks(className, tableInfos);
        if (!StringUtil.isBlank(ConfigUtil.getConfiguration().getPath().getEntity())) {
            taskQueue.add(new EntityTask(className, tableInfos));
        }
        if (!StringUtil.isBlank(ConfigUtil.getConfiguration().getPath().getMapper())) {
            taskQueue.add(new MapperTask(className, tableName, tableInfos));
        }
    }

    public boolean isEmpty() {
        return taskQueue.isEmpty();
    }

    public AbstractTask poll() {
        return taskQueue.poll();
    }

}
