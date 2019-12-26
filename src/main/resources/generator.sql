CREATE TABLE `project_stage` (
                                 `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
                                 `gmt_create` datetime NOT NULL COMMENT '创建时间',
                                 `gmt_modified` datetime NOT NULL COMMENT '修改时间',
                                 `project_id` bigint(20) NOT NULL COMMENT '项目Id',
                                 `name` varchar(64) NOT NULL COMMENT '阶段名称',
                                 PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=116 DEFAULT CHARSET=utf8mb4 COMMENT='项目阶段';