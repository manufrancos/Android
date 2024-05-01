package com.manui.myapplication.utills

sealed class ToolbarStatus {
    object Default: ToolbarStatus()
    object None: ToolbarStatus()
    object Home: ToolbarStatus()
}