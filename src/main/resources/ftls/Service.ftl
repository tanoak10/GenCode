package ${BasePackageName}${InterfacePackageName};

import ${BasePackageName}${EntityPackageName}.${ClassName};

import java.util.List;

/**
 * @author ${Author}
 * @date  ${Date}
 */
public interface ${ClassName}Service {

    /**
    * select by primary key
    *
    * @param id primary key
    * @return ${ClassName} by primary key
    */
    ${ClassName} findById(${ClassType} id);

    /**
    * find list
    * @return List<${ClassName}>
    */
    List<${ClassName}> findList();

    /**
    * save  table
    *
    * @param ${EntityName} ${ClassName}
    * @return insert count
    */
    int save(${ClassName} ${EntityName});

    /**
    *  batch insert  data
    *
    * @param ${EntityName}s List<${ClassName}>
    * @return insert count
    */
    int saveBatch(List<${ClassName}> ${EntityName}s);
}
