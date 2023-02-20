package com.app.screens.hosts.ui.sections

import androidx.fragment.app.Fragment
import com.app.components.navigation.named.RouterNames.MAIN_SECTION
import com.app.screens.hosts.R
import com.app.screens.hosts.presentation.section.viewmodels.MainSectionHostViewModel
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.androidx.AppNavigator
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.qualifier.named

class MainSectionHostFragment : Fragment(R.layout.fragment_main_section_host) {

	companion object {

		fun newInstance() = MainSectionHostFragment()
	}

	private val viewModel: MainSectionHostViewModel by viewModel()
	private val mainSectionNavigatorHolder: NavigatorHolder by inject(named(MAIN_SECTION))
	private val navigator by lazy { AppNavigator(requireActivity(), R.id.main_stack_container, fragmentManager = childFragmentManager) }

	override fun onResume() {
		super.onResume()
		mainSectionNavigatorHolder.setNavigator(navigator)
		viewModel.initRouter()
	}

	override fun onPause() {
		super.onPause()
		mainSectionNavigatorHolder.removeNavigator()
	}
}