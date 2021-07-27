package com.quexb.testbitmapscale

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.Menu
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.quexb.testbitmapscale.databinding.ActivityMainBinding
import com.quexb.testbitmapscale.image.*
import java.io.IOException

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bitmap = assetsToBitmap()
        bitmap?.let { e -> binding.imageView2.setImageBitmap(e) }

        binding.radioGroup.setOnCheckedChangeListener { group, _ ->
            val systemImageView = binding.imageView3
            val imageView = binding.imageView2
            val width = imageView.width
            val height = imageView.height

            val button = findViewById<RadioButton>(group.checkedRadioButtonId)
            val resizedBitmap = when (button.text) {
                "Fill" -> {
                    systemImageView.scaleType = ImageView.ScaleType.FIT_XY
                    bitmap?.boxFitFill(width, height)
                }
                "Contains" -> {
                    systemImageView.scaleType = ImageView.ScaleType.CENTER_INSIDE
                    bitmap?.boxFitContains(width, height)
                }
                "Cover" -> {
                    systemImageView.scaleType = ImageView.ScaleType.CENTER_CROP
                    bitmap?.boxFitCover(width, height)
                }
                "FitWith" -> {
                    Toast.makeText(this, "No relative system style: FitWith", Toast.LENGTH_SHORT).show()
                    bitmap?.boxFitFitWidth(width)
                }
                "FitHeight" -> {
                    Toast.makeText(this, "No relative system style: FitHeight", Toast.LENGTH_SHORT).show()
                    bitmap?.boxFitFitHeight(height)
                }
                else -> null
            }

            if (resizedBitmap != null) {
                imageView.setImageBitmap(resizedBitmap)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    private fun assetsToBitmap(): Bitmap? {
        return try {
            BitmapFactory.decodeResource(resources, R.mipmap.ic_sea)
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }
}
