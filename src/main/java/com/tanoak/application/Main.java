package com.tanoak.application;

import com.tanoak.invoker.SingleInvoker;
import com.tanoak.invoker.base.Invoker;

/**
 * 主要
 *
 * @author tanoak
 * @date 2019/12/25
 */
public class Main {

    public static void main(String[] args) {
        single();
    }

    private static void single() {
        Invoker invoker = new SingleInvoker.Builder()
                .setTableName("project_stage")
                .setClassName("ProjectStage")
                .build();
        invoker.execute();
    }

}
