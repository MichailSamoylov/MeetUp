package com.app.screens.hosts.ui.sections

import androidx.fragment.app.Fragment
import com.app.components.navigation.named.RouterNames.MAP_SECTION
import com.app.screens.hosts.R
import com.app.screens.hosts.presentation.section.viewmodels.MapSectionHostViewModel
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.androidx.AppNavigator
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.qualifier.named

class MapSectionHostFragment : Fragment(R.layout.fragment_map_section_host) {

	companion object {

		fun newInstance() = MapSectionHostFragment()
	}

	private val viewModel: MapSectionHostViewModel by viewModel()
	private val tasksSectionNavigateHolder: NavigatorHolder by inject(named(MAP_SECTION))
	private val navigator by lazy { AppNavigator(requireActivity(), R.id.map_stack_container, fragmentManager = childFragmentManager) }

	override fun onResume() {
		super.onResume()
		tasksSectionNavigateHolder.setNavigator(navigator)
		viewModel.initRouter()
	}

	override fun onPause() {
		super.onPause()
		tasksSectionNavigateHolder.removeNavigator()
	}
}