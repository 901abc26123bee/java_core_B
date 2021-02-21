package com.wong.annotation;

/*
自定義註解
* */

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

import java.lang.annotation.Repeatable;

// after jdk8
@Target({TYPE, FIELD, METHOD, PARAMETER, CONSTRUCTOR, LOCAL_VARIABLE, MODULE})
@Repeatable(MyAnnotation2.class) // jdk8
//沒有定義 @Retention，默認的生命週期 CLASS
public @interface MyAnnotation {
// String[] value();

    // 也可以定義帶默認值的
    String[] value() default {"hehe"};
}

/**
 // before jdk8
@Target({TYPE, FIELD, METHOD, PARAMETER, CONSTRUCTOR, LOCAL_VARIABLE, MODULE})
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotation {
// String[] value();

    // 也可以定義帶默認值的
    String[] value() default {"hehe"};
}

**/
