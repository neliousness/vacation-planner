package com.pulsar.vacationplanner.util

import com.pulsar.vacationplanner.domain.model.itinerary.Activity
import com.pulsar.vacationplanner.domain.model.itinerary.ItineraryData
import com.pulsar.vacationplanner.domain.model.itinerary.Location
import com.pulsar.vacationplanner.domain.model.itinerary.LocationItinerary

object DummyData {

    private val dummyItineraryResponse = LocationItinerary(
        location = "Cape Town, South Africa",
        country = null,
        itinerary = listOf(
            ItineraryData(
                date = "2024-10-10T00:00:00.000Z",
                title = "Table Mountain and V&A Waterfront",
                activities = listOf(
                    Activity(
                        name = "Table Mountain",
                        description = "Cable car ride to the top of one of the Seven Wonders of Nature",
                        location = Location(
                            name = "Table Mountain",
                            latitude = -33.964772,
                            longitude = 18.405557
                        ),
                        meal = "The Granary Cafe for coffee and breakfast"
                    ),
                    Activity(
                        name = "V&A Waterfront",
                        description = "Shopping and dining area with great views of the harbor",
                        location = Location(
                            name = "V&A Waterfront",
                            latitude = -33.905628,
                            longitude = 18.416642
                        ),
                        meal = "The Lobster Pot for seafood lunch"
                    )
                )
            ),
            ItineraryData(
                date = "2024-10-11T00:00:00.000Z",
                title = "Cape of Good Hope and Boulders Beach",
                activities = listOf(
                    Activity(
                        name = "Cape of Good Hope National Park",
                        description = "The southernmost tip of Africa with stunning views",
                        location = Location(
                            name = "Cape of Good Hope National Park",
                            latitude = -34.583033,
                            longitude = 18.481699
                        ),
                        meal = "The Two Oceans Restaurant for lunch"
                    ),
                    Activity(
                        name = "Boulders Beach",
                        description = "Beach with massive granite boulders and a colony of African penguins",
                        location = Location(
                            name = "Boulders Beach",
                            latitude = -34.302227,
                            longitude = 18.503017
                        ),
                        meal = "The Boulders Beach Restaurant for dinner"
                    )
                )
            ),
            ItineraryData(
                date = "2024-10-12T00:00:00.000Z",
                title = "Wine Tasting and Cape Malay Cuisine",
                activities = listOf(
                    Activity(
                        name = "Stellenbosch Wine Estate",
                        description = "Wine tasting and cellar tour of one of the oldest wine estates in South Africa",
                        location = Location(
                            name = "Stellenbosch Wine Estate",
                            latitude = -33.927057,
                            longitude = 18.862737
                        ),
                        meal = "The Waterfront Restaurant for lunch"
                    ),
                    Activity(
                        name = "Bo-Kaap Cape Malay Restaurant",
                        description = "Try Cape Malay cuisine, a unique blend of Indian and African flavors",
                        location = Location(
                            name = "Bo-Kaap Cape Malay Restaurant",
                            latitude = -33.922795,
                            longitude = 18.425225
                        ),
                        meal = "Cape Malay lunch at the restaurant"
                    )
                )
            )
        )
    )


    private val tokyoItineraryResponse = LocationItinerary(
        location = "Tokyo, Japan",
        itinerary = listOf(
            ItineraryData(
                date = "2024-11-15T00:00:00.000Z",
                title = "Shibuya & Shinjuku Exploration",
                activities = listOf(
                    Activity(
                        name = "Shibuya Crossing",
                        description = "Witness the iconic scramble crossing.",
                        location = Location(
                            name = "Shibuya Crossing",
                            latitude = 35.6591,
                            longitude = 139.7002
                        ),
                        meal = "Ramen at Ichiran Shibuya"
                    ),
                    Activity(
                        name = "Shinjuku Gyoen National Garden",
                        description = "Explore this tranquil oasis.",
                        location = Location(
                            name = "Shinjuku Gyoen",
                            latitude = 35.6853,
                            longitude = 139.7076
                        ),
                        meal = "Sushi at Tsukiji Outer Market"
                    )
                )
            ),
            ItineraryData(
                date = "2024-11-16T00:00:00.000Z",
                title = "Culture & Tradition",
                activities = listOf(
                    Activity(
                        name = "Senso-ji Temple",
                        description = "Visit Tokyo's oldest temple.",
                        location = Location(
                            name = "Senso-ji Temple",
                            latitude = 35.7148,
                            longitude = 139.7968
                        ),
                        meal = "Street food in Asakusa"
                    ),
                    Activity(
                        name = "Imperial Palace East Garden",
                        description = "Explore the Emperor's gardens.",
                        location = Location(
                            name = "Imperial Palace",
                            latitude = 35.6856,
                            longitude = 139.7532
                        ),
                        meal = "Tempura at Tsunahachi Shinjuku"
                    )
                )
            )
            // Add more days as needed
        )
    )

