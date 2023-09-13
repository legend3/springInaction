# 1.1 简化Java开发
虽然Spring用bean或者JavaBean来表示应用组件，但并不意味着Spring组 件必须要遵循JavaBean规范。
一个Spring组件可以是任何形式的POJO。在本书中，我采用 JavaBean的广泛定义，即POJO的同义词。
>Spring最根本的使命上：简化Java开发。

为了降低Java开发的复杂性，Spring采取了以下4种关键策略：
- 基于POJO的轻量级和最小侵入性编程；
- 通过依赖注入和面向接口实现松耦合；
- 基于切面和惯例进行声明式编程；
- 通过切面和模板减少样板式代码。
>几乎Spring所做的任何事情都可以追溯到上述的一条或多条策略

### 1.1.1 激发POJO的潜能
Spring赋予POJO魔力的方式之一就是通 过DI来装配它们。让我们看看DI是如何帮助应用对象彼此之间保持松散耦合的。
### 1.1.2 依赖注入
耦合具有两面性（two-headed beast）。一方面，紧密耦合的代码难以测试、难以复用、难以理  
解，并且典型地表现出“打地鼠”式的bug特性（修复一个bug，将会出现一个或者更多新的bug）。  
另一方面，一定程度的耦合又是必须的——完全没有耦合的代码什么也做不了。为了完成有  
实际意义的功能，不同的类必须以适当的方式进行交互。总而言之，耦合是必须的，但应当被 小心谨慎地管理。  

通过DI，对象的依赖关系将由系统中负责协调各对象的第三方组件在创建对象的时候进行设
定。对象无需自行创建或管理它们的依赖关系，如图1.1所示，依赖关系将被自动注入到需要
它们的对象当中去。  

创建应用组件之间协作的行为通常称为装配（wiring）。。Spring有多种装配bean的方式：
- 采用 XML是很常见的一种装配方式
- Spring还支持使用Java来描述配置
Spring通过应用上下文（Application Context）装载bean的定义并把它们组装起来。Spring应用上 下文全权负责对象的创建和组装。  
Spring自带了多种应用上下文的实现，它们之间主要的区别 仅仅在于如何加载配置。  

### 1.1.3 应用切面
DI能够让相互协作的软件组件保持松散耦合，而面向切面编程（aspect-oriented programming，AOP）允许你把遍布应用各处的功能分离出来形成可重用的组件。  
除了实现自身核心的功能之外，这些组件还经常承 担着额外的职责。诸如日志、事务管理和安全这样的系统服务经常融入到自身具有核心业务
逻辑的组件中去，这些系统服务通常被称为横切关注点，因为它们会跨越系统的多个组件。  
问题：如果将这些关注点分散到多个组件中去，你的代码将会带来双重的复杂性。  
- 实现系统关注点功能的代码将会重复出现在多个组件中。这意味着如果你要改变这些关
注点的逻辑，必须修改各个模块中的相关实现。即使你把这些关注点抽象为一个独立的
模块，其他模块只是调用它的方法，但方法的调用还是会重复出现在各个模块中。  
- 组件会因为那些与自身核心业务无关的代码而变得混乱。一个向地址簿增加地址条目的
方法应该只关注如何添加地址，而不应该关注它是不是安全的或者是否需要支持事务。  
aop作用：  
切面想象为覆盖在很多组件之上的一个外壳。应用是由那些实现各 自业务功能的模块组成的。  
借助AOP，可以使用各种功能层去包裹核心业务层。这些层以声明的方式灵活地应用到系统中，你的核心应用甚至根本不知道它们的存在。  
这是一个非常强大的理念，可以将安全、事务和日志关注点与核心业务逻辑相分离。  
### 1.1.4 使用模板消除样板式代码
你是否写过这样的代码，当编写的时候总会感觉以前曾经这么写过？我的朋友，这不是似曾
相识。这是样板式的代码（boilerplate code）。通常为了实现通用的和简单的任务，你不得不一
遍遍地重复编写这样的代码。  
Spring旨在通过模板封装来消除样板式代码。Spring的**JdbcTemplate**使得执行数据库操作时，避
免传统的JDBC样板代码成为了可能。  
### 1.2 容纳你的Bean
在基于Spring的应用中，你的应用对象生存于Spring容器（container）中。  
Spring容器负责创建对象，装配它们，配置它们并管理它们的整个生命周期，从生存到死亡（在这里，可
能就是new到finalize()）。  
在下一章，你将了解如何配置Spring，从而让它知道该创建、配置和组装哪些对象。但首先，最
重要的是了解容纳对象的容器。理解容器将有助于理解对象是如何被管理的。  
Spring容器并不是只有一个。Spring自带了多个容器实现，可以归为两种不同的类型(**容器的不同实现**)：
- bean工厂 
（由org.springframework. beans. factory.eanFactory接口定义）是最简单的容
器，提供基本的DI支持。
- 应用上下文
（由org.springframework.context.ApplicationContext接口定义）基于
BeanFactory构建，并提供应用框架级别的服务，例如从属性文件解析文本信息以及发布应用
事件给感兴趣的事件监听者。
>虽然我们可以在bean工厂和应用上下文之间任选一种，但bean工厂对大多数应用来说往往太
低级了，因此，**应用上下文**要比bean工厂更受欢迎。我们会把精力集中在应用上下文的使用
上，不再浪费时间讨论bean工厂。

