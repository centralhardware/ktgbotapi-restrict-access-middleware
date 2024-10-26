plugins {
    java
    `maven-publish`
    kotlin("jvm") version "2.0.21"
    id("com.ncorti.ktfmt.gradle") version "0.20.1"
}

group = "me.centralhardware.telegram"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("dev.inmo:tgbotapi:18.2.2")
}

tasks.test {
    useJUnitPlatform()
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

ktfmt {
    kotlinLangStyle()
}