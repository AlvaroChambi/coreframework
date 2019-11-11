package es.developer.achambi.coreframework.utils

import java.text.SimpleDateFormat
import java.util.*

object DateFormatUtils {
    private val YEAR_MONTH_DAY_HOUR_MINUTE_SECONDS = "yyyyMMdd_HHmmss"
    fun formatDateDetailed(date: Date) : String {
        val dateFormat = SimpleDateFormat(YEAR_MONTH_DAY_HOUR_MINUTE_SECONDS, Locale.getDefault())
        return dateFormat.format(date)
    }
}