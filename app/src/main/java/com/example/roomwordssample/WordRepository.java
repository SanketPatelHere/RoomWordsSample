package com.example.roomwordssample;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class WordRepository {
    private WordDao mWordDao;
    private LiveData<List<Word>> mAllWords;

    WordRepository(Application application) {
        WordRoomDatabase db = WordRoomDatabase.getDatabase(application);
        mWordDao = db.wordDao();
        mAllWords = mWordDao.getAllWords();
    }
    LiveData<List<Word>> getAllWords() {
        return mAllWords;
    }
    public void insert (Word word) {
        new insertAsyncTask(mWordDao).execute(word);
    }
    //MainActivity mathi
    //call from WordViewModel =>
    //WordRoomDatabase => WordRepository => WordDao
    //delete all records
    public void deleteAll()
    {
        new deleteAllWordsAsyncTask(mWordDao).execute();
    }

    //delete specific 1 record
    public void deleteWord(Word word)
    {
        new deleteWordAsyncTask(mWordDao).execute(word);
    }

    private static class insertAsyncTask extends AsyncTask<Word, Void, Void> {
        private WordDao mAsyncTaskDao;
        insertAsyncTask(WordDao dao) {
            mAsyncTaskDao = dao;
        }
        @Override
        protected Void doInBackground(final Word... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
    private static class deleteAllWordsAsyncTask extends AsyncTask<Void, Void, Void>
    {
        private WordDao mAsynbcTaskDao;
        deleteAllWordsAsyncTask(WordDao dao)
        {
            mAsynbcTaskDao = dao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            mAsynbcTaskDao.deleteAll();
            return null;
        }
    }
    private static class deleteWordAsyncTask extends AsyncTask<Word, Void, Void>
    {
        private WordDao mAsyncTaskDao;
        deleteWordAsyncTask(WordDao dao)
        {
            mAsyncTaskDao = dao;
        }
        @Override
        protected Void doInBackground(final Word... parmas) {
            mAsyncTaskDao.deleteWord(parmas[0]);
            return null;
        }
    }
}
