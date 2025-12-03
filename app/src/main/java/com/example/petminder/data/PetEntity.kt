package com.example.petminder.data.pet

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pets")
data class PetEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val type: String,
    val age: String,
    val birthday: String,
    val weight: String,
    val height: String,
    val vaccines: String,
    val imageUri: String? = null
)
