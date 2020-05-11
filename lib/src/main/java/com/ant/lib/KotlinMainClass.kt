package com.ant.lib

import sun.security.krb5.Confounder.bytes
import java.io.File
import java.io.FileInputStream


/**
 * copyright：
 * @author：anthui
 * Version：1.0
 * creation date： 2020/4/16.
 * describe：
 */
fun main() {

//    fileRead()

    var path = "lib/file/text.txt"
    var file: File? = checkFile(path) ?: return

    fileRedArray(file!!)


};

fun checkFile(path: String): File? {
    val file = File(path)
    if (file.exists() && file.isFile) {
        return file
    }



    return null
}


fun fileRedArray(file: File) {

    var inputStream = FileInputStream(file)
    var byte = bytes(4)

    var read = 0
    var leng: Float = 0f
    var max = inputStream.available()

    while (true) {
        read = inputStream.read(byte)

        if (read == -1) {
            break
        }

        leng += read

        var i: Int = (leng / max * 100).toInt()

        println("red== " + String(byte, 0, read) + " progress=== " + i)
    }
    inputStream.close()


}

fun fileRead() {

    var path = "lib/file/text.txt"
    var file: File? = checkFile(path) ?: return

    val inputStream = FileInputStream(file)


    var len = 0

    do {
        len = inputStream.read()
        print("message===  " + len.toChar() + "\n")
    } while (len != -1)


    inputStream.close()
    print("  读取完成=========================")


}

