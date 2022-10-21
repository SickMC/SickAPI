@file:Suppress("SpellCheckingInspection")

plugins {
    kotlin("jvm") version "1.7.10"
    kotlin("plugin.serialization") version "1.7.10"

    id("com.github.breadmoirai.github-release") version "2.+"
    `maven-publish`
    signing
}

group = "net.sickmc"
version = "1.0.8"
description = "API providing fundamentals for the SickMC components"
val isSnapshot = false
val authors = listOf("btwonion")
val githubRepo = "SickMC/SickAPI"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.litote.kmongo:kmongo-coroutine-serialization:4.7.1")

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")

    val ktorVersion = "2.1.2"
    implementation("io.ktor:ktor-client-core:$ktorVersion")
    implementation("io.ktor:ktor-client-websockets:$ktorVersion")
    implementation("io.ktor:ktor-client-cio:$ktorVersion")
}

githubRelease {
    token(findProperty("github.token")?.toString())

    val split = githubRepo.split("/")
    owner(split[0])
    repo(split[1])
    tagName("v${project.version}")
    prerelease(isSnapshot)
    releaseAssets(tasks["build"].outputs.files)
}

java {
    withSourcesJar()
    withJavadocJar()
}

tasks {
    register("release") {
        group = "publishing"

        dependsOn("githubRelease")
        dependsOn("publish")
    }
}

publishing {
    repositories {
        maven {
            name = "ossrh"
            credentials(PasswordCredentials::class)
            setUrl(
                if (!isSnapshot) "https://s01.oss.sonatype.org/service/local/staging/deploy/maven2"
                else "https://s01.oss.sonatype.org/content/repositories/snapshots"
            )
        }
    }

    publications {
        register<MavenPublication>(project.name) {
            from(components["java"])

            this.groupId = project.group.toString()
            this.artifactId = project.name
            this.version = rootProject.version.toString()

            pom {
                name.set(project.name)
                description.set(project.description)

                developers {
                    authors.forEach {
                        developer {
                            name.set(it)
                        }
                    }
                }

                licenses {
                    license {
                        name.set("GNU General Public License 3")
                        url.set("https://www.gnu.org/licenses/gpl-3.0.txt")
                    }
                }

                url.set("https://github.com/${githubRepo}")

                scm {
                    connection.set("scm:git:git://github.com/${githubRepo}.git")
                    url.set("https://github.com/${githubRepo}/tree/main")
                }
            }
        }
    }
}

signing {
    sign(publishing.publications)
}