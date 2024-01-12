package com.example.clockcanvas.clockScreen.component

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class ClockStyle(
    val normalLineLength: Dp = 15.dp,
    val fiveStepLineLength: Dp = 20.dp,
    val normalLineWidth: Dp = 0.5.dp,
    val fiveStepLineWidth: Dp = 1.dp,
    val normalLineColor: Color = Color.LightGray,
    val fiveStepLineColor: Color = Color.DarkGray,
    val textSize: TextUnit = 15.sp
)