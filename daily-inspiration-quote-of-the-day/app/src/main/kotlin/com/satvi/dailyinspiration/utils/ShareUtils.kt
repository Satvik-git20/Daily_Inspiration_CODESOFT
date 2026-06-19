package com.satvi.dailyinspiration.utils

import android.content.Context
import android.content.Intent
import com.satvi.dailyinspiration.model.Quote

object ShareUtils {
    fun shareQuote(context: Context, quote: Quote) {
        val message = "\"${quote.text}\"\n\n— ${quote.author}\n\nShared via Daily Inspiration App"
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, message)
        }
        context.startActivity(Intent.createChooser(intent, "Share quote"))
    }
}
