package com.taskflow

import io.micronaut.runtime.Micronaut

fun main(args: Array<String>) {
    Micronaut.build()
        .args(*args)
        .packages("com.taskflow")
        .start()
}
