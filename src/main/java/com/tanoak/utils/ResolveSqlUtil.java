package com.tanoak.utils;

//import cn.tanoak.test.JdbcType2JavaType;
//import cn.tanoak.test.POJOfield;
//import cn.tanoak.test.PoJoMaker;
//import cn.tanoak.test.TemplateUtil;

import com.google.common.base.CaseFormat;
import com.tanoak.entity.ColumnInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * 解析Sql util
 *
 * @author tanoak
 * @date 2019/12/25
 */
public class ResolveSqlUtil {


    /**
     * 句柄表名
     * 处理表名为pojo的名字
     *
     * @param tableName  表名
     * @param ignoreName 表名的前缀或者后缀如：_tab
     * @param pojoSuffix 实体类的后缀如：DO
     * @return 实体类的名称
     */
    public static String handleTableName(String tableName, String ignoreName, String pojoSuffix) {
        //删除前后两个 `
        tableName = tableName.replace("`", "");
        //删除表名的前缀或者后缀
        if (ignoreName != null) {

            tableName = tableName.replaceAll(ignoreName, "");
        }
        //转大驼峰
        tableName = CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, tableName);
        //加上pojo的后缀
        return tableName + pojoSuffix;
    }

    /**
     * 处理领域
     * 处理pojo的所有字段
     *
     * @param sql sql
     * @return Ppjo的实体类
     */
    public static List<ColumnInfo> handleField(String sql) {
        List<ColumnInfo> columnInfoList = new ArrayList<>();
        String[] sqlArray = sql.split("\n");
        for (int i = 1; i < sqlArray.length - 1; i++) {

            String rawField = sqlArray[i].trim();
            String[] fieldArr = rawField.split(" ");
            if (rawField.contains("utf8") || rawField.contains("UTF8")) {
                continue;
            }
            if (rawField.contains(" KEY ")) {
                continue;
            }
            if (!fieldArr[0].contains("`")) {
                return null;
            }
            String field = fieldArr[0].replace("`", "");
            //如果是以下划线方式命名则需要转成小驼峰
            ColumnInfo columnInfo = new ColumnInfo();
            //是否主键
            if ("id".equalsIgnoreCase(field)) {
                columnInfo.setPrimaryKey(true);
            }
            //字段名
            columnInfo.setColumnName(field);
            String property = CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, field);
            //属性名
            columnInfo.setPropertyName(property);
            //将数据库类型转换为java类型
            String type = "";
            type = JdbcType2JavaType.map.get(fieldArr[1].toUpperCase().replaceAll("[^a-z^A-Z]", ""));
            if (type == null) {
                type = "Object";
            }
            columnInfo.setType(type);
            for (int j = 2; j < fieldArr.length; j++) {
                if (fieldArr[j].equalsIgnoreCase("COMMENT")) {
                    String comment = fieldArr[j + 1];
                    comment = comment.replace("'", "");
                    comment = comment.replace(",", "");
                    columnInfo.setComment(comment);
                    continue;
                }
            }
            if (columnInfo != null) {
                columnInfoList.add(columnInfo);
            }
        }

        return columnInfoList;
    }


    /**
     * 处理表名称的注释
     */
    private static String handleTableComment(String rawTableComment) {
        rawTableComment = rawTableComment.trim();
        String[] tableCommentArr = rawTableComment.split(" ");
        if (tableCommentArr.length == 0) {
            return "";
        }

        String tableComment = tableCommentArr[tableCommentArr.length - 1];
        if (!tableComment.contains("COMMENT")) {
            return "";
        }

        tableComment = tableComment.replace("COMMENT=", "");
        tableComment = tableComment.replace("'", "");
        tableComment = tableComment.replace(";", "");
        return tableComment;
    }
}