package com.maxsch.symmetricguide.ui.encoding

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.ayush.imagesteganographylibrary.Text.AsyncTaskCallback.TextEncodingCallback
import com.ayush.imagesteganographylibrary.Text.ImageSteganography
import com.ayush.imagesteganographylibrary.Text.TextEncoding
import com.maxsch.symmetricguide.R
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_encoding.*
import moxy.MvpAppCompatActivity
import java.io.*
import java.util.*
import kotlin.random.Random

class EncodingFragment : MvpAppCompatActivity(R.layout.fragment_encoding), TextEncodingCallback {

    private val SELECT_PICTURE = 100
    private val TAG = "EncodingFragment class"
    private var filepath: Uri? = null
    private lateinit var textEncoding: TextEncoding
    private lateinit var imageSteganography: ImageSteganography
    private lateinit var originalImage: Bitmap
    private lateinit var srcImage: Bitmap
    private var disposable: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        btnRandomizeKey.setOnClickListener { randomizeKey() }
        btnChooseImage.setOnClickListener { imageChooser() }
        btnEncode.setOnClickListener {
            var textToEncode: String = imageMessage.text.toString()
            if (filepath != null) {
                if (imageMessage.text.toString().endsWith(".pbm")) {
                    try {
                        textToEncode = getPbmFile(imageMessage.text.toString())
                    } catch (error: FileNotFoundException) {
                        Toast.makeText(this, "Такого файла нет", Toast.LENGTH_SHORT).show()
                    }
                }
                imageSteganography = ImageSteganography(
                    textToEncode,
                    secretKey.text.toString(),
                    originalImage
                )
                textEncoding = TextEncoding(this, this)
                textEncoding.execute(imageSteganography)
            }
        }
        btnSaveImage.setOnClickListener {
            val imgToSave: Bitmap = srcImage
            disposable = Single.fromCallable {
                saveToInternalStorage(imgToSave)
            }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Toast.makeText(this, "Сохранено", Toast.LENGTH_LONG).show()
                }, {})
        }
        checkAndRequestPermissions()
    }

    override fun onDestroy() {
        disposable?.dispose()
        super.onDestroy()
    }


    fun imageChooser() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Выберите изображение"), SELECT_PICTURE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == SELECT_PICTURE && resultCode == MvpAppCompatActivity.RESULT_OK && data != null && data.data != null) {
            filepath = data.data!!
            try {
                originalImage =
                    MediaStore.Images.Media.getBitmap(contentResolver, filepath)
                srcImageEncoding.setImageBitmap(originalImage)
            } catch (e: IOException) {
                Log.d(TAG, "Error: " + e)
            }
        }
    }

    override fun onStartTextEncoding() {
        TODO("Not yet implemented")
    }

    override fun onCompleteTextEncoding(result: ImageSteganography?) {
        if (result != null && result.isEncoded) {
            srcImage = result.encoded_image
            Toast.makeText(this, "Зашифровано", Toast.LENGTH_LONG).show()
            srcImageEncoding.setImageBitmap(srcImage)
        }
    }

    private fun saveToInternalStorage(bitmapImage: Bitmap) {
        var fOut: OutputStream
        var file = File(
            Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOWNLOADS
            ), "Encoded" + ".PNG"
        )
        try {
            fOut = FileOutputStream(file)
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fOut)
            fOut.close()
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun checkAndRequestPermissions() {
        val permissionWriteStorage = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
        val ReadPermission = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.READ_EXTERNAL_STORAGE
        )
        val listPermissionsNeeded: MutableList<String> =
            ArrayList()
        if (ReadPermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.READ_EXTERNAL_STORAGE)
        }
        if (permissionWriteStorage != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(
                this,
                listPermissionsNeeded.toTypedArray(),
                1
            )
        }
    }

    fun randomizeKey() {
        secretKey.setText(Random.nextInt(0, 99999).toString())
    }
}

fun getPbmFile(file: String): String =
    FileInputStream(
        Environment.getExternalStorageDirectory().toString() + "/Download/" + "$file"
    ).use {
        return it.readBytes().toString(Charsets.US_ASCII)
    }