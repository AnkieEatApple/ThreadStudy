package Chapter28;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 ********************************************************************** 
 * @Title: Subscribe.java
 * @Description:
 * @author ankie
 * @date 2018年12月18日
 * @version 1.0
 **********************************************************************
 */
@Retention(RetentionPolicy.RUNTIME) // 注解存在于class字节码文件中，运行的时候可以获取
@Target(ElementType.METHOD) // 表示注解的类型为方法类型
public @interface Subscribe {

	// 注解时指定topic，不指定的情况下为默认的topic(default-topic)
	String topic() default "default-topic";
	// 这里预定义的方法只是使用了topic()方法，返回的是字符串
	// 如果没有定义的话，返回default-topic字符串
}

/*
 * 元注解
 *
 * @Retention(RetentionPolicy.RUNTIME):
 * 修饰的注解，表示注解的信息被保留在class文件(字节码文件)中当程序编译时，会被虚拟机保留在运行时， 注意：局部变量声明的注解不能储存在 .class
 * 中
 *
 */