package com.tanoak.task;

import com.tanoak.entity.ColumnInfo;
import com.tanoak.task.base.AbstractTask;
import com.tanoak.utils.*;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Mapper任务
 *
 * @author tanoak
 * @date 2019/12/25
 */
public class MapperTask extends AbstractTask {

    /**
     * 单表Mapper
     */
    public MapperTask(String className, String tableName, List<ColumnInfo> infos) {
        super(tableName, className, infos);
    }

    @Override
    public void run() throws IOException, TemplateException {
        // 生成Mapper填充数据
        System.out.println("Generating " + className + "Mapper.xml");
        Map<String, String> mapperData = new HashMap<>();
        mapperData.put("PackageName", ConfigUtil.getConfiguration().getPackageName() + "." + ConfigUtil.getConfiguration().getPath().getDao());
        mapperData.put("BasePackageName", ConfigUtil.getConfiguration().getPackageName());
        mapperData.put("DaoPackageName", ConfigUtil.getConfiguration().getPath().getDao());
        mapperData.put("EntityPackageName", ConfigUtil.getConfiguration().getPath().getEntity());
        mapperData.put("ClassName", className);
        mapperData.put("EntityName", StringUtil.firstToLowerCase(className));
        mapperData.put("TableName", tableName);
        mapperData.put("InsertProperties", GeneratorUtil.generateMapperInsertProperties(columnInfoList));
        mapperData.put("PrimaryKey", getPrimaryKeyColumnInfo(columnInfoList).getColumnName());
        mapperData.put("WhereId", "#{" + getPrimaryKeyColumnInfo(columnInfoList).getPropertyName() + "}");
        mapperData.put("Id", "#{id}");
        // 单表
        mapperData.put("ColumnMap", GeneratorUtil.generateMapperColumnMap(tableName, columnInfoList));
        mapperData.put("ResultMap", GeneratorUtil.generateMapperResultMap(columnInfoList));
        mapperData.put("InsertBatchValues", GeneratorUtil.generateMapperInsertBatchValues(columnInfoList, StringUtil.firstToLowerCase(className)));
        mapperData.put("InsertValues", GeneratorUtil.generateMapperInsertValues(columnInfoList));
        mapperData.put("UpdateProperties", GeneratorUtil.generateMapperUpdateProperties(columnInfoList));
        String filePath = FileUtils.getSourcePath() + StringUtil.package2Path(ConfigUtil.getConfiguration().getPackageName()) + StringUtil.package2Path(ConfigUtil.getConfiguration().getPath().getDao());
        String fileName = className + "Mapper.xml";
        // 生成Mapper文件
        FileUtils.generateToJava(FreemarkerConfigUtils.TYPE_MAPPER, mapperData, filePath + fileName);
    }

    private ColumnInfo getPrimaryKeyColumnInfo(List<ColumnInfo> list) {
        for (ColumnInfo columnInfo : list) {
            if (columnInfo.isPrimaryKey()) {
                return columnInfo;
            }
        }
        return null;
    }

}
