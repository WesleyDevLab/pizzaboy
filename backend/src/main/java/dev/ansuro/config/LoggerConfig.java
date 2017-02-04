package dev.ansuro.config;

import dev.ansuro.aop.LoggingAspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InjectionPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;

@Configuration
@EnableAspectJAutoProxy
public class LoggerConfig {

    @Bean
    @Scope("prototype")
    public Logger log(InjectionPoint injectionPoint) {
        return LoggerFactory.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
    }

    @Bean
    @Profile("dev")
    public LoggingAspect loggingAspect() {
        return new LoggingAspect();
    }
}
