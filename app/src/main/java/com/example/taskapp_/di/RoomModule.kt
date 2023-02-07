package com.example.taskapp_.di

import android.content.Context
import androidx.room.Room
import com.example.taskapp_.data.AppDatabase
import com.example.taskapp_.data.TaskDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    @Singleton
    fun provideRoomDatabase(@ApplicationContext context: Context):AppDatabase{
        return Room.databaseBuilder(context,AppDatabase::class.java,"Todo")
            .allowMainThreadQueries()
            .build()
    }

    @Provides
    fun userDao(appDatabase: AppDatabase):TaskDao = appDatabase.taskDao()
}