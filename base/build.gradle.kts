plugins {
    id("com.android.library")
    id("kotlin-android")
    `maven-publish`
}

group = "com.github.sange93"
version = "1.1.2"

android {
    namespace = "com.sange.base"
    compileSdk = 34

    defaultConfig {
        minSdk = 21
//        targetSdk = 34

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
//        consumerProguardFiles "consumer-rules.pro"
        // 开启矢量图
        vectorDrawables {
            useSupportLibrary = true
        }
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
        // 启用compose
        compose = true
    }
    composeOptions {
        // kotlin编译器与kotlin版本对应关系：https://developer.android.google.cn/jetpack/androidx/releases/compose-kotlin#kts
        // 1.5.2对应kotlin v1.9.0
        // 1.5.3	1.9.10
        kotlinCompilerExtensionVersion = "1.5.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    val lifecycleVersion = "2.6.2"

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    //-----------以下为定制内容------------
    api("androidx.activity:activity-compose:1.8.2")
    // compose最新Bom版本：https://developer.android.google.cn/jetpack/compose/bom?hl=en
    // Bom内Lib详细版本：https://developer.android.google.cn/jetpack/compose/bom/bom-mapping?hl=en
    val composeBom = platform("androidx.compose:compose-bom:2023.10.01")
    api(composeBom)
    api("androidx.compose.ui:ui")
    api("androidx.compose.ui:ui-graphics")
    api("androidx.compose.ui:ui-tooling-preview")
    api("androidx.compose.material3:material3")
    androidTestApi(composeBom)
    androidTestApi("androidx.compose.ui:ui-test-junit4")
    debugApi("androidx.compose.ui:ui-tooling")
    debugApi("androidx.compose.ui:ui-test-manifest")

    api("androidx.compose.material3:material3-window-size-class")
    // Integration with observables
    api("androidx.compose.runtime:runtime-livedata")
    // Compose Material 图标
    api("androidx.compose.material:material-icons-core")
    api("androidx.compose.material:material-icons-extended")
    // Integration with ViewModels
    api("androidx.lifecycle:lifecycle-viewmodel-compose:$lifecycleVersion")
    // 用于Compose的生命周期运行时
    api("androidx.lifecycle:lifecycle-runtime-compose:$lifecycleVersion")
    // navigation
    api("androidx.navigation:navigation-compose:2.7.5")

    // Accompanist: https://google.github.io/accompanist/
    val accompanistVersion = "0.33.2-alpha"
    // 动态权限申请
    api("com.google.accompanist:accompanist-permissions:$accompanistVersion")

    // 启动画面
    api("androidx.core:core-splashscreen:1.0.0")


    implementation(files("libs/sun.misc.BASE64Decoder.jar"))
    // 约束布局
    api("androidx.constraintlayout:constraintlayout:2.1.4")
    // 添加Jetpack中架构组件的依赖，注意viewmodel要添加viewmodel-ktx的依赖
    api("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")
    api("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
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
                version = "1.1.2"
                from(components["release"])
            }
        }
    }
}