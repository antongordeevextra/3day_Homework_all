package com.example.a3dayhomework

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import com.example.a3dayhomework.databinding.ActivitySecondBinding
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding
    private lateinit var text: String
    private lateinit var toMainThread: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        toMainThread = Handler(Looper.getMainLooper())

        binding = ActivitySecondBinding.inflate(layoutInflater).also { setContentView(it.root) }

        binding.editText.setOnClickListener {
            text = binding.editText.text.toString()
            getImage(text)
        }
    }

    private fun getImage(link: String) {
        Thread(Runnable {
            try {
                val imageUrl = URL(link)
                val connection = imageUrl.openConnection() as HttpURLConnection
                connection.doInput = true
                connection.connect()

                val inputStream = connection.inputStream
                val bitmap = BitmapFactory.decodeStream(inputStream)
                bitmap?.let {
                    toMainThread.post { setImage(bitmap) }
                }

            } catch (error: Exception) {
                toMainThread.post {
                    Toast.makeText(this, "Error: $error", Toast.LENGTH_SHORT).show()
                }
            }
        }).start()
    }

    private fun setImage(imageBitMap: Bitmap) {
        binding.imageView.setImageBitmap(imageBitMap)
    }
}