package ${BasePackageName}${ControllerPackageName};

import ${BasePackageName}${EntityPackageName}.${ClassName};
import ${BasePackageName}${ServicePackageName}.${ClassName}Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author ${Author}
 * @date  ${Date}
 */
@RestController
@RequestMapping(value = "/${EntityName}")
public class ${ClassName}Controller {
    @Resource
    private ${ClassName}Service ${EntityName}Service;

    @GetMapping(value = {"/get"})
    public Object get(@RequestParam String id) {
        ${ClassName} ${EntityName} = ${EntityName}Service.get(id);
        return ${EntityName};
    }
    @GetMapping(value = {"/list"})
    public Object list() {
    List<${ClassName}> ${EntityName}s = ${EntityName}Service.findAllList();
    return ${EntityName}s;
    }

    @PostMapping(value = "/save")
    public String insert(@RequestBody ${ClassName} ${EntityName}) {
        if (${EntityName}Service.save(${EntityName}) > 0) {
            return "success";
        } else {
            return "failed";
        }
    }

    @PostMapping(value = "/insertBatch")
    public String insertBatch(@RequestBody List<${ClassName}> ${EntityName}s) {
        if (${EntityName}Service.saveBatch(${EntityName}s) > 0) {
            return "success";
        } else {
            return "failed";
        }
    }

}
