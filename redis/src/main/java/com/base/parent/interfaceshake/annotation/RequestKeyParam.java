package com.base.parent.interfaceshake.annotation;

import java.lang.annotation.*;

@Target({ElementType.METHOD,ElementType.PARAMETER,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface RequestKeyParam {
}
