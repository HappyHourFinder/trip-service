package com.mathieuaime.hhf.core.trip.generator

import org.springframework.stereotype.Component

@Component
class TripGeneratorStrategy(generatorsList: List<TripGenerator>) {
    private val generators: Map<TripType, TripGenerator> = generatorsList.associateBy({ it.type() }, { it })

    fun get(type: TripType): TripGenerator = generators.getValue(type)
}