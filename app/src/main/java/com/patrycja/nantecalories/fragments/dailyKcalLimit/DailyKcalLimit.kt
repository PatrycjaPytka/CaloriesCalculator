package com.patrycja.nantecalories.fragments.dailyKcalLimit

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName="daily_kcal_table")
data class DailyKcalLimit(
    @PrimaryKey(autoGenerate=true) val limitId: Int,
    val kcal: Float
)
