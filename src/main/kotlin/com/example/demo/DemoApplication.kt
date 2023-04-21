package com.example.demo

import com.example.demo.services.DemoService
import org.neo4j.driver.AuthTokens
import org.neo4j.driver.Driver
import org.neo4j.driver.GraphDatabase
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.context.event.EventListener
import org.springframework.data.neo4j.core.Neo4jClient
import org.springframework.stereotype.Component

@SpringBootApplication
class DemoApplication

// docker run --rm -p 7474:7474 -p 7687:7687 --env NEO4J_AUTH=none neo4j:4.4
fun main(args: Array<String>) {
    runApplication<DemoApplication>(*args)
}

@Configuration
class Configs {

    @Bean
    fun driver(): Driver {
        return GraphDatabase.driver("bolt://localhost:7687", AuthTokens.none())
    }

    @Bean
    fun neo4jClient(driver: Driver): Neo4jClient {
        return Neo4jClient.create(driver)
    }
}

@Component
class StartupEventListener(val demoService: DemoService) {

    @EventListener
    fun onApplicationEvent(event: ContextRefreshedEvent) {
        demoService.doSomeJob()
    }
}
