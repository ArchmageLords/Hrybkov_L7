package com.example.threads

import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicInteger


fun main() {
    val counter = AtomicInteger(0)

    val counterPrint = Runnable {
        repeat(20) {
            Thread.sleep(1000)
            println(counter)
        }
    }

    val counterIncrease = Runnable {
        repeat(2000) {
            Thread.sleep(10)
            counter.incrementAndGet()
        }
    }

    val executor = Executors.newFixedThreadPool(5)

    repeat(4) {
        executor.execute(counterIncrease)
    }
    executor.execute(counterPrint)
    executor.shutdown()
    executor.awaitTermination(2, TimeUnit.SECONDS)
}