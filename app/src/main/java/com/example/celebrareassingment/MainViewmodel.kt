package com.example.celebrareassingment

import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainViewModel:ViewModel() {

    private val _textData = MutableStateFlow(TextData())
    val textData: StateFlow<TextData> = _textData

    private var isUnderline = false

    private val history = mutableListOf<TextData>()
    private var currentIndex = -1
    private var currentAlignmentIndex = 0

    fun cycleTextAlign(alignments: List<Pair<String, TextAlign>>) {
        saveToHistory()
        currentAlignmentIndex = (currentAlignmentIndex + 1) % alignments.size
        val selectedAlignment = alignments[currentAlignmentIndex]
        _textData.value = _textData.value.copy(textAlign = selectedAlignment.second)
    }

    fun updateTextContent(newContent: String) {
        saveToHistory()
        _textData.value = _textData.value.copy(content = newContent)
    }

    fun increaseSize()
    {
        saveToHistory()
        _textData.value = _textData.value.copy(fontSize = _textData.value.fontSize + 2f)
    }
    fun deacreaseSize()
    {
        saveToHistory()
        _textData.value = _textData.value.copy(fontSize = maxOf(10f, _textData.value.fontSize - 2f))
    }

    fun bold() {
        saveToHistory()
        _textData.value = _textData.value.copy(isBold = !_textData.value.isBold)
    }

    fun toggleUnderline() {
        saveToHistory()
        isUnderline = !isUnderline
        _textData.value = _textData.value.copy(
            textDecoration = if (isUnderline) TextDecoration.Underline else TextDecoration.None
        )
    }

    fun italic() {
        saveToHistory()
        _textData.value = _textData.value.copy(isItalic = !_textData.value.isItalic)
    }

    fun moveText(deltaX: Float, deltaY: Float) {
        saveToHistory()
        _textData.value = _textData.value.copy(
            offsetX = _textData.value.offsetX + deltaX,
            offsetY = _textData.value.offsetY + deltaY
        )
    }


    fun redo()
    {
        if (currentIndex < history.size - 1) {
            currentIndex++
            _textData.value = history[currentIndex]
        }

    }

    fun undo(){
        if (currentIndex > 0) {
            currentIndex--
            _textData.value = history[currentIndex]
        }
    }

    fun updateFontFamily(fontFamily: FontFamily) {
        saveToHistory()
        _textData.value = _textData.value.copy(fontFamily = fontFamily)
    }

    private fun saveToHistory() {
        if (currentIndex < history.size - 1) {
            history.subList(currentIndex + 1, history.size).clear()
        }
        history.add(_textData.value.copy())
        currentIndex++
    }

}