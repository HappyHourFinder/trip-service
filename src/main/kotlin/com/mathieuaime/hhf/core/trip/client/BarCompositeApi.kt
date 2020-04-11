package com.mathieuaime.hhf.core.trip.client

import com.mathieuaime.hhf.core.trip.model.BarAggregate
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(value = "bar-composite", fallback = BarCompositeApiFallback::class)
interface BarCompositeApi {
    @GetMapping
    fun get(): List<BarAggregate>

    @GetMapping("/{uuid}")
    fun getByUuid(@RequestParam uuid: String): BarAggregate?
}

private class BarCompositeApiFallback : BarCompositeApi {
    override fun get(): List<BarAggregate> = emptyList()
    override fun getByUuid(uuid: String): BarAggregate? = null
}