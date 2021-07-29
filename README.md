# Simple_Spring
## 背景：为了能够后续了解 Spring 相关源码进行铺垫，实现一个简单的 Spring 容器

## 0.1-SNAPSHOT: 
  
  概述：实现最简单的容器
  
  选择 Map 作为容器承载体，Key 为 BeanName，Value 为实体对象

  BeanDefinition Bean 对象定义信息
  BeanFactory 获取 Bean 对象 Factory 

    新增类图：
        // TODO
    新增类对象说明:
        // TODO

## 0.2-SNAPSHOT:
  
  概述: 优化代码结构以及项目层次，便于后续功能扩展
  
  优化：
    1. 优化 BeanDefinition 字段不再保存 Object 对象而是 Class，便于按需实例化
    2. 引入相关接口以及抽象类，模板方法的设计模式优化代码结构以及层次，添加自定义异常类，处理异常
    3. 引入泛型避免获取 Bean 对象需要强制类型转换
  新增：
    1. 新增单例 Bean 对象的 Map，无需每次获取 Bean 对象实例化
  待补充：
    1. 添加日志系统，记录关键位置日志信息

    新增类图：
        // TODO
    新增类对象说明:
        // TODO

## 0.3-SNAPSHOT

    概述：支持按照传入参数匹配构造器实例化对象、添加 JDK、CGLIB 实例化策略

    优化：
        1. 0.2-SNAPSHOT 版本仅支持无参改造函数，无法指定对象参数，支持按照传入参数匹配构造器实例化
            （未匹配到或者未传入参数则默认为无参构造函数）
        2. 由于实例化策略变更，将 BeanFactory 中 getBean 逻辑统一包装到 doGetBean 中，做到对内逻辑收口
    新增
        1. 实例化策略可以有很多因此定义实例化策略接口，新增 JDK 动态代理、CGLIB 动态代理 实例化策略

    新增类图：
        // TODO
    新增类对象说明:
        // TODO