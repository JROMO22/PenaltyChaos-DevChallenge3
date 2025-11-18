plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    // Add the Google services Gradle plugin
    id("com.google.gms.google-services")
}

android {
    namespace = "com.joel.penaltychaos"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.joel.penaltychaos"
        minSdk = 24
        targetSdk = 36
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
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {

    dependencies {

        implementation(libs.androidx.core.ktx)
        implementation(libs.androidx.appcompat)
        implementation(libs.material)
        implementation(libs.androidx.activity)
        implementation(libs.androidx.constraintlayout)
        implementation(libs.androidx.gridlayout)
        implementation(libs.filament.android)

        testImplementation(libs.junit)
        androidTestImplementation(libs.androidx.junit)
        androidTestImplementation(libs.androidx.espresso.core)
        implementation(libs.material.v1120)

        // Import the Firebase BoM
        implementation(platform("com.google.firebase:firebase-bom:34.3.0"))
        // TODO: Add the dependencies for Firebase products you want to use
        implementation("com.google.firebase:firebase-database-ktx:21.0.0")

        // When using the BoM, don't specify versions in Firebase dependencies
        // https://firebase.google.com/docs/android/setup#available-libraries
    }

}