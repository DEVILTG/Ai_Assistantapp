package com.example.mikaai.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "assistant_messages_table")
data class Assistant (

    @field:PrimaryKey(autoGenerate = true)
    public final var assistantId: Long = 0L,

            @field:ColumnInfo(name = "assistant_message")
            public final var assistant_message: String= "DEFAULT_MESSAGE",

                    @field:ColumnInfo(name = "human_message")
                    public final var human_message: String = "DEFAULT_MESSAGE"
)