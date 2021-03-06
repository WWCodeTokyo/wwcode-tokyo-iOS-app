@testable import WWCode_Tokyo_iOS

struct EventFixture {
    static func JavaScript() -> Event {
        let location = Location(
            lat: 1.23,
            lon: 4.56,
            address: "venue address",
            city: "venue city")
        let venue = Venue(
            name: "Code Chrysalis",
            location: location)
        
        return Event(
            name: "WTF is JavaScript?! Talk + Workshop for Beginners with WWCode & Automattic",
            startDateTime: "2021-06-12T18:30:00",
            endDateTime: "2021-06-12T21:30:00",
            description: "description",
            venue: venue,
            link: "example.com")
    }

    static func Hackathon() -> Event {
        let location = Location(
            lat: 1.23,
            lon: 4.56,
            address: "venue address",
            city: "venue city")
        let venue = Venue(
            name: "Mercari",
            location: location)
        
        return Event(
            name: "Hackathon 101 with Junction Tokyo",
            startDateTime: "2020-04-12T19:00:00",
            endDateTime: "2020-04-12T21:00:00",
            description: "description",
            venue: venue,
            link: "example.com")
    }
}
