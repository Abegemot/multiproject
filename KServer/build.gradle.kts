

plugins{
    kotlin("jvm").version(Deps.kotlinVer)
    application
}

dependencies{
    implementation(Deps.kotlin_stdlib_jdk8)
    implementation(Deps.grpc_netty)
    implementation(Deps.grpc_stub)
    implementation(Deps.grpc_protobuf)
    implementation(Deps.logback_classic)
    implementation(Deps.kotlin_logging)
    implementation(project(":KProto"))
}

application{
    mainClassName="KServerKt"
}