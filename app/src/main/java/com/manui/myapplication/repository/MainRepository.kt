package com.manui.myapplication.repository

import com.manui.myapplication.base.BaseRepository
import com.manui.myapplication.model.Team
import com.manui.myapplication.rest.Rest
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

import java.util.ArrayList

class MainRepository : BaseRepository() {

    companion object {
        @Volatile
        private var instance: MainRepository? = null

        fun getInstance() =
            instance ?: synchronized(this) {
                instance ?: MainRepository().also { instance = it }
            }
    }

    suspend fun loadTeams() = Rest.getAuth().loadTeams()
    suspend fun createTeam(team: Team) = Rest.getAuth().createTeam(team)


}