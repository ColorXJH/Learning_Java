package com.master.chapter16;

public record UserRecord(String name,UserRecord parent) {
}

/**
 * 这个结构的效果就等同于User类以及其中的模板代码的效果
 * 大幅度的减少的模板代码的书写
 */