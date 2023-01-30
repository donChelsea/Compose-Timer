package com.example.composetimer.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composetimer.R

@Composable
fun CountDownButton(
    modifier: Modifier = Modifier,
    isRunning: Boolean,
    optionSelected: () -> Unit,
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.wrapContentSize()
    ) {
        Button(
            onClick = { optionSelected() },
            modifier = Modifier
                .height(70.dp)
                .width(200.dp),
            shape = RoundedCornerShape(25.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = colorResource(id = R.color.mustard),
                contentColor = colorResource(id = R.color.white),
            ),
        ) {
            val pair = if (!isRunning) "START" else "STOP"
            Text(
                pair,
                fontSize = 20.sp,
                color = Color.White,
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun previewCountDownButton() {
    CountDownButton(
        modifier = Modifier,
        isRunning = false,
        optionSelected = {},
    )
}

