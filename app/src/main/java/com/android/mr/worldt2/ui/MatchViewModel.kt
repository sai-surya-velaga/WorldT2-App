package com.android.mr.worldt2.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.mr.worldt2.data.MatchState
import com.android.mr.worldt2.data.Team
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MatchViewModel @Inject constructor() : ViewModel() {

    private val _matchState = MutableLiveData<MatchState>()
    val matchState: LiveData<MatchState> = _matchState

    private lateinit var team1: Team
    private lateinit var team2: Team
    private var isSecondInnings = false

    private val outcomes = listOf("0", "1", "2", "3", "4", "6", "Out")

    fun startMatch(teamA: Team, teamB: Team) {
        team1 = teamA
        team2 = teamB

        _matchState.value = MatchState(
            currentBattingTeam = team1,
            otherTeam = team2,
        )
    }

    fun playNextBall() {
        val state = _matchState.value ?: return
        if (state.matchEnded) return

        val outcome = outcomes.random()
        val newState = state.copy(lastOutcome = outcome)

        var runs = state.runs
        var wickets = state.wickets
        var balls = state.balls

        // handle outcome
        when (outcome) {
            "Out" -> wickets++
            else -> runs += outcome.toInt()
        }

        balls++

        var status = "${state.currentBattingTeam.name} batting..."
        var matchEnded = false

        // innings over?
        val inningsOver = wickets == 3 || balls == 12

        if (inningsOver) {
            if (isSecondInnings) {
                matchEnded = true
                // Decide winner
                val winner = if (runs > state.otherTeamScore) {
                    state.currentBattingTeam.name
                } else if (runs < state.otherTeamScore) {
                    state.otherTeam.name
                } else {
                    "It's a Draw"
                }
                status = "Match Over! Winner: $winner"
            } else {
                // Switch innings
                isSecondInnings = true
                _matchState.value = MatchState(
                    currentBattingTeam = state.otherTeam,
                    otherTeam = state.currentBattingTeam,
                    otherTeamScore = runs,
                    otherTeamWickets = wickets,
                    otherTeamBalls = balls,
                    status = "${state.otherTeam.name} now batting...",
                )
                return
            }
        } else if (isSecondInnings && runs > state.otherTeamScore) {
            matchEnded = true
            status = "Match Over! ${state.currentBattingTeam.name} won by chasing!"
        }

        _matchState.value = newState.copy(
            runs = runs,
            wickets = wickets,
            balls = balls,
            status = status,
            matchEnded = matchEnded
        )
    }
}
