package com.android.mr.worldt2.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.navArgs
import com.android.mr.worldt2.data.MatchRepository
import com.android.mr.worldt2.data.Team
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TeamSelectionViewModel(application: Application) : AndroidViewModel(application) {

    private val _teams = MutableLiveData<List<Team>>()
    val teams: LiveData<List<Team>> get() = _teams

    private val repository = MatchRepository(application)

    fun loadTeams() {
        _teams.value = repository.getTeams()
    }

}
