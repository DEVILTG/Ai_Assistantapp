package com.example.mikaai.assistant

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mikaai.data.AssistantDao
import java.lang.IllegalArgumentException

class AssistantViewmodelFactory (
    private val dataSource: AssistantDao,
    private val application:Application) :ViewModelProvider.Factory

{
    @Suppress("UNCHECKED_CAST")

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(AssistantViewmodel::class.java)){
            return AssistantViewmodel(dataSource,application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel  class")
    }


}