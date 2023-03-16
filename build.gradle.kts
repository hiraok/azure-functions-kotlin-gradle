plugins {
    kotlin("jvm") version "1.7.21"
    id("com.microsoft.azure.azurefunctions") version "1.9.0"
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
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

azurefunctions {
    subscription = "68d58ca3-d623-4068-9c50-8c624025a65e"
    resourceGroup = "function-test"
    appName = "azure-function-kotlin-gradle-1"
    pricingTier = "Consumption"
    region = "westus"
    localDebug = "transport=dt_socket,server=y,suspend=n,address=5005"
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