package com.example.composetimer.ui.main

import android.os.CountDownTimer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composetimer.util.TimerUtil
import com.example.composetimer.util.TimerUtil.formatTime
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private var countDownTimer: CountDownTimer? = null

    private val _state = MutableStateFlow(TimerState())
    val state: StateFlow<TimerState> = _state.asStateFlow()

    private val _celebrate = MutableSharedFlow<Boolean>()
    val celebrate: SharedFlow<Boolean> = _celebrate.asSharedFlow()

    fun handleCountDownTimer() {
        if (_state.value.isRunning) {
            pauseTimer()
            viewModelScope.launch { _celebrate.emit(false) }
        } else {
            startTimer()
        }
    }

    private fun pauseTimer() {
        countDownTimer?.cancel()
        handleTimerValues(false, TimerUtil.TIME_COUNTDOWN.formatTime(), 1.0F, false)
    }

    private fun startTimer() {
        _state.update { it.copy(isRunning = true) }
        countDownTimer = object : CountDownTimer(TimerUtil.TIME_COUNTDOWN, 1000) {

            override fun onTick(millisRemaining: Long) {
                val progressValue = millisRemaining.toFloat() / TimerUtil.TIME_COUNTDOWN
                handleTimerValues(true, millisRemaining.formatTime(), progressValue, false)
            }

            override fun onFinish() {
                pauseTimer()
                viewModelScope.launch { _celebrate.emit(true) }
            }
        }.start()
    }

    private fun handleTimerValues(isRunning: Boolean, text: String, progress: Float, celebrate: Boolean) {
        _state.update { it.copy(isRunning = isRunning, time = text, progress = progress) }
        viewModelScope.launch { _celebrate.emit(celebrate) }
    }
}

data class TimerState(
    val time: String = TimerUtil.TIME_COUNTDOWN.formatTime(),
    val progress: Float = 1.00F,
    val isRunning: Boolean = false,
)