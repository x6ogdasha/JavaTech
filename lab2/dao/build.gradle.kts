plugins {
    id("java")
    id("org.springframework.boot") version "3.2.4" apply false
    id("io.spring.dependency-management") version "1.1.4"
}

group = "ru.bkitaev"
version = "unspecified"

repositories {
    mavenCentral()
}
dependencyManagement {
    imports {
        mavenBom(org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES)
    }
}


dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    runtimeOnly("org.postgresql:postgresql")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
    implementation("org.springframework.boot:spring-boot-starter-security")
    testImplementation("org.springframework.security:spring-security-test")

}
tasks.withType<JavaCompile> {
    options.compilerArgs.add("-parameters")
}

tasks.test {
    useJUnitPlatform()
}