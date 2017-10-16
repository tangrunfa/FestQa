package com.example.tyz.festqa.generators;


import com.example.annotations.PayEntryGenerator;
import com.example.tyz.latte.wechat.templates.WXPayEntryTemplate;

/**
 * Created by Administrator on 2017/10/12.
 */
@SuppressWarnings("unused")
@PayEntryGenerator(
        packageName = "com.example.tyz.festqa",
        payEntryTemplate = WXPayEntryTemplate.class
)
public interface WeChatPayEntry {
}
