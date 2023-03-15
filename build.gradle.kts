plugins {
    kotlin("jvm") version "1.7.21"
    id("com.microsoft.azure.azurefunctions") version "1.8.0"
    application
}


group = "jp.co.mazda"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.7.21")
    implementation("com.microsoft.azure.functions:azure-functions-java-library:3.0.0")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain {
        languageVersion.set(JavaLanguageVersion.of(11))
    }
}

application {
    mainClass.set("FunctionKt")
}

azurefunctions {
    subscription = "f31b1b25-6cef-41d9-af18-07e484302cf4"
    resourceGroup = "poc-rg-authdemo-usw2"
    appName = "azure-function-samples"
    setRuntime(closureOf<com.microsoft.azure.gradle.configuration.GradleRuntimeConfig> {
        os("Windows")
    })
    setAuth(closureOf<com.microsoft.azure.gradle.auth.GradleAuthConfig> {
        type = "azure_cli"
    })
    setDeployment(closureOf<com.microsoft.azure.plugin.functions.gradle.configuration.deploy.Deployment> {
        type = "run_from_blob"
    })
}