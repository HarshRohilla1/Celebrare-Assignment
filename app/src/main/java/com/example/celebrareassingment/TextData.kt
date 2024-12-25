package com.example.celebrareassingment

import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration

data class TextData(
    var content: String = "",
    var offsetX: Float = 0f,
    var offsetY: Float = 0f,
    var fontSize: Float = 16f,
    var isBold: Boolean = false,
    var isItalic: Boolean = false,
    val textDecoration: TextDecoration = TextDecoration.None,
    var fontFamily: FontFamily = FontFamily.Default,
    var textAlign: TextAlign = TextAlign.Start
)
