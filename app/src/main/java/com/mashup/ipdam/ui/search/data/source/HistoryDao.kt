package com.mashup.ipdam.ui.search.data.source

import androidx.room.*
import com.mashup.ipdam.entity.history.History
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface HistoryDao {
    @Query("SELECT * FROM History ORDER BY id DESC")
    fun getAll(): Single<List<History>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertHistory(history: History): Completable

    @Query("DELETE FROM HISTORY WHERE address = :address")
    fun deleteHistoryWithAddress(address: String) : Completable

    @Delete
    fun deleteHistory(history: History): Completable

    @Query("DELETE FROM History")
    fun deleteAll(): Completable
}