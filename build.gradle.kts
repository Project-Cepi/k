import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    // Apply the Kotlin JVM plugin to add support for Kotlin.
    id("org.jetbrains.kotlin.jvm") version "1.7.20"
    // Kotlinx serialization for any data format
    kotlin("plugin.serialization") version "1.7.20"
    // Shade the plugin
    id("com.github.johnrengelman.shadow") version "7.0.0"
    // Allow publishing
    `maven-publish`

    // Apply the application plugin to add support for building a jar
    java
    // Dokka documentation w/ kotlin
    id("org.jetbrains.dokka") version "1.7.20"
}

repositories {
    // Use mavenCentral
    mavenCentral()

    maven(url = "https://jitpack.io")
    maven(url = "https://repo.spongepowered.org/maven")
    maven(url = "https://repo.minestom.com/repository/maven-public/")
    maven(url = "https://repo.velocitypowered.com/snapshots/")
}

dependencies {
    // Align versions of all Kotlin components
    compileOnly(platform("org.jetbrains.kotlin:kotlin-bom"))

    // Use the Kotlin JDK 8 standard library.
    compileOnly(kotlin("stdlib"))

    // Use the Kotlin reflect library.
    compileOnly(kotlin("reflect"))

    // Compile Minestom into project
    compileOnly("com.github.Minestom:Minestom:9152c40753")

    // Add KStom
    compileOnly("com.github.Project-Cepi:KStom:5daaf786cf")

    // Add Scripting Engine
    implementation(kotlin("scripting-jsr223"))
    implementation(kotlin("compiler-embeddable"))
    implementation(kotlin("scripting-jvm-host"))
    implementation(kotlin("scripting-jvm"))
    implementation(kotlin("scripting-common"))
    implementation(kotlin("script-runtime"))
    implementation(kotlin("script-util"))
    implementation(kotlin("script-runtime"))
    implementation(kotlin("scripting-compiler-embeddable"))

    // Use the kotlin test library
    testImplementation("io.kotest:kotest-assertions-core:5.5.4")
    testImplementation("io.kotest:kotest-runner-junit5:5.5.3")

    // import kotlinx serialization
    compileOnly("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1")
}

configurations {
    testImplementation {
        extendsFrom(configurations.compileOnly.get())
    }
}

// Take gradle.properties and apply it to resources.
tasks {
    processResources {
        // Apply properties to extension.json
        filesMatching("extension.json") {
            expand(project.properties)
        }
    }

    // Set name, minimize, and merge service files
    named<ShadowJar>("shadowJar") {
        archiveBaseName.set(project.name)
        mergeServiceFiles()
        minimize()
    }

    test { useJUnitPlatform() }

    // Make build depend on shadowJar as shading dependencies will most likely be required.
    build { dependsOn(shadowJar) }

}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}


val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions.jvmTarget = JavaVersion.VERSION_16.toString()

compileKotlin.kotlinOptions {
    freeCompilerArgs = listOf("-Xinline-classes")
}
