plugins {
    id("java")
}

group = "org.lukas"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    val jUnitVersion = "5.9.0"
    testImplementation("org.junit.jupiter:junit-jupiter-api:$jUnitVersion")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$jUnitVersion")
}

tasks.jar {
    manifest {
        attributes["Main-Class"] = "org.lukas.Main"
    }
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}