# 如何配置单数据源

## 直接配置所需的Bean

数据源相关

- DataSource(根据选择的连接池实现决定)

## 事务相关（可选）

- DataSourceTransactionManager
- TransactionTemplate

## 操作相关（可选）

- JdbcTemplate



------

# Spring Boot做了哪些配置

## DataSourceAutoConfiguration

- 配置DataSource

## DataSourceTransactionManagerAutoConfiguration

- 配置DataSourceTransactionManager

## JdbcTemplateAutoConfiguration

- 配置JdbcTemplate



------

# Spring Boot中的多数据源配置

## 手工配置两组DataSource及相关内容

## 与Spring Boot协同工作（二选一）

- 配置@Primary类型的 Bean
- 排除Spring Boot的自动配置
  - DataSourceAutoConfiguration
  - DataSourceTransactionManager
  - JdbcTemplate



------

# Spring的JDBC操作类

## spring-jdbc

- core , JdbcTemplate等相关核心接口和类
- datasource , 数据源相关的辅助类
- object， 将基本的JDBC操作封装成对象
- support, 错误码等其他辅助工具

 

------

# 常用的Bean注解

## 通过注解定义Bean

- **@Component  （通用的注解）**
- **@Repository  （数据库操作）**
- @Service  （业务服务）
- @Controller  （springMVC）
- @RestController  （RESTful Web Service）



------

# 简单的JDBC操作

### jdbc Template

- query
- queryForObject
- queryForList
- update
- execute

------

# Spring 的事务抽象

### 一致的事务模型

- JDBC/Hibernate/myBatis
- DataSource/JTA

------

# 事务传播特性（7个）

- required：使用当前的事务，如果当前没有事务，则创建一个事务，子方法是必须运行在一个事务中的，如果当前存在事务，则加入这个事务，成为一个整体。
- supports:如果当前有事务，则使用事务，如果当前没有事务，则不使用事务
- mandatory：该传播属性强制必须存在一个事务，如果不存在，则抛出异常
- required_new:如果当前有事务，则挂起该事务，并且自己创建一个新的事务给自己使用；如果当前没有事务，则同required   
- not_support:如果当前有事务，则把事务挂起，自己不使用事务去进行数据库操作
- never:如果当前事务存在，则抛出异常
- nested：  如果当前有事务，则开启子事务（嵌套事务），嵌套事务是独立提交或者回滚；如果当前没有事务，则同required

但是如果父事务提交，则会携带子事务一起提交。如果父事务回滚，则子事务会一起回滚。相反，子事务异常，则父事务可以不会滚（捕获子事务的异常），也可以回滚。有异常才会回滚事务，如果异常被捕获，也就是try，catch，就不会回滚事务了，发生异常，或者抛出异常都会回滚事务
### 事务隔离特性

- Read uncommitted (读未提交)：最低级别，任何情况都无法保证。
  第一隔离级别怕回滚，因为它能读取到别的事务未提交的数据。如果当前事务将这个数据当成真正的数据，那么如果别的事务回滚，那么就会数据错误，导致脏读。
- Read committed (读已提交)：可避免脏读的发生。
  第二个隔离级别怕提交，因为提交会导致不可重复读。
- Repeatable read (可重复读)：可避免脏读、不可重复读的发生。*MySQL默认隔离级别
- Serializable (串行化)：可避免脏读、不可重复读、幻读的发生。

------

# 编程式事务

### TransactionTemplate

- TransactionCallback
- TransactionCallbackWithoutResult

### PlatformTransactionManager

- 可以代入TransactionDefinition进行定义

------

# 基于注解的配置方式

### 开启事务注解的方式

- @EnableTransactionManagement

### 一些配置

- proxyTargetClass
- mode
- order

### @Transactional

- transactionManager
- propagation
- isolation
- timeout
- readOnly
- 怎么判断回滚

------

## Spring的JDBC异常抽象

spring会将数据操作的异常转换为DataAccessException，无论使用何种数据访问方式，都能使用一样的异常。







------

# 第三章：O/R Mapping实践

## 认识MyBatis

### MyBatis

- 一款优秀的持久层框架
- 支持定制化SQL、存储过程和高级映射

