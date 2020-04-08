package com.mathieuaime.hhf.happyhour.client

import com.mathieuaime.hhf.happyhour.model.HappyHour
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(value = "happy-hour-service", fallback = HappyHourApiFallback::class)
interface HappyHourApi {
    @GetMapping
    fun get(): List<HappyHour>

    @GetMapping("/?barId={barId}")
    fun getByBarId(@RequestParam id: Int): List<HappyHour>
}

private class HappyHourApiFallback : HappyHourApi {
    override fun get(): List<HappyHour> {
        return emptyList()
    }

    override fun getByBarId(id: Int): List<HappyHour> {
        return emptyList()
    }

}