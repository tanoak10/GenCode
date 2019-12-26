package ${BasePackageName}${DaoPackageName};

import ${BasePackageName}${EntityPackageName}.${ClassName};
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author ${Author}
 * @date  ${Date}
 */
@Mapper
public interface ${ClassName}Dao {

    /**
    * select by primary key
    *
    * @param id primary key
    * @return ${ClassName} by primary key
    */
    ${ClassName} selectByPrimaryKey(${ClassType} id);

    /**
    * select by primary key
    * @return List<${ClassName}>
    */
    List<${ClassName}> selectList();

    /**
    * insert  table
    *
    * @param  ${EntityName} ${ClassName}
    * @return insert count
    */
    int insert(${ClassName} ${EntityName});

    /**
    *  batch insert  table
    *
    * @param  ${EntityName}s List<${ClassName}>
    * @return insert count
    */
    int insertBatch(List<${ClassName}> ${EntityName}s);

    /**
    * update ${ClassName}
    *
    * @param ${EntityName} ${ClassName}
    * @return update count
    */
    int updateByPrimaryKey(${ClassName} ${EntityName});

    /**
    * delete by primary key ${ClassName}
    *
    * @param id  primary key
    * @return update count
    */
    int deleteByPrimaryKey(${ClassType} id);


}