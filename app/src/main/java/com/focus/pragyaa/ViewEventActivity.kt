package com.focus.pragyaa

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.focus.pragyaa.databinding.ActivityViewEventBinding

class ViewEventDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityViewEventBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewEventBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val eventName = intent.getStringExtra("EVENT_NAME")
        val eventImageResId = intent.getIntExtra("EVENT_IMAGE", 0)


        binding.viewEventEventName.text = eventName
        binding.viewEventCardImageview.setImageResource(eventImageResId)


    }
}
