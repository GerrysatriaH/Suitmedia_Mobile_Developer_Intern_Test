package com.gerrysatria.core.domain.repository

import com.gerrysatria.core.data.Resource
import com.gerrysatria.core.domain.model.User
import kotlinx.coroutines.flow.Flow

interface IUserRepository {
    fun getAllUsers(page: Int, perPage: Int): Flow<Resource<List<User>>>
}