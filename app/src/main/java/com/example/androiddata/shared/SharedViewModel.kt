package com.example.androiddata.shared

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.androiddata.LOG_TAG
import com.example.androiddata.data.MonsterRepository
import com.example.androiddata.model.Monster
import com.example.androiddata.utilities.FileHelper
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.util.stream.DoubleStream.builder

class SharedViewModel(app:Application) : AndroidViewModel(app) {


    private val dataRepo =MonsterRepository(app)
    val monsterData =dataRepo.monsterData

    fun refreshData() {
       dataRepo.refreshDataFromWeb()
    }

    val selectedMonster =MutableLiveData<Monster>()
}
