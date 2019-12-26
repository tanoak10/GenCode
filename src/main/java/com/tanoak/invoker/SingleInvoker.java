package com.tanoak.invoker;

import com.tanoak.db.CustomDataSource;
import com.tanoak.invoker.base.AbstractBuilder;
import com.tanoak.invoker.base.AbstractInvoker;
import com.tanoak.invoker.base.Invoker;
import com.tanoak.utils.GeneratorUtil;
import com.tanoak.utils.StringUtil;

/**
 * 单表调用程序
 *
 * @author tanoak
 * @date 2019/12/25
 */
public class SingleInvoker extends AbstractInvoker {

    @Override
    protected void getTableInfos() {
        tableInfos = customDataSource.getMetaData(CustomDataSource.getDataSource().getTableName());
    }

    @Override
    protected void initTasks() {
        taskQueue.initSingleTasks(className, tableName, tableInfos);
    }

    public static class Builder extends AbstractBuilder {
        private SingleInvoker invoker = new SingleInvoker();

        public Builder setTableName(String tableName) {
            invoker.setTableName(tableName);
            return this;
        }

        public Builder setClassName(String className) {
            invoker.setClassName(className);
            return this;
        }

        @Override
        public Invoker build() {
            if (!isParamValid()) {
                return null;
            }
            return invoker;
        }

        @Override
        public void checkBeforeBuild() throws Exception {
            if (StringUtil.isBlank(invoker.getTableName())) {
                throw new Exception("Expect table's name, but get a blank String.");
            }
            if (StringUtil.isBlank(invoker.getClassName())) {
                invoker.setClassName(GeneratorUtil.generateClassName(invoker.getTableName()));
            }
        }
    }

}
