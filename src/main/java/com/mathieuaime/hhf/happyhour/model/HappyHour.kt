package com.mathieuaime.hhf.happyhour.model

import java.time.LocalTime

data class HappyHour(var id: Int, var begin: LocalTime, var end: LocalTime, var barId: Int)