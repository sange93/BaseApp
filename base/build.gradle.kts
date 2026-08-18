import com.android.build.api.dsl.LibraryExtension

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.compose)
    `maven-publish`
}

//group = "com.github.sange93"
//version = "1.2.9"

configure<LibraryExtension> {
    namespace = "com.sange.base"
    compileSdk = 37

    defaultConfig {
        minSdk = 23

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
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
    buildFeatures{
        // 启用ViewBinding
        viewBinding = true
        // 启用compose
        compose = true
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    // 主动指明需要发布的构建变体为release
    publishing {
        singleVariant("release") {
            withSourcesJar() // 可选，自动生成源码jar包，发布后方便使用者查看源码
            withJavadocJar() // 可选，生成javadoc注释包
        }
    }
}

// Kotlin 编译配置
kotlin {
    jvmToolchain(21)
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
    val composeBom = platform("androidx.compose:compose-bom:2026.08.00")
    api(composeBom)
    api(libs.androidx.ui)
    api(libs.androidx.ui.graphics)
    api(libs.androidx.ui.tooling.preview)
    api(libs.androidx.material3)
    api(libs.androidx.material)
    androidTestImplementation(composeBom)
    androidTestImplementation(libs.androidx.ui.test.junit4)
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
    api(libs.xxPermissions)

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

publishing {
    publications {
        // Creates a Maven publication called "release".
        create<MavenPublication>("release"){
            groupId = "com.github.sange93"
            artifactId = "BaseApp"
            version = "1.2.10"
//                from(project.components["release"])
            afterEvaluate {
                from(components["release"])
            }
        }
    }
}