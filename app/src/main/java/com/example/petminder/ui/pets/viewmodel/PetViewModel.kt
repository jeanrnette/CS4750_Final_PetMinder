package com.example.petminder.ui.pets.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.petminder.data.AppDatabase
import com.example.petminder.data.pet.PetEntity
import com.example.petminder.data.pet.PetRepository
import kotlinx.coroutines.launch

class PetViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: PetRepository
    val allPets: LiveData<List<PetEntity>>

    init {
        val petDao = AppDatabase.getDatabase(application).petDao()
        repository = PetRepository(petDao)
        allPets = repository.getAllPets()
    }

    fun addPet(pet: PetEntity) {
        viewModelScope.launch {
            repository.insertPet(pet)
        }
    }

    fun updatePet(pet: PetEntity) {
        viewModelScope.launch {
            repository.updatePet(pet)
        }
    }

    fun deletePet(pet: PetEntity) {
        viewModelScope.launch {
            repository.deletePet(pet)
        }
    }
}
