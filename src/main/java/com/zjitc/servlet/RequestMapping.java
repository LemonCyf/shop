package com.zjitc.servlet;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Create By IntelliJ IDEA
 *
 * @author: jsonor
 * @create-Time: 2017/10/30 14:30
 * @description: ${description}
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestMapping {

  /**
   * The url .....
   */
  String url() default "";
}
