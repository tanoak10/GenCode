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
 * 控制器的任务
 *
 * @author tanoak
 * @date 2019/12/25
 */
public class ControllerTask extends AbstractTask {

    public ControllerTask(String className, List<ColumnInfo> tableInfos) {
        super(className);
    }

    @Override
    public void run() throws IOException, TemplateException {
        // 生成Controller填充数据
        System.out.println("Generating " + className + "Controller.java");
        Map<String, String> controllerData = new HashMap<>();
        controllerData.put("BasePackageName", ConfigUtil.getConfiguration().getPackageName());
        controllerData.put("ControllerPackageName", ConfigUtil.getConfiguration().getPath().getController());
        if (StringUtil.isBlank(ConfigUtil.getConfiguration().getPath().getService())) {
            controllerData.put("ServicePackageName", ConfigUtil.getConfiguration().getPath().getServiceImpl());
        } else {
            controllerData.put("ServicePackageName", ConfigUtil.getConfiguration().getPath().getService());
        }
        controllerData.put("EntityPackageName", ConfigUtil.getConfiguration().getPath().getEntity());
        controllerData.put("Author", ConfigUtil.getConfiguration().getAuthor());
        controllerData.put("Date", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        controllerData.put("ClassName", className);
        controllerData.put("EntityName", StringUtil.firstToLowerCase(className));
        String filePath = FileUtils.getSourcePath() + StringUtil.package2Path(ConfigUtil.getConfiguration().getPackageName()) + StringUtil.package2Path(ConfigUtil.getConfiguration().getPath().getController());
        String fileName = className + "Controller.java";
        // 生成Controller文件
        FileUtils.generateToJava(FreemarkerConfigUtils.TYPE_CONTROLLER, controllerData, filePath + fileName);
    }
}
