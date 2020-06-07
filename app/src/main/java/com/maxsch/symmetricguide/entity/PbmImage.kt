package com.maxsch.symmetricguide.entity

import android.graphics.Bitmap
import android.os.Environment
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException

fun List<Boolean>.toBitArray() =
    map {
        if (it)
            0
        else
            1
    }
        .toIntArray()

fun IntArray.toBWPixels() =
    map {
        if (it == 1)
            0xffffff
        else
            0
    }
        .toIntArray()

data class PbmImage(
    val filename: String,
    var commentaries: List<String>,
    var width: Int,
    var height: Int,
    var pixels: List<Boolean>
) {

    companion object {
        private const val PNG_EXTENSION = ".png"
        private const val PBM_EXTENSION = ".pbm"
        private const val PBM_FORMAT = "P1"

        fun String.toPbm(): PbmImage {
            val strokes = this.split("\n")
            val numOfComments = strokes.count {
                it.startsWith("#")
            }
            val width = strokes[numOfComments + 1].split(" ")[0].toInt()
            val height = strokes[numOfComments + 1].split(" ")[1].toInt()
            val comments = strokes.subList(1, numOfComments + 1)
            return PbmImage(
                "temp",
                comments,
                width,
                height,
                strokes[numOfComments + 2]
                    .map {
                        it.toString() == "0"
                    }
            )
        }
    }

    fun toBitmap(): Bitmap =
        Bitmap.createBitmap(pixels.toBitArray().toBWPixels(), width, height, Bitmap.Config.RGB_565)

    fun saveToPng() {
        val fileToSave = File(
            Environment.getExternalStorageDirectory().toString() + "/Download",
            filename + PNG_EXTENSION
        )
        try {
            FileOutputStream(fileToSave).use { out ->
                toBitmap().compress(
                    Bitmap.CompressFormat.PNG,
                    100,
                    out
                )
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    fun saveAsPbm() {
        val fileToSave = File(
            Environment.getExternalStorageDirectory().toString() + "/Download",
            filename + PBM_EXTENSION
        )

        try {
            FileOutputStream(fileToSave).use { out ->
                out.write(writePbmToString().toByteArray(Charsets.US_ASCII))
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun writePbmToString(): String {
        val pixelsArray = pixels.toBitArray()
        return "$PBM_FORMAT\n${commentaries.joinToString(
            separator = "\n",
            postfix = "\n",
            prefix = "# "
        )}$width $height\n${pixelsArray.joinToString(separator = "")}"
    }
}

fun isPbmFile(file: String): Boolean {
    FileInputStream(
        Environment.getExternalStorageDirectory().toString() + "/Download/" + "$file.pbm"
    ).use {
        return it.readBytes().toString(Charsets.US_ASCII).contains("P1\n#")
    }
}

fun getPbmFile(file: String): String =
    FileInputStream(
        Environment.getExternalStorageDirectory().toString() + "/Download/" + "$file.pbm"
    ).use {
        return it.readBytes().toString(Charsets.US_ASCII)
    }