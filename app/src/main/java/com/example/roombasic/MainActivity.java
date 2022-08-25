package com.example.roombasic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    WordDatabase wordDatabase;
    WordDao wordDao;
    TextView textView;
    Button insert,updata,delete,query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wordDatabase = Room.databaseBuilder(this,WordDatabase.class,"word datadase")
                .allowMainThreadQueries()
                .build();
        wordDao = wordDatabase.getWordDao();
        textView = findViewById(R.id.textView);
        updataView();

        insert = findViewById(R.id.insert);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Word word1 = new Word("Hello","你好");
                Word word2 = new Word("World!","世界！");
                wordDao.insertWords(word1,word2);
                updataView();
            }
        });
        updata = findViewById(R.id.updata);
        updata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Word word = new Word("Hi!","你好啊！");
                word.setId(10);
                wordDao.updateWord(word);
                updataView();
            }
        });
        delete = findViewById(R.id.delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wordDao.deleteAllWords();
                updataView();
            }
        });
        query = findViewById(R.id.query);
        query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = 10;
                Word word = new Word("mini",wordDao.selectById(id).getChineseMeaning());
                wordDao.changeWordById(word.getWord(),id);

                updataView();
            }
        });
    }

    void updataView(){
        List<Word> list = wordDao.getAllWords();//获取list
        String text = "";
        for (int i = 0; i < list.size(); i++){
            Word word = list.get(i);
            text += word.getId() + ":" + word.getWord() + "=" + word.getChineseMeaning() + "\n";
        }
        textView.setText(text);
    }
}