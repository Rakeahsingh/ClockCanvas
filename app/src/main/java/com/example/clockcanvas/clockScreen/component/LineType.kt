package com.example.clockcanvas.clockScreen.component

sealed class LineType {

    data object NormalLine: LineType()
    data object FiveStepLine: LineType()

}