package com.satria.oop_ii.Database

import androidx.room.*
import com.satria.oop_ii.Database.Kopi

@Dao
interface KopiDao {
    @Insert
    suspend fun addKopi(kopi: Kopi)

    @Update
    suspend fun updateKopi(kopi: Kopi)

    @Delete
    suspend fun deleteKopi(kopi: Kopi)

    @Query("SELECT * FROM kopi")
    suspend fun getKopi(): List<Kopi>

    @Query("SELECT * FROM kopi WHERE id=:kopi_id")
    suspend fun getKopi(kopi_id: Int) : List<Kopi>
}