    private val parisItineraryResponse = LocationItinerary(
        location = "Paris, France",
        itinerary = listOf(
            ItineraryData(
                date = "2024-12-05T00:00:00.000Z",
                title = "Iconic Landmarks",
                activities = listOf(
                    Activity(
                        name = "Eiffel Tower",
                        description = "Ascend the iconic tower.",
                        location = Location(
                            name = "Eiffel Tower",
                            latitude = 48.8584,
                            longitude = 2.2945
                        ),
                        meal = "Croissants and coffee at a local café"
                    ),
                    Activity(
                        name = "Louvre Museum",
                        description = "Admire masterpieces like the Mona Lisa.",
                        location = Location(
                            name = "Louvre Museum",
                            latitude = 48.8606,
                            longitude = 2.3376
                        ),
                        meal = "French cuisine at Le Bouillon Chartier"
                    )
                )
            ),
            ItineraryData(
                date = "2024-12-06T00:00:00.000Z",
                title = "Charming Neighborhoods",
                activities = listOf(
                    Activity(
                        name = "Montmartre",
                        description = "Wander through the artistic streets.",
                        location = Location(
                            name = "Montmartre",
                            latitude = 48.8864,
                            longitude = 2.3431
                        ),
                        meal = "Crêpes at a street vendor"
                    ),
                    Activity(
                        name = "Latin Quarter",
                        description = "Explore the historic Latin Quarter.",
                        location = Location(
                            name = "Latin Quarter",
                            latitude = 48.8467,
                            longitude = 2.3428
                        ),
                        meal = "Steak frites at a traditional bistro"
                    )
                )
            )
            // Add more days as needed
        )
    )

    private val newYorkItineraryResponse = LocationItinerary(
        location = "New York City, USA",
        itinerary = listOf(
            ItineraryData(
                date = "2025-01-20T00:00:00.000Z",
                title = "Midtown Marvels",
                activities = listOf(
                    Activity(
                        name = "Times Square",
                        description = "Experience the dazzling lights.",
                        location = Location(
                            name = "Times Square",
                            latitude = 40.7589,
                            longitude = -73.9851
                        ),
                        meal = "New York-style pizza"
                    ),
                    Activity(
                        name = "Empire State Building",
                        description = "Ascend to the top for city views.",
                        location = Location(
                            name = "Empire State Building",
                            latitude = 40.7484,
                            longitude = -73.9857
                        ),
                        meal = "Dinner at a rooftop restaurant"
                    )
                )
            ),
            ItineraryData(
                date = "2025-01-21T00:00:00.000Z",
                title = "Downtown Delights",
                activities = listOf(
                    Activity(
                        name = "Statue of Liberty & Ellis Island",
                        description = "See these iconic symbols.",
                        location = Location(
                            name = "Statue of Liberty",
                            latitude = 40.6892,
                            longitude = -74.0445
                        ),
                        meal = "Lunch at a café in Battery Park"
                    ),
                    Activity(
                        name = "9/11 Memorial & Museum",
                        description = "Reflect on the events of 9/11.",
                        location = Location(
                            name = "9/11 Memorial",
                            latitude = 40.7115,
                            longitude = -74.0124
                        ),
                        meal = "Dinner in the Financial District"
                    )
                )
            )
            // Add more days as needed
        )
    )

    private val londonItineraryResponse = LocationItinerary(
        location = "London, United Kingdom",
        itinerary = listOf(
            ItineraryData(
                date = "2024-10-25T00:00:00.000Z",
                title = "Royal London",
                activities = listOf(
                    Activity(
                        name = "Buckingham Palace",
                        description = "Witness the Changing of the Guard.",
                        location = Location(
                            name = "Buckingham Palace",
                            latitude = 51.5014,
                            longitude = -0.1419
                        ),
                        meal = "Afternoon tea at The Ritz"
                    ),
                    Activity(
                        name = "Tower of London",
                        description = "Explore the historic fortress.",
                        location = Location(
                            name = "Tower of London",
                            latitude = 51.5081,
                            longitude = -0.0759
                        ),
                        meal = "Fish and chips near Tower Bridge"
                    )
                )
            ),
            ItineraryData(
                date = "2024-10-26T00:00:00.000Z",
                title = "Museums & Culture",
                activities = listOf(
                    Activity(
                        name = "British Museum",
                        description = "Discover world history and artifacts.",
                        location = Location(
                            name = "British Museum",
                            latitude = 51.5194,
                            longitude = -0.1269
                        ),
                        meal = "Lunch at a pub in Bloomsbury"
                    ),
                    Activity(
                        name = "National Gallery",
                        description = "Admire European paintings.",
                        location = Location(
                            name = "National Gallery",
                            latitude = 51.5089,
                            longitude = -0.1283
                        ),
                        meal = "Dinner in Covent Garden"
                    )
                )
            )
            // Add more days as needed
        )
    )

    val locationItinerarys = listOf(
        dummyItineraryResponse, tokyoItineraryResponse,
        parisItineraryResponse, newYorkItineraryResponse, londonItineraryResponse
    )
}