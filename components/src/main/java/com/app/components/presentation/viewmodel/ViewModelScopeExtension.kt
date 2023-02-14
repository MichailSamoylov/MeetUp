package com.app.components.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

fun ViewModel.launch(run: suspend () -> Unit, catchError: () -> Unit) {
	viewModelScope.launch(
		CoroutineExceptionHandler { _, _ ->
			catchError()
		}
	) {
		run()
	}
}