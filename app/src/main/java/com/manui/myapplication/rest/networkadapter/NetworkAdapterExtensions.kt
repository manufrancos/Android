package com.manui.myapplication.rest.networkadapter

fun NetworkResponse<Any, Any>?.isLoading(): Boolean = this is NetworkResponse.Loading
