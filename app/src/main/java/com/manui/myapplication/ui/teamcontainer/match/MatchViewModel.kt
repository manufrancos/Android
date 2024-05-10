package com.manui.myapplication.ui.teamcontainer.match

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.manui.myapplication.base.BaseViewModel
import com.manui.myapplication.model.Match
import com.manui.myapplication.model.Player
import com.manui.myapplication.model.Team
import com.manui.myapplication.repository.MainRepository
import com.manui.myapplication.rest.networkadapter.NetworkResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Date

class MatchViewModel(private val repo: MainRepository =  MainRepository.getInstance()): BaseViewModel() {


    val matchs = MutableLiveData<ArrayList<Match>>()

    val homeTeam = MutableLiveData<Int>()
    val awayTeam = MutableLiveData<Int>()
    val homeGoals = MutableLiveData<Int>()
    val awayGoals = MutableLiveData<Int>()
    val matchday = MutableLiveData<Date>()

    val createMatch = MutableLiveData<Match>()
    val matchCreate = MutableLiveData<Match>()

    fun loadMatchs(idTeam: Int){
        response.value = NetworkResponse.Loading
        viewModelScope.launch(Dispatchers.IO) {
            val resp = repo.loadMatch(idTeam)
            if(resp is NetworkResponse.Success){
                matchs.postValue(resp.body)
                status.postValue(REST_CUD.SUCCESS)
            }else{
                status.postValue(REST_CUD.FAIL)
            }
            response.postValue(resp)
        }
    }


    fun createMatch(){
        response.value = NetworkResponse.Loading
        viewModelScope.launch(Dispatchers.IO) {
            if(createMatch.value == null){
                createMatch.value = Match()
            }
            createMatch.value!!.homeTeam =  Team(homeTeam.value)
            createMatch.value!!.awayTeam =  Team(awayTeam.value)
            createMatch.value!!.homeGoals =  homeGoals.value
            createMatch.value!!.awayGoals =  awayGoals.value
            createMatch.value!!.matchday =  matchday.value

            val resp = repo.createMatch(createMatch.value!!)
            if(resp is NetworkResponse.Success){
                status.postValue(REST_CUD.SUCCESS)
                matchCreate.postValue(resp.body)
            }else{
                status.postValue(REST_CUD.FAIL)

            }
            response.postValue(resp)
        }
    }


    fun changeDate(time: Date) {
        this.matchday.postValue(time)
    }


}