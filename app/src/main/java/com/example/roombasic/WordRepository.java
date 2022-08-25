package com.example.roombasic;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class WordRepository {
    private final WordDao wordDao;
    LiveData<List<Word>> allWordsLive;

    public WordRepository(Context context) {
        WordDatabase wordDatabase = WordDatabase.getDatabase(context.getApplicationContext());
        wordDao = wordDatabase.getWordDao();
        allWordsLive = wordDao.getAllWordsLive();
    }

    public LiveData<List<Word>> getAllWordsLive() {
        return allWordsLive;
    }

    void insertWords(Word... words) {
        new InsertAsyncTask(wordDao).execute(words);
    }

    void updataWords(Word... words) {
        new UpdataAsyncTask(wordDao).execute(words);
    }

    void deleteWords(Word... words) {
        new DeleteAsyncTask(wordDao).execute(words);
    }

    void deleteAllWords(Word... words) {
        new DeleteAllAsyncTask(wordDao).execute();
    }

    /**
     * AsyncTask:非同步的意思
     * 第二个参数是用来报告进度
     * 第三个参数是用来报告结果
     */
    static class InsertAsyncTask extends AsyncTask<Word, Void, Void> {
        private WordDao wordDao;

        InsertAsyncTask(WordDao wordDao) {
            this.wordDao = wordDao;
        }

        /**
         * 在后台上进行什么操作
         *
         * @param words
         * @return
         */
        @Override
        protected Void doInBackground(Word... words) {
            wordDao.insertWords(words);
            return null;
        }

    }

    static class UpdataAsyncTask extends AsyncTask<Word, Void, Void> {
        private WordDao wordDao;

        UpdataAsyncTask(WordDao wordDao) {
            this.wordDao = wordDao;
        }

        /**
         * 在后台上进行什么操作
         *
         * @param words
         * @return
         */
        @Override
        protected Void doInBackground(Word... words) {
            wordDao.updateWord(words);
            return null;
        }

    }

    static class DeleteAsyncTask extends AsyncTask<Word, Void, Void> {
        private WordDao wordDao;

        DeleteAsyncTask(WordDao wordDao) {
            this.wordDao = wordDao;
        }

        /**
         * 在后台上进行什么操作
         *
         * @param words
         * @return
         */
        @Override
        protected Void doInBackground(Word... words) {
            wordDao.deleteWord(words);
            return null;
        }

    }

    static class DeleteAllAsyncTask extends AsyncTask<Void, Void, Void> {
        private WordDao wordDao;

        DeleteAllAsyncTask(WordDao wordDao) {
            this.wordDao = wordDao;
        }

        /**
         * 在后台上进行什么操作
         *
         * @param voids
         * @return
         */
        @Override
        protected Void doInBackground(Void... voids) {
            wordDao.deleteAllWords();
            return null;
        }

    }

}
