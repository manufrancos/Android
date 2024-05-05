package com.manui.myapplication.model

import java.util.*

data class Player(

    var idPlayer: Int? = null,
    var name: String? = null,
    var birthday: Date? = null,
    var team: Team? = null,
)

