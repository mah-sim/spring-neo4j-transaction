package com.example.demo.services

import com.example.demo.models.Demo
import com.example.demo.repositories.DemoRepository
import org.neo4j.driver.Driver
import org.springframework.data.neo4j.core.Neo4jClient
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
@Transactional
class DemoService(
    val demoRepository: DemoRepository,
    val transactionHelper: TransactionHelper,
    val neo4jClient: Neo4jClient,
    val driver: Driver,
) {
    fun doSomeJob() {
        neo4jClient.query("CREATE CONSTRAINT demoNameUnique IF NOT EXISTS FOR (n:Demo) REQUIRE n.name IS UNIQUE").run()

        transactionHelper.doInNewTransaction {
            // save with demoRepository/neo4jClient is blocking application
            demoRepository.save(Demo(name = "demo 1"))
            println("this line will not be printed")
        }
/*
        // create with driver is also blocking application if missing "RETURN n" in query
        driver.session().run(
            "CREATE (n:Demo { uuid: \$uuid, name: \$name })",
            mapOf("uuid" to UUID.randomUUID().toString(), "name" to "demo 2")
        )

        // this one is working
        driver.session().run(
            "CREATE (n:Demo { uuid: \$uuid, name: \$name }) RETURN n",
            mapOf("uuid" to UUID.randomUUID().toString(), "name" to "demo 3")
        )
*/
    }
}
