package com.tanoak.entity;

import com.tanoak.utils.StringUtil;
import lombok.Data;

import java.io.Serializable;

/**
 * @author 三木
 * @date 2019/12/25
 * 列
 */
@Data
public class ColumnInfo implements Serializable {
    /**
     * 列名
     */
    private String columnName;
    /**
     * 类型
     */
    private String type;
    /**
     * 属性名
     */
    private String propertyName;
    /**
     * 是否主键
     */
    private boolean isPrimaryKey;
    /**
     * 注释
     */
    private String comment;

    public ColumnInfo() {

    }

    public ColumnInfo(String columnName, String type, boolean isPrimaryKey) {
        this.columnName = columnName;
        this.type = type;
        this.propertyName = StringUtil.columnName2PropertyName(columnName);
        this.isPrimaryKey = isPrimaryKey;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public boolean isPrimaryKey() {
        return isPrimaryKey;
    }

    public void setPrimaryKey(boolean primaryKey) {
        isPrimaryKey = primaryKey;
    }
}
