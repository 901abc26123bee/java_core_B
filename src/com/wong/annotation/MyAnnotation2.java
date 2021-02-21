package com.wong.annotation;

import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.LOCAL_VARIABLE;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.MODULE;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
自定義 註釋
after jdk8
* */
@Retention(RetentionPolicy.RUNTIME) // MyAnnotation2 的生命週期必須長於 MyAnnotation（引用註解）
@Target({TYPE, FIELD, METHOD, PARAMETER, CONSTRUCTOR, LOCAL_VARIABLE, MODULE}) // @Target 範圍需和 MyAnnotation（引用註解）一致
public @interface MyAnnotation2 {
	MyAnnotation[] value();
}

/**
 * before jdk8
 public @interface MyAnnotation2 {
	MyAnnotation[] value();
}
 **/
