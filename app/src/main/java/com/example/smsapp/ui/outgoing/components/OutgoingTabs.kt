package com.example.smsapp.ui.outgoing.components

import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.smsapp.viewmodel.TimeGroup

@Composable
fun OutgoingTabs(
    selected: TimeGroup,
    counts: Map<TimeGroup, Int>,
    onSelected: (TimeGroup) -> Unit
) {

    TabRow(selectedTabIndex = selected.ordinal) {

        TimeGroup.entries.forEach { group ->
            val count = counts[group] ?: 0
            val title = "${group.title()} ($count)"

            Tab(
                selected = selected == group,
                onClick = { onSelected(group) },
                text = {
                    val count = counts[group] ?: 0

                    androidx.compose.animation.AnimatedContent(
                        targetState = count,
                        label = ""
                    ) { animatedCount ->

                        Text("${group.title()} ($animatedCount)")
                    }
                }
            )
        }
    }
}

private fun TimeGroup.title(): String = when (this) {
    TimeGroup.TODAY -> "Today"
    TimeGroup.YESTERDAY -> "Yesterday"
    TimeGroup.THIS_MONTH -> "This Month"
    TimeGroup.OLDER -> "Older"
}