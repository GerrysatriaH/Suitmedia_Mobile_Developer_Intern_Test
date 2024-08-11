package com.gerrysatria.core.data

import com.gerrysatria.core.DataMapper
import com.gerrysatria.core.data.response.DataItem
import com.gerrysatria.core.domain.model.User
import com.gerrysatria.core.domain.repository.IUserRepository
import kotlinx.coroutines.flow.Flow

class UserRepository(private val remoteDataSource: RemoteDataSource) : IUserRepository {
    override fun getAllUsers(page: Int, perPage: Int): Flow<Resource<List<User>>> =
        object : NetworkOnlyResource<List<User>, List<DataItem>>(){
            override fun loadFromNetwork(data: List<DataItem>): Flow<List<User>> =
                DataMapper.mapResponsesToDomain(data)

            override suspend fun createCall(): Flow<ApiResponse<List<DataItem>>> =
                remoteDataSource.getUsers(page, perPage)
        }.asFlow()
}