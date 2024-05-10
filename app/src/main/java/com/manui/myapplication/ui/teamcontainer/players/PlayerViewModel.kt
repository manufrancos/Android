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
import java.util.Date

class PlayerViewModel(private val repo: MainRepository =  MainRepository.getInstance()): BaseViewModel() {


    val teams = MutableLiveData<ArrayList<Player>>()

    val playerName = MutableLiveData<String>()
    val date = MutableLiveData<Date>()

    val playerCreate = MutableLiveData<Player>()

    fun createPlayer(idTeam: Int){
        response.value = NetworkResponse.Loading
        viewModelScope.launch(Dispatchers.IO) {
            if(playerCreate.value == null){
                playerCreate.value = Player()
            }
            playerCreate.value!!.name = playerName.value
            playerCreate.value!!.birthday = date.value
            playerCreate.value

            val resp = repo.createPlayer(playerCreate.value!!)
            if(resp is NetworkResponse.Success){
                status.postValue(REST_CUD.SUCCESS)
                //teamCreate.postValue(resp.body)
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

    fun changeDateBirthDay(time: Date) {
        date.postValue(time)
    }


}