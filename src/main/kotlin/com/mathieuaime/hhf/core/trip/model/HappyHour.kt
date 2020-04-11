package com.mathieuaime.hhf.core.trip.model

import java.time.LocalTime

data class HappyHour(var uuid: String, var begin: LocalTime, var end: LocalTime, var barUuid: String)