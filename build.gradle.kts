plugins {
    id("java")
    id("org.jetbrains.kotlin.jvm") version "2.0.20"
}

buildscript {
    val kotlinVersion by extra("2.0.20")

    repositories {
        maven("https://cache-redirector.jetbrains.com/maven-central")
        maven("https://cache-redirector.jetbrains.com/intellij-dependencies")
    }

    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
    }
}

sourceSets {
    val test by getting {
        kotlin.srcDirs("testSrc")
        resources {
            srcDirs("resources")
            include("**/*.*")
        }
    }
}

repositories {
    maven("https://cache-redirector.jetbrains.com/maven-central")
    maven("https://cache-redirector.jetbrains.com/intellij-dependencies")
    maven("https://cache-redirector.jetbrains.com/download.jetbrains.com/teamcity-repository")
    maven("https://www.jetbrains.com/intellij-repository/releases")
    maven("https://www.jetbrains.com/intellij-repository/snapshots")
    maven("https://download.jetbrains.com/teamcity-repository")
    maven("https://cache-redirector.jetbrains.com/packages.jetbrains.team/maven/p/grazi/grazie-platform-public")
}

dependencies {
    testImplementation("com.jetbrains.intellij.tools:ide-starter-squashed:251.23774.109-EAP-SNAPSHOT")
    testImplementation("com.jetbrains.intellij.tools:ide-starter-junit5:251.23774.109-EAP-SNAPSHOT")
    testImplementation("com.jetbrains.intellij.tools:ide-metrics-collector:251.23774.109-EAP-SNAPSHOT")
    testImplementation("com.jetbrains.intellij.tools:ide-metrics-collector-starter:251.23774.109-EAP-SNAPSHOT")
    testImplementation("com.jetbrains.intellij.tools:ide-performance-testing-commands:251.23774.109-EAP-SNAPSHOT")
    testImplementation("com.jetbrains.intellij.tools:ide-starter-driver:251.23774.109-EAP-SNAPSHOT")
    testImplementation("com.jetbrains.intellij.driver:driver-client:251.23774.109-EAP-SNAPSHOT")
    testImplementation("com.jetbrains.intellij.driver:driver-sdk:251.23774.109-EAP-SNAPSHOT")
    testImplementation("com.jetbrains.intellij.driver:driver-model:251.23774.109-EAP-SNAPSHOT")
    testImplementation("org.kodein.di:kodein-di-jvm:7.20.2")
    testImplementation("junit:junit:4.13.2")
    testImplementation("commons-io:commons-io:2.15.0")
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.2")
    testImplementation("org.junit.platform:junit-platform-launcher:1.10.2")
    testImplementation("com.fasterxml.jackson.core:jackson-databind:2.16.0")
    testImplementation("com.jetbrains.fus.reporting:ap-validation:76")
    testImplementation("com.jetbrains.fus.reporting:model:76")
}

kotlin {
    jvmToolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

configurations {
    implementation {
        exclude(group = "io.ktor")
        exclude(group = "com.jetbrains.infra")
        exclude(group = "com.jetbrains.intellij.remoteDev")
    }
}

tasks.test {
    useJUnitPlatform()

    testLogging {
        events("passed", "skipped", "failed", "standardOut", "standardError")
    }
}