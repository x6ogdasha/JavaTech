plugins {
    id("java")
    id("org.springframework.boot") version "3.2.4"
    id("io.spring.dependency-management") version "1.1.4"
}

group = "ru.bkitaev"
version = "unspecified"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    //implementation("org.springframework.boot:spring-boot-starter-security")
    // testImplementation("org.springframework.security:spring-security-test")

    implementation("org.springframework.kafka:spring-kafka")
    testImplementation("org.springframework.kafka:spring-kafka-test")
    implementation("org.modelmapper:modelmapper:2.1.1")

    implementation(project(":lab5:catMicroservice:dao"))
    implementation(project(":lab5:catMicroservice:services"))
    implementation(project(":lab5:catMicroservice:controllers"))


}

tasks.test {
    useJUnitPlatform()
}