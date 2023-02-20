package com.app.screens.map.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.app.screens.map.databinding.FragmentMapBinding
import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition

class MapFragment : Fragment() {

	companion object {

		fun newInstance() = MapFragment()
	}

	private lateinit var binding: FragmentMapBinding

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		MapKitFactory.initialize(requireContext())
	}

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
		binding = FragmentMapBinding.inflate(inflater, container, false)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		binding.mapview.map.move(
			CameraPosition(Point(55.751574, 37.573856), 11.0f, 0.0f, 0.0f),
			Animation(Animation.Type.SMOOTH, 0f),
			null
		)
	}

	override fun onStart() {
		super.onStart()
		MapKitFactory.getInstance().onStart()
		binding.mapview.onStart()
	}

	override fun onStop() {
		super.onStop()
		binding.mapview.onStop()
		MapKitFactory.getInstance().onStop()
	}
}