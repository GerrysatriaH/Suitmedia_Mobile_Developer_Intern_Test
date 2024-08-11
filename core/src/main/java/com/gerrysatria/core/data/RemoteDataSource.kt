package com.gerrysatria.core.data

import android.util.Log
import com.gerrysatria.core.data.response.DataItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {
    suspend fun getUsers(page: Int, perPage: Int) : Flow<ApiResponse<List<DataItem>>> =
        flow {
            try {
                val response = apiService.getUsers(page, perPage)
                val dataArray = response.data
                if (dataArray.isEmpty()){
                        emit(ApiResponse.Error("null"))
                    } else {
                        emit(ApiResponse.Success(response.data))
                    }
            } catch (e: Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
}