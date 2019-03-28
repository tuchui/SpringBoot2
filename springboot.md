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



