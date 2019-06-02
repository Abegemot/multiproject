
plugins {
    kotlin("jvm") version Deps.kotlinVer

}

version = "unspecified"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
 //   implementation(Deps.logback_classic)
    implementation(Deps.kotlin_logging)
    implementation(Deps.grpc_netty)
    implementation(Deps.grpc_stub)
    implementation(Deps.grpc_protobuf)
    implementation(Deps.logback_classic)
//    runtime("org.slf4j:slf4j-api:1.7.25")
//    runtime("ch.qos.logback:logback-core:1.2.3")
    implementation(project(":KLib"))
    implementation(project(":KProto"))
}

 