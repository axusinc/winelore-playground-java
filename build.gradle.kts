plugins {
    kotlin("jvm") version "2.1.20"
    id("org.jetbrains.kotlin.plugin.serialization") version "2.1.10"
}

group = "com.axus.winelore"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
//    mavenLocal()
}

dependencies {
//    api("io.github.likespro:axusid-core:0.0.1")
    implementation("io.github.likespro:commons-core:3.1.0")
    implementation("io.github.likespro:lpfcp-core:1.1.0")
    implementation("io.ktor:ktor-serialization-gson:3.1.3")

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}