package com.mathieuaime.hhf.happyhour.controller

import com.mathieuaime.hhf.happyhour.service.TripService
import org.springframework.web.bind.annotation.RestController

@RestController
class TripController(private val tripService: TripService)