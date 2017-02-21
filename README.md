# BaseUtils
一个项目的基类，包含BaseActivity、BaseFragment等 提高项目的效率和开发进度；
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
