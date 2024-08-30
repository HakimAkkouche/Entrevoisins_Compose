plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.haksoftware.entrevoisins_kotlin_compose"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.haksoftware.entrevoisins_kotlin_compose"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            excludes += "/META-INF/LICENSE.md"
            excludes += "/META-INF/LICENSE"
            excludes += "/META-INF/NOTICE"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    implementation(libs.androidx.room.common)
    implementation(libs.androidx.room.ktx)
    implementation(libs.androidx.room.runtime)
    implementation(libs.material)
    ksp(libs.androidx.room.compiler)

    implementation(libs.lifecycle.viewmodel.compose)

    implementation(libs.hilt.android)
    kapt(libs.dagger.hilt.android.compiler)

    implementation(libs.androidx.hilt.navigation.compose)

    implementation(libs.accompanist.pager)
    implementation(libs.accompanist.pager.indicators)


    testImplementation(libs.androidx.room.testing)

    implementation(libs.coil.compose)

    testImplementation(libs.junit)

    testImplementation(libs.mockito.kotlin)
    testImplementation(libs.mockito.inline)
    androidTestImplementation(libs.mockito.kotlin)

    testImplementation(libs.kotlinx.coroutines.test)
    androidTestImplementation(libs.kotlinx.coroutines.test)

    testImplementation(libs.byte.buddy)
    testImplementation(libs.byte.buddy.agent)

    testImplementation(libs.androidx.core.testing)

    testImplementation(libs.turbine)
    androidTestImplementation(libs.turbine)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    androidTestImplementation(libs.androidx.core.testing)
    androidTestImplementation(libs.androidx.rules)

    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}