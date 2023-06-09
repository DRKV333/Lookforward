package io.github.drkv333.lookforward.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.drkv333.lookforward.network.CalendarificService
import io.github.drkv333.lookforward.persistence.HolidayDao
import io.github.drkv333.lookforward.ui.details.DetailsRepository
import io.github.drkv333.lookforward.ui.main.MainRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideMainRepository(calendarificService: CalendarificService, holidayDao: HolidayDao): MainRepository =
        MainRepository(calendarificService, holidayDao)

    @Provides
    @Singleton
    fun provideDetailsRepository(holidayDao: HolidayDao): DetailsRepository =
        DetailsRepository(holidayDao)
}