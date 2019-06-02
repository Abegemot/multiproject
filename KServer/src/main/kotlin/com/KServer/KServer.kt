import org.slf4j.LoggerFactory
import  org.slf4j.Logger
import io.grpc.ServerBuilder
import io.grpc.myproto.*
import com.google.protobuf.Message
import io.grpc.stub.ClientCalls
import io.grpc.stub.StreamObserver
import mu.KotlinLogging
import org.slf4j.MDC

inline fun <reified M : Message, reified B : Message.Builder> createMessage(body: B.() -> Unit): M {
    return B::class.java.newInstance().apply(body).build() as M
}

/*suspend fun <ReqT, RespT> callUnaryRpc(call: (ReqT, StreamObserver<RespT>) -> Unit, request: ReqT): RespT {
    return ClientCalls.suspendingUnaryCall { responseObserver: StreamObserver<RespT> ->
        call(request, responseObserver)
    }
}*/

//trace,debug,info,warn,error,fatal
val logger=KotlinLogging.logger {  }



fun main(){
    //val logger = LoggerFactory.getLogger("KServer")

   // val logger = org.slf4j.LoggerFactory.getLogger({}.javaClass) as ch.qos.logback.classic.Logger
   // logger.level = ch.qos.logback.classic.Level.ALL
    MDC.put("REQ","patata")

    //L.underlyingLogger
  //  logger.debug{"ggff"}

    println("Hola KServer")
    logger.trace { "tttttrace" }
    logger.debug { "ddddebug" }
    logger.info{"iiiinfo"}
    logger.warn { "wwwwwwWarn" }
    logger.error { "eeerrrooorrr" }
    MDC.remove("REQ"  )
    logger.trace("Trace---->")

    logger.debug("Debug---->")
    logger.info("Info---->")
    logger.warn("Warn---->")
    logger.error("Error--->")

    val iport = 8089
    logger.info("HHHHHHHHHHHHHOOOOOSSSSTIAS")
    logger.error("Hola ")
    logger.warn("--->mabusseServer   GTZ I.  17/05/2019  rev 0 port :$iport")
    val server = ServerBuilder.forPort(iport).addService(KServiceGrpcImpl()).build()
    server.start()
    logger.error { "Hola mariposa" }
    logger.warn("Server started now")
    Runtime.getRuntime().addShutdownHook(Thread() { println("Ups, JVM shutdown") })
    server.awaitTermination()
    logger.warn("Server stopped")


}



class KServiceGrpcImpl : KServiceGrpc.KServiceImplBase() {
    val logger=KotlinLogging.logger {  }

/*    override fun addLesson(request: Lesson?, responseObserver: StreamObserver<NewId>?) {
        super.addLesson(request, responseObserver)
        createMessage<Lesson, Lesson.Builder> {
            level = 10
        }

    }*/


    override fun addUpdateUser(user: User?, responseObserver: StreamObserver<NewId>?) {
        logger.warn { "addUpdateUsersss" }
        val reply = NewId.newBuilder()
 //       if (user != null) //logger.error(" addUpdateUser " + execute { id = addupdateUserDB(user) })
/*            reply.id = try {
                DBAction.addUpdateUserDB(user)
                //addupdateUserDB(user)
            } catch (ex: Exception) {
                reply.serror = ex.message; logger.error("${ex.message}"); 666
            }
 */       responseObserver?.onNext(reply.build())
        responseObserver?.onCompleted()  //<--- si no si es blocking espera eternament
    }

    override fun getUsers(request: User?, responseObserver: StreamObserver<ListUsers>?) {
        super.getUsers(request, responseObserver)
    }


}