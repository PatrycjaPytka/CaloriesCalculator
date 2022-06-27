package com.patrycja.nantecalories.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.patrycja.nantecalories.fragments.dailyKcalLimit.DailyKcalLimit


@Dao
interface DailyKcalLimitDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addDailyKcalLimit(dailyKcalLimit: DailyKcalLimit)

    @Query("SELECT limitId, kcal FROM daily_kcal_table ORDER BY limitId DESC LIMIT 1")
    fun readAllData(): LiveData<List<DailyKcalLimit>>

    @Query("DELETE FROM daily_kcal_table")
    suspend fun deleteAllData()

}