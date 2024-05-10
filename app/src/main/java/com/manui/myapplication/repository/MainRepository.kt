package com.manui.myapplication.repository

import com.manui.myapplication.base.BaseRepository
import com.manui.myapplication.model.Match
import com.manui.myapplication.model.Player
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

    suspend fun loadPlayers(idTeam: Int) = Rest.getAuth().loadPlayers(idTeam)
    suspend fun loadMatch(idTeam: Int) = Rest.getAuth().loadMatch(idTeam)
    suspend fun createPlayer(player: Player) = Rest.getAuth().createPlayer(player)
    suspend fun createMatch(math: Match) = Rest.getAuth().createMatch(math)



}