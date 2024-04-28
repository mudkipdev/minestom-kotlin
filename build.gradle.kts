plugins {
    kotlin("jvm") version "1.9.21"
    application
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "org.example"
version = "0.1.0"

application.mainClass = "org.example.server.ServerKt"

kotlin {
    jvmToolchain(21)
}

repositories {
    mavenCentral()
    maven("https://jitpack.io")
}

dependencies {
    implementation("com.github.Minestom:Minestom:-SNAPSHOT")

    // Enable logging in Minestom
    implementation("ch.qos.logback:logback-classic:1.5.6")

    // Find some more Minestom libraries here:
    // https://minestom.net/libraries
}

tasks.shadowJar {
    archiveClassifier = ""
}

tasks.build {
    dependsOn(tasks.shadowJar)
}