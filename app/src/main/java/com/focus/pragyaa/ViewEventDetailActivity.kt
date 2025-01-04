package com.focus.pragyaa

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.focus.pragyaa.databinding.ActivityViewEventBinding


class ViewEventDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityViewEventBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityViewEventBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val eventName = intent.getStringExtra("eventName")
        val coverImageUrl = intent.getStringExtra("coverImageUrl")
        val eventDateDay = intent.getStringExtra("eventDateDay")
        val eventDateMonth = intent.getStringExtra("eventDateMonth")
        val eventDescription = intent.getStringExtra("eventDescription")
        val eventRegistrationLink = intent.getStringExtra("eventRegistrationLink")

        binding.viewEventEventName.text = eventName
       // binding.eventDateTextView.text = "$eventDateDay $eventDateMonth"
        binding.eventDescription.text = eventDescription


        Glide.with(this)
            .load(coverImageUrl)
            .into(binding.viewEventCardImageview)

        binding.btnRegisterEvent.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(eventRegistrationLink))
            startActivity(intent)
        }
    }
}
