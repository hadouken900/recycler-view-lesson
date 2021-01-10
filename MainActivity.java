package com.example.recyclerviewapplication;  //поменять пакет на свой

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //объекты которые есть на макете
    RecyclerView recyclerView;
    EditText stringEditText;

    //адаптер и лист с элементами ( пока пустой )
    ItemAdapter itemAdapter;
    ArrayList<StringItem> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //привязка объектов с макета по id
        recyclerView = findViewById(R.id.recycler);
        stringEditText = findViewById(R.id.stringEditText);

        //добавление элементов в список
        list.add(new StringItem("text 1"));
        list.add(new StringItem("text 2"));
        list.add(new StringItem("text 3"));

        //создание адаптера, записываем туда список с данными
        itemAdapter = new ItemAdapter(list);
        //привязываем адаптер к recyclerView
        recyclerView.setAdapter(itemAdapter);
        //вертикальный список
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //добавление разделителей между элементами
        recyclerView.addItemDecoration(new DividerItemDecoration(this,1));


    }
}