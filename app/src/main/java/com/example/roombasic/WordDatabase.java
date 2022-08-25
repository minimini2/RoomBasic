package com.example.roombasic;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
//singleton:确保一个类只有一个实例
@Database(entities = {Word.class},version = 1,exportSchema = false)
public abstract class WordDatabase extends RoomDatabase {
    private static WordDatabase INSTANCE;
    //synchronized:进一步强化singleton。如果有多个线程同时申请INSTANCE，可以保证不会碰撞，采用排队的机制。
    static synchronized WordDatabase getDatabase(Context context){
        if (INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),WordDatabase.class,"word database")
                    .build();
        }
        return INSTANCE;
    }
    public abstract WordDao getWordDao();
}
