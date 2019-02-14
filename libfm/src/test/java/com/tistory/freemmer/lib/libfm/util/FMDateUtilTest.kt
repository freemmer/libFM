package com.tistory.freemmer.lib.libfm.util

import org.junit.After
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by freemmer on 14/02/2019.
 * History
 * - 14/02/2019 Create file
 */
class FMDateUtilTest {

    @Before
    fun setUp() {
        println("\r\n> Start Test")
    }

    @After
    fun tearDown() {
    }

    @Test
    fun basicCheck_Today_날짜_포맷_변경() {
        val date = FMDateUtil("yyyy-MM-dd HH:mm:ss")
        println("Current date is : ${date.toString()}")
        println("Current date is : ${date.toString("ddMM,yyyy HH:mm:ss")}")
    }

    @Test
    fun basicCheck_Today_오늘_날짜_기준() {
        val date = FMDateUtil("yyyy-MM-dd HH:mm:ss")
        val today = date.toString()
        println("Current date is : $date")
        date.addDay(10)
        println("Calculated date after 10 days from 'the Date' (+10 Days) > Current date is : $date")
        assertNotEquals(today, date.toString())
        date.addDay(-10)
        println("Calculated date before 10 days from 'the Date' (-10 Days) > Current date is : $date")
        assertEquals(today, date.toString())
        println("Current date's day of the week is : ${date.getDayOfTheWeek()}")
        println("Current date's last day of month is : ${date.getLastDayOfMonth()}")
        println("Current date's time zone offset is : ${date.getTimeZoneOffSet()}")
    }

    @Test
    fun basicCheck_TheDay_특정_날짜_기준() {
        val date = FMDateUtil("yyyy-MM-dd HH:mm:ss", "1979-06-25 01:02:03")
        val today = date.toString()
        println("Current date is : $date")
        date.addDay(10)
        println("Calculated date after 10 days from 'the Date' (+10 Days) > Current date is : $date")
        assertNotEquals(today, date.toString())
        date.addDay(-10)
        println("Calculated date before 10 days from 'the Date' (-10 Days) > Current date is : $date")
        assertEquals(today, date.toString())

        date.addYear(-1)
        println("-1 year > Current date is : $date")
        assertEquals("1978-06-25 01:02:03", date.toString())
        date.addMonth(-1)
        println("-1 month > Current date is : $date")
        assertEquals("1978-05-25 01:02:03", date.toString())
        date.addDay(1)
        println("+1 day > Current date is : $date")
        assertEquals("1978-05-26 01:02:03", date.toString())
        date.addHour(-1)
        println("-1 hour > Current date is : $date")
        assertEquals("1978-05-26 00:02:03", date.toString())
        date.addMinute(-3)
        println("-3 min > Current date is : $date")
        assertEquals("1978-05-25 23:59:03", date.toString())
        date.addSecond(-5)
        println("-5 sec > Current date is : $date")
        assertEquals("1978-05-25 23:58:58", date.toString())
    }

}

