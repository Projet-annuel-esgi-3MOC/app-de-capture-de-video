package com.esgi.students.camerax.services

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.esgi.students.camerax.bo.Participation

class GlobalViewModel(application: Application) : AndroidViewModel(application) {
    private val _participation = MutableLiveData<Participation?>()
    val participation: LiveData<Participation?>
        get() = _participation

    fun setParticipation(newParticipation: Participation?) {
        _participation.value = newParticipation
    }
}
