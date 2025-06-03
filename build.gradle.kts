plugins {
    kotlin("multiplatform") version "2.1.21"
    kotlin("plugin.serialization") version "2.1.21"
}

group = "de.daniel"
version = "1.0"

repositories {
    mavenCentral()
}

kotlin {
    val hostOs = System.getProperty("os.name")
    val isArm64 = System.getProperty("os.arch") == "aarch64"
    val isMingwX64 = hostOs.startsWith("Windows")
    val nativeTarget = when {
        hostOs == "Mac OS X" && isArm64 -> macosArm64("nativeMacos")
        hostOs == "Mac OS X" && !isArm64 -> macosX64("nativeMacos")
        hostOs == "Linux" && isArm64 -> linuxArm64("nativeLinux")
        hostOs == "Linux" && !isArm64 -> linuxX64("nativeLinux")
        isMingwX64 -> mingwX64("nativeWindows")
        else -> throw GradleException("Host OS is not supported in Kotlin/Native.")
    }

    nativeTarget.apply {
        binaries {
            executable {
                entryPoint = "main"
            }
        }
    }

    sourceSets {
        nativeMain.dependencies {
            implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.8.1")
            implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.10.2")
        }
    }
}
