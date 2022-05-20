package com.patrycja.nantecalories.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.patrycja.nantecalories.fragments.dailyKcalLimit.DailyKcalLimit


@Dao
interface DailyKcalLimitDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addDailyKcalLimit(dailyKcalLimit: DailyKcalLimit)

    @Query("SELECT * FROM daily_kcal_table ORDER BY limitId ASC")
    fun readAllData(): LiveData<List<DailyKcalLimit>>

    @Update
    fun updateDailyKcalLimit(dailyKcalLimit: DailyKcalLimit)
}