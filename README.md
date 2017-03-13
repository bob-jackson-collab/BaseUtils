# BaseUtils
一个项目的基类，包含BaseActivity、BaseFragment等 提高项目的效率和开发进度；
最近新添加一些对Retrofit 以及 Rxjava 的封装 

此外对Rxjava一些操作符进行详细解释 以及 展示一些demo 供大家参考 （本人也是小白一枚哦)



Step 1. Add the JitPack repository to your build file
gradle
maven
sbt
leiningen
Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
Step 2. Add the dependency

	dependencies {
	        compile 'com.github.yangsongsong:BaseUtils:V1.0'
	}
