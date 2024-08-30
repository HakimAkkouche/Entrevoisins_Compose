package com.haksoftware.entrevoisins_kotlin_compose.data.database

import android.content.Context
import androidx.room.Room
import com.haksoftware.entrevoisins_kotlin_compose.data.dao.NeighbourDao
import com.haksoftware.entrevoisins_kotlin_compose.data.repository.NeighboursRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): NeighboursDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            NeighboursDatabase::class.java,
            "EntreVoisins.db"
        )
            .createFromAsset("database/EntreVoisinsDatabase.db")
            .build()
    }

    @Provides
    fun provideNeighbourDao(database: NeighboursDatabase): NeighbourDao {
        return database.NeighbourDao()
    }

    @Provides
    @Singleton
    fun provideRepository(@ApplicationContext context: Context,
                          ioDispatcher: CoroutineDispatcher,
                          neighbourDao: NeighbourDao,
    ): NeighboursRepository {
        return NeighboursRepository(context.applicationContext,ioDispatcher,neighbourDao)
    }

    @Provides
    fun provideIoDispatcher(): CoroutineDispatcher = Dispatchers.IO
}
