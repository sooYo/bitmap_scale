package com.quexb.testbitmapscale.image

import android.graphics.Bitmap
import android.graphics.Matrix
import kotlin.math.roundToInt

fun Bitmap.boxFitFill(width: Int, height: Int): Bitmap {
    return Bitmap.createScaledBitmap(this, width, height, true)
}

fun Bitmap.boxFitContains(width: Int, height: Int): Bitmap {
    return boxFit(width, height, true)
}

fun Bitmap.boxFitCover(width: Int, height: Int): Bitmap {
    return boxFit(width, height, false)
}

fun Bitmap.boxFitFitWidth(width: Int): Bitmap {
    val ratio: Float = this.width.toFloat() / this.height.toFloat()
    val height: Int = (width / ratio).roundToInt()

    return Bitmap.createScaledBitmap(
        this,
        width,
        height,
        true
    )
}

fun Bitmap.boxFitFitHeight(height: Int): Bitmap {
    val ratio: Float = this.height.toFloat() / this.width.toFloat()
    val width: Int = (height / ratio).roundToInt()

    return Bitmap.createScaledBitmap(
        this,
        width,
        height,
        true
    )
}

private fun Bitmap.boxFit(width: Int, height: Int, fitInside: Boolean): Bitmap {
    val scaledWidth = width.toFloat() / this.width
    val scaledHeight = height.toFloat() / this.height
    val scaleRatio = when (fitInside) {
        true -> scaledHeight.coerceAtMost(scaledWidth)
        else -> scaledHeight.coerceAtLeast(scaledWidth)
    }

    val matrix = Matrix().also { e -> e.postScale(scaleRatio, scaleRatio) }
    return Bitmap.createBitmap(
        this,
        0,
        0,
        this.width,
        this.height,
        matrix,
        true
    )
}
