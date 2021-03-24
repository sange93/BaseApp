# BaseApp（基础组件）
[![](https://jitpack.io/v/sange93/BaseApp.svg)](https://jitpack.io/#sange93/BaseApp)  
Android App 快速开发框架  
架构：Jetpack MVVM  
组件化架构图：  
![组件化架构图](image/组件化架构图.jpg)
base组件[使用说明](base/README.md)
## 引入依赖（gradle）
Step 1. Add the JitPack repository to your build file  
Add it in your root build.gradle at the end of repositories:
```gradle
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
Step 2. Add the dependency
```gradle
dependencies {
	        implementation 'com.github.sange93:BaseApp:1.0.1'
	}
```