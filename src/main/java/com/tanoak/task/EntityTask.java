package com.tanoak.task;

import com.tanoak.entity.ColumnInfo;
import com.tanoak.task.base.AbstractTask;
import com.tanoak.utils.*;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author 三木
 * @date 2019/12/25
 * 实体
 */
public class EntityTask extends AbstractTask {

    /**
     * 1.单表生成  2.多表时生成子表实体
     */
    public EntityTask(String className, List<ColumnInfo> infos) {
        super(className, infos);
    }


    @Override
    public void run() throws IOException, TemplateException {
        // 生成Entity填充数据
        System.out.println("Generating " + className + ".java");
        Map<String, String> entityData = new HashMap<>();
        entityData.put("BasePackageName", ConfigUtil.getConfiguration().getPackageName());
        entityData.put("EntityPackageName", ConfigUtil.getConfiguration().getPath().getEntity());
        entityData.put("Author", ConfigUtil.getConfiguration().getAuthor());
        entityData.put("Date", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        entityData.put("ClassName", className);
        // 单表关系
        entityData.put("Properties", GeneratorUtil.generateEntityProperties(columnInfoList));
        String filePath = FileUtils.getSourcePath() + StringUtil.package2Path(ConfigUtil.getConfiguration().getPackageName()) + StringUtil.package2Path(ConfigUtil.getConfiguration().getPath().getEntity());
        String fileName = className + ".java";
        // 生成Entity文件
        FileUtils.generateToJava(FreemarkerConfigUtils.TYPE_ENTITY, entityData, filePath + fileName);
    }
}
