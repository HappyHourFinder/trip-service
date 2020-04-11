package com.mathieuaime.hhf.core.trip.generator

enum class TripType { RANDOM }

data class GenerateTripRequest constructor(val count: Int, val tripType: TripType = TripType.RANDOM, val mandatoryBars: List<String> = emptyList())
