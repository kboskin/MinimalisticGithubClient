package com.github.minimalistic.data.common.cache

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update
import io.reactivex.Completable

@Dao
interface BaseDao<T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(obj: T): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addAll(objects: List<T>): Completable

    @Update
    fun update(obj: T): Completable

    @Update
    fun updateAll(objects: List<T>): Completable

    @Delete
    fun delete(obj: T): Completable

    @Delete
    fun deleteAll(objects: List<T>): Completable
}