Spring自带了多种类型的应用上下文。下面罗列的几个是你最有可能遇到的：
- AnnotationConfigApplicationContext：从一个或多个基于**Java的配置类**中加载
Spring应用上下文。
- AnnotationConfigWebApplicationContext：从一个或多个基于**Java的配置类**中
加载Spring Web应用上下文。
- ClassPathXmlApplicationContext：从类路径下的一个或多个**XML配置文件**中加
载上下文定义，把应用上下文的定义文件作为类资源。
- FileSystemXmlapplicationcontext：从文件系统下的一个或多个**XML配置文件**
中加载上下文定义。
- XmlWebApplicationContext：从Web应用下的一个或多个**XML配置文件**中加载上下
文定义。
### 1.2.2 bean的生命周期
1. Spring对bean进行实例化；
2. Spring将值和bean的引用注入到bean对应的属性中；
3. 如果bean实现了BeanNameAware接口，Spring将bean的ID传递给setBean-Name()方法；
4. 如果bean实现了BeanFactoryAware接口，Spring将调用setBeanFactory()方法，将
BeanFactory容器实例传入；
5. 如果bean实现了ApplicationContextAware接口，Spring将调
用setApplicationContext()方法，将bean所在的应用上下文的引用传入进来；
6. 如果bean实现了BeanPostProcessor接口，Spring将调用它们的post-ProcessBeforeInitialization()方法；
7. 如果bean实现了InitializingBean接口，Spring将调用它们的after-PropertiesSet()方法。类似地，如果bean使用init-method声明了初始化方法，该方法
也会被调用； 
8. 如果bean实现了BeanPostProcessor接口，Spring将调用它们的post-rocessAfterInitialization()方法； 
9. 此时，bean已经准备就绪，可以被应用程序使用了，它们将一直驻留在应用上下文中，直到
   该应用上下文被销毁； 
10. 如果bean实现了DisposableBean接口，Spring将调用它的destroy()接口方法。同样，
    如果bean使用destroy-method声明了销毁方法，该方法也会被调用。
## 1.3 俯瞰Spring风景线
正如你所看到的，Spring框架关注于通过DI、AOP和消除样板式代码来简化企业级Java开发。 
即使这是Spring所能做的全部事情，那Spring也值得一用。但是，Spring实际上的功能超乎你的
想象。  
在Spring框架之外还存在 一个构建在核心框架之上的庞大生态圈，它将Spring扩展到不同的领域，
例如Web服务、 REST、移动开发以及NoSQL。  
### 1.3.1 Spring模块
在Spring 4.0中，Spring框架的发布版本包括了20个不同的模块，每个模块会有3个JAR文件。  
这些模块依据其所属的功能可以划分为6类不同的功能:  

















