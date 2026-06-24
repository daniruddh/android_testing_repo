import java.util.Properties
import java.io.FileInputStream

plugins {
    id("com.android.application")
    kotlin("android")
}


android {
    namespace = "org.opstree.app"
    compileSdk = 35 // Update to your version
    
    defaultConfig {
        applicationId = "com.trueworkpoint.app"
        minSdk = 32 // Update to your version
        targetSdk = 35 // Update to your version
        versionCode = 42
        versionName = "1.0.1"
    }
	signingConfigs {
    	create("release") {
        // Read keystore file path from environment variable or use default
        val keystoreFile = System.getenv("KEYSTORE_FILE")
        storeFile = file(keystoreFile)
        
        // Read signing credentials from environment variables
        storePassword = System.getenv("KEYSTORE_PASSWORD")
        keyAlias = System.getenv("KEY_ALIAS") 
        keyPassword = System.getenv("KEY_PASSWORD") 
    	}
	}
    
    buildTypes {
        release {
            signingConfig = signingConfigs.getByName("release")
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
        isCoreLibraryDesugaringEnabled = true
    }
    
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:2.0.4")
    implementation("org.apache.commons:commons-text:1.11.0")
    implementation(project(":utilities"))
}
