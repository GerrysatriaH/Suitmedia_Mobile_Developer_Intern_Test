package com.gerrysatria.suitmedia

import com.gerrysatria.core.domain.usecase.UserInteractor
import com.gerrysatria.core.domain.usecase.UserUseCase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<UserUseCase> { UserInteractor(get()) }
}

val viewModelModule = module {
    viewModel { ThirdScreenViewModel(get()) }
}