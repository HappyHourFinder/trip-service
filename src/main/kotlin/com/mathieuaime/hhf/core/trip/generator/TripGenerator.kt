package com.mathieuaime.hhf.core.trip.generator

import com.mathieuaime.hhf.core.trip.model.BarAggregate

interface TripGenerator {
    fun type(): TripType
    fun generate(request: GenerateTripRequest, mandatory: List<BarAggregate>): List<BarAggregate>
}