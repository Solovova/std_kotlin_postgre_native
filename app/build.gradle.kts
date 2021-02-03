plugins {
    id("org.jetbrains.kotlin.jvm") version "1.4.30-RC"
    application
}

repositories {
    jcenter()
}

dependencies {
    implementation("org.postgresql:postgresql:42.2.18")
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("com.google.guava:guava:29.0-jre")
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")
}

application {
    mainClass.set("std_kotlin_postgre_native.AppKt")
}
