import org.jetbrains.kotlin.kapt3.base.Kapt.kapt

plugins {
    id ("com.android.library")
    id("org.jetbrains.kotlin.android")
    id ("kotlin-kapt")
    id ("dagger.hilt.android.plugin")
}

android {
    namespace = "com.d208.presentation"
    compileSdk = 33

    defaultConfig {
//        applicationId = "com.d208.presentation"
        minSdk = 24
        targetSdk = 33
//        versionCode = 1
//        versionName = "1.0"

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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures{
        viewBinding = true
    }
}

dependencies {
    implementation (project (":domain"))
    implementation (project (":data"))
    implementation("androidx.core:core-ktx:1.8.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")


    implementation(com.d208.buildsrc.Library.LOADING)
    implementation(com.d208.buildsrc.Library.LOADING_SPINEER)
    implementation(com.d208.buildsrc.Library.ANIMATION)
    implementation(com.d208.buildsrc.Library.PIE_GRAPH)
    implementation(com.d208.buildsrc.Library.LOTTIE)
    implementation(com.d208.buildsrc.Library.SEARCH_BAR)
    implementation(com.d208.buildsrc.AndroidX.FRAGMENT)
    implementation(com.d208.buildsrc.Library.DROPDOWN)

    // LiveData
    implementation(com.d208.buildsrc.ViewModel.LIVEDATA)

    //nav component
//    implementation(com.d208.buildsrc.NavComponent.NAVIGATION_COMPOSE)
    implementation(com.d208.buildsrc.NavComponent.NAVIGATION_FRAGMENT)
//    implementation(com.d208.buildsrc.NavComponent.NAVIGATION_DYNAMIC_FEATURES_FRAGMENT)
    implementation(com.d208.buildsrc.NavComponent.NAVIGATION_UI)
    //by viewModel
    implementation (com.d208.buildsrc.AndroidX.ACTIVITY)
    implementation(com.d208.buildsrc.AndroidX.FRAGMENT)

    // Retrofit
    implementation (com.d208.buildsrc.Retrofit.RETROFIT)
    implementation (com.d208.buildsrc.Retrofit.CONVERTER_GSON)
    implementation (com.d208.buildsrc.Retrofit.CONVERTER_JAXB)

    //okHttp
    implementation (com.d208.buildsrc.OkHttp.OKHTTP)
    implementation (com.d208.buildsrc.OkHttp.LOGGING_INTERCEPTOR)

    //coroutines
    implementation (com.d208.buildsrc.Coroutines.COROUTINES)

    //dagger-hilt
    implementation (com.d208.buildsrc.DaggerHilt.DAGGER_HILT)
    kapt (com.d208.buildsrc.DaggerHilt.DAGGER_HILT_COMPILER)
    kapt (com.d208.buildsrc.DaggerHilt.DAGGER_HILT_ANDROIDX_COMPILER)

    //kakao
    implementation(com.d208.buildsrc.KaKao.KAKAO_LOGIN)


}