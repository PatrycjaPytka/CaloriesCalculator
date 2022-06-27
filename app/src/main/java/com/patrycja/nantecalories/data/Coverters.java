package com.patrycja.nantecalories.data;

import androidx.room.TypeConverter;

import java.util.Date;

public class Coverters {
    @TypeConverter
    public static Date toDate(Long value) {
        return value == null ? null: new Date(value);
    }

    @TypeConverter
    public static Long fromDate(Date date) {
        return date == null ? null: date.getTime();
    }
}
