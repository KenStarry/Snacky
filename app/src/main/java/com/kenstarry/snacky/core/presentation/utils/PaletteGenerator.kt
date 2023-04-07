package com.kenstarry.snacky.core.presentation.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import androidx.palette.graphics.Palette
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.ImageResult
import coil.request.SuccessResult

object PaletteGenerator {

    suspend fun convertImageToBitmap(
        imageUrl: String,
        context: Context
    ): Bitmap? {

        val loader = ImageLoader(context)
        val request = ImageRequest.Builder(context)
            .data(imageUrl)
            .allowHardware(false)
            .build()

        val imageResult = loader.execute(request = request)

        return if (imageResult is SuccessResult)
            (imageResult.drawable as BitmapDrawable).bitmap
        else
            null
    }

    //  extract colors from Bitmap image
    fun extractColorsFromBitmap(bitmap: Bitmap): Map<String, String> {

        return mapOf(
            "vibrant" to parseColorSwatch(
                color = Palette.from(bitmap).generate().vibrantSwatch
            ),
            "darkVibrant" to parseColorSwatch(
                color = Palette.from(bitmap).generate().darkVibrantSwatch
            ),
            "onDarkVibrant" to parseBodyColor(
                color = Palette.from(bitmap).generate().darkVibrantSwatch?.bodyTextColor
            ),
            "lightVibrant" to parseColorSwatch(
                color = Palette.from(bitmap).generate().lightVibrantSwatch
            ),
            "dominantSwatch" to parseColorSwatch(
                color = Palette.from(bitmap).generate().dominantSwatch
            ),
            "mutedSwatch" to parseColorSwatch(
                color = Palette.from(bitmap).generate().mutedSwatch
            ),
            "lightMutedSwatch" to parseColorSwatch(
                color = Palette.from(bitmap).generate().lightMutedSwatch
            ),
            "darkMutedSwatch" to parseColorSwatch(
                color = Palette.from(bitmap).generate().darkMutedSwatch
            ),
        )
    }

    fun parseColorSwatch(color: Palette.Swatch?): String {
        return if (color != null) {
            val parsedColor = Integer.toHexString(color.rgb)
            "#$parsedColor"
        } else {
            "#000000"
        }
    }

    fun parseBodyColor(color: Int?): String {
        return if (color != null) {
            val parsedColor = Integer.toHexString(color)
            "#$parsedColor"
        } else {
            "#FFFFFF"
        }
    }
}































