package com.example.todo.ui.calendar

/*import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.todo.data.Task
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
fun CalendarScreen(
    viewModel: CalendarViewModel = viewModel(),
    onTaskClick: (Int) -> Unit,
    onBack: () -> Unit
) {
    val tasks by viewModel.tasks.collectAsState()
    val currentMonth by viewModel.currentMonth.collectAsState()
    val selectedDate by viewModel.selectedDate.collectAsState()
    val tasksByDate by viewModel.tasksByDate.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        currentMonth.month.getDisplayName(
                            TextStyle.FULL,
                            Locale.getDefault()
                        ) + " " + currentMonth.year
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Назад")
                    }
                },
                actions = {
                    IconButton(onClick = {
                        viewModel.setCurrentMonth(currentMonth.minusMonths(1))
                    }) {
                        Icon(Icons.Default.ChevronLeft, contentDescription = "Предыдущий месяц")
                    }
                    IconButton(onClick = {
                        viewModel.setCurrentMonth(currentMonth.plusMonths(1))
                    }) {
                        Icon(Icons.Default.ChevronRight, contentDescription = "Следующий месяц")
                    }
                }
            )
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            // Календарь
            HorizontalCalendar(
                currentMonth = currentMonth,
                tasksByDate = tasksByDate,
                onDateSelected = { date ->
                    viewModel.selectDate(date)
                },
                modifier = Modifier.padding(horizontal = 8.dp)
            )

            // Список задач на выбранную дату
            selectedDate?.let { date ->
                val dailyTasks = tasks.filter { it.dueDate == date }
                Text(
                    text = "Задачи на ${date.format(DateTimeFormatter.ofPattern("dd MMMM yyyy"))}",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(16.dp)
                )

                if (dailyTasks.isEmpty()) {
                    Text(
                        text = "Нет задач на этот день",
                        modifier = Modifier.padding(16.dp)
                    )
                } else {
                    LazyColumn {
                        items(dailyTasks) { task ->
                            CalendarTaskItem(
                                task = task,
                                onClick = { onTaskClick(task.id) }
                            )
                        }
                    }
                }
            } ?: run {
                Text(
                    text = "Выберите дату для просмотра задач",
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}
@SuppressLint("NewApi")
@Composable
private fun HorizontalCalendar(
    currentMonth: YearMonth,
    tasksByDate: Map<LocalDate, Int>,
    onDateSelected: (LocalDate) -> Unit,
    modifier: Modifier = Modifier
) {
    val dates = remember(currentMonth) {
        currentMonth.getDatesInMonth()
    }

    val scrollState = rememberScrollState()

    Column(modifier = modifier) {
        Row(
            modifier = Modifier
                .horizontalScroll(scrollState)
                .padding(vertical = 8.dp)
        ) {
            dates.forEach { date ->
                val hasTasks = tasksByDate.containsKey(date)
                val tasksCount = tasksByDate[date] ?: 0
                val isToday = date == LocalDate.now()

                Box(
                    modifier = Modifier
                        .padding(4.dp)
                        .clickable { onDateSelected(date) }
                        .background(
                            color = when {
                                isToday -> MaterialTheme.colorScheme.primary.copy(alpha = 0.2f)
                                hasTasks -> MaterialTheme.colorScheme.secondary.copy(alpha = 0.1f)
                                else -> Color.Transparent
                            },
                            shape = CircleShape
                        )
                        .size(48.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = date.dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.getDefault()),
                            style = MaterialTheme.typography.labelSmall
                        )
                        Text(
                            text = date.dayOfMonth.toString(),
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = if (isToday) FontWeight.Bold else FontWeight.Normal
                        )
                        if (hasTasks) {
                            Box(
                                modifier = Modifier
                                    .size(6.dp)
                                    .clip(CircleShape)
                                    .background(MaterialTheme.colorScheme.secondary)
                            )
                        }
                    }
                }
            }
        }

        // Индикатор количества задач под датой
        if (tasksByDate.isNotEmpty()) {
            Row(
                modifier = Modifier
                    .horizontalScroll(scrollState)
                    .padding(bottom = 8.dp)
            ) {
                dates.forEach { date ->
                    Box(
                        modifier = Modifier
                            .padding(4.dp)
                            .size(48.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        tasksByDate[date]?.let { count ->
                            if (count > 0) {
                                Text(
                                    text = "$count",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = MaterialTheme.colorScheme.secondary
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
@SuppressLint("NewApi")
private fun YearMonth.getDatesInMonth(): List<LocalDate> {
    return (1..lengthOfMonth()).map { day -> LocalDate.of(year, month, day) }
}*/