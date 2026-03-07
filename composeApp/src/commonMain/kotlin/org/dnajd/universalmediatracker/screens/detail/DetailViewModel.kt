package org.dnajd.universalmediatracker.screens.detail

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow
import org.dnajd.universalmediatracker.data.MuseumObject
import org.dnajd.universalmediatracker.data.MuseumRepository

class DetailViewModel(private val museumRepository: MuseumRepository) : ViewModel() {
    fun getObject(objectId: Int): Flow<MuseumObject?> =
        museumRepository.getObjectById(objectId)
}
