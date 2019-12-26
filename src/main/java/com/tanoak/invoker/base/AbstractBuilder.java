package com.tanoak.invoker.base;

/**
 * 抽象构建器
 *
 * @author tanoak
 * @date 2019/12/25
 */
public abstract class AbstractBuilder {

    public abstract Invoker build();

    /**
     * 检查参数有效性
     *
     * @return boolean
     */
    protected boolean isParamValid() {
        try {
            checkBeforeBuild();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public abstract void checkBeforeBuild() throws Exception;

}
