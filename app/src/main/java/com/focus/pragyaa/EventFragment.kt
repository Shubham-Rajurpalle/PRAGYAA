package com.focus.pragyaa

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.focus.pragyaa.Utils.EventDataClass
import com.focus.pragyaa.Utils.EventAdapter

import com.focus.pragyaa.databinding.FragmentEventBinding

class EventFragment : Fragment() {

    private var _binding: FragmentEventBinding? = null
    private val binding get() = _binding!!
//helll
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEventBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sampleEvents = listOf(
            EventDataClass("HackFusion 2.0", R.drawable.hackfusion, "20", "Feb"),
            EventDataClass("TechFest", R.drawable.image1, "25", "Feb"),
            EventDataClass("Tech Conference", R.drawable.image2, "28", "Feb")
        )

        binding.eventRecyclerCardview.layoutManager = LinearLayoutManager(requireContext())
        binding.eventRecyclerCardview.adapter = EventAdapter(sampleEvents)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
