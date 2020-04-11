package com.mathieuaime.hhf.core.trip.controller

import com.mathieuaime.hhf.core.trip.service.TripService
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@Import(TripService::class)
@WebFluxTest(TripController::class)
internal class TripControllerTest