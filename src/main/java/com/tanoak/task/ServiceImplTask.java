package com.tanoak.task;

import com.tanoak.entity.ColumnInfo;
import com.tanoak.task.base.AbstractTask;
import com.tanoak.utils.ConfigUtil;
import com.tanoak.utils.FileUtils;
import com.tanoak.utils.FreemarkerConfigUtils;
import com.tanoak.utils.StringUtil;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 服务任务
 *
 * @author tanoak
 * @date 2019/12/25
 */
public class ServiceImplTask extends AbstractTask {

    public ServiceImplTask(String className, List<ColumnInfo> tableInfos) {
        super(className, tableInfos);
    }

    @Override
    public void run() throws IOException, TemplateException {
        // 生成Service填充数据
        Map<String, String> serviceData = new HashMap<>();
        serviceData.put("BasePackageName", ConfigUtil.getConfiguration().getPackageName());
        serviceData.put("ServicePackageName", ConfigUtil.getConfiguration().getPath().getServiceImpl());
        serviceData.put("EntityPackageName", ConfigUtil.getConfiguration().getPath().getEntity());
        serviceData.put("DaoPackageName", ConfigUtil.getConfiguration().getPath().getDao());
        serviceData.put("Author", ConfigUtil.getConfiguration().getAuthor());
        serviceData.put("Date", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        serviceData.put("ClassName", className);
        ColumnInfo columnInfo = columnInfoList.stream().filter(info -> info.getPropertyName().equalsIgnoreCase("id")).findFirst().get();
        serviceData.put("ClassType", columnInfo.getType());
        serviceData.put("EntityName", StringUtil.firstToLowerCase(className));
        String filePath = FileUtils.getSourcePath() + StringUtil.package2Path(ConfigUtil.getConfiguration().getPackageName()) + StringUtil.package2Path(ConfigUtil.getConfiguration().getPath().getServiceImpl());
        serviceData.put("Impl", "Impl implements " + className + "Service");
        serviceData.put("Override", "\n    @Override");
        serviceData.put("InterfaceImport", "import " + ConfigUtil.getConfiguration().getPackageName() + ConfigUtil.getConfiguration().getPath().getService() + "." + className + "Service;");
        String fileName = className + "ServiceImpl.java";
        System.out.println("Generating " + className + "ServiceImpl.java");
        // 生成Service文件
        FileUtils.generateToJava(FreemarkerConfigUtils.TYPE_SERVICE, serviceData, filePath + fileName);
    }
}
