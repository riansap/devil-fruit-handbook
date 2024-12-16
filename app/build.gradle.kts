plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("kotlin-parcelize") // plugin Parcelize untuk Parcelable
}


android {
    namespace = "com.riansap.submissiondicoding"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.riansap.submissiondicoding"
        minSdk = 28
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        viewBinding = true
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    dataBinding {
        enable = true
    }

    kotlinOptions {
        jvmTarget = "11"
    }

    viewBinding {
        enable = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.swiperefreshlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // AndroidX Core untuk ViewModels
    implementation("androidx.activity:activity-ktx:1.7.2")

    // Material
    implementation("com.google.android.material:material:1.8.0")

    // AndroidX Lifecycle ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")

    // LiveData
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.1")

    // Retrofit dan Gson
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    // Picasso Image Loading
    implementation("com.squareup.picasso:picasso:2.8")

    // Null-Safety
    implementation("androidx.annotation:annotation:1.6.0")
}