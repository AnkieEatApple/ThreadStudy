##Chapter18 不可变对象设计模式

####一、整理的常用方法

1. Arrays方式初始化

```
// 定义一个List并使用Arrays的方式进行初始化
List<String> list = 
	Arrays.asList("Java", "Thread", "Concurrency", "Scala", "Clojure");
	
// 获取并行的Stream，然后通过map函数对list数据进行加工
// 对该list进行循环输出，可以使用forEach(System.out::println)
list.parallelStream().map(String::toUpperCase).forEach(System.out::println);
```