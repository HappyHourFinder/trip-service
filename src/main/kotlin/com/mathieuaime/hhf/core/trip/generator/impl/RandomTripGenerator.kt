package com.mathieuaime.hhf.core.trip.generator.impl

import com.mathieuaime.hhf.core.trip.client.BarCompositeApi
import com.mathieuaime.hhf.core.trip.generator.GenerateTripRequest
import com.mathieuaime.hhf.core.trip.generator.TripGenerator
import com.mathieuaime.hhf.core.trip.generator.TripType
import com.mathieuaime.hhf.core.trip.model.BarAggregate
import org.springframework.stereotype.Component
import kotlin.math.min
import kotlin.streams.toList

@Component
internal class RandomTripGenerator(private val api: BarCompositeApi) : TripGenerator {
    override fun type(): TripType = TripType.RANDOM

    override fun generate(request: GenerateTripRequest, mandatory: List<BarAggregate>): List<BarAggregate> {
        val bars: List<BarAggregate> = api.get().shuffled()
        val limitBars = min(request.count, bars.size) - mandatory.size
        val mandatoryBars = mandatory.map { it.bar }

        return bars.stream().filter { !mandatoryBars.contains(it.bar) }.limit(limitBars.toLong()).toList()
    }
}