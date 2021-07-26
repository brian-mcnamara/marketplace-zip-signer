plugins {
    kotlin("jvm")
    id("com.github.johnrengelman.shadow") version "5.2.0"
}

dependencies {
    implementation(platform("com.google.cloud:libraries-bom:20.8.0"))
    implementation("com.google.cloud:google-cloud-kms")
    implementation("com.github.spullara.cli-parser:cli-parser:1.1.2")
    implementation(project(":lib"))
}