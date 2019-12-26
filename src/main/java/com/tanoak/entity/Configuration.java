package com.tanoak.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 三木
 * @date 2019/12/23
 */
@Data
public class Configuration implements Serializable {
    private String author;
    private String packageName;
    private Path path;
    private String tableName ;
}
