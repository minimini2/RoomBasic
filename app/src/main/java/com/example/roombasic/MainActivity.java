package com.example.roombasic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.room.Room;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    WordDatabase wordDatabase;
    WordDao wordDao;
    TextView textView;
    Button insert,updata,delete,deleteAll;
    WordViewModel wordViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wordViewModel = ViewModelProviders.of(this).get(WordViewModel.class);

        wordDatabase = Room.databaseBuilder(this,WordDatabase.class,"word datadase")
                .allowMainThreadQueries()
                .build();
        wordDao = wordDatabase.getWordDao();
        textView = findViewById(R.id.textView);
        wordViewModel.getAllWordsLive().observe(this, new Observer<List<Word>>() {
            @Override
            public void onChanged(List<Word> words) {
                StringBuilder text = new StringBuilder();
                for (int i = 0; i < words.size(); i++){
                    Word word = words.get(i);
                    text.append(word.getId()).append(":").append(word.getWord()).append("=").append(word.getChineseMeaning()).append("\n");
                }
                textView.setText(text.toString());
            }
        });

        insert = findViewById(R.id.insert);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Word word1 = new Word("Hello","你好");
                Word word2 = new Word("World!","世界！");
                /**
                 * 实现了副线程的插入
                 * execute():在传入wordDao之后用于执行
                 */
                wordViewModel.insertWords(word1,word2);
            }
        });
        updata = findViewById(R.id.updata);
        updata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Word word = new Word("Hi!","你好啊！");
                word.setId(10);
                wordViewModel.updataWords(word);
            }
        });
        delete = findViewById(R.id.delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Word word = new Word("Hi!","你好啊！");
                word.setId(5);
                wordViewModel.deleteWords(word);
            }
        });
        deleteAll = findViewById(R.id.deleteAll);
        deleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wordViewModel.deleteAllWords();
            }
        });
    }

}