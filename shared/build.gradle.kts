import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("com.squareup.sqldelight")
    id("kotlinx-serialization")
}

kotlin {
    android()

    val iosTarget: (String, KotlinNativeTarget.() -> Unit) -> KotlinNativeTarget =
        if (System.getenv("SDK_NAME")?.startsWith("iphoneos") == true)
            ::iosArm64
        else
            ::iosX64

    iosTarget("ios") {
        binaries {
            framework {
                baseName = "shared"
            }
        }
    }
    sourceSets {

        val commonMain by getting {
            dependencies {
                api ("org.jetbrains.kotlin:kotlin-stdlib-common")

                // COROUTINES
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.2-native-mt")

                // MOKO - MVVM
                implementation ("dev.icerock.moko:mvvm:0.11.0")

                // KTOR
                implementation("io.ktor:ktor-client-core:1.6.4")
                implementation("io.ktor:ktor-client-json:1.6.4")
                implementation("io.ktor:ktor-client-cio:1.6.4")

                implementation("io.ktor:ktor-client-logging:1.6.4")
                implementation("io.ktor:ktor-client-serialization:1.6.4")

                // SQL Delight
                implementation("com.squareup.sqldelight:runtime:1.5.1")

                //logger
                implementation("io.github.aakira:napier:2.1.0")

            }
        }

        val androidMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlin:kotlin-stdlib:1.5.31")
                implementation("org.jetbrains.kotlin:kotlin-stdlib-common:1.5.31")

                // MOKO - MVVM
                implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")

                // KTOR
                implementation("io.ktor:ktor-client-android:1.6.4")

                // SQL Delight
                implementation("com.squareup.sqldelight:android-driver:1.5.1")
            }
        }

        val iosMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlin:kotlin-stdlib-common:1.5.31")

                // KTOR
                implementation("io.ktor:ktor-client-ios:1.6.4")

                // SQL Delight
                implementation("com.squareup.sqldelight:native-driver:1.5.1")
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }

        val androidTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
                implementation("junit:junit:4.13.2")
            }
        }

        val iosTest by getting
    }

}

android {
    compileSdkVersion(30)
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdkVersion(21)
        targetSdkVersion(30)
    }
}

val packForXcode by tasks.creating(Sync::class) {
    val mode = System.getenv("CONFIGURATION") ?: "DEBUG"
    val framework = kotlin.targets.getByName<KotlinNativeTarget>("ios").binaries.getFramework(mode)
    val targetDir = File(buildDir, "xcode-frameworks")

    group = "build"
    dependsOn(framework.linkTask)
    inputs.property("mode", mode)

    from({ framework.outputDirectory })
    into(targetDir)
}

tasks.getByName("build").dependsOn(packForXcode)

sqldelight {
    database("AppDatabase") {
        packageName = "com.kmmsampleapp.shared.db"
    }

    linkSqlite = true
}
