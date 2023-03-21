package com.example.mikaai.assistant

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.mikaai.data.Assistant
import com.example.mikaai.data.AssistantDao
import kotlinx.coroutines.*

class AssistantViewmodel (
    val database: AssistantDao,
            application: Application) :AndroidViewModel(application)
{
                private var viewModeJob = Job()

    override fun onCleared() {
        super.onCleared()
        viewModeJob.cancel()
    }
    //initializer
    init {
        initializeCurrentMessage()

    }

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModeJob)

    private var curreMessage = MutableLiveData<Assistant?>()
    val messages = database.getALLMessages()



    private fun initializeCurrentMessage() {
        uiScope.launch { curreMessage.value = getCurrentMessageFromDatabase() }
    }

    private suspend fun getCurrentMessageFromDatabase(): Assistant?{
        return withContext(Dispatchers.IO){
            var message = database.getCurrentMessage()
            if(message?.assistant_message== "DEFAULT_MESSAGE" || message?.human_message == "DEFAULT_MESSAGE")
            {
                message = null
            }
            message
        }

    }

    fun sendMessageToDatabase(assistantMessage: String, humanMessage: String){
        uiScope.launch {
            val newAssistant= Assistant()
            newAssistant.assistant_message = assistantMessage
            newAssistant.human_message = humanMessage
            insert(newAssistant)
            curreMessage.value=getCurrentMessageFromDatabase()

        }
    }


    private suspend fun insert(message: Assistant){
        withContext(Dispatchers.IO){
            database.insert(message)
        }
    }


    private suspend fun update(message: Assistant){
        withContext(Dispatchers.IO){
            database.update(message)
        }
    }

    fun onClear()
    {
        uiScope.launch { clear()
            curreMessage.value= null}
    }

    private suspend fun clear(){
        withContext(Dispatchers.IO){
            database.clear()
        }
    }

}