package com.syl.mycompose.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp


// 自定义字体
//val nunitoSansFamily = FontFamily(
//    Font(R.font.nunitosans_light, FontWeight.Light),
//    Font(R.font.nunitosans_semibold, FontWeight.SemiBold),
//    Font(R.font.nunitosans_bold, FontWeight.Bold)
//)


/**
 * 设置字体样式，
 * ⚠️最好不要设置颜色，颜色在使用字体时设置
 */
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    /*titleLarge = TextStyle(
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
    )*/
)