package br.edu.tonetunidavi.giovani.todolist;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;

public class DateConverters {

    @TypeConverter
    public static Date toDate(Long timeInMillis){
        if (timeInMillis == null){
            return null;
        }

        return new Date(timeInMillis);
    }

    @TypeConverter
    public static Long fromDate(Date date){
        if (date == null){
            return null;
        }

        return date.getTime();
    }

    /*
    @TypeConverter
    public static Date fromTimestamp(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long dateToTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }*/
}
