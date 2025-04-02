package com.example.todo.ui.calendar

/*import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo.data.Task
import com.example.todo.data.TaskDao
import kotlinx.coroutines.flow.*
import java.time.LocalDate
import java.time.YearMonth

class CalendarViewModel(private val taskDao: TaskDao) : ViewModel() {
    // Поток всех задач из базы данных
    private val _allTasks = taskDao.getAllTasks()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    // Выбранный месяц в календаре
    @SuppressLint("NewApi")
    private val _currentMonth = MutableStateFlow(YearMonth.now())
    val currentMonth: StateFlow<YearMonth> = _currentMonth

    // Выбранная дата в календаре
    private val _selectedDate = MutableStateFlow<LocalDate?>(null)
    val selectedDate: StateFlow<LocalDate?> = _selectedDate

    // Задачи для отображения (все или на выбранную дату)
    val tasks: StateFlow<List<Task>> = combine(
        _allTasks,
        _selectedDate
    ) { allTasks, selectedDate ->
        if (selectedDate == null) {
            allTasks
        } else {
            allTasks.filter { it.dueDate == selectedDate }
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    // Количество задач по дням для отображения маркеров в календаре
    val tasksByDate: StateFlow<Map<LocalDate, Int>> = _allTasks
        .map { tasks ->
            tasks
                .filter { it.dueDate != null }
                .groupBy { it.dueDate!! }
                .mapValues { it.value.size }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyMap()
        )

    // Обновление выбранного месяца
    fun setCurrentMonth(month: YearMonth) {
        _currentMonth.value = month
    }

    // Выбор даты в календаре
    fun selectDate(date: LocalDate) {
        _selectedDate.value = date
    }

    // Сброс выбранной даты
    fun clearSelectedDate() {
        _selectedDate.value = null
    }

    // Получение задач для конкретного месяца
    @SuppressLint("NewApi")
    fun getTasksForMonth(month: YearMonth): Flow<List<Task>> {
        val firstDay = month.atDay(1)
        val lastDay = month.atEndOfMonth()

        return _allTasks
            .map { tasks ->
                tasks.filter { task ->
                    task.dueDate != null &&
                            !task.dueDate!!.isBefore(firstDay) &&
                            !task.dueDate!!.isAfter(lastDay)
                }
            }
    }
}*/