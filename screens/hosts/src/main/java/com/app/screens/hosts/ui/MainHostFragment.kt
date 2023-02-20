package com.app.screens.hosts.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.app.components.navigation.named.RouterNames.MAIN_HOST
import com.app.components.navigation.named.SectionNames
import com.app.components.navigation.navigator.KeepStateNavigator
import com.app.components.ui.fragment.addBackPressedListener
import com.app.screens.hosts.R
import com.app.screens.hosts.databinding.FragmentMainHostBinding
import com.app.screens.hosts.presentation.MainHostViewModel
import com.github.terrakok.cicerone.NavigatorHolder
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.qualifier.named

class MainHostFragment : Fragment(R.layout.fragment_main_host), BottomNavigationView.OnNavigationItemSelectedListener {

	companion object {

		fun newInstance() = MainHostFragment()
	}

	private lateinit var binding: FragmentMainHostBinding
	private val viewModel: MainHostViewModel by viewModel()
	private val mainHostNavigatorHolder: NavigatorHolder by inject(named(MAIN_HOST))
	private val navigator by lazy { KeepStateNavigator(requireActivity(), R.id.main_host_container, childFragmentManager) }

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
		binding = FragmentMainHostBinding.inflate(inflater, container, false)
		return binding.root
	}

	override fun onResume() {
		super.onResume()
		mainHostNavigatorHolder.setNavigator(navigator)
		viewModel.init()
	}

	override fun onPause() {
		super.onPause()
		mainHostNavigatorHolder.removeNavigator()
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		setListeners()
	}

	private fun setListeners() {
		binding.bottomNav.setOnNavigationItemSelectedListener(this)
		addBackPressedListener { viewModel.exit() }
		viewModel.currentHost.observe(viewLifecycleOwner) { sectionName ->
			viewModel.stateProcessingStart()
			when (sectionName) {
				SectionNames.MAIN -> binding.bottomNav.selectedItemId = R.id.main_section
				SectionNames.MAP  -> binding.bottomNav.selectedItemId = R.id.map_section
				else              -> Unit
			}
			viewModel.stateProcessingEnd()
		}
	}

	override fun onNavigationItemSelected(item: MenuItem): Boolean {
		return when (item.itemId) {
			R.id.main_section    -> {
				viewModel.navigateToMainSection()
				return true
			}

			R.id.map_section     -> {
				viewModel.navigateToMapSection()
				return true
			}

			R.id.profile_section -> false //		TODO Реализовать вместе с экраном "Профиля"

			else                 -> false
		}
	}
}