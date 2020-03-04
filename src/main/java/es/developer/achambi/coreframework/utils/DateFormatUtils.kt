package es.developer.achambi.coreframework.utils

import java.text.SimpleDateFormat
import java.util.*

object DateFormatUtils {
    private const val YEAR_MONTH_DAY_HOUR_MINUTE_SECONDS = "yyyyMMdd_HHmmss"
    private const val DAY_MONTH_YEAR = "dd MMM yyyy"
    private const val RANGE_DATE_MONTH_DAY = "dd 'de' MMMM"
    fun formatDateDetailed(date: Date) : String {
        val dateFormat = SimpleDateFormat(YEAR_MONTH_DAY_HOUR_MINUTE_SECONDS, Locale.getDefault())
        return dateFormat.format(date)
    }

    fun formatDateSimplified(date: Date): String {
        val dateFormat = SimpleDateFormat(DAY_MONTH_YEAR, Locale.getDefault())
        return dateFormat.format(date)
    }

    fun formatTrimesterDate(month: Int): String {
        val dateFormat = SimpleDateFormat(RANGE_DATE_MONTH_DAY, Locale.getDefault())
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.MONTH, month)
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH))
        return dateFormat.format(calendar.time)
    }

    fun getStartAndEndDate(startMonth: Int, endMonth: Int): Pair<Date, Date>  {
        val calendar = Calendar.getInstance()

        calendar.set(Calendar.MONTH, startMonth)
        calendar.set(Calendar.DAY_OF_MONTH, 1)
        val startDate = calendar.time

        calendar.set(Calendar.MONTH, endMonth)
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH))
        val endDate = calendar.time

        return Pair<Date, Date>( startDate, endDate )
    }

    //TODO add string resource format and avoid harcoded
    fun formatDateRange(startMonth: Int, endMonth: Int): String {
        val dateFormat = SimpleDateFormat(RANGE_DATE_MONTH_DAY, Locale.getDefault())
        val dates = getStartAndEndDate(startMonth, endMonth)
        var formatted = "Desde el " + dateFormat.format(dates.first.time)
        formatted += " hasta el " + dateFormat.format(dates.second.time)
        return formatted
    }
}