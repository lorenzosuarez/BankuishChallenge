package com.lsuarez.bankuishchallenge.presentation.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.lsuarez.domain.useCase.ItemUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

/**
 * Created by Lorenzo on 12/5/2022.
 */

@HiltViewModel
class HomeViewModel @Inject constructor(
    itemUseCases: ItemUseCases
) : ViewModel() {
    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    val getAllItems = itemUseCases.getItemsUseCase().cachedIn(viewModelScope)
}