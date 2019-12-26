package com.tanoak.entity;

import lombok.Data;

/**
 * 路径
 *
 * @author tanoak
 * @date 2019/12/25
 */
@Data
public class Path {
    private String controller;
    private String serviceImpl;
    private String service;
    private String dao;
    private String entity;
    private String mapper;


}
