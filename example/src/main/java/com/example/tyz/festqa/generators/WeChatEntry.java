package com.example.tyz.festqa.generators;



/**
 * Created by Administrator on 2017/10/12.
 */

import com.example.annotations.EntryGenerator;
import com.example.tyz.latte.wechat.templates.WXEntryTemplate;

@SuppressWarnings("unused")
@EntryGenerator(
        packageName = "com.example.tyz.festqa",
        entryTemplate = WXEntryTemplate.class
)
public interface WeChatEntry {
}
