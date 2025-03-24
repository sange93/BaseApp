plugins {
    id("com.android.library")
    id("kotlin-android")
    `maven-publish`
}

group = "com.github.sange93"
version = "1.2.2"

android {
    namespace = "com.sange.base"
    compileSdk = 34

    defaultConfig {
        minSdk = 21

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        kotlinCompilerExtensionVersion = "1.5.13"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    //-----------以下为定制内容------------
    api(libs.androidx.activity.compose)
    // compose最新Bom版本：https://developer.android.google.cn/jetpack/compose/bom?hl=en
    // Bom内Lib详细版本：https://developer.android.google.cn/jetpack/compose/bom/bom-mapping?hl=en
    val composeBom = platform("androidx.compose:compose-bom:2024.12.01")
    api(composeBom)
    api(libs.androidx.ui)
    api(libs.androidx.ui.graphics)
    api(libs.androidx.ui.tooling.preview)
    api(libs.androidx.material3)
    api(libs.androidx.material)
    androidTestApi(composeBom)
    androidTestApi(libs.androidx.ui.test.junit4)
    debugApi(libs.androidx.ui.tooling)
    debugApi(libs.androidx.ui.test.manifest)

    api(libs.androidx.compose.material3.window.size.class4)
    // Integration with observables
    api(libs.androidx.runtime.livedata)
    // Compose Material 图标
    api(libs.androidx.material.icons.core)
    api(libs.androidx.material.icons.extended)
    // Integration with ViewModels
    api(libs.androidx.lifecycle.viewmodel.compose)
    // 用于Compose的生命周期运行时
    api(libs.androidx.lifecycle.runtime.compose)
    // navigation
    api(libs.androidx.navigation.compose)

    // Accompanist: https://google.github.io/accompanist/
    // 动态权限申请
    api(libs.accompanist.permissions)
//    // 权限请求框架 适配Android 14 https://github.com/getActivity/XXPermissions
//    api 'com.github.getActivity:XXPermissions:18.5'
    api(libs.xxPermissions)
    // PermissionX 权限请求库
//    api("com.guolindev.permissionx:permissionx:1.7.1")

    // 启动画面
    api(libs.androidx.core.splashscreen)


    implementation(files("libs/sun.misc.BASE64Decoder.jar"))
    // 约束布局
    api(libs.androidx.constraintlayout)
    // 添加Jetpack中架构组件的依赖，注意viewmodel要添加viewmodel-ktx的依赖
    api(libs.androidx.lifecycle.livedata.ktx)
    api(libs.androidx.lifecycle.viewmodel.ktx)
    // Kotlin Coroutines 协程
    api(libs.coroutines)
    // ViewPager2
    api(libs.androidx.viewpager2)
    // 强大而灵活的RecyclerView Adapter
    api(libs.baserecyclerviewadapterhelper)
    // Android 版本更新 https://github.com/AlexLiuSheng/CheckVersionLib
    // 原版已不再维护，未适配Android 12 会报java.lang.IllegalArgumentException错误
//    api("com.github.AlexLiuSheng:CheckVersionLib:2.4.1_androidx")
    // Android 版本更新 https://github.com/jikun2008/CheckVersionLib/
    api(libs.checkversionlib)
}

afterEvaluate {
    publishing {
        publications {
            // Creates a Maven publication called "release".
            create<MavenPublication>("release"){
                groupId = "com.github.sange93"
                artifactId = "BaseApp"
                version = "1.2.2"
                from(components["release"])
            }
        }
    }
}