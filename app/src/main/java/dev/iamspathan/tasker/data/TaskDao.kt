package dev.iamspathan.tasker.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import dev.iamspathan.tasker.data.SortOrder.BY_DATE
import dev.iamspathan.tasker.data.SortOrder.BY_NAME
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    fun getTask(query: String, sortOrder: SortOrder, hideCompleted: Boolean): Flow<List<Task>> {
      return when(sortOrder){
          BY_DATE -> getTaskSortedByDateCreated(query,hideCompleted)
          BY_NAME -> getTaskSortedByName(query, hideCompleted)
      }

    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(task:Task)

    @Update
    suspend fun update(task: Task)

    @Delete
    suspend fun delete(task: Task)

    @Query("SELECT * FROM task_table WHERE (completed != :hideCompleted OR completed = 0) AND  name LIKE '%' || :searchQuery || '%'  ORDER BY important DESC, name")
    fun getTaskSortedByName(searchQuery : String, hideCompleted:Boolean): Flow<List<Task>>

    @Query("SELECT * FROM task_table WHERE (completed != :hideCompleted OR completed = 0) AND  name LIKE '%' || :searchQuery || '%'  ORDER BY important DESC, created")
    fun getTaskSortedByDateCreated(searchQuery : String, hideCompleted:Boolean): Flow<List<Task>>

    @Query("DELETE FROM task_table WHERE completed = 1")
    suspend fun deleteCompletedTask()

}