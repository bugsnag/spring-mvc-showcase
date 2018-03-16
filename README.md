## Thread count testing

This application uses Spring MVC 4.0.7 and Bugsnag 3.1.4.

This app is configured to:

- Notify a newly instantiated `Throwable` each time the `/simple` endpoint is accessed
- Notify each time a `Throwable` is logged via a custom `LogbackAppender` 


## Setup

### API Key

Alter the API key in `webapp/WEB-INF/spring/root-context.xml`

```
<bean id="bugsnag" class="com.bugsnag.Bugsnag">
	<constructor-arg value="api-key"/>
</bean>
```

Also alter the API key in `src/main/java/org.springframework.BugsnagAppender`

Alter the bugsnag endpoint to point at your Bugsnag instance if not running locally

Run application with `mvn tomcat7:run -Dmaven.tomcat.port=1234`

### Testing

Install [ApacheBench](https://httpd.apache.org/docs/2.4/programs/ab.html) and JProfiler

Attach JProfiler to the application, then run: `ab -n 100000 -c 100 -s 5 http://localhost:1234/spring-mvc-showcase/simple`

Compare the thread count when `bugsnag.notify(new RuntimeException())` is present in the `SimpleController`, and when it is commented out.

Compare the thread count when `logger.warn("Error encountered", new RuntimeException("Hello World"))` is present in the `SimpleController`, and when it is commented out. 
