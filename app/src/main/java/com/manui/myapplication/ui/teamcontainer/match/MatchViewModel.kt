package com.manui.myapplication.ui.teamcontainer.match

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.manui.myapplication.base.BaseViewModel
import com.manui.myapplication.model.Match
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

    fun changeDate(time: Date) {
        TODO("Not yet implemented")
    }


}