package com.example.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Administrator on 2017/10/12.
 */
@Target(ElementType.TYPE)//作用于类
@Retention(RetentionPolicy.SOURCE) //源码阶段
public @interface PayEntryGenerator {
    String packageName();
    Class <?> payEntryTemplate();
}
