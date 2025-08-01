plugins {
    id("net.labymod.labygradle")
    id("net.labymod.labygradle.addon")
    kotlin("jvm") version "2.2.0"
}

val versions = providers.gradleProperty("net.labymod.minecraft-versions").get().split(";")

group = "com.github.wildtooth"
version = providers.environmentVariable("VERSION").getOrElse("0.1.0")

labyMod {
    defaultPackageName = "com.github.wildtooth.knowledge" //change this to your main package name (used by all modules)

    minecraft {
        registerVersion(versions.toTypedArray()) {
            runs {
                getByName("client") {
                    // When the property is set to true, you can log in with a Minecraft account
                    // devLogin = true
                }
            }
        }
    }

    addonInfo {
        namespace = "knowledge"
        displayName = "Server Knowledge"
        author = "Champen_V1ldtand"
        description = "Helps players navigate servers with known knowledge."
        minecraftVersion = "*"
        version = rootProject.version.toString()
    }
}

subprojects {
    plugins.apply("net.labymod.labygradle")
    plugins.apply("net.labymod.labygradle.addon")
    plugins.apply("org.jetbrains.kotlin.jvm")

    group = rootProject.group
    version = rootProject.version
}