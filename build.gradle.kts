plugins {
    java
    `maven-publish`
    kotlin("jvm") version "2.2.0"
    id("org.jetbrains.dokka") version "2.0.0"
}

group = "me.centralhardware.telegram"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("dev.inmo:tgbotapi:27.0.0")
    implementation("dev.inmo:kslog:1.5.0")
}

tasks.test {
    useJUnitPlatform()
}

tasks.dokkaHtml.configure {
    outputDirectory.set(file("${buildDir}/dokka"))
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "me.centralhardware.telegram"
            artifactId = "ktgbotapi-restrict-access-middleware"
            version = "1.0-SNAPSHOT"
            from(components["java"])
        }
    }
}
