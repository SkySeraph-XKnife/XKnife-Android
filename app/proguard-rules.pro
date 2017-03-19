# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in D:\FreeInstall\android\android-sdk-windows/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

## 位于module下的proguard-rules.pro
#################################################
############### 主程序不能混淆的代码 ###############
#################################################
-dontwarn xxx.model.**
-keep class xxx.model.** { *; }


#################################################
################# 不优化泛型和反射 ################
#################################################
-keepattributes Signature


#################################################
############### 第三方库或者jar包 #################
#################################################
