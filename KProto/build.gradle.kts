import com.google.protobuf.gradle.*  //sense aixo tot peta

plugins{
   //idea
   //base
    `java`
  // `java-gradle-plugin`
   id("com.google.protobuf").version(Deps.protobufPluginVer)
}


dependencies{
   compileOnly("javax.annotation:javax.annotation-api:1.2")
   implementation(Deps.kotlinx_coroutines_core)
   //implementation("com.github.marcoferrer.krotoplus:kroto-plus-coroutines:0.3.0")
   implementation(Deps.grpc_protobuf)
   implementation(Deps.protobuf_java)
   implementation(Deps.grpc_netty)
   implementation(Deps.grpc_stub)
   implementation(Deps.kotlin_gradle_plugin)
}
sourceSets {
    main {
        java {
            srcDirs( "build/generated/source/proto/main/grpc")
            srcDirs( "build/generated/source/proto/main/java")
        }
    }
}

protobuf{
      //generatedFilesBaseDir = "$projectDir/src/main/java"
      // generatedFilesBaseDir = "$projectDir/out/src"
      protoc{  artifact=Deps.protocc   }
      plugins{
               id("grpc"){ artifact=Deps.protoc_gen_grpc_java }
         }
       generateProtoTasks{
             ofSourceSet("main").forEach{
                     it.plugins{
                      id("grpc") // {outputSubDir = "java"}
                   }
            }
        }

   // generatedFilesBaseDir = "$projectDir/out"

}

