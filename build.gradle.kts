import java.io.FileInputStream
import java.util.Properties

buildscript {
    repositories {
        google()
        mavenCentral()
        maven { setUrl("https://plugins.gradle.org/m2/") }
    }
    dependencies {
        classpath(Dep.androidGradlePlugin)
        classpath(Dep.googleServices)
        classpath(Dep.Kotlin.gradlePlugin)
        classpath(Dep.Dagger.hiltAndroidGradlePlugin)
        classpath(Dep.Apollo.gradlePlugin)
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}

ext["localProperties"] = loadPropertiesFromFile("local.properties")
fun Project.loadPropertiesFromFile(fileName: String): Properties {
    val file = file(fileName)
    if (!file.exists()) {
        logger.warn("$fileName file does not exist: creating it now - please check its values")
        copy {
            from(fileName)
            into(project.projectDir)
            rename { fileName }
        }
    }
    val res = Properties()
    val fileInputStream = FileInputStream(file)
    fileInputStream.use {
        res.load(it)
    }
    return res
}
