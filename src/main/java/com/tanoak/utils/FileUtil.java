package com.tanoak.utils;

import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;

/**
 * 文件工具
 *
 * @author tanoak
 * @date 2019/12/25
 */
public class FileUtil {

    /**
     * @param type     使用模板类型
     * @param data     填充数据
     * @param filePath 输出文件
     * @throws IOException
     * @throws TemplateException
     */
    public static void generateToJava(int type, Object data, String filePath) throws IOException, TemplateException {
        File file = new File(filePath);
        if (file.exists()) {
            System.err.println("ERROR: " + file.getPath().substring(file.getPath().lastIndexOf("\\") + 1, file.getPath().length()) + " 已存在，请手动修改");
            return;
        }
        // 获取模板文件
        Template tpl = getTemplate(type);
        // 填充数据
        StringWriter writer = new StringWriter();
        tpl.process(data, writer);
        writer.flush();
        // 写入文件
        FileOutputStream fos = new FileOutputStream(filePath);
        OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
        BufferedWriter bw = new BufferedWriter(osw, 1024);
        tpl.process(data, bw);
        fos.close();
    }

    /**
     * 获取模板
     *
     * @param type 模板类型
     * @return
     * @throws IOException
     */
    private static Template getTemplate(int type) throws IOException {
        switch (type) {
            case FreemarkerConfigUtils.TYPE_ENTITY:
                return FreemarkerConfigUtils.getInstance().getTemplate("Entity.ftl");
            case FreemarkerConfigUtils.TYPE_DAO:
                return FreemarkerConfigUtils.getInstance().getTemplate("Dao.ftl");
            case FreemarkerConfigUtils.TYPE_SERVICE:
                return FreemarkerConfigUtils.getInstance().getTemplate("ServiceImpl.ftl");
            case FreemarkerConfigUtils.TYPE_CONTROLLER:
                return FreemarkerConfigUtils.getInstance().getTemplate("Controller.ftl");
            case FreemarkerConfigUtils.TYPE_MAPPER:
                return FreemarkerConfigUtils.getInstance().getTemplate("Mapper.ftl");
            case FreemarkerConfigUtils.TYPE_INTERFACE:
                return FreemarkerConfigUtils.getInstance().getTemplate("Service.ftl");
            default:
                return null;
        }
    }

    private static String getBasicProjectPath() {
        String path = new File(FileUtil.class.getClassLoader().getResource("").getFile()).getPath() + File.separator;
        StringBuilder sb = new StringBuilder();
        sb.append(path.substring(0, path.indexOf("target"))).append("src").append(File.separator).append("main").append(File.separator);
        return sb.toString();
    }

    /**
     * 获取源码路径
     *
     * @return
     */
    public static String getSourcePath() {
        StringBuilder sb = new StringBuilder();
        sb.append(getBasicProjectPath()).append("java").append(File.separator);
        return sb.toString();
    }

    /**
     * 获取资源文件路径
     *
     * @return
     */
    public static String getResourcePath() {
        StringBuilder sb = new StringBuilder();
        sb.append(getBasicProjectPath()).append("resources").append(File.separator);
        return sb.toString();
    }

}
