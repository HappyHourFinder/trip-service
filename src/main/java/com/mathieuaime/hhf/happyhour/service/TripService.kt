package com.mathieuaime.hhf.happyhour.service

import com.mathieuaime.hhf.happyhour.client.HappyHourApi
import com.mathieuaime.hhf.happyhour.model.HappyHour
import com.mathieuaime.hhf.happyhour.model.Trip
import org.springframework.stereotype.Service
import java.util.stream.Collectors
import kotlin.math.min

@Service
class TripService(private val happyHourApi: HappyHourApi) {
    data class GenerateTripRequest constructor(val count: Int, val mandatoryBars: List<Int> = emptyList())

    fun generate(request: GenerateTripRequest): Trip {
        if (request.count < request.mandatoryBars.size) {
            return Trip()
        }

        val happyHours = getMandatoryHappyHours(request.mandatoryBars)

        happyHours += randomHappyHours(request, happyHours)
        return Trip(happyHours)
    }

    private fun getMandatoryHappyHours(mandatoryBars: List<Int>): MutableList<HappyHour> {
        return mandatoryBars.parallelStream()
                .flatMap { id -> happyHourApi.getByBarId(id).stream() }
                .collect(Collectors.toList())
    }

    private fun randomHappyHours(request: GenerateTripRequest, actualHappyHours: List<HappyHour>): List<HappyHour> {
        val happyHours: List<HappyHour> = happyHourApi.get().shuffled()
        val limitBars = min(request.count, happyHours.size) - actualHappyHours.size

        return happyHours.stream()
                .filter { happyHour: HappyHour -> !actualHappyHours.contains(happyHour) }
                .limit(limitBars.toLong())
                .collect(Collectors.toList())
    }
}