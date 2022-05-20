package com.patrycja.nantecalories.fragments.dailyKcalLimit

import androidx.lifecycle.LiveData
import com.patrycja.nantecalories.data.DailyKcalLimitDao


class DailyKcalLimitRepository(private val dailyKcalLimitDao: DailyKcalLimitDao) {
    val readAllData: LiveData<List<DailyKcalLimit>> = dailyKcalLimitDao.readAllData()

    suspend fun addDailyKcalLimit(dailyKcalLimit: DailyKcalLimit) {
        dailyKcalLimitDao.addDailyKcalLimit(dailyKcalLimit)
    }

    suspend fun updateDailyKcalLimit(dailyKcalLimit: DailyKcalLimit) {
        dailyKcalLimitDao.updateDailyKcalLimit(dailyKcalLimit)
    }
}