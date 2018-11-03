package com.thehecklers.fsrkotlincoffeeclient

import java.time.Instant

data class CoffeeOrder(val coffeeId: String, val whenOrdered: Instant)