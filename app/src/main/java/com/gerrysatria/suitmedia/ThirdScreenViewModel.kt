package com.gerrysatria.suitmedia

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.gerrysatria.core.domain.usecase.UserUseCase

class ThirdScreenViewModel(private val userUseCase: UserUseCase) : ViewModel() {
    fun getListUser(page: Int = 1, perPage: Int = 10) = userUseCase.getAllUser(page, perPage).asLiveData()
}