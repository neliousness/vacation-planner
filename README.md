# Vacation Planner

## About

Vacation Planner is an Android application that helps users plan their vacations by providing itinerary suggestions and allowing them to view popular and afforable location itineraries

**Features:**

* Search for destinations and get itinerary suggestions.
* View itinerary details, including location on a map.
* Save recent locations for quick access.
* View a list of saved recent locations.
* Uses modern Android development practices (Jetpack Compose, Room, Koin, Coroutines, Flows).

## Prerequisites

* Android Studio (latest stable version recommended)
* Android SDK (API level 21 or higher)
* Kotlin plugin for Android Studio
* An Android device or emulator to run the app

## Getting Started

1. **Clone the repository:**
git clone https://github.com/neliousness/vacation-planner.git
2. **Open the project in Android Studio:**
      Import the project into Android Studio by navigating to `File > Open` and selecting the project directory.
3. **Build the project:**
   Click on the "Build" menu and select "Make Project" to build the project.
4. **Run the app:**
   Connect your Android device or start an emulator. Click on the "Run" menu and select "Run 'app'" to launch the application.

## Usage

* **Home Screen:**
    * Enter a destination in the search bar.
    * Select the desired duration (number of days).
    * Click "Search" to get itinerary suggestions.
* **Itinerary Details:**
    * View the itinerary details, including the location on a map.
    * Click "Save" to add the location to your recent locations.
* **Popular and Affordable Locations:**
    * View a list of popular locations and afforablable locations.
    * Click on a location to view its itinerary details.

## Libraries Used

* **Jetpack Compose:** For building the UI.
* **Koin:** For dependency injection.
* **Coroutines:** For asynchronous operations.
* **Flows:** For reactive data streams.
* **Joda Time:** Date and Time API
* **OSMDroid:** Mapping and location services
* **Retrofit:** Network operations
* **OkHttp:** HTTP client
