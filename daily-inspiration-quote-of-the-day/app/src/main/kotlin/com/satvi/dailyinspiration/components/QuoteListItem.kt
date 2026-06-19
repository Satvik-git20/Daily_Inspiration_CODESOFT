package com.satvi.dailyinspiration.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.satvi.dailyinspiration.model.Quote
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun QuoteListItem(
    quote: Quote,
    onFavoriteClick: () -> Unit,
    modifier: Modifier = Modifier,
    showSavedDate: Boolean = false
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(22.dp)
    ) {
        Column(
            modifier = Modifier
                .clickable(onClick = onFavoriteClick)
                .padding(18.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(
                text = "\"${quote.text}\"",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "— ${quote.author}",
                        style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold)
                    )
                    Text(
                        text = quote.category,
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.primary
                    )
                    if (showSavedDate && quote.savedAt != null) {
                        val formatter = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
                        Text(
                            text = "Saved ${formatter.format(Date(quote.savedAt))}",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.72f)
                        )
                    }
                }
                if (quote.isFavorite) {
                    Icon(
                        imageVector = Icons.Rounded.Favorite,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.tertiary
                    )
                }
            }
        }
    }
}
