package com.gerrysatria.core.domain.usecase

import com.gerrysatria.core.data.Resource
import com.gerrysatria.core.domain.model.User
import com.gerrysatria.core.domain.repository.IUserRepository
import kotlinx.coroutines.flow.Flow

class UserInteractor(private val userRepository: IUserRepository): UserUseCase {
    override fun getAllUser(page: Int, perPage: Int): Flow<Resource<List<User>>> =
        userRepository.getAllUsers(page, perPage)
}