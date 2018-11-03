package com.thehecklers.fsrkotlincoffeeclient

import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToFlux

@Configuration
class DemoConfig {
    @Bean
    fun client() = WebClient.create("http://localhost:8082/coffees")

    @Bean
    fun demo(client: WebClient) = CommandLineRunner {
        client.get()
                .retrieve()
                .bodyToFlux<Coffee>()
                .filter { it.name.equals("kaldi's coffee", true) }
                .flatMap { client.get()
                        .uri("/{id}/orders", it.id)
                        .retrieve()
                        .bodyToFlux<CoffeeOrder>() }
                .subscribe { println(it) }
    }
}