
# 欢迎来到 GenCode
1. 把需要生成的表sql放到resource下的generator.sql
2. 在generator.yml下进行配置，配置项如下
```
author: 作者（填自己名字）
packageName: cn.tanoak.（包名）
tableName: project_stage(表名)
path:（如不需要生成，用#注释）
#  controller: controller （controller层）
#  service: service.impl(service实现层)
#  interf: service（service层）
#  dao: dao.mapper（dao层）
  entity: dao.entity（bo层）
#  mapper: mapper（dao xml）
```
xml生成的路径是跟dao接口一起的，在项目的pom.xml配置下打包、不然xml会丢失
```xml
    <build>
        <resources>
            <resource>
                <directory>src/main/java</directory>
                <includes>
                    <include>**/*.xml</include>
                </includes>
            </resource>
            <resource>
                <directory>src/main/resources</directory>
                <includes>
                    <include>**/*</include>
                </includes>
            </resource>
        </resources>
    </build>
```