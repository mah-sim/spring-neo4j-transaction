package com.example.demo.repositories

import com.example.demo.models.Demo
import org.springframework.data.neo4j.repository.Neo4jRepository
import org.springframework.stereotype.Repository

@Repository
interface DemoRepository : Neo4jRepository<Demo, String>
