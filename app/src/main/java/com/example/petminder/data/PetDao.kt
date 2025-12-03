package com.example.petminder.data.pet

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface PetDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPet(pet: PetEntity)

    @Update
    suspend fun updatePet(pet: PetEntity)

    @Delete
    suspend fun deletePet(pet: PetEntity)

    @Query("SELECT * FROM pets ORDER BY id ASC")
    fun getAllPets(): LiveData<List<PetEntity>>

    @Query("SELECT * FROM pets WHERE id = :id LIMIT 1")
    suspend fun getPetById(id: Int): PetEntity?
}