### 在Spring中使用MyBatis

- MyBatis Spring Adapter
- MyBatis Spring-Boot-Starter



## Mapper的定义与扫描

- @MapperScan配置扫描位置
- @Mapper定义接口
- 映射的定义--XML与注解

------

## 第四章  NoSQL实践

#### MongoDB是一款开源的文档型数据库

#### Spring对MongoDB的支持

- Spring Data MongoDB
  - MongoTemplate
  - Repository支持



------

#### Sring Data MongoDB的基本用法

**注解**

- @Document
- @Id

**MongoTemplate**

- save  /  remove
- Criteria  /  Query  /  Update

![image-20240709102841385](C:\Users\86182\AppData\Roaming\Typora\typora-user-images\image-20240709102841385.png)

------

### Spring Data MongoDB 的 Repository

@EnableMongoRepositories

对应接口

- MongoRepository< T , ID >
- PagingAndSortingRepository < T , ID >
- CrudRepository< T , ID >

------

### Spring 对 Redis 的支持

##### Redis是一款开源的内存KV存储，支持多种数据结构

##### Spring对Redis的支持

- Spring Data Redis
  - 支持的客户端Jedis / Lettuce
  - RedisTemplate
  - Repository支持

![image-20240709111627351](C:\Users\86182\AppData\Roaming\Typora\typora-user-images\image-20240709111627351.png)

![image-20240709112910427](C:\Users\86182\AppData\Roaming\Typora\typora-user-images\image-20240709112910427.png)

#### 基于注解的缓存

**@EnableCaching**

- Cacheable
- CacheEvict
- CachePut
- Caching
- CacheConfig

![image-20240709114014629](C:\Users\86182\AppData\Roaming\Typora\typora-user-images\image-20240709114014629.png)

#### RedisTemplate

RedisTemplate<K , V >

- opsForXxx()

StringRedisTemplate

**一定注意设置过期时间！！！！**

------

#### Redis Repository

**实体注解**

- @RedisHash
- @Id
- @Indexed



##### 处理不同类型数据源的Repository

如何区分这些Repository

- 根据实体的注解
- 根据继承的接口类型
- 扫描不同的包



------

## 数据访问进阶

![image-20240710095432636](C:\Users\86182\AppData\Roaming\Typora\typora-user-images\image-20240710095432636.png)

![image-20240710095616783](C:\Users\86182\AppData\Roaming\Typora\typora-user-images\image-20240710095616783.png)

------

##### Spring Data Redis

Lettuce能够支持Reactive方式

Spring Data Redis中主要的支持

- ReactiveRedisConnection
- ReactiveRedisConnectionFactory
- ReactiveRedisTemplate
  - opsForXxx()

------

### Spring AOP常用注解

- @EnableAspectJAutoProxy
- @Aspect
- @Pointcut
- @Before
- @After / @AfterReturning / @AfterThrowing
- @Around
- @Order

------

### Spring MVC实践

#### 认识Spring MVC

DispatcherServlet

- Controller
- xxxResolver
  - ViewResolver
  - HandlerExceptionResolver
  - MultipartResolver
- HandlerMapping

![image-20240710143046918](C:\Users\86182\AppData\Roaming\Typora\typora-user-images\image-20240710143046918.png)

------

Spring MVC的请求处理流程

![image-20240711104222827](C:\Users\86182\AppData\Roaming\Typora\typora-user-images\image-20240711104222827.png)

![image-20240711105818760](C:\Users\86182\AppData\Roaming\Typora\typora-user-images\image-20240711105818760.png)

![image-20240711105942168](C:\Users\86182\AppData\Roaming\Typora\typora-user-images\image-20240711105942168.png)

![image-20240711112026954](C:\Users\86182\AppData\Roaming\Typora\typora-user-images\image-20240711112026954.png)

![image-20240711113036554](C:\Users\86182\AppData\Roaming\Typora\typora-user-images\image-20240711113036554.png)

![image-20240711113424819](C:\Users\86182\AppData\Roaming\Typora\typora-user-images\image-20240711113424819.png)

------

#### 重定向

两种不同的重定向前缀

- redirect;
- forward:
