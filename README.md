<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
</head>
<body>

<h1>✈️ Airplane Booking System (Java + Gradle CLI App)</h1>

<p>This is a command-line based Java application for booking airplane tickets. Users can sign up, log in, search for flights, book seats, view bookings, and cancel reservations. The data is stored in JSON files using Jackson's ObjectMapper.</p>

<hr>

<h2>📁 Project Structure</h2>

<pre><code>app/
├── build.gradle               # Gradle build script
├── src/
│   └── main/
│       ├── java/
│       │   ├── entities/      # POJOs: Plane, Ticket, User
│       │   ├── services/      # Business logic: UserBookingService, PlaneService
│       │   ├── util/          # Utility classes: JsonUploader, UserServiceUtil
│       │   └── App.java       # Main CLI program
│       └── resources/
│           ├── planes.json    # Plane & route data
│           └── users.json     # User data & bookings
</code></pre>

<hr>

<h2>🚀 Features</h2>

<ul>
  <li>🧑‍💻 User Signup & Login with password hashing (<code>bcrypt</code>)</li>
  <li>✈️ Flight search by source and destination</li>
  <li>🪑 Seat selection and booking</li>
  <li>🎫 View and cancel bookings</li>
  <li>📖 Data persistence with JSON using Jackson</li>
  <li>✅ Simple CLI-based user interaction</li>
</ul>

<hr>

<h2>🔧 Technologies Used</h2>

<ul>
  <li><strong>Java 17</strong></li>
  <li><strong>Gradle 8.14</strong></li>
  <li><strong>Jackson Databind</strong> – JSON mapping</li>
  <li><strong>jBCrypt</strong> – Password hashing</li>
  <li><strong>MongoDB Driver</strong> – (Imported, not used in current logic)</li>
  <li><strong>JUnit</strong> – For testing (can be extended)</li>
</ul>

<hr>

<h2>📦 Requirements</h2>

<ul>
  <li>Java 17 or above</li>
  <li>Gradle (or use the Gradle wrapper)</li>
  <li>Internet (for downloading dependencies from Maven Central)</li>
</ul>

<hr>

<h2>🛠️ How to Run</h2>

<ol>
  <li><strong>Clone the repository</strong>
    <pre><code>git clone https://github.com/your-username/airplane-booking-system.git
cd airplane-booking-system</code></pre>
  </li>
  <li><strong>Build the project</strong>
    <pre><code>gradle build</code></pre>
  </li>
  <li><strong>Run the application</strong>
    <pre><code>gradle run</code></pre>
  </li>
</ol>

<hr>

<h2>⚙️ Gradle Build Configuration (<code>build.gradle</code>)</h2>

<pre><code>plugins {
    id 'application'
}

repositories {
    mavenCentral()
}

dependencies {
    implementation 'com.fasterxml.jackson.core:jackson-databind:2.15.3'
    implementation 'org.mindrot:jbcrypt:0.4'
    implementation 'org.mongodb:mongodb-driver-sync:4.11.0'
    implementation 'org.json:json:20230227'

    testImplementation libs.junit
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

application {
    mainClass = 'App'
}

sourceSets {
    main {
        resources {
            srcDirs = ['src/main/resources']
        }
    }
}
</code></pre>

<hr>

<h2>📚 Sample Data Files</h2>

<h3><code>planes.json</code></h3>
<pre><code>[
  {
    "planeId": "P123",
    "route": ["DEL", "BLR", "HYD"],
    "seats": [[0, 0], [0, 0]]
  }
]
</code></pre>

<h3><code>users.json</code></h3>
<pre><code>[
  {
    "username": "john",
    "password": "$2a$10$hashedpass",
    "userId": "uuid-string",
    "bookings": []
  }
]
</code></pre>

<hr>

<h2>🧪 Coming Soon</h2>

<ul>
  <li>MongoDB database support</li>
  <li>Admin dashboard</li>
  <li>Booking history filter by dates</li>
</ul>

<hr>

<h2>🤝 Contributing</h2>

<p>Pull requests are welcome! For major changes, please open an issue first to discuss what you would like to change.</p>
<hr>

<h2>📝 License</h2>

<p>This project is open source and available under the <a href="LICENSE">MIT License</a>.</p>

<hr>

<p><em>Built with ❤️ in Java by Hanu</em></p>

</body>


