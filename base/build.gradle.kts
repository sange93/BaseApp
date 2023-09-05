plugins {
    id("com.android.library")
    id("kotlin-android")
    `maven-publish`
}

group = "com.github.sange93"
version = "1.0.20"

android {
    namespace = "com.sange.base"
    compileSdk = 34

    defaultConfig {
        minSdk = 21
//        targetSdk = 34

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
//        consumerProguardFiles "consumer-rules.pro"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
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
        // 启用ViewBinding
        viewBinding = true
    }
}

dependencies {
//    implementation "org.jetbrains.kotlin:kotlin-stdlib:$kotlin_version"
//    implementation "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlin_version"
//    implementation "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlin_version"
    implementation("androidx.core:core-ktx:1.10.1")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.8.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    //-----------以下为定制内容------------
    implementation(files("libs/sun.misc.BASE64Decoder.jar"))
    // 约束布局
    api("androidx.constraintlayout:constraintlayout:2.1.4")
    // 添加Jetpack中架构组件的依赖，注意viewmodel要添加viewmodel-ktx的依赖
    api("androidx.lifecycle:lifecycle-livedata-ktx:2.6.1")
    api("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")
    // Kotlin Coroutines 协程
    api("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.1")
    // ViewPager2
    api("androidx.viewpager2:viewpager2:1.0.0")
    // 强大而灵活的RecyclerView Adapter
    api("com.github.CymChad:BaseRecyclerViewAdapterHelper:3.0.6")
    // PermissionX 权限请求库
    api("com.guolindev.permissionx:permissionx:1.7.1")
    // Android 版本更新 https://github.com/AlexLiuSheng/CheckVersionLib
    // 原版已不再维护，未适配Android 12 会报java.lang.IllegalArgumentException错误
//    api("com.github.AlexLiuSheng:CheckVersionLib:2.4.1_androidx")
    api("com.github.jikun2008:CheckVersionLib:2.4.5")
}

afterEvaluate {
    publishing {
        publications {
            // Creates a Maven publication called "release".
            create<MavenPublication>("release"){
                groupId = "com.github.sange93"
                artifactId = "BaseApp"
                version = "1.0.20"
                from(components["release"])
            }
        }
    }
}

/*afterEvaluate {
    publishing {
        publications {
            // Creates a Maven publication called "release".
            release(MavenPublication) {
                from components.release
                groupId = "com.github.sange93"
                artifactId = "BaseApp"
                version = "1.0.20"
            }
        }
    }
}*/
// Because the components are created only during the afterEvaluate phase, you must
// configure your publications using the afterEvaluate() lifecycle method.
/*
afterEvaluate {
    publishing {
        publications {
            // Creates a Maven publication called "release".
            release(MavenPublication) {
                // Applies the component for the release build variant.
                from components.release

                        // You can then customize attributes of the publication as shown below.
                        groupId = 'com.example.MyLibrary'
                artifactId = 'final'
                version = '1.0'
            }
            // Creates a Maven publication called “debug”.
            debug(MavenPublication) {
                // Applies the component for the debug build variant.
                from components.debug

                        groupId = 'com.example.MyLibrary'
                artifactId = 'final-debug'
                version = '1.0'
            }
        }
    }*/
