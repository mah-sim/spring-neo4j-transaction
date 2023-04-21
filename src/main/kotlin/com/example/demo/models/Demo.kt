package com.example.demo.models

import org.springframework.data.neo4j.core.schema.GeneratedValue
import org.springframework.data.neo4j.core.schema.Id
import org.springframework.data.neo4j.core.schema.Node
import org.springframework.data.neo4j.core.support.UUIDStringGenerator

@Node
data class Demo(

    @Id @GeneratedValue(UUIDStringGenerator::class)
    val uuid: String? = null,
    val name: String,
)
