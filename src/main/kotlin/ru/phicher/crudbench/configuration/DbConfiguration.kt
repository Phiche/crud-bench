package ru.phicher.crudbench.configuration

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.springframework.boot.autoconfigure.liquibase.LiquibaseDataSource
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@Configuration
@EnableJpaAuditing
class DbConfiguration {

    @Configuration
    @ConfigurationProperties("datasource")
    class DSProperty : HikariConfig()

    @Bean
    @LiquibaseDataSource
    fun benchDatasource(properties: DSProperty): HikariDataSource {
        return HikariDataSource(properties)
    }
}