package com.example.clockcanvas.clockScreen.component

import android.graphics.Color
import android.graphics.Paint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.graphics.withRotation
import java.util.Collections.rotate
import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun Clock(
    hour: Float = 0f,
    minute: Float = 0f,
    second: Float = 0f,
    radius: Dp = 100.dp,
    style: ClockStyle = ClockStyle()
) {
    Canvas(modifier = Modifier.size(radius * 2f)){

        drawContext.canvas.nativeCanvas.apply {

            drawCircle(
                center.x,
                center.y,
                radius.toPx(),
                Paint().apply {
                    color = Color.WHITE
                    setShadowLayer(
                        50f,
                        0f,
                        0f,
                        Color.argb(50,0,0,0)
                    )
                }
            )

        }

        // Lines
        for (i in 0..59){
            val angleInRed = i * (360f / 60f) * (PI.toFloat() / 180f)
            val lineType = when{
                i % 5 == 0 -> LineType.FiveStepLine
                else -> LineType.NormalLine
            }
            val lineLength = when(lineType){
                LineType.NormalLine -> style.normalLineLength.toPx()
                LineType.FiveStepLine -> style.fiveStepLineLength.toPx()
            }

            val lineWidth = when(lineType){
                LineType.NormalLine -> style.normalLineWidth.toPx()
                LineType.FiveStepLine -> style.fiveStepLineWidth.toPx()
            }

            val lineColor = when(lineType){
                LineType.NormalLine -> style.normalLineColor
                LineType.FiveStepLine -> style.fiveStepLineColor
            }

            val lineStart = Offset(
                x = radius.toPx() * cos(angleInRed) + center.x,
                y = radius.toPx() * sin(angleInRed) + center.y
            )

            val lineEnd = Offset(
                x = (radius.toPx() - lineLength) * cos(angleInRed) + center.x,
                y = (radius.toPx() - lineLength) * sin(angleInRed) + center.y
            )

//            drawContext.canvas.nativeCanvas.apply {
//                if (lineType is LineType.FiveStepLine) {
//
//                    val textRadius =
//                        radius.toPx() - lineLength - 2.dp.toPx() - style.textSize.toPx()
//                    val x = textRadius * cos(angleInRed) + center.x
//                    val y = textRadius * sin(angleInRed) + center.y
//
//                    withRotation(
//                        degrees = angleInRed * (360f / PI.toFloat()) + 60f,
//                        pivotX = x,
//                        pivotY = y
//                    ) {
//
//                        drawText(
//                            abs(i).toString(),
//                            x,
//                            y,
//                            Paint().apply {
//                                textSize = style.textSize.toPx()
//                                textAlign = Paint.Align.CENTER
//                            }
//                        )
//
//                     }
//                  }
//
//            }

            drawLine(
                color = lineColor,
                start = lineStart,
                end = lineEnd,
                strokeWidth = lineWidth
            )

        }

        // seconds line
        rotate(
            degrees = second * (360f / 60f)
        ){

            drawLine(
                color = androidx.compose.ui.graphics.Color.Red,
                start = center,
                end = Offset(center.x, 24.dp.toPx()),
                cap = StrokeCap.Round,
                strokeWidth = 2.dp.toPx()
            )

        }

        // minutes Line
        rotate(
            degrees = minute * (360f / 60f)
        ){
            drawLine(
                color = androidx.compose.ui.graphics.Color.Blue,
                start = center,
                end = Offset(center.x, 28.dp.toPx()),
                cap = StrokeCap.Round,
                strokeWidth = 2.5.dp.toPx()
            )
        }

        // hour Line
        rotate(
            degrees = hour * (360f / 12f)
        ){
            drawLine(
                color = androidx.compose.ui.graphics.Color.Black,
                start = center,
                end = Offset(center.x, 38.dp.toPx()),
                cap = StrokeCap.Round,
                strokeWidth = 3.dp.toPx()
            )
        }

    }

}