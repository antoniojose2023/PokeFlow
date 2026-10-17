plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "br.com.devmobileaj.pokeflow"
    compileSdk {
        version = release(37)
    }

    defaultConfig {
        applicationId = "br.com.devmobileaj.pokeflow"
        minSdk = 24
        targetSdk = 37
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            optimization {
                enable = true
                packageScope = setOf("androidx.**", "kotlin.**", "kotlinx.**")
            }
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    buildFeatures {
        viewBinding = true
    }

}

dependencies {
    implementation(libs.androidx.activity.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.recyclerview)
    implementation(libs.material)

    //Testes unitários
    testImplementation(libs.junit)
    testImplementation("com.google.truth:truth:1.4.5")
    testImplementation("org.mockito:mockito-core:5.23.0")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.11.0")
    testImplementation("androidx.arch.core:core-testing:2.2.0")


    //Testes instrumentados
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation("androidx.test.espresso:espresso-intents:3.6.1")
    androidTestImplementation( "androidx.test.espresso:espresso-contrib:3.7.0")


    // ViewModel e LiveData (Essencial para arquitetura MVVM)
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.7")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.8.7")

    implementation("io.coil-kt.coil3:coil:3.0.4")

    // Biblioteca principal do Retrofit
    implementation("com.squareup.retrofit2:retrofit:3.0.0")

    // Conversor Gson para transformar JSON em objetos
    implementation("com.squareup.retrofit2:converter-gson:3.0.0")

    implementation("com.github.bumptech.glide:glide:5.0.9")
}