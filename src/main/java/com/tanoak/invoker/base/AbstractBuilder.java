package com.tanoak.invoker.base;

/**
 * 抽象构建器
 *
 * @author tanoak
 * @date 2019/12/25
 */
public abstract class AbstractBuilder {

    /**
     * 构建
     *
     * @return {@link Invoker}
     */
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

    /**
     * build前检查 类名
     *
     * @throws Exception 异常
     */
    public abstract void checkBeforeBuild() throws Exception;

}
