package com.ostrovec.tablehockey.ui.main

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.widget.TextView
import com.ostrovec.tablehockey.R

class MainActivity : AppCompatActivity() {

    private val ONE_SECOND: Long = 1000
    private val DEFAULT_TIME: String = "00:00"
    private val SECONDS_IN_MINUTE: Int = 60
    private var BEGIN_MOTIVATION_TIME: Long = 13L

    private lateinit var timerTextView: TextView
    private lateinit var startEndTextView: TextView
    private lateinit var cancelTextView: TextView

    private lateinit var countDownTimer: CountDownTimer
    private lateinit var descendingCountDownTimer: CountDownTimer

    private lateinit var mediaPlayerMotivation: MediaPlayer
    private lateinit var mediaPlayerSiren: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        mediaPlayerMotivation = MediaPlayer.create(this, R.raw.motivation)
        mediaPlayerSiren = MediaPlayer.create(this, R.raw.sirena)
        initViews()
        initListeners()
        initCountDownTimers()
    }

    private fun initViews() {
        timerTextView = findViewById(R.id.main_timer_text_view)
        startEndTextView = findViewById(R.id.main_start_end_button_text_view)
        cancelTextView = findViewById(R.id.main_cancel_button_text_view)
    }

    private fun initListeners() {
        startEndTextView.setOnClickListener {
            descendingCountDownTimer.start()
        }

        cancelTextView.setOnClickListener {
            descendingCountDownTimer.cancel()
            countDownTimer.onFinish()
            countDownTimer.cancel()
            timerTextView.text = DEFAULT_TIME
            updatePlayers()
        }
    }

    private fun initCountDownTimers() {
        countDownTimer = object : CountDownTimer(300 * ONE_SECOND, ONE_SECOND) {
            override fun onFinish() {
                mediaPlayerSiren.start()
            }

            override fun onTick(millisUntilFinished: Long) {
                timerTextView.text = getTime(millisUntilFinished)
            }

        }

        descendingCountDownTimer = object : CountDownTimer(16 * ONE_SECOND, ONE_SECOND) {
            override fun onFinish() {
                countDownTimer.start()
            }

            override fun onTick(millisUntilFinished: Long) {
                if (convertMillisecondsInSeconds(millisUntilFinished) == BEGIN_MOTIVATION_TIME) {
                    mediaPlayerMotivation.start()
                }
                timerTextView.text = getTime(millisUntilFinished)
            }

        }
    }

    private fun getTime(milliseconds: Long): String {
        if (convertMillisecondsInSeconds(milliseconds) < 10) {
            return "0" + convertMillisecondsInMinutes(milliseconds) + ":" +
                    "0" + convertMillisecondsInSeconds(milliseconds)
        } else {
            return "0" + convertMillisecondsInMinutes(milliseconds) + ":" + convertMillisecondsInSeconds(milliseconds)
        }
    }

    private fun convertMillisecondsInSeconds(milliseconds: Long): Long {
        Log.e("timeHockey in secon = ", ((milliseconds % (SECONDS_IN_MINUTE * ONE_SECOND)) / ONE_SECOND)
                .toString())

        return (milliseconds % (SECONDS_IN_MINUTE * ONE_SECOND)) / ONE_SECOND
    }

    private fun convertMillisecondsInMinutes(milliseconds: Long): Long {
        Log.e("timeHockey in minu = ", (milliseconds / SECONDS_IN_MINUTE).toString())
        return milliseconds / (SECONDS_IN_MINUTE * ONE_SECOND)
    }

    private fun updatePlayers(){
        mediaPlayerSiren.pause()
        mediaPlayerSiren.seekTo(0)
        mediaPlayerMotivation.pause()
        mediaPlayerMotivation.seekTo(0)
    }


}
