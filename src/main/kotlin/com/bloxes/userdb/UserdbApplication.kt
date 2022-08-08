package com.bloxes.userdb

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class UserdbApplication

fun main(args: Array<String>) {
	runApplication<UserdbApplication>(*args)
}
