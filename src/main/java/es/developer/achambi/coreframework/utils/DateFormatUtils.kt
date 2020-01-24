package es.developer.achambi.coreframework.utils

import java.text.SimpleDateFormat
import java.util.*

object DateFormatUtils {
    private const val YEAR_MONTH_DAY_HOUR_MINUTE_SECONDS = "yyyyMMdd_HHmmss"
    private const val DAY_MONTH_YEAR = "dd MMM yyyy"
    fun formatDateDetailed(date: Date) : String {
        val dateFormat = SimpleDateFormat(YEAR_MONTH_DAY_HOUR_MINUTE_SECONDS, Locale.getDefault())
        return dateFormat.format(date)
    }

    fun formatDateSimplified(date: Date): String {
        val dateFormat = SimpleDateFormat(DAY_MONTH_YEAR, Locale.getDefault())
        return dateFormat.format(date)
    }
}