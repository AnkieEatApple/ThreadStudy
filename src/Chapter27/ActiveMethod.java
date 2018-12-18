package Chapter27;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 ********************************************************************** 
 * @Title: ActiveMethod.java
 * @Description:
 * @author ankie
 * @date 2018年12月15日
 * @version 1.0
 **********************************************************************
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ActiveMethod {

}
