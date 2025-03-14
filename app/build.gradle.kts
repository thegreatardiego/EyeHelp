plugins {
    id("com.android.application")
    id("com.google.gms.google-services")
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")

}

android {
    namespace = "com.example.eyehelp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.eyehelp"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation ("com.android.support:appcompat-v7:28.0.0")
    implementation ("com.android.support.constraint:constraint-layout:2.0.4")
    implementation ("androidx.camera:camera-camera2:1.3.1")
    implementation ("androidx.camera:camera-lifecycle:1.3.1")
    implementation ("androidx.camera:camera-view:1.4.0-alpha03")
    implementation ("com.google.code.gson:gson:2.8.9")
    implementation("com.google.firebase:firebase-auth:22.3.1")
    implementation("com.google.firebase:firebase-storage:20.3.0")
    implementation("com.google.firebase:firebase-database:20.3.1")
    implementation("com.google.firebase:firebase-database-connection-license:12.0.1")
    implementation("com.google.firebase:firebase-database-ktx:20.3.1")
    implementation("androidx.cardview:cardview:1.0.0")
    implementation("androidx.navigation:navigation-fragment:2.6.0")
    implementation("androidx.navigation:navigation-ui:2.6.0")
    implementation("com.google.android.gms:play-services-maps:18.2.0")
    implementation("com.google.android.gms:play-services-location:17.0.0")
    implementation("com.android.volley:volley:1.2.1")
    implementation ("com.github.bumptech.glide:glide:4.14.2")
    implementation ("com.firebaseui:firebase-ui-database:8.0.0")
    implementation ("androidx.recyclerview:recyclerview:1.3.2")
    implementation ("androidx.core:core-ktx:+")
    implementation ("com.android.support.constraint:constraint-layout:2.1.0")
    implementation ("com.github.clans:fab:1.6.4")
    implementation("androidx.activity:activity:1.8.0")
    annotationProcessor ("com.github.bumptech.glide:compiler:4.14.2")
    implementation("io.github.medyo:android-about-page:2.0.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")



}