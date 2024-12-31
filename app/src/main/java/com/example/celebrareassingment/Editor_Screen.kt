package com.example.celebrareassingment

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import kotlin.math.roundToInt

@Composable
fun Editor_Screen(modifier: Modifier, viewModel: MainViewModel) {

    val textData by viewModel.textData.collectAsState()
    var inputText by remember { mutableStateOf("") }
    val fontOptions = listOf(
        "Default" to FontFamily.Default,
        "Serif" to FontFamily.Serif,
        "Sans-Serif" to FontFamily.SansSerif,
        "Monospace" to FontFamily.Monospace
    )

    var expanded by remember { mutableStateOf(false) }
    var selectedFont by remember { mutableStateOf(fontOptions.first()) }
    val alignOptions = listOf(
        "Left" to TextAlign.Start,
        "Center" to TextAlign.Center,
        "Right" to TextAlign.End
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 50.dp)
    )
    {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Top
        )
        {

            Column() {
                IconButton(onClick = { viewModel.undo() })
                {
                    Icon(
                        painter = painterResource(id = R.drawable.undo),
                        contentDescription = "undo"
                    )
                }
                Text(modifier = Modifier.align(Alignment.CenterHorizontally), text = "Undo")
            }

            Column() {
                IconButton(onClick = { viewModel.redo() })
                {
                    Icon(
                        painter = painterResource(R.drawable.redo),
                        contentDescription = "redo"
                    )
                }
                Text(modifier = Modifier.align(Alignment.CenterHorizontally), text = "Redo")
            }
        }
        Box(
            modifier = Modifier
                .width(500.dp)
                .height(500.dp)
                .padding(10.dp)
                .background(Color.LightGray),
            contentAlignment = Alignment.Center
        )
        {
            Text(
                text = textData.content,
                fontSize = textData.fontSize.sp,
                fontWeight = if (textData.isBold) FontWeight.Bold else FontWeight.Normal,
                fontStyle = if (textData.isItalic) FontStyle.Italic else FontStyle.Normal,
                fontFamily = textData.fontFamily,
                textAlign = textData.textAlign,
                textDecoration = textData.textDecoration,
                modifier = Modifier
                    .fillMaxWidth()
                    .offset {
                        IntOffset(
                            textData.offsetX.roundToInt(),
                            textData.offsetY.roundToInt()
                        )
                    }
                    .pointerInput(Unit) {
                        detectDragGestures { _, dragAmount ->
                            viewModel.moveText(dragAmount.x, dragAmount.y)
                        }
                    }
            )

        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Bottom
        )
        {

            Box(modifier = Modifier.width(200.dp), contentAlignment = Alignment.TopStart) {
                Button(onClick = { expanded = true }) {
                    Text("Font: ${selectedFont.first}")
                }
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    fontOptions.forEach { (name, fontFamily) ->
                        DropdownMenuItem(
                            onClick = {
                                expanded = false
                                selectedFont = name to fontFamily
                                viewModel.updateFontFamily(fontFamily)
                            },
                            text = { Text(text = name) }
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.width(5.dp))

            Button(
                modifier = Modifier
                    .width(60.dp)
                    .height(35.dp),
                onClick = {viewModel.deacreaseSize()},
                shape = RoundedCornerShape(50.dp, 0.dp, 0.dp, 50.dp),
                colors = ButtonDefaults.buttonColors(Color.White),
                border = ButtonDefaults.outlinedButtonBorder
            ) {

                Image(

                    painter = painterResource(id = R.drawable.subtract),
                    contentDescription = "decrease font size"
                )

            }
            Button(
                modifier = Modifier
                    .width(70.dp)
                    .height(35.dp),
                onClick = {},
                shape = RoundedCornerShape(0.dp, 0.dp, 0.dp, 0.dp),
                colors = ButtonDefaults.buttonColors(Color.White),
                border = ButtonDefaults.outlinedButtonBorder
            ) {
                Text("${textData.fontSize.toInt()}")
            }

            Button(
                modifier = Modifier
                    .width(60.dp)
                    .height(35.dp),
                onClick = {viewModel.increaseSize()},
                shape = RoundedCornerShape(0.dp, 50.dp, 50.dp, 0.dp),
                colors = ButtonDefaults.buttonColors(Color.White),
                border = ButtonDefaults.outlinedButtonBorder
            ) {

                Image(

                    painter = painterResource(R.drawable.add),
                    contentDescription = "decrease font size"
                )

            }
        }
        Spacer(modifier = Modifier.height(25.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.width(25.dp))
            Button(
                modifier = Modifier
                    .width(70.dp)
                    .height(35.dp),
                onClick = {viewModel.bold()},
                shape = RoundedCornerShape(0.dp, 0.dp, 0.dp, 0.dp),
                colors = ButtonDefaults.buttonColors(Color.White),
                border = ButtonDefaults.outlinedButtonBorder
            ) {
                Image(

                    painter = painterResource(R.drawable.bold),
                    contentDescription = "bold"
                )

            }
            Button(
                modifier = Modifier
                    .width(70.dp)
                    .height(35.dp),
                onClick = {viewModel.italic()},
                shape = RoundedCornerShape(0.dp, 0.dp, 0.dp, 0.dp),
                colors = ButtonDefaults.buttonColors(Color.White),
                border = ButtonDefaults.outlinedButtonBorder
            ) {
                Image(

                    painter = painterResource(R.drawable.italic),
                    contentDescription = "italic"
                )

            }
            Button(
                modifier = Modifier
                    .width(70.dp)
                    .height(35.dp),
                onClick = {viewModel.toggleUnderline()},
                shape = RoundedCornerShape(0.dp, 0.dp, 0.dp, 0.dp),
                colors = ButtonDefaults.buttonColors(Color.White),
                border = ButtonDefaults.outlinedButtonBorder
            ) {
                Image(

                    painter = painterResource(R.drawable.underline),
                    contentDescription = "underline"
                )

            }
            Button(
                modifier = Modifier
                    .width(70.dp)
                    .height(35.dp),
                onClick = {viewModel.cycleTextAlign(alignOptions)},
                shape = RoundedCornerShape(0.dp, 0.dp, 0.dp, 0.dp),
                colors = ButtonDefaults.buttonColors(Color.White),
                border = ButtonDefaults.outlinedButtonBorder
            ) {
                Image(

                    painter = painterResource(R.drawable.centrealignment),
                    contentDescription = "alignment"
                )

            }
        }

        Spacer(modifier = Modifier.height(25.dp))

        Row {
            TextField(
                value = inputText,
                onValueChange = { inputText = it },
                placeholder = { Text("Enter text") },
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = {
                if (inputText.isNotBlank()) {
                    viewModel.updateTextContent(inputText)
                    inputText = ""
                }
            }) {
                Text("Add Text")
            }
        }
    }
}



