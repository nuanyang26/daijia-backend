package com.atguigu.daijia.common.login;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//登录判断
@Target(ElementType.METHOD)   // 元注解-注解作用的地方target
@Retention(RetentionPolicy.RUNTIME)  // 元注解-在什么时候能使用
public @interface GuiguLogin {

}
