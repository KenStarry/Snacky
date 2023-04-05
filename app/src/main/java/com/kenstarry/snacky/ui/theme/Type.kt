package com.kenstarry.snacky.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.kenstarry.snacky.R

//  custom font
val Poppins = FontFamily(
    Font(R.font.poppins_black, FontWeight.Black),
    Font(R.font.poppins_bold, FontWeight.Bold),
    Font(R.font.poppins_extra_bold, FontWeight.ExtraBold),
    Font(R.font.poppins_extra_light, FontWeight.ExtraLight),
    Font(R.font.poppins_medium, FontWeight.Medium),
    Font(R.font.poppins_regular, FontWeight.Normal),
    Font(R.font.poppins_semi_bold, FontWeight.SemiBold),
    Font(R.font.poppins_thin, FontWeight.Thin),
)

// Set of Material typography styles to start with
val Typography = Typography(

    bodySmall = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),

    bodyMedium = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),

    bodyLarge = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),

    titleSmall = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.SemiBold,
        fontSize = 22.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),

    titleMedium = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.Bold,
        fontSize = 26.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),

    titleLarge = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 28.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)