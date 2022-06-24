package com.batch

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
@EnableBatchProcessing
class BatchProcessingApplication

fun main() {
    SpringApplication.run(BatchProcessingApplication::class.java)
}