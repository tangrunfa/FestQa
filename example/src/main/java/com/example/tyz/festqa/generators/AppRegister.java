package com.example.tyz.festqa.generators;

import com.example.annotations.AppRegisterGenerator;
import com.example.tyz.latte.wechat.templates.AppRegisterTemplate;

/**
 * Created by Administrator on 2017/10/12.
 */
@SuppressWarnings("unused")
@AppRegisterGenerator(
        packageName = "com.example.tyz.festqa",
        registerTemplate = AppRegisterTemplate.class
)
public interface AppRegister {
}

