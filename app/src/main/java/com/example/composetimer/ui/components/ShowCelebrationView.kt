package com.example.composetimer.ui.components
//
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.viewinterop.AndroidView
//import nl.dionsegijn.konfetti.KonfettiView
//import nl.dionsegijn.konfetti.models.Shape
//import nl.dionsegijn.konfetti.models.Size
//
//@Composable
//fun ShowCelebration() {
//    AndroidView(
//        modifier = Modifier.fillMaxSize(),
//        factory = { ctx ->
//            KonfettiView(context = ctx).apply {
//
//            }
//        },
//        update = {
//            it.build()
//                .addColors(
//                    android.graphics.Color.YELLOW,
//                    android.graphics.Color.GREEN,
//                    android.graphics.Color.MAGENTA
//                )
//                .setDirection(0.0, 359.0)
//                .setSpeed(1f, 5f)
//                .setFadeOutEnabled(true)
//                .setTimeToLive(2000L)
//                .addShapes(Shape.Square, Shape.Circle)
//                .addSizes(Size(12))
//                .setPosition(0f, 100f, 0f, 100F)
//                .streamFor(300, 5000L)
//
//        }
//    )
//}
//
//
//@Preview(showBackground = true)
//@Composable
//fun previewShowCelebration() {
//    ShowCelebration()
//}
