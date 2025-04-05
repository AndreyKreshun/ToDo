package com.example.todo.ui.calendar

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.todo.data.Category
import com.example.todo.data.Priority
import com.example.todo.data.Task
import java.time.LocalDate
import java.time.format.DateTimeFormatter


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CalendarScreen(tasks: List<Task>) {
    val today = remember { LocalDate.now() }
    val currentMonth = remember { today.withDayOfMonth(1) }
    val daysInMonth = remember { currentMonth.lengthOfMonth() }
    val dayOfWeekOffset = remember { currentMonth.dayOfWeek.value % 7 }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = currentMonth.format(DateTimeFormatter.ofPattern("LLLL yyyy")),
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(7),
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(4.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            items(dayOfWeekOffset) {
                Box(modifier = Modifier.size(40.dp)) {}
            }
            items(daysInMonth) { index ->
                val date = currentMonth.plusDays(index.toLong())
                val hasTask = tasks.any { it.dueDate == date }

                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(if (hasTask) Color(0xFF90CAF9) else Color.Transparent)
                        .border(1.dp, Color.Gray, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "${index + 1}", style = MaterialTheme.typography.bodySmall)
                }
            }
        }
    }
}
