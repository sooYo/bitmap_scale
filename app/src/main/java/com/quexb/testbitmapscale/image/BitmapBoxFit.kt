package com.quexb.testbitmapscale.image

import android.graphics.Bitmap
import android.graphics.Matrix
import android.util.Log
import kotlin.math.roundToInt

fun Bitmap.boxFitFill(width: Int, height: Int): Bitmap {
    return try {
        Bitmap.createScaledBitmap(this, width, height, true)
    } catch (e: Exception) {
        Log.e("BitmapUtils", "boxFitFill: $e")
        this
    }
}

fun Bitmap.boxFitContains(width: Int, height: Int): Bitmap {
    return boxFit(width, height, true)
}

fun Bitmap.boxFitCover(width: Int, height: Int): Bitmap {
    return boxFit(width, height, false)
}

fun Bitmap.boxFitFitWidth(width: Int): Bitmap {
    return try {
        val ratio = this.width.toFloat() / this.height.toFloat()
        val height = (width / ratio).roundToInt()

        Bitmap.createScaledBitmap(
            this,
            width,
            height,
            true
        )
    } catch (e: Exception) {
        Log.e("BitmapUtils", "boxFitFitWidth: $e")
        this
    }
}

fun Bitmap.boxFitFitHeight(height: Int): Bitmap {
    return try {
        val ratio = this.height.toFloat() / this.width.toFloat()
        val width = (height / ratio).roundToInt()

        Bitmap.createScaledBitmap(
            this,
            width,
            height,
            true
        )
    } catch (e: Exception) {
        Log.e("BitmapUtils", "boxFitFitHeight: $e")
        this
    }
}

private fun Bitmap.boxFit(width: Int, height: Int, fitInside: Boolean): Bitmap {
    return try {
        val scaledWidth = width.toFloat() / this.width
        val scaledHeight = height.toFloat() / this.height
        val scaleRatio = when (fitInside) {
            true -> scaledHeight.coerceAtMost(scaledWidth)
            else -> scaledHeight.coerceAtLeast(scaledWidth)
        }

        val matrix = Matrix().apply { postScale(scaleRatio, scaleRatio) }
        return Bitmap.createBitmap(
            this,
            0,
            0,
            this.width,
            this.height,
            matrix,
            true
        )
    } catch (e: Exception) {
        Log.e("BitmapUtils", "boxFit: $e")
        this
    }
}

