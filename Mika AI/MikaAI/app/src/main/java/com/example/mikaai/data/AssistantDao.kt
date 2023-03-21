package com.example.mikaai.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface AssistantDao {

    @Insert
    fun insert(assistant: Assistant)

    @Update
    fun update(assistant: Assistant)

    @Query(value = "SELECT * FROM assistant_messages_table WHERE assistantId = :key")
    fun get(key: Long): Assistant?

    @Query(value = "DELETE FROM assistant_messages_table")
    fun clear()

    @Query(value = "SELECT * FROM assistant_messages_table ORDER By assistantId DESC")
    fun getALLMessages(): LiveData<List<Assistant>>

    @Query(value = "SELECT * FROM assistant_messages_table ORDER By assistantId DESC LIMIT 1")
    fun getCurrentMessage(): Assistant?

}
