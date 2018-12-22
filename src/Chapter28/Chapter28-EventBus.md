##EventBus详解
1. 主要介绍了EventBus中的语法重点以及结构

###一、类图

![EventBus类图.jpeg](https://i.loli.net/2018/12/22/5c1dc8f000702.jpeg)

--
###二、实现思路
####1. 概述
1. 总体来说是一种发布者订阅者的模式
2. 首先订阅的用户需要注册，同时在生成对象的类的方法中需要有注解为@Subscribe的方法
3. 注册时利用反射机制将对象和生成对象的类中的订阅注解的方法，与对象封装成Subscriber模型，存入到对应的注解的Queue中
4. 在发布者发布消息的时候，首先利用反射机制，检查发布消息的类型和队列中封装的模型的方法的参数类型是否相同，若相同的话，利用反射中的方法的invoke调用封装模型中方法，传入引用对象和方法参数。
5. 关闭该方法的时候，直接将线程池shutdown，就没有办法发布消息了。

####2. 疑问
1. 为什么会用反射的方法来获取对象中的类中的注释方法？
2. 该EventBus中有两种方式，一种是同步，一种是异步，同步中的两种继承了Executor，Seq和PreThread，但是没区别啊
3. 线程池的Executor的使用
4. 注解的原理

###三、语法知识
####1. 过滤集合中的元素 Java 8 Streams filter
1. 参考： [Java 8 Streams filter](https://blog.csdn.net/u014042066/article/details/76360380)

--
####2. Java8 使用Map中的computeIfAbsent方法
1. 参考：[java8 使用Map中的computeIfAbsent方法构建本地缓存，提高程序效率](https://my.oschina.net/cloudcoder/blog/217775)

--
####3. Java注解总结
1. 参考：[Java注解总结](https://www.jianshu.com/p/73a778c1b5b7)

#####3.1 问题
1. method.getDeclaredAnnotation(注解类.class)和method.getAnnotation(注解类.class)，其实是一个方法。

--
####4. 通过反射机制调用某个类的方法
1. 参考：[通过反射机制调用某个类的方法](https://blog.csdn.net/Handsome_fan/article/details/54862873)
2. 参考：[java反射拿到方法的参数名列表的方法](https://blog.csdn.net/viviju1989/article/details/8529453)

#####4.1. 问题


