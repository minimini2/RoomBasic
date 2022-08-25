package com.example.roombasic;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface WordDao {
    @Insert
    void insertWords(Word...words);

    @Update
    void updateWord(Word...words);

    @Delete
    void deleteWord(Word...words);

    @Query("DELETE FROM WORD")
    void deleteAllWords();

    @Query("SELECT * FROM WORD ORDER BY ID DESC")
    List<Word> getAllWords();

    @Query("SELECT * FROM WORD where id= :id")
    Word selectById(int id);

    @Query("UPDATE WORD set english_word = :word where id= :id")
    void changeWordById(String word,int id);

    @Query("UPDATE WORD set english_word = :word where id= :id")
    void changeWordById2(String word,int id);

}
