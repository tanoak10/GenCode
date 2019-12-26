package ${BasePackageName}${ServicePackageName};

import ${BasePackageName}${DaoPackageName}.${ClassName}Dao;
import ${BasePackageName}${EntityPackageName}.${ClassName};
${InterfaceImport}
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ${Author}
 * @date  ${Date}
 */
@Service
public class ${ClassName}Service${Impl} {
    @Resource
    private ${ClassName}Dao ${EntityName}Dao;
    ${Override}
    public ${ClassName} findById(${ClassType} id){
        return ${EntityName}Dao.selectByPrimaryKey(id);
    }
    ${Override}
    public List<${ClassName}> findList() {
        return ${EntityName}Dao.selectList();
    }
    ${Override}
    public int save(${ClassName} ${EntityName}) {
        return ${EntityName}Dao.insert(${EntityName});
    }
    ${Override}
    public int saveBatch(List<${ClassName}> ${EntityName}s){
        return ${EntityName}Dao.insertBatch(${EntityName}s);
    }
}
