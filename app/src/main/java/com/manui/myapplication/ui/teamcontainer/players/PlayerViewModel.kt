package com.manui.myapplication.ui.teamcontainer.players

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.manui.myapplication.base.BaseViewModel
import com.manui.myapplication.model.Player
import com.manui.myapplication.model.Team
import com.manui.myapplication.repository.MainRepository
import com.manui.myapplication.rest.networkadapter.NetworkResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PlayerViewModel(private val repo: MainRepository =  MainRepository.getInstance()): BaseViewModel() {


    val teams = MutableLiveData<ArrayList<Player>>()

    val teamName = MutableLiveData<String>()
    val teamCity = MutableLiveData<String>()

    val teamCreate = MutableLiveData<Team>()

    fun createTeam(){
        response.value = NetworkResponse.Loading
        viewModelScope.launch(Dispatchers.IO) {
            val team = Team()
            team.name = teamName.value
            team.city = teamCity.value
            val resp = repo.createTeam(team)
            if(resp is NetworkResponse.Success){
                status.postValue(REST_CUD.SUCCESS)
                teamCreate.postValue(resp.body)
            }else{
                status.postValue(REST_CUD.FAIL)

            }
            response.postValue(resp)
        }
    }

    fun loadPlayers(idTeam: Int){
        response.value = NetworkResponse.Loading
        viewModelScope.launch(Dispatchers.IO) {
            val resp = repo.loadPlayers(idTeam)
            if(resp is NetworkResponse.Success){
                teams.postValue(resp.body)
                status.postValue(REST_CUD.SUCCESS)
            }else{
                status.postValue(REST_CUD.FAIL)
            }
            response.postValue(resp)
        }
    }


}