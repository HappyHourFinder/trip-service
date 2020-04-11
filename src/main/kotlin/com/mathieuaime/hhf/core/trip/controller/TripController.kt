package com.mathieuaime.hhf.core.trip.controller

import com.mathieuaime.hhf.core.trip.generator.GenerateTripRequest
import com.mathieuaime.hhf.core.trip.service.TripService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class TripController(private val tripService: TripService) {
    @PostMapping
    fun generate(@RequestBody request: GenerateTripRequest) = tripService.generate(request)
}