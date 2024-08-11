package com.example.jokeoftheday.di

import android.content.Context
import androidx.room.Room
import com.example.jokeoftheday.data.dao.JokeDao
import com.example.jokeoftheday.data.room.AppDatabase
import com.example.jokeoftheday.domain.JokeRepositoryImpl
import com.example.jokeoftheday.domain.repository.ApiService
import com.example.jokeoftheday.domain.repository.JokeRepository
import com.example.jokeoftheday.domain.usecases.FetchJokeUseCase
import com.example.jokeoftheday.domain.usecases.GetLatestJokesUseCase
import com.example.jokeoftheday.domain.usecases.InsertJokeUseCase
import com.example.jokeoftheday.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "jokes_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideJokeDao(database: AppDatabase): JokeDao {
        return database.jokeDao()
    }

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    fun provideJokeRepository(
        jokeDao: JokeDao,
        apiService: ApiService,
        @ApplicationContext context: Context
    ): JokeRepository {
        return JokeRepositoryImpl(jokeDao, apiService, context)
    }

    @Provides
    @Singleton
    fun provideGetLatestJokesUseCase(repository: JokeRepository): GetLatestJokesUseCase {
        return GetLatestJokesUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideInsertJokeUseCase(repository: JokeRepository): InsertJokeUseCase {
        return InsertJokeUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideFetchJokeUseCase(repository: JokeRepository): FetchJokeUseCase {
        return FetchJokeUseCase(repository)
    }
}