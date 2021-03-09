package dev.iamspathan.tasker.di

import android.app.Application
import androidx.room.PrimaryKey
import androidx.room.Room
import androidx.room.RoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dev.iamspathan.tasker.data.TaskDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Qualifier
import javax.inject.Singleton
import kotlin.annotation.AnnotationRetention.RUNTIME

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(application: Application,
        callback : TaskDatabase.CallBack
    ) = Room.databaseBuilder(application, TaskDatabase::class.java, "task_database")
            .fallbackToDestructiveMigration()
            .addCallback(callback)
            .build()

    @Provides
    fun provideTaskDao(taskDatabase: TaskDatabase) = taskDatabase.taskDao()

    @ApplicationScope
    @Provides
    @Singleton
    fun provideApplicationScope() = CoroutineScope(SupervisorJob())

}
@Retention(RUNTIME)
@Qualifier
annotation class ApplicationScope