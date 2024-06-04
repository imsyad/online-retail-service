package com.icad.shop.retailservice.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan(basePackages = {"com.icad.shop.retailservice.*"})
@EnableJpaRepositories(basePackages = "com.icad.shop.retailservice.repository")
@EntityScan(basePackages = "com.icad.shop.retailservice.model")
@RequiredArgsConstructor
public class InitConfig {
}
