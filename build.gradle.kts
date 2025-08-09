import org.jetbrains.kotlin.cli.js.klib.generateIrForKlibSerialization

plugins {
    kotlin("jvm") version "2.0.0"
}

group = "org.meopedevts"
version = "1.0.0"

repositories {
    mavenCentral()
    maven {
        url = uri("https://artifacts-partners.sankhya.com.br/repository/maven-releases")
    }
}

dependencies {
    api(kotlin("stdlib"))
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation(files("libs/mge-modelcore-master.jar"))
    implementation(files("libs/libs-master.jar"))

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(11)
}