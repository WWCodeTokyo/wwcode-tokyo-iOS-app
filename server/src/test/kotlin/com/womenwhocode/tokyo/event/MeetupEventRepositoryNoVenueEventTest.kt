package com.womenwhocode.tokyo.event

import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.womenwhocode.tokyo.meetupapi.EventType.UPCOMING
import com.womenwhocode.tokyo.meetupapi.MeetupAPIClient
import com.womenwhocode.tokyo.meetupapi.MeetupAPIEvent
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class MeetupEventRepositoryNoVenueEventTest {
    private lateinit var subject: MeetupEventRepository
    private lateinit var APIClient: MeetupAPIClient

    @BeforeEach
    internal fun setUp() {
        val onlineMeetupEvent = MeetupAPIEvent(
            "1",
            10800000,
            "Django Workshop",
            "2021-07-15",
            "10:00",
            10,
            99,
            null,
            "past-event-link",
            "Yokohama"
        )

        APIClient = mock {
            on { getEvents(true, "public", "upcoming", false, "2019-06-01T00:00:00.000", 30) } doReturn listOf(
                onlineMeetupEvent
            )
        }

        subject = MeetupEventRepository(APIClient)
    }

    @Test
    fun `get events returns events with no venue from meetup api`() {
        val response = subject.getEvents(UPCOMING)

        val event = response[0]
        assertThat(event.id, equalTo("1"))
        assertThat(event.name, equalTo("Django Workshop"))
        assertThat(event.date, equalTo("2021-07-15"))
        assertThat(event.duration, equalTo(10800000))
        assertThat(event.time, equalTo("10:00"))
        assertThat(event.link, equalTo("past-event-link"))
        assertNull(event.venue)
    }
}

