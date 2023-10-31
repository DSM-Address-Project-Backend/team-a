package com.example.address_project

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@EnableFeignClients
@SpringBootApplication
class AddressProjectApplication

fun main(args: Array<String>) {
    runApplication<AddressProjectApplication>(*args)
}
