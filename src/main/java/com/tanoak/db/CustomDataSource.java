package com.tanoak.db;

import cn.hutool.core.io.file.FileReader;
import com.tanoak.entity.ColumnInfo;
import com.tanoak.utils.ConfigUtil;
import com.tanoak.utils.FileUtil;
import com.tanoak.utils.ResolveSqlUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * 自定义数据来源
 *
 * @author 三木
 * @date 2019/12/24
 */
@Getter
@Setter
@ToString
public class CustomDataSource {
    private String tableName;
    private String sql;
    private static CustomDataSource singleCustomDataSource = new CustomDataSource();

    private CustomDataSource() {

    }

    /**
     * 读文件的Sql
     */
    static String readFileSql;

    static {
        String fileName = "generator.sql";
        FileReader fileReader = new FileReader(FileUtil.getResourcePath() + fileName);
        readFileSql = fileReader.readString();
    }

    public static CustomDataSource getDataSource() {
        singleCustomDataSource.setTableName(ConfigUtil.getConfiguration().getTableName());
        singleCustomDataSource.setSql(readFileSql);
        return singleCustomDataSource;
    }

    /**
     * 获取表结构数据
     *
     * @param tableName 表名
     * @return 包含表结构数据的列表
     */
    public List<ColumnInfo> getMetaData(String tableName) {
        // 1. 解析表名称
        if (!sql.contains(tableName)) {
            return null;
        }
        //2 .解析字段
        return ResolveSqlUtil.handleField(sql);
    }
}
