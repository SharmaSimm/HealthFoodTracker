package com.example.bitfit.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.humcomp.bitfitpart2.R
class SummaryFragment : Fragment(R.layout.fragment_summary) {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the fragment's layout
        return inflater.inflate(R.layout.fragment_summary, container, false)
    }

    // Other fragment logic goes here
}
