package com.example.dog_breeds.di

import android.content.Context
import androidx.room.Room
import com.example.dog_breeds.data.local.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideApplicationDatabase(@ApplicationContext appContext: Context) =
        Room.databaseBuilder(appContext, AppDatabase::class.java, "DogBreeds_Database")
            .allowMainThreadQueries().build()


    @Provides
    @Singleton
    fun provideDogBreedDao(appDatabase: AppDatabase) = appDatabase.breedDog()

}