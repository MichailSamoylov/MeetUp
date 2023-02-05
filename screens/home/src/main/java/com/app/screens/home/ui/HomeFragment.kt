package com.app.screens.home.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.app.screens.home.presentation.HomeViewModel
import com.app.screens.home.databinding.FragmentStartScreenBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

	companion object {

		fun newInstance() = HomeFragment()
	}

	private lateinit var binding: FragmentStartScreenBinding
	private val viewModel: HomeViewModel by viewModel()

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
		super.onCreateView(inflater, container, savedInstanceState)
		binding = FragmentStartScreenBinding.inflate(inflater, container, false)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		with(binding) {
			buttonExit.setOnClickListener { viewModel.navigateBack() }
			buttonToSearchScreen.setOnClickListener {}
		}
	}
}