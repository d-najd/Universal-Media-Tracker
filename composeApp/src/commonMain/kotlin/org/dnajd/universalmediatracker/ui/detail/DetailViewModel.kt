package org.dnajd.universalmediatracker.ui.detail

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow
import org.dnajd.universalmediatracker.domain.MuseumObject
import org.dnajd.universalmediatracker.data.museum.MuseumRepository

class DetailViewModel(private val museumRepository: MuseumRepository) : ViewModel() {
    fun getObject(objectId: Int): Flow<MuseumObject?> =
        museumRepository.getObjectById(objectId)
}
