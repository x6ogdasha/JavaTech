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
//    implementation("org.hibernate:hibernate-core:6.4.4.Final")
//    // https://mvnrepository.com/artifact/javax.xml.bind/jaxb-api
//    implementation("javax.xml.bind:jaxb-api:2.3.1")
//
//    // https://mvnrepository.com/artifact/org.javassist/javassist
//    implementation("org.javassist:javassist:3.30.2-GA")

    implementation(project(":lab2:dao"))
    //implementation(project(":lab2:services"))
//    implementation(project(":Services"))

}
tasks.withType<JavaCompile> {
    options.compilerArgs.add("-parameters")
}

tasks.test {
    useJUnitPlatform()
}