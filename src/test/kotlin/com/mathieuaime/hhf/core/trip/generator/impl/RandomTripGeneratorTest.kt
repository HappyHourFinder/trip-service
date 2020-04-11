package com.mathieuaime.hhf.core.trip.generator.impl

import com.mathieuaime.hhf.core.trip.client.BarCompositeApi
import com.mathieuaime.hhf.core.trip.generator.GenerateTripRequest
import com.mathieuaime.hhf.core.trip.model.Bar
import com.mathieuaime.hhf.core.trip.model.BarAggregate
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

@ExtendWith(MockitoExtension::class)
internal class RandomTripGeneratorTest {
    @Mock
    private lateinit var api: BarCompositeApi

    @InjectMocks
    private lateinit var generator: RandomTripGenerator

    @Test
    fun givenCountRequestIsZero_whenIGenerateATrip_thenTheResultShouldBeEmpty() {
        `when`(api.get()).thenReturn(emptyList())
        val bars = generator.generate(GenerateTripRequest(0), emptyList())

        assertTrue { bars.isEmpty() }
    }

    @Test
    fun givenApiReturnEmptyList_whenIGenerateATrip_thenTheResultShouldBeEmpty() {
        `when`(api.get()).thenReturn(emptyList())
        val bars = generator.generate(GenerateTripRequest(2), emptyList())

        assertTrue { bars.isEmpty() }
    }

    @Test
    fun givenMandatoryBarsSizeAtLeastEqualToRequestCount_whenIGenerateATrip_thenTheResultShouldBeEmpty() {
        `when`(api.get()).thenReturn(generateBars())
        val mandatory = listOf(BarAggregate(Bar("uuid1", "bar1", 1.0, 2.0), emptyList()),
                BarAggregate(Bar("uuid2", "bar2", 2.0, 3.0), emptyList()))
        val bars = generator.generate(GenerateTripRequest(2), mandatory)

        assertTrue { bars.isEmpty() }
    }

    @Test
    fun givenMandatoryBarsSizeInferiorToRequestCount_whenIGenerateATrip_thenTheResultShouldContainsNewBars() {
        `when`(api.get()).thenReturn(generateBars())
        val mandatory = listOf(BarAggregate(Bar("uuid", "bar", 1.0, 2.0), emptyList()))
        val bars = generator.generate(GenerateTripRequest(2), mandatory)

        assertEquals(1, bars.size)
    }

    @Test
    fun givenMandatoryBars_whenIGenerateATrip_thenTheResultShouldNotContainsDuplicates() {
        `when`(api.get()).thenReturn(generateBars())
        val mandatory = listOf(BarAggregate(Bar("uuid1", "bar1", 1.0, 2.0), emptyList()))
        val bars = generator.generate(GenerateTripRequest(4), mandatory)

        assertEquals(2, bars.size)
        assertFalse { bars.contains(BarAggregate(Bar("uuid1", "bar1", 1.0, 2.0), emptyList())) }
    }

    private fun generateBars(): List<BarAggregate> {
        return listOf(BarAggregate(Bar("uuid1", "bar1", 1.0, 2.0), emptyList()),
                BarAggregate(Bar("uuid2", "bar2", 2.0, 3.0), emptyList()),
                BarAggregate(Bar("uuid3", "bar3", 3.0, 4.0), emptyList()))
    }
}