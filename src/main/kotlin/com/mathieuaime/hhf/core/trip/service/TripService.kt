package com.mathieuaime.hhf.core.trip.service

import com.mathieuaime.hhf.core.trip.client.BarCompositeApi
import com.mathieuaime.hhf.core.trip.generator.GenerateTripRequest
import com.mathieuaime.hhf.core.trip.generator.TripGeneratorStrategy
import com.mathieuaime.hhf.core.trip.model.BarAggregate
import com.mathieuaime.hhf.core.trip.model.Trip
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service
class TripService(private val generatorStrategy: TripGeneratorStrategy, private val api: BarCompositeApi) {

    fun generate(request: GenerateTripRequest): Trip =
            if (request.count < request.mandatoryBars.size) Trip() else generateTrip(request)

    private fun generateTrip(request: GenerateTripRequest): Trip {
        val bars = getMandatoryBars(request.mandatoryBars)
        bars += generatorStrategy.get(request.tripType).generate(request, bars)
        return Trip(bars)
    }

    private fun getMandatoryBars(mandatoryBars: List<String>): MutableList<BarAggregate> =
            mandatoryBars.parallelStream().map(api::getByUuid).filter { it != null }.collect(Collectors.toList())
}