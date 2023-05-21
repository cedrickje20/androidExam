package com.example.practice;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.viewHolder> {
    private Context mcontext;
    private ArrayList<Item> items;

    public Adapter(Context context, ArrayList<Item> itemList){
        mcontext = context;
        items = itemList;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mcontext).inflate(R.layout.item,parent,false);
        return new viewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        Item currentItem = items.get(position);

        String answer;

        String question = currentItem.getQuestion();
        String optionA = currentItem.getChoiceA();
        String optionB = currentItem.getChoiceB();
        String optionC = currentItem.getChoiceC();
        String optionD = currentItem.getChoiceD();

        holder.question.setText(question);
        holder.choiceA.setText(optionA);
        holder.choiceB.setText(optionB);
        holder.choiceC.setText(optionC);
        holder.choiceD.setText(optionD);

        //int radioId = holder.radioGroup.getCheckedRadioButtonId();



    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{
        public TextView question;
        public RadioButton choiceA;
        public RadioButton choiceB;
        public RadioButton choiceC;
        public RadioButton choiceD;
        public RadioGroup radioGroup;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            question = itemView.findViewById(R.id.question);
            choiceA = itemView.findViewById(R.id.optionA);
            choiceB = itemView.findViewById(R.id.optionB);
            choiceC = itemView.findViewById(R.id.optionC);
            choiceD = itemView.findViewById(R.id.optionD);
            radioGroup = itemView.findViewById(R.id.radioGroup);

        }
    }
}
