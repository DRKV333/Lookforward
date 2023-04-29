package io.github.drkv333.lookforward.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.drkv333.lookforward.persistence.AppDatabase
import io.github.drkv333.lookforward.persistence.HolidayDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PersistenceModule {
    @Provides
    @Singleton
    fun provideAppDatabase(application: Application): AppDatabase = Room
        .databaseBuilder(application, AppDatabase::class.java, "appDatabase")
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    @Singleton
    fun provideHolidayDao(appDatabase: AppDatabase): HolidayDao = appDatabase.holidayDao()
}