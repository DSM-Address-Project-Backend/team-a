package com.example.address_project

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@EnableFeignClients
@EnableBatchProcessing
@SpringBootApplication
class AddressProjectApplication

fun main(args: Array<String>) {
    runApplication<AddressProjectApplication>(*args)
}
