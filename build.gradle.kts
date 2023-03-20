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
        languageVersion.set(JavaLanguageVersion.of(11))
    }
}

azurefunctions {
    subscription = property("azure.subscription").toString()
    resourceGroup = property("azure.rg").toString()
    appName = property("azure.appname").toString()
    pricingTier = property("azure.pricing").toString()
    region = property("azure.region").toString()
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