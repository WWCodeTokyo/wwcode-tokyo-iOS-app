package com.womenwhocode.tokyo.web

import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test


internal class MeetupEventRepositoryTest {

    private lateinit var subject: MeetupEventRepository
    private lateinit var client: MeetupClient

    @BeforeEach
    internal fun setUp() {
        val meetupVenue = MeetupEvent.MeetupVenue(
                "name",
                1.23,
                4.56,
                "address_1",
                "city")

        val meetupEvent = MeetupEvent(
                "name",
                "local_date",
                "local_time",
                1,
                2,
                meetupVenue,
                "link",
                "description")

        client = mock {
            on { getEvents(true, "public", "recent_past", 20) } doReturn listOf(meetupEvent)
        }

        subject = MeetupEventRepository(client)
    }

    @Test
    fun `get events returns events from meetup`() {
        val response = subject.getEvents()

        assertThat(response[0].name, equalTo("name"))
        assertThat(response[0].local_date, equalTo("local_date"))
        assertThat(response[0].local_time, equalTo("local_time"))
        assertThat(response[0].waitlist_count, equalTo(1))
        assertThat(response[0].yes_rsvp_count, equalTo(2))
        assertThat(response[0].link, equalTo("link"))
        assertThat(response[0].description, equalTo("description"))
        assertThat(response[0].venue.name, equalTo("name"))
        assertThat(response[0].venue.lat, equalTo(1.23))
        assertThat(response[0].venue.lon, equalTo(4.56))
        assertThat(response[0].venue.address_1, equalTo("address_1"))
        assertThat(response[0].venue.city, equalTo("city"))
    }
}