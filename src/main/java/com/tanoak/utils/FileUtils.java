package com.tanoak.utils;

import cn.hutool.core.io.FileUtil;
import exception.FileException;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * 文件工具
 *
 * @author tanoak
 * @date 2019/12/25
 */
public class FileUtils {

    /**
     * 生成Java
     *
     * @param type     使用模板类型
     * @param data     填充数据
     * @param filePath 输出文件
     * @throws IOException       IOException
     * @throws TemplateException 模板异常
     */
    public static void generateToJava(int type, Object data, String filePath) throws IOException, TemplateException {
        File file = new File(filePath);
        if (file.exists()) {
            throw new FileException("ERROR: " + file.getPath().substring(file.getPath().lastIndexOf("\\") + 1) + " 已存在，请手动修改") ;
        }
        if (!FileUtil.exist(file.getParent())) {
            FileUtil.mkdir(file.getParent()) ;
        }
        // 获取模板文件
        Template tpl = getTemplate(type);
        // 填充数据
        StringWriter writer = new StringWriter();
        tpl.process(data, writer);
        writer.flush();
        // 写入文件
        FileOutputStream fos = new FileOutputStream(filePath);
        OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
        BufferedWriter bw = new BufferedWriter(osw, 1024);
        tpl.process(data, bw);
        fos.close();
    }

    /**
     * 获取模板
     *
     * @param type 模板类型
     * @return {@link Template}
     * @throws IOException IOException
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
        String path = new File(Objects.requireNonNull(FileUtils.class.getClassLoader().getResource(""))
                .getFile()).getPath() + File.separator;
        return path.substring(0, path.indexOf("target")) + "src" + File.separator + "main" + File.separator;
    }

    /**
     * 获取源码路径
     *
     * @return {@link String}
     */
    public static String getSourcePath() {
        return getBasicProjectPath() + "java" + File.separator;
    }

    /**
     * 获取资源路径
     * 获取资源文件路径
     *
     * @return {@link String}
     */
    public static String getResourcePath() {
        return getBasicProjectPath() + "resources" + File.separator;
    }

}
