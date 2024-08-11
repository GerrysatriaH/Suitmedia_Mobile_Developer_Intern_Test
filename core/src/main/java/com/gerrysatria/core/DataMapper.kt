package com.gerrysatria.core

import com.gerrysatria.core.data.response.DataItem
import com.gerrysatria.core.domain.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

object DataMapper {
    fun mapResponsesToDomain(input: List<DataItem>): Flow<List<User>> {
        val userList = ArrayList<User>()
        input.map { userResponse ->
            val user = User(
                id = userResponse.id,
                avatar = userResponse.avatar,
                firstName = userResponse.firstName,
                lastName = userResponse.lastName,
                email = userResponse.email
            )
            userList.add(user)
        }
        return flowOf(userList)
    }
}