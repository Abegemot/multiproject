

plugins{
    //java
    `java-library`
    kotlin("jvm").version(Deps.kotlinVer)
    //application
}

dependencies{
    implementation(kotlin("stdlib"))
}

