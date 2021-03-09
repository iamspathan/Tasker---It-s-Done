package dev.iamspathan.tasker.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import dev.iamspathan.tasker.di.ApplicationScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

@Database(entities = [Task::class], version = 1, exportSchema = true)
abstract class TaskDatabase  : RoomDatabase(){

    abstract fun taskDao() : TaskDao


    class CallBack @Inject constructor(
        private val database:Provider<TaskDatabase>,
        @ApplicationScope private val applicationScope: CoroutineScope//Lazy Initialization
    ) : RoomDatabase.Callback(){

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            val dao = database.get().taskDao()
            applicationScope.launch {
                dao.insert(Task(name = "Wash the dishes"))
                dao.insert(Task(name = "Do the Laundry"))
                dao.insert(Task(name = "Buy Groceries", important = true))
                dao.insert(Task(name = "Prepare Food", completed = true))
                dao.insert(Task(name = "Call mom"))
                dao.insert(Task(name = "Visit Grandma", completed = true))
                dao.insert(Task(name = "Repair My Bike"))
                dao.insert(Task(name = "Call Elon Musk"))
            }





        }
    }
}