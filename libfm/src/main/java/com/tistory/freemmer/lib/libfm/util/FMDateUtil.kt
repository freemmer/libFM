package com.tistory.freemmer.lib.libfm.util

import android.annotation.SuppressLint
import android.support.annotation.IntDef
import android.support.annotation.IntRange
import java.text.SimpleDateFormat
import java.util.*

/**
 *
 *
 * @see libfm/src/test/java/com/tistory/freemmer/lib/libfm/util/FMDateUtilTest.kt
 *
 * Created by freemmer on 14/02/2019.
 * History
 *    - 14/02/2019 Create file
 */
class FMDateUtil constructor(
    dateFormat: String,
    baseDate: String? = null
) {
    @SuppressLint("SimpleDateFormat")
    private val format = SimpleDateFormat(dateFormat)
    private val calendar = Calendar.getInstance()!!

    init {
        if (baseDate != null) {
            calendar.time = format.parse(baseDate)
        }
    }

    override fun toString(): String {
        return format.format(Date(calendar.timeInMillis))
    }

    fun toTimeInMillis(): Long {
        return calendar.timeInMillis
    }

    fun addYear(year: Int) {
        calendar.add(Calendar.YEAR, year)
    }

    fun addMonth(month: Int) {
        calendar.add(Calendar.MONTH, month)
    }

    fun addDay(day: Int) {
        calendar.add(Calendar.DATE, day)
    }

    fun addHour(hour : Int) {
        calendar.add(Calendar.HOUR, hour)
    }

    fun addMinute(min : Int) {
        calendar.add(Calendar.MINUTE, min)
    }

    fun addSecond(sec: Int) {
        calendar.add(Calendar.SECOND, sec)
    }

    fun getLastDayOfMonth(): Int {
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
    }

    @IntRange(from = -12, to = 12)
    fun getTimeZoneOffSet(): Int {
        return (calendar.get(Calendar.ZONE_OFFSET) / (60*60*1000))
    }


    @Retention(AnnotationRetention.SOURCE)
    @IntDef(Calendar.SUNDAY, Calendar.MONDAY, Calendar.TUESDAY, Calendar.WEDNESDAY, Calendar.THURSDAY, Calendar.FRIDAY, Calendar.SATURDAY)
    annotation class DayOfTheWeek

    @DayOfTheWeek
    fun getDayOfTheWeek(): Int {
        return calendar.get(Calendar.DAY_OF_WEEK)
    }

}

