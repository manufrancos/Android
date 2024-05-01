package com.manui.myapplication.ui

import androidx.lifecycle.MutableLiveData
import com.manui.myapplication.base.BaseViewModel
import com.manui.myapplication.utills.ToolbarStatus


class MainViewModel : BaseViewModel() {


    val toolbarStatus = MutableLiveData<ToolbarStatus>(ToolbarStatus.None)
    val showBottomBar = MutableLiveData(false)
    val showToolbar = MutableLiveData(true)


}