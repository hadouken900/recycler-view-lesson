package com.example.recyclerviewapplication; //поменять пакет на свой

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

//наш специально созданный класс который умеет работать с объектом StringItem
public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {

    //список который мы будем получать из MainActivity
    ArrayList<StringItem> list;

    //получаем его через конструктор
    public ItemAdapter(ArrayList<StringItem> list) {
        this.list = list;
    }


    //этот метод служит для того чтобы создавать элементы списка
    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.string_item, parent, false);
        return new ItemViewHolder(view);
    }

    //этот метод нужен для того, чтобы связать ItemViewHolder и данные которые лежат в списке
    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        StringItem s = list.get(position);
        holder.stringTextView.setText(s.getString());
    }

    //количество элементов в списке
    @Override
    public int getItemCount() {
        return list.size();
    }


    //этот внутренний класс нужен для того, чтобы связать данные и отображения на макете
    public class ItemViewHolder extends RecyclerView.ViewHolder{

        //элемент который есть в string_item
        TextView stringTextView;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            //привязываем его по id
            stringTextView = itemView.findViewById(R.id.stringTextView);
        }
    }
}
