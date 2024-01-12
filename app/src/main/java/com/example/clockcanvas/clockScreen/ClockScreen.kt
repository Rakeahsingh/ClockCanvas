package com.example.clockcanvas.clockScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.clockcanvas.clockScreen.component.Clock
import kotlinx.coroutines.delay
import java.util.Calendar

@Composable
fun ClockScreen() {

    val milliSeconds = remember {
        System.currentTimeMillis()
    }

    var seconds by remember {
        mutableStateOf("0")
    }
    var minutes by remember {
        mutableStateOf("0")
    }
    var hours by remember {
        mutableStateOf("0")
    }
    var amOrPm by remember {
        mutableStateOf("0")
    }

    LaunchedEffect(Unit){
        while (true){
            val cal = Calendar.getInstance()
            hours = cal.get(Calendar.HOUR).run {
                if (this.toString().length == 1) "0$this" else "$this"
            }
            minutes = cal.get(Calendar.MINUTE).run {
                if (this.toString().length == 1) "0$this" else "$this"
            }
            seconds = cal.get(Calendar.SECOND).run {
                if (this.toString().length == 1) "0$this" else "$this"
            }
            amOrPm = cal.get(Calendar.AM_PM).run {
                if (this == Calendar.AM) "AM" else "PM"
            }
            delay(1000)
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Clock",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "$hours:$minutes $amOrPm",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ){

            Clock(
                hour = hours.toFloat(),
                minute = minutes.toFloat(),
                second = seconds.toFloat()
            )


        }


    }

}


