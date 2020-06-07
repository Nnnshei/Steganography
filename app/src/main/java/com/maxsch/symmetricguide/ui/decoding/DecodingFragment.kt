package com.maxsch.symmetricguide.ui.decoding

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.core.view.isVisible
import com.ayush.imagesteganographylibrary.Text.AsyncTaskCallback.TextDecodingCallback
import com.ayush.imagesteganographylibrary.Text.ImageSteganography
import com.ayush.imagesteganographylibrary.Text.TextDecoding
import com.maxsch.symmetricguide.R
import com.maxsch.symmetricguide.entity.PbmImage.Companion.toPbm
import kotlinx.android.synthetic.main.fragment_decoding.*
import moxy.MvpAppCompatActivity
import java.io.IOException

class DecodingFragment : MvpAppCompatActivity(R.layout.fragment_decoding), TextDecodingCallback {

    private val SELECT_PICTURE = 100
    private val TAG = "Decode class"
    private lateinit var filepath: Uri
    private lateinit var originalImage: Bitmap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        btnChooseImage.setOnClickListener { imageChooser() }
        btnDecode.setOnClickListener {
            if (filepath != null) {
                var imageSteganography =
                    ImageSteganography(secretKeyDecode.text.toString(), originalImage)
                var textDecoding = TextDecoding(this, this)
                textDecoding.execute(imageSteganography)
            }
        }
    }

    fun imageChooser() {
        var intent = Intent()
        intent.setType("image/*")
        intent.setAction(Intent.ACTION_GET_CONTENT)
        startActivityForResult(Intent.createChooser(intent, "Выберите изображение"), SELECT_PICTURE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == SELECT_PICTURE && resultCode == RESULT_OK && data != null && data.data != null) {
            filepath = data.data!!
            try {
                originalImage =
                    MediaStore.Images.Media.getBitmap(contentResolver, filepath)
                srcImageDecoding.setImageBitmap(originalImage)
            } catch (e: IOException) {
                Log.d(TAG, "Error: " + e)
            }
        }
    }

    override fun onStartTextEncoding() {
        TODO("Not yet implemented")
    }

    override fun onCompleteTextEncoding(result: ImageSteganography?) {
        if (result != null) {
            if (!result.isDecoded)
                Toast.makeText(this, "Не выбрано изображение", Toast.LENGTH_LONG).show()
            else {
                if (!result.isSecretKeyWrong) {
                    Toast.makeText(this, "Расшифровано", Toast.LENGTH_LONG).show()
                    if (result.message.contains("P1\n#")) {
                        val pbm = result.message.toPbm()
                        srcImageDecoding.isVisible = true
                        srcImageDecoding.setImageBitmap(pbm.toBitmap())
                    } else {
                        messageDecode.text = "" + result.message
                        messageDecode.isVisible = true
                    }
                } else {
                    Toast.makeText(this, "Неверный ключ", Toast.LENGTH_LONG).show()
                }
            }
        } else {
            Toast.makeText(this, "Выберите сначала изображение", Toast.LENGTH_LONG).show()
        }
    }
}