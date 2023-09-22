plugins {
    id ("org.jetbrains.kotlin.jvm")
    id ("kotlin-kapt")
}

dependencies {

    // dager hilt
    implementation(com.d208.buildsrc.DaggerHilt.DAGGER_HILT_JAVAX)
}