plugins {
    id("java")
    id("idea")
    id("org.jetbrains.intellij") version "1.9.0"
}

group = "kim.nzxy"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

// Configure Gradle IntelliJ Plugin
// Read more: https://plugins.jetbrains.com/docs/intellij/tools-gradle-intellij-plugin.html
intellij {
    version.set("2021.3.3")
    type.set("IU") // Target IDE Platform

    plugins.set(listOf("DatabaseTools"))
}

tasks {
    // Set the JVM compatibility versions
    withType<JavaCompile> {
        sourceCompatibility = "11"
        targetCompatibility = "11"
    }

    patchPluginXml {
        sinceBuild.set("213")
        untilBuild.set("223.*")
    }

    signPlugin {
        certificateChain.set(System.getenv("CERTIFICATE_CHAIN"))
        privateKey.set(System.getenv("PRIVATE_KEY"))
        password.set(System.getenv("PRIVATE_KEY_PASSWORD"))
    }

    publishPlugin {
        token.set(System.getenv("PUBLISH_TOKEN"))
    }
}

dependencies {
    implementation("com.softwareloop:mybatis-generator-lombok-plugin:1.0")
    implementation("uk.com.robust-it:cloning:1.9.2")
    implementation("org.mybatis.generator:mybatis-generator-core:1.4.0")
    implementation("org.freemarker:freemarker:2.3.31")
    implementation("com.itranswarp:compiler:1.0")
    implementation("com.github.jiconfont:jiconfont-swing:1.0.1")
//    annotationProcessor("org.projectlombok:lombok:1.18.0")
    compileOnly("org.projectlombok:lombok:1.18.0")
}

idea {
    module {
        isDownloadJavadoc = true
        isDownloadSources = true
    }
}