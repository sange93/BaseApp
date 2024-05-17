// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    // Android Gradle plugin and Android Studio 对应关系: https://developer.android.google.cn/studio/releases/
//    id("com.android.application") version "8.4.0" apply false
//    id("com.android.library") version "8.4.0" apply false
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    // kotlin编译器与kotlin版本对应关系：https://developer.android.google.cn/jetpack/androidx/releases/compose-kotlin#kts
    alias(libs.plugins.jetbrains.kotlin.android) apply false
//    id("org.jetbrains.kotlin.android") version "1.9.22" apply false// kotlin插件版本
    // 声明 KSP 插件。请务必选择与项目的 Kotlin 版本一致的 KSP 版本。https://developer.android.google.cn/studio/build/migrate-to-ksp?hl=zh-cn
//    id("com.google.devtools.ksp") version "1.9.22-1.0.17" apply false
    alias(libs.plugins.ksp) apply false
}