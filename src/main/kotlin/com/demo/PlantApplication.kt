package com.demo

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
@EnableAutoConfiguration
open class PlantApplication

fun main(args: Array<String>) {
    SpringApplication.run(PlantApplication::class.java, *args)
}
