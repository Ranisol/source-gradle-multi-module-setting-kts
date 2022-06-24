import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    id("org.springframework.boot") version "2.4.1"
    //id("net.researchgate.release") version "2.8.1"
    id("io.spring.dependency-management") version "1.0.6.RELEASE"
    kotlin("jvm") version "1.5.0"
    kotlin("kapt") version "1.5.0"
    kotlin("plugin.spring") version "1.5.0"
    kotlin("plugin.jpa") version "1.5.0"
}

repositories {
    mavenCentral()
    maven(url = "https://repo1.maven.org/maven2/")
}

subprojects {
    repositories {
        mavenCentral()
        maven(url = "https://repo1.maven.org/maven2/")
    }

    apply(plugin = "kotlin")
    apply(plugin = "kotlin-kapt")
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")
    apply(plugin = "org.jetbrains.kotlin.plugin.spring")
    apply(plugin = "org.jetbrains.kotlin.plugin.jpa")

    dependencyManagement {
        // spring boot 와 호환되는 spring cloud 버전 가이드 참고 : https://spring.io/projects/spring-cloud
        val springCloudVersion = "2020.0.5"
        imports {
            mavenBom("org.springframework.cloud:spring-cloud-dependencies:${springCloudVersion}")
        }
    }

    dependencies {
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

        implementation("org.springframework.boot:spring-boot-starter-data-rest")
        implementation("org.springdoc:springdoc-openapi-ui:1.5.3")
        implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

        implementation("org.springframework.boot:spring-boot-starter-data-jpa")
        implementation("org.springframework.data:spring-data-envers")
        runtimeOnly("mysql:mysql-connector-java")

        testImplementation("org.springframework.boot:spring-boot-starter-jdbc")

        developmentOnly("org.springframework.boot:spring-boot-devtools")
        testImplementation("org.springframework.boot:spring-boot-starter-test")
        developmentOnly("com.h2database:h2")
        testImplementation("com.h2database:h2")
        testImplementation("org.junit.jupiter:junit-jupiter-engine")
        testImplementation("org.jetbrains.kotlin:kotlin-test")
        testImplementation("org.springframework.security:spring-security-test")
        testImplementation("com.ninja-squad:springmockk:3.0.1")


        implementation("org.springframework.boot:spring-boot-starter-validation")
        implementation("org.springframework.boot:spring-boot-starter-web")
        annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")


        // map-struct
        implementation("org.mapstruct:mapstruct:1.4.0.Final")
        kapt("org.mapstruct:mapstruct-processor:1.4.0.Final")

        implementation("org.springframework.cloud:spring-cloud-starter-aws:2.2.5.RELEASE")
        implementation("org.springframework.cloud:spring-cloud-starter-openfeign")
        implementation("io.github.openfeign:feign-okhttp:11.6")

        // mail
        implementation("org.springframework.boot:spring-boot-starter-mail")
        implementation("org.springframework.boot:spring-boot-starter-thymeleaf")

    }

    tasks {
        compileKotlin {
            kotlinOptions {
                freeCompilerArgs = listOf("-Xjsr305=strict")
                jvmTarget = "11"
            }
            dependsOn(processResources) // kotlin 에서 ConfigurationProperties
        }

        compileTestKotlin {
            kotlinOptions {
                freeCompilerArgs = listOf("-Xjsr305=strict")
                jvmTarget = "11"
            }
        }

        test {
            useJUnitPlatform()
        }
    }


    val jar: Jar by tasks
    val bootJar: BootJar by tasks

    bootJar.enabled = false
    jar.enabled = true
}



val jar: Jar by tasks
val bootJar: BootJar by tasks

bootJar.enabled = false
jar.enabled = false