package kampukter.mtshop.data.repository

import androidx.room.*

@Dao
interface BasicDao<in T> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(t: T): Long
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(items: List<T>)
    @Delete
    fun delete(type : T)
    @Update
    fun update(type : T)
}