package com.example.a3dayhomework

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.a3dayhomework.databinding.ActivityGlideBinding
import com.example.a3dayhomework.databinding.ActivitySecondBinding

class GlideActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGlideBinding
    private lateinit var text: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_glide)

        binding = ActivityGlideBinding.inflate(layoutInflater).also { setContentView(it.root) }

        binding.editText.setOnClickListener {
            text = binding.editText.text.toString()
            showImage(text)
        }
    }

    private fun showImage(link: String) {
        Glide.with(this)
            .load(link)
            .error(R.drawable.ic_launcher_foreground)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    Toast.makeText(this@GlideActivity, "Error: $e", Toast.LENGTH_SHORT).show()
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }
            })
            .into(binding.imageView)
    }
}