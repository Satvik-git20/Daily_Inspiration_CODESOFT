package com.satvi.dailyinspiration.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.satvi.dailyinspiration.components.GradientBackground
import com.satvi.dailyinspiration.components.SectionHeader
import com.satvi.dailyinspiration.components.SettingsRow
import com.satvi.dailyinspiration.data.preferences.FontScale
import com.satvi.dailyinspiration.ui.theme.AppTheme
import com.satvi.dailyinspiration.utils.NotificationScheduler
import com.satvi.dailyinspiration.viewmodel.SettingsViewModel

@Composable
fun SettingsScreen(viewModel: SettingsViewModel) {
    val preferences by viewModel.preferences.collectAsStateWithLifecycle()
    val context = LocalContext.current
    val showAbout = remember { mutableStateOf(false) }
    val showPrivacy = remember { mutableStateOf(false) }

    GradientBackground {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Spacer(modifier = Modifier.height(24.dp))
            SectionHeader(
                title = "Settings",
                subtitle = "Shape the app to match your rhythm."
            )
            
            Card {
                Column(
                    modifier = Modifier.padding(18.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Text(
                        text = "App Theme",
                        style = MaterialTheme.typography.titleLarge
                    )
                    AppTheme.entries.forEach { theme ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(vertical = 4.dp)
                        ) {
                            RadioButton(
                                selected = preferences.theme == theme,
                                onClick = { viewModel.setTheme(theme) }
                            )
                            Text(
                                text = theme.name.replace("_", " ").lowercase().replaceFirstChar(Char::titlecase),
                                style = MaterialTheme.typography.bodyLarge,
                                modifier = Modifier.padding(start = 8.dp)
                            )
                        }
                    }
                }
            }

            SettingsRow(
                title = "Daily Quote Notifications",
                subtitle = "Receive your inspiration at 9:00 AM.",
                checked = preferences.notificationsEnabled,
                onCheckedChange = {
                    viewModel.setNotifications(it)
                    if (it) NotificationScheduler.scheduleDaily(context) else NotificationScheduler.cancel(context)
                }
            )
            Card {
                Column(
                    modifier = Modifier.padding(18.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Text(
                        text = "Font Size",
                        style = MaterialTheme.typography.titleLarge
                    )
                    FontScale.entries.forEach { size ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(vertical = 4.dp)
                        ) {
                            RadioButton(
                                selected = preferences.fontScale == size,
                                onClick = { viewModel.setFontScale(size.value) }
                            )
                            Text(
                                text = size.name.lowercase().replaceFirstChar(Char::titlecase),
                                style = MaterialTheme.typography.bodyLarge,
                                modifier = Modifier.padding(start = 8.dp)
                            )
                        }
                    }
                }
            }
            Card(onClick = { showAbout.value = true }) {
                Text("About App", modifier = Modifier.padding(18.dp), style = MaterialTheme.typography.titleLarge)
            }
            Card(onClick = { showPrivacy.value = true }) {
                Text("Privacy Policy", modifier = Modifier.padding(18.dp), style = MaterialTheme.typography.titleLarge)
            }
        }
    }

    if (showAbout.value) {
        AlertDialog(
            onDismissRequest = { showAbout.value = false },
            confirmButton = {
                TextButton(onClick = { showAbout.value = false }) { Text("Close") }
            },
            title = { Text("About Daily Inspiration") },
            text = { Text("Daily Inspiration is an offline wellness companion that delivers a beautiful daily quote, searchable categories, favorites, and thoughtful settings in a calm Compose experience.") }
        )
    }

    if (showPrivacy.value) {
        AlertDialog(
            onDismissRequest = { showPrivacy.value = false },
            confirmButton = {
                TextButton(onClick = { showPrivacy.value = false }) { Text("Close") }
            },
            title = { Text("Privacy Policy") },
            text = { Text("Daily Inspiration works fully offline. Your favorites and preferences stay on your device, and quote sharing only uses Android's system share sheet when you choose to share.") }
        )
    }
}
