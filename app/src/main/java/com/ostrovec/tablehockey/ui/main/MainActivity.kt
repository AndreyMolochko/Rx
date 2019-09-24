package com.ostrovec.tablehockey.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.ostrovec.tablehockey.R

class MainActivity : AppCompatActivity() {

    private lateinit var timerTextView: TextView
    private lateinit var startEndTextView: TextView
    private lateinit var cancelTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        initViews()
        initListeners()
    }

    private fun initViews() {
        timerTextView = findViewById(R.id.main_timer_text_view)
        startEndTextView = findViewById(R.id.main_start_end_button_text_view)
        cancelTextView = findViewById(R.id.main_cancel_button_text_view)
    }

    private fun initListeners() {
        startEndTextView.setOnClickListener {

        }

        cancelTextView.setOnClickListener {
            
        }
    }


}
