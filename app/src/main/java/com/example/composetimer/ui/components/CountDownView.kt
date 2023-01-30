package com.example.composetimer.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composetimer.R
import com.example.composetimer.ui.main.MainViewModel
import nl.dionsegijn.konfetti.core.Party
import nl.dionsegijn.konfetti.core.emitter.Emitter
import java.util.concurrent.TimeUnit

@Composable
fun CountDownScreen(viewModel: MainViewModel) {
    val state = viewModel.state.collectAsState()
    val celebrate = remember { mutableStateOf(false) }

    LaunchedEffect(key1 = Unit) {
        viewModel.celebrate.collect {
            celebrate.value = it
        }
    }

    CountDownView(
        time = state.value.time,
        progress = state.value.progress,
        isRunning = state.value.isRunning,
        celebrate = celebrate.value,
        optionSelected = { viewModel.handleCountDownTimer() }
    )
}

@Composable
fun CountDownView(
    time: String,
    progress: Float,
    isRunning: Boolean,
    celebrate: Boolean,
    optionSelected: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.navy)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

//        if (celebrate) Party(emitter = Emitter(duration = 5, TimeUnit.SECONDS).perSecond(30))

        Text(
            text = "Timer",
            color = androidx.compose.ui.graphics.Color.White,
            fontSize = 25.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
        )

        Text(
            text = "1 minute to launch...",
            color = androidx.compose.ui.graphics.Color.White,
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
        )

        Text(
            text = "Click to start or stop countdown",
            color = androidx.compose.ui.graphics.Color.White,
            fontSize = 14.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth(),
        )

        CountDownIndicator(
            Modifier.padding(top = 50.dp),
            progress = progress,
            time = time,
            size = 250,
            stroke = 12
        )

        CountDownButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 70.dp),
            isRunning = isRunning
        ) {
            optionSelected()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun previewCountDownView() {
    CountDownView(
        time = "1.00",
        progress = .5F,
        isRunning = false,
        celebrate = false,
        optionSelected = {}
    )
}
