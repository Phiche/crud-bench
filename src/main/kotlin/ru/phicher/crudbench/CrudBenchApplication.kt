package ru.phicher.crudbench

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CrudBenchApplication

fun main(args: Array<String>) {
    runApplication<CrudBenchApplication>(*args)
}
