package com.manui.myapplication.model


import java.util.*
data class Match (
    var idMatch: Int? = null,
    var homeTeam: Team? = null,
    var awayTeam: Team? = null,
    var homeGoals: Int? = null,
    var awayGoals: Int? = null,
    var matchday: Date? = null,
)
