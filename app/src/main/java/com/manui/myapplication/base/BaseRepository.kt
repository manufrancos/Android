package com.manui.myapplication.base

import android.content.Context
import com.google.gson.Gson
import com.manui.myapplication.App

open class BaseRepository {

    protected val preferences =
        App.context.getSharedPreferences(App.context.packageName, Context.MODE_PRIVATE)

}