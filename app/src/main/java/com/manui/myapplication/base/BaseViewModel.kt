package com.manui.myapplication.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.manui.myapplication.rest.networkadapter.NetworkResponse
import kotlin.reflect.KFunction0

abstract class BaseViewModel : ViewModel() {

    companion object {
        var retryUnit: KFunction0<Unit>? = null
        val response = MutableLiveData<NetworkResponse<Any, Any>>(NetworkResponse.NotExecuted)
    }

    val status = MutableLiveData<REST_CUD>(REST_CUD.NONE)
    val error = MutableLiveData<Error>()

    val toolbarTitle = MutableLiveData("")

    fun getResponse(): LiveData<NetworkResponse<Any, Any>> = response
    fun getToolbarTitle(): LiveData<String> = toolbarTitle

    fun getRetryUnit(): KFunction0<Unit>? = retryUnit
    fun setRetryUnit(retry: KFunction0<Unit>) {
        retryUnit = retry
    }

    fun showLoading(){
        response.value = NetworkResponse.Loading
    }

    fun hideLoading(){
        response.value = NetworkResponse.NotExecuted
    }
    enum class REST_CUD {
        NONE, SUCCESS, FAIL
    }
}
