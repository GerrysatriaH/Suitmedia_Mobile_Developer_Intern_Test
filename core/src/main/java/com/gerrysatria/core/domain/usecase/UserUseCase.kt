package com.gerrysatria.core.domain.usecase

import com.gerrysatria.core.data.Resource
import com.gerrysatria.core.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserUseCase {
    fun getAllUser(page: Int, perPage: Int): Flow<Resource<List<User>>>
}