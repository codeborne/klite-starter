import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.io.ByteArrayOutputStream

plugins {
  kotlin("jvm") version "2.0.20"
}

repositories {
  mavenCentral()
  maven { url = uri("https://jitpack.io") }
}

dependencies {
  val kliteVersion = "1.6.9"
  implementation("com.github.codeborne.klite:klite-server:$kliteVersion")
  implementation("com.github.codeborne.klite:klite-json:$kliteVersion")
  implementation("com.github.codeborne.klite:klite-i18n:$kliteVersion")
  implementation("com.github.codeborne.klite:klite-jdbc:$kliteVersion")
  implementation("com.github.codeborne.klite:klite-slf4j:$kliteVersion")
  implementation("org.postgresql:postgresql:42.7.3")

  testImplementation("com.github.codeborne.klite:klite-jdbc-test:$kliteVersion")
  testImplementation("org.junit.jupiter:junit-jupiter:5.10.0")
  testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.10.0")
  testImplementation("ch.tutteli.atrium:atrium-fluent:1.2.0")
  testImplementation("io.mockk:mockk:1.13.13")
  testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.3")
}

sourceSets {
  main {
    kotlin.setSrcDirs(listOf("src"))
    resources.setSrcDirs(listOf("src")).exclude("**/*.kt")
  }
  test {
    kotlin.setSrcDirs(listOf("test"))
    resources.setSrcDirs(listOf("test")).exclude("**/*.kt")
  }
}

tasks.test {
  workingDir(rootDir)
  useJUnitPlatform()
  // enable JUnitAssertionImprover from klite.jdbc-test
  jvmArgs("-DENV=test", "-Djunit.jupiter.extensions.autodetection.enabled=true", "--add-opens=java.base/java.lang=ALL-UNNAMED", "-XX:-OmitStackTraceInFastThrow")
}

tasks.withType<KotlinCompile> {
  kotlinOptions.jvmTarget = "21"
  if (System.getProperty("user.name") != "root") finalizedBy("types.ts")
}

tasks.register<JavaExec>("types.ts") {
  dependsOn("classes")
  mainClass.set("klite.json.TSGenerator")
  classpath = sourceSets.main.get().runtimeClasspath
  args("${project.buildDir}/classes/kotlin/main")
  standardOutput = ByteArrayOutputStream()
  doLast {
    project.file(".gradle/types-${project.name}.ts").writeText("""
      export type Id<T extends Entity<T>> = string & {_of?: T}
      export type Entity<T extends Entity<T>> = {id: Id<T>}
      """.trimIndent() + "\n\n" + standardOutput.toString().replace("TSID", "Id"))
  }
}
