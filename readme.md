# SpringBoot2 学习指南

**注意：**

**cmd启动jar包会出现没有主清单属性，构件maven插件解决此问题**

```xml
 <!--cmd启动jar包会出现没有主清单属性，构件maven插件解决此问题 -->
  <build>
    <plugins>
      <plugin>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-maven-plugin</artifactId>
      </plugin>
    </plugins>
  </build>
</project>
```

#### 一  SpringBoot源码分析



SpringBoot启动实质是加载spring-boot-2.0.0.RELEASE.jar包下的  Meta-Inf/spring.factories配置文件。

通过源码分析，Spring Boot 是通过 SpringFactoriesLoader 的loadFactoryNames读取Spring.factories.

SpringFactoriesLoader是spring框架的一个工具类，属于spring框架私有的一种扩展方案。

配好@EnableAutoConfiguaration使用，可以提供一种配置查找的功能，即根据@EnableAutoConfiguartion的完整类名作为key，查找一组@Configuration类

###### **1 @SpringBootApplication自动配置原理：**

@EnableAutoConfiguaration 利用AutoConfigurationImportSelector.class自动加载一些配置。AutoConfigurationImportSelector类实质会使用SpringFactoriesLoader, 加载自己包下的spring.factories中以自己的EnableAutoConfiguaration 类名为key的相关配置

然后在加载spring-boot-2.0.0.RELEASE.jar包下的  Meta-Inf/spring.factories配置文件，并将EnableAutoConfiguration类对应的配置通过反射实例化为对应的标注了@Configuration的Ioc容器配置类，然后汇总加载到Spring的框架Ioc容器

```java
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@AutoConfigurationPackage
@Import(AutoConfigurationImportSelector.class)
public @interface EnableAutoConfiguration
```

```properties
# Auto Configure
org.springframework.boot.autoconfigure.EnableAutoConfiguration=\
org.springframework.boot.autoconfigure.admin.SpringApplicationAdminJmxAutoConfiguration,\
org.springframework.boot.autoconfigure.aop.AopAutoConfiguration,\
org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration,\
org.springframework.boot.autoconfigure.batch.BatchAutoConfiguration,\
org.springframework.boot.autoconfigure.cache.CacheAutoConfiguration,\
org.springframework.boot.autoconfigure.cassandra.CassandraAutoConfiguration,\
org.springframework.boot.autoconfigure.cloud.CloudAutoConfiguration,\
org.springframework.boot.autoconfigure.context.ConfigurationPropertiesAutoConfiguration,\
org.springframework.boot.autoconfigure.context.MessageSourceAutoConfiguration,\
org.springframework.boot.autoconfigure.context.PropertyPlaceholderAutoConfiguration,\

```



**2 webmvc自动配置源码**

根据spring.factories,会加载 WebMvcAutoConfiguration

@ConditionalOnMissingBean表示当前环境没有此bean时，会自动加载

prefixhesufix是根据全局配置文件加载而来

```java
		@Bean
		@ConditionalOnMissingBean
		public InternalResourceViewResolver defaultViewResolver() {
			InternalResourceViewResolver resolver = new InternalResourceViewResolver();
			resolver.setPrefix(this.mvcProperties.getView().getPrefix());
			resolver.setSuffix(this.mvcProperties.getView().getSuffix());
			return resolver;
		}

```



#### 二SpringBoot的web开发

##### 1 Spring Boot的web开发支持

web相关的自动配置支持保存在Spring-boot-autoconfigure.jar中

重要的自动配置如下：

- HttpEncodingAutoConfiguration
- JacksonHttpMessageConvertersConfiguration
- MultipartAutoConfiguration 和Multipartproperties 
- ServerPropertiesAutoConfiguration和ServerProperties
- WebMvcAutoConfiguration 和WebMvcProperties 

##### 2 文件上传

文件上传必须为post请求且 enctype 设置为 multipart/form-data

SpringMVC 直接提供了MultipartReslover 。此技术使用了Apache Commons FileUpload实现

SpringBoot集成了SpringMVC，所有更加简单

```java
 String filePath=  req.getServletContext().getRealPath("/upload/");
        System.out.println("filePath: "+filePath);
        //上传文件名
        String fileName=file.getOriginalFilename();
        //创建目录
        File newFilePath=new File(filePath,fileName);
        if(!newFilePath.getParentFile().exists()){
              newFilePath.getParentFile().mkdirs();
        }
        //将上传文件保存
        file.transferTo(new File(filePath+File.pathSeparator+fileName));
```

默认文件大小1M，需设置大小

```properties
#设置文件上传大小
spring.servlet.multipart.max-request-size=10Mb
spring.servlet.multipart.max-file-size=10Mb
```



##### 3 文件下载

使用ResponseEntity类型

使用Apache Commons FileUpload中的FileUtils 读取upload下的文件，并构建成ResponseEntity对象返回客户端

##### 4 异常处理

1 默认异常会被springboot捕获并跳转到error页面 ，若无则显示默认页面

2 使用@ExecptionHandler 处理异常 ：

当异常抛出时，该Controller类中 的 @ExceptionHandler注解会处理异常，而不会抛给Servlet容器

3 父类Controller处理异常

4 Advice处理异常 解决父类处理异常耦合过度问题

#### 三 SpringBoot 数据库访问

##### 1 JPA ,Spring Data JPA 概念

数据库为关系型，而与面向对象的理念不符，于是衍生ORM框架。

ORM框架作用：把持久化对象保存、修改等操作转换成对数据库的操作。

**ORM框架简介**

Hibernate框架

JPA是官方提出的JAVA持久化规范，Herbinate需遵循JPA规范

Spring Data 是Spring简化数据库访问的一个子项目

Spring Data JPA 简化JPA写法，实现对数据库访问和操作

##### 2 Spring Data JPA

###### 2.1 Spring Data 核心数据访问接口

- 基本的增删改查接口

CrudRepository<T,ID extends Serializable>

- 分页与排序接口

PagingAndSortingRepository<T, ID extends Serializable>

Iterable <T> findAll(Sort sort) : 按照指定排序对象规则查询实体对象数据

Page< T > findAll(Pageable page):分页查询实体对象，包含排序

###### 2.2 Spring Data JPA 开发

spring data jpa 只需要数据访问层接口实现 JpaRepository接口即可 。

JpaRepository也继承了 PagingAndSortingRepository接口

JpaRepository 是基于 JPA的 Repository 接口

- 简单条件查询

- 关联查询 (" _ ")  和 @Query查询

  使用  JPQL 语句 

  使用@Modify 和@Query 执行更新查询操作

  出错：

```xml
Consider injecting the bean as one of its interfaces or forcing the use of CGLib-based proxies by setting proxyTargetClass=true on @EnableAsync and/or @EnableCaching.

```

