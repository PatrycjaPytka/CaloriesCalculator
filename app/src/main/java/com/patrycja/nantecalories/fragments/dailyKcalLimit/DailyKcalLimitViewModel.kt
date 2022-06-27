package com.patrycja.nantecalories.fragments.dailyKcalLimit

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.patrycja.nantecalories.data.DailyKcalLimitDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext


class DailyKcalLimitViewModel(application: Application): AndroidViewModel(application) {
    val readAllData: LiveData<List<DailyKcalLimit>>
    private val repository: DailyKcalLimitRepository

    init {
        val dailyKcalLimitDao = DailyKcalLimitDatabase.getDatabase(application).dailyKcalLimitDao()
        repository = DailyKcalLimitRepository(dailyKcalLimitDao)
        readAllData = repository.readAllData
    }

    fun addDailyKcalLimit(dailyKcalLimit: DailyKcalLimit) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addDailyKcalLimit(dailyKcalLimit)
        }
    }

    fun deleteAllData() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllData()
        }
    }

}