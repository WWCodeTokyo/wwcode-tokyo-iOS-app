package com.womenwhocode.tokyo.event

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.womenwhocode.tokyo.meetupapi.EventType.*
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.time.LocalDateTime


class EventServiceTest {

    private lateinit var meetupEventRepository: MeetupEventRepository
    private lateinit var subject: EventService

    @BeforeEach
    internal fun setUp() {
        meetupEventRepository = mock { }
        subject = EventService(meetupEventRepository)
    }

    @Test
    fun `get events return upcoming WWCEvents`() {
        val eventList = listOf<RepositoryEvent>(
                RepositoryEvent("PAL training!",
                        "2020-12-24",
                        "19:30",
                        7200000,
                        "Pivotal Japan")
        )
        whenever(meetupEventRepository.getEvents(UPCOMING)).thenReturn(eventList)
        val events = subject.getEvents(UPCOMING)

        assertThat(events[0].startDateTime, equalTo(LocalDateTime.of(2020, 12, 24,19, 30)))
        assertThat(events[0].endDateTime, equalTo(LocalDateTime.of(2020, 12, 24,21, 30)))
        assertThat(events[0].name, equalTo("PAL training!"))
        assertThat(events[0].venueName, equalTo("Pivotal Japan"))
    }

    @Test
    fun `get events return past WWCEvents`() {
        val pastEvents = listOf(
                RepositoryEvent(
                        "past event name",
                        "2019-10-31",
                        "18:00",
                        10800000,
                        "past event venue"
                )
        )
        whenever(meetupEventRepository.getEvents(PAST)).thenReturn(pastEvents)
        val events = subject.getEvents(PAST)

        assertThat(events[0].startDateTime, equalTo(LocalDateTime.of(2019, 10, 31, 18, 0)))
        assertThat(events[0].endDateTime, equalTo(LocalDateTime.of(2019, 10, 31, 21,0)))
        assertThat(events[0].name, equalTo("past event name"))
        assertThat(events[0].venueName, equalTo("past event venue"))
    }
}