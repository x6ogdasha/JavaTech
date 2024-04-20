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
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    //implementation("org.springframework.boot:spring-boot-starter-actuator")

    // runtimeOnly("org.postgresql:postgresql")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    // implementation("org.hibernate:hibernate-core:6.4.4.Final")
    // https://mvnrepository.com/artifact/javax.xml.bind/jaxb-api
    //implementation("javax.xml.bind:jaxb-api:2.3.1")

    // https://mvnrepository.com/artifact/org.javassist/javassist
    //implementation("org.javassist:javassist:3.30.2-GA")
    //implementation(project(":lab2:controllers"))
    implementation(project(":lab2:dao"))
    implementation(project(":lab2:controllers"))
    implementation(project(":lab2:services"))

}

tasks.test {
    useJUnitPlatform()
}