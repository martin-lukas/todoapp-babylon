plugins {
    java
}

group = "org.lukas"
version = "1.0-SNAPSHOT"

java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.vertx:vertx-core:4.0.0")
    implementation("io.vertx:vertx-web:4.0.0")
}
