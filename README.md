✈️ Airplane Booking System - Java CLI App
A Command-Line Interface (CLI) based Java application for airplane seat booking. The system allows users to sign up, log in, search for flights between airports, view and book available seats, and manage their bookings. The app uses JSON files to persist data, and leverages ObjectMapper (Jackson) for JSON serialization and deserialization.

📁 Project Structure
bash
Copy
Edit
app/
├── build.gradle       # Gradle build script
├── src/
│   └── main/
│       ├── java/
│       │   ├── entities/         # POJOs: Plane, Ticket, User
│       │   ├── services/         # Business logic: UserBookingService, PlaneService
│       │   ├── util/             # Utility classes: JsonUploader, UserServiceUtil
│       │   └── App.java          # Main CLI program
│       └── resources/
│           ├── planes.json       # Plane & route data
│           └── users.json        # User data & bookings
🚀 Features
🧑‍💻 Sign up & log in with password hashing (bcrypt)

🔎 Search flights by source and destination airport

🪑 View available seats on a selected flight

🎫 Book a seat (persisted in JSON)

❌ Cancel a booking

📖 View past bookings

🛠 Requirements
Java 17+

Gradle 8.14+

Internet access (to resolve dependencies)

Dependencies
groovy
Copy
Edit
// Jackson for JSON parsing
implementation 'com.fasterxml.jackson.core:jackson-databind:2.15.3'

// BCrypt for password hashing
implementation 'org.mindrot:jbcrypt:0.4'

// MongoDB driver (not used in JSON version, possibly for future updates)
implementation 'org.mongodb:mongodb-driver-sync:4.11.0'

// JSON.org library
implementation 'org.json:json:20230227'

// Google Guava (utility collections, caching, etc.)
implementation libs.guava
▶️ Running the App
Clone the repository

bash
Copy
Edit
git clone https://github.com/your-username/airplane-booking-system.git
cd airplane-booking-system
Build the project using Gradle

bash
Copy
Edit
./gradlew build
Run the application

bash
Copy
Edit
./gradlew run
The CLI will guide you through all available actions: signup, login, search flights, book seats, and cancel bookings.

📚 Sample CLI Interaction
markdown
Copy
Edit
Running Airplane booking system
Choose Option
1. Sign Up
2. Log in
3. Fetch Bookings
4. Search Flights
5. Book a seat
6. Cancel a Booking
7. Exit
📦 Data Storage
All data is stored in JSON files:

users.json - Stores user details, hashed passwords, and bookings.

planes.json - Stores plane information, routes, and seat availability.

Uses Jackson ObjectMapper to read/write these files.

📌 Notes
Authentication is done using username and bcrypt-hashed password.

Booking logic includes checking seat availability and updating JSON.

This is a CLI-only prototype and can be expanded into a web or Android app in future.

Currently uses local file storage, MongoDB dependency is included but unused.

📷 Screenshot

(Update with correct path in repo after pushing image)

🤝 Contribution
Pull requests and feature ideas are welcome! Feel free to fork the project and build something awesome on top of it.
