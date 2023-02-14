package com.app.components.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.components.presentation.state.StateView
import com.app.components.ui.intent.IntentView

abstract class BaseViewModel<S : StateView, I : IntentView> : ViewModel() {

	protected val _state = MutableLiveData<S>()
	val state: LiveData<S> = _state

	final fun sendIntent(intent: I) {
		handleAction(intent)
	}

	protected abstract fun handleAction(intent: I)
}