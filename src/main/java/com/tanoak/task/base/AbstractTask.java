package com.tanoak.task.base;

import com.tanoak.entity.ColumnInfo;
import freemarker.template.TemplateException;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

/**
 * 抽象的任务
 *
 * @author tanoak
 * @date 2019/12/25
 */
@Setter
@Getter
public abstract class AbstractTask implements Serializable {
    /**
     * 表名
     */
    protected String tableName;
    /**
     * 类名
     */
    protected String className;

    /**
     * 表信息
     */
    protected List<ColumnInfo> columnInfoList;


    /**
     * 抽象的任务
     * Controller、Service、Dao
     *
     * @param className 类名
     */
    public AbstractTask(String className) {
        this.className = className;
    }

    /**
     * 抽象的任务
     * Entity
     *
     * @param className  类名
     * @param columnInfoList 表信息
     */
    public AbstractTask(String className, List<ColumnInfo> columnInfoList) {
        this.className = className;
        this.columnInfoList = columnInfoList;
    }


    /**
     * 抽象的任务
     * Mapper
     *
     * @param tableName  表名
     * @param className  类名
     * @param columnInfoList 表信息
     */
    public AbstractTask(String tableName, String className, List<ColumnInfo> columnInfoList) {
        this.tableName = tableName;
        this.className = className;
        this.columnInfoList = columnInfoList;
    }

    /**
     * 运行
     *
     * @throws IOException       IOException
     * @throws TemplateException 模板异常
     */
    public abstract void run() throws IOException, TemplateException;

}
