package com.android.mr.worldt2.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Team(
    val name: String,
    val flag: String
) : Parcelable

data class MatchState(
    val currentBattingTeam: Team,
    val otherTeam: Team,
    val runs: Int = 0,
    val wickets: Int = 0,
    val balls: Int = 0,
    val otherTeamScore: Int = 0,
    val otherTeamWickets: Int = 0,
    val otherTeamBalls: Int = 0,
    val lastOutcome: String = "–",
    val status: String = "",
    val matchEnded: Boolean = false
)






