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
    implementation("com.fasterxml.jackson.core:jackson-databind:2.14.0")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.0")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<Jar> {
    doLast {
        print(File("${buildDir}/libs").listFiles()?.forEach {
            println("File: ${it.name}, size: ${it.length() / 1024} KB")
        })
    }
}
