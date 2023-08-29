package com.hatchlabs.kfms

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication(exclude = [SecurityAutoConfiguration::class])
class KfmsApplication

fun main(args: Array<String>) {
	runApplication<KfmsApplication>(*args)
}
