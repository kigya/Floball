package com.tms.floball.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tms.floball.model.FloballGoModelService

class FloballViewModel : ViewModel() {
    val isQuestionSuccessLiveData = MutableLiveData<Unit>()
    val isQuestionFailedLiveData = MutableLiveData<Unit>()
    val questionLiveData = MutableLiveData<String>()

    private val floballGoModelService: FloballGoModelService = FloballGoModelService()

    fun onGoClicked(question: String) {
        val isSuccess = floballGoModelService.onGoClicked(question)
        if (isSuccess) isQuestionSuccessLiveData.postValue(Unit)
        else isQuestionFailedLiveData.postValue(Unit)
    }
}