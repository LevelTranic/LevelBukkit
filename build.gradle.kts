plugins {
    java
    `maven-publish`
    id("io.papermc.paperweight.patcher") version "1.7.1"
    kotlin("jvm") version "2.0.0"
    kotlin("plugin.serialization") version "2.0.0"
}

val paperMavenPublicUrl = "https://repo.papermc.io/repository/maven-public/"

repositories {
    mavenCentral()
    maven(paperMavenPublicUrl) {
        content { onlyForConfigurations(configurations.paperclip.name) }
    }
}

dependencies {
    remapper("net.fabricmc:tiny-remapper:0.10.1:fat")
    decompiler("org.vineflower:vineflower:1.10.1")
    paperclip("io.papermc:paperclip:3.0.3")
}

subprojects {
    apply(plugin = "java")
    apply(plugin = "maven-publish")

    java {
        toolchain {
            languageVersion = JavaLanguageVersion.of(21)
        }
    }

    tasks.withType<JavaCompile> {
        options.encoding = Charsets.UTF_8.name()
        options.release = 21
    }

    tasks.withType<Javadoc> {
        options.encoding = Charsets.UTF_8.name()
    }

    tasks.withType<ProcessResources> {
        filteringCharset = Charsets.UTF_8.name()
    }

    repositories {
        mavenCentral()
        maven(paperMavenPublicUrl)
        maven("https://oss.sonatype.org/content/groups/public/")
        maven("https://ci.emc.gs/nexus/content/groups/aikar/")
        maven("https://repo.aikar.co/content/groups/aikar")
        maven("https://repo.md-5.net/content/repositories/releases/")
        maven("https://hub.spigotmc.org/nexus/content/groups/public/")
        maven("https://jitpack.io")
        maven("https://repo.codemc.io/repository/maven-public/")
    }
}

paperweight {
    serverProject.set(project(":levelbukkit-server"))

    remapRepo.set("https://maven.fabricmc.net/")
    decompileRepo.set("https://maven.quiltmc.org/")

    useStandardUpstream("luminol") {
        url.set(github("LuminolMC", "Luminol"))
        ref.set(providers.gradleProperty("luminolCommit"))

        withStandardPatcher {
            apiSourceDirPath.set("Luminol-API")
            serverSourceDirPath.set("Luminol-Server")


            apiPatchDir.set(layout.projectDirectory.dir("patches/api"))
            apiOutputDir.set(layout.projectDirectory.dir("LevelBukkit-API"))

            serverPatchDir.set(layout.projectDirectory.dir("patches/server"))
            serverOutputDir.set(layout.projectDirectory.dir("LevelBukkit-Server"))
        }

        patchTasks.register("generatedApi") {
            isBareDirectory = true
            upstreamDirPath = "paper-api-generator/generated"
            patchDir = layout.projectDirectory.dir("patches/generatedApi")
            outputDir = layout.projectDirectory.dir("paper-api-generator/generated")
        }
    }
}

tasks.generateDevelopmentBundle {
    apiCoordinates.set("one.tranic.bukkit:levelbukkit-api")
    libraryRepositories.addAll(
        "https://repo.maven.apache.org/maven2/",
        paperMavenPublicUrl,
    )
}

allprojects {
    publishing {
        repositories {
            maven {
                name = "githubPackage"
                url = uri("https://maven.pkg.github.com/404Setup/LevelBukkit")

                credentials.username = System.getenv("GITHUB_USERNAME")
                credentials.password = System.getenv("GITHUB_TOKEN")
            }

            publications {
                register<MavenPublication>("gpr") {
                    from(components["java"])
                }
            }
        }
    }
 }

publishing {
    if (project.hasProperty("publishDevBundle")) {
        publications.create<MavenPublication>("devBundle") {
            artifact(tasks.generateDevelopmentBundle) {
                artifactId = "dev-bundle"
            }
        }
    }
}

