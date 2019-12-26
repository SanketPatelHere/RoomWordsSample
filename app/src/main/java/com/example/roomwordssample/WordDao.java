package com.example.roomwordssample;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;
@Dao
public interface WordDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Word word);

    @Query("DELETE FROM word_table")  //specific id
    void deleteAll();

    @Query("SELECT * from word_table ORDER BY word ASC")
    //List<Word> getAllWords();           //simple
    LiveData<List<Word>> getAllWords();   //using livedata

    @Query("select * from word_table LIMIT 1")
    Word[] getAnyWord();

    @Delete
    void deleteWord(Word word); //object compare by checking row
}
