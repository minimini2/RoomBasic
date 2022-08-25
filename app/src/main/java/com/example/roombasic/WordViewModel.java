package com.example.roombasic;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class WordViewModel extends AndroidViewModel {

    private WordRepository wordRepository;

    public WordViewModel(@NonNull Application application) {
        super(application);
        wordRepository = new WordRepository(application);
    }

    public LiveData<List<Word>> getAllWordsLive() {
        return wordRepository.getAllWordsLive();
    }

    void insertWords(Word... words){
        wordRepository.insertWords(words);
    }
    void updataWords(Word... words){
        wordRepository.updataWords(words);
    }
    void deleteWords(Word... words){
        wordRepository.deleteWords(words);
    }
    void deleteAllWords(){
        wordRepository.deleteAllWords();
    }

}
