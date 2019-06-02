import org.slf4j.LoggerFactory
import com.KLib.HolaKLib
import com.google.protobuf.Message
import io.grpc.myproto.KServiceGrpc
import io.grpc.myproto.NewId
import io.grpc.myproto.User
//import io.grpc.myproto
import mu.KotlinLogging
import org.slf4j.MDC
import io.grpc.ManagedChannelBuilder
import java.util.concurrent.TimeUnit
const val sHttp = "localhost"
//const val iport=32768
const val iport = 8089
//val logger = LoggerFactory.getLogger("KClient")
val logger =KotlinLogging.logger {  }

inline fun <reified M : Message, reified B : Message.Builder> createMessage(body: B.() -> Unit): M {
     val f=B::class.java.getDeclaredConstructor()
    f.isAccessible=true
   return  f.newInstance().apply(body).build() as M
   // return   B::class.java.newInstance().apply(body).build() as M
}


fun main(){
    MDC.put("REQ","KClient logger")
    logger.info{"Start"}
    HolaKLib()
    testuser()
    testuser()
    logger.warn("FIN")
}

fun testuser(){

    val puser=createMessage<User,User.Builder> {
         id=220
        name="PEPE"
        email="ksks@yahoo.com"
    }
    logger.info {  "\nUser: ${puser.toString()} endlog" }
    addUpdateUserBlocking(puser)

}


typealias grpBStub = KServiceGrpc.KServiceBlockingStub


fun addEntityBlocking(eMessage: String, a: Any, afun: (Any, grpBStub) -> NewId): NewId {
    val channel = ManagedChannelBuilder.forAddress(sHttp, iport).usePlaintext(true).build()
    val blockingStub = KServiceGrpc.newBlockingStub(channel)
    //var newID=NewId.newBuilder()
    logger.info { "addEntytyBlocking gRPC"}
    lateinit var newID: NewId
    try {
        //newID=blockingStub.addWord(w).id
        newID = afun(a, blockingStub)
    } catch (e: Exception) {
        logger.error("error  $eMessage  ${e.message}")
        //newID.serror="client exception $eMessage  ${e.message}"
    } finally {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS)
        return newID
    }
}

fun addUpdateUserBlocking(user: User): NewId {
    logger.warn { "addUpdateUserBlocking" }
    return addEntityBlocking("--addUpdateUserBlocking--", user, { a: Any, grpBS: grpBStub -> grpBS.addUpdateUser(a as User) })

}
