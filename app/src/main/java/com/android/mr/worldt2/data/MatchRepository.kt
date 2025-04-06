package com.android.mr.worldt2.data

import android.app.Application
import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class MatchRepository @Inject constructor(
    private val context: Application
) {
    fun getTeams(): List<Team> {
        return try {
            val jsonString = context.assets.open("teams.json")
                .bufferedReader()
                .use { it.readText() }

            val listType = object : TypeToken<List<Team>>() {}.type
            Gson().fromJson(jsonString, listType)
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }
}
