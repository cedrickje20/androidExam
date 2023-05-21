package com.example.practice;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private Button submitBtn;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private Adapter adapter;
    private ArrayList<Item> itemsList;
    ArrayList<String> answer = new ArrayList<>();
    String ans;

    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.RecView);
        submitBtn = findViewById(R.id.submitBtn);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        itemsList = new ArrayList<>();
        requestQueue = Volley.newRequestQueue(this);
        parseJSON();

        submitBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AfterSubmit.class);

              /*  int radioID = Adapter.viewHolder.radioGroup.getCheckedRadioButtonId();
                radioButton = findViewById(radioID);
                ans = radioButton.getText().toString();
                Log.d("Received Item", ans);*/

                startActivity(intent);
            }
        });





    }


    private void parseJSON() {
        String url = "https://api.npoint.io/e2221e56a1823eb27a19";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("pangutana");
                            for (int i = 0 ; i < jsonArray.length(); i ++){
                                JSONObject pangutanas = jsonArray.getJSONObject(i);
                                String question = pangutanas.getString("question");
                                String choiceA = pangutanas.getString("a");
                                String choiceB = pangutanas.getString("b");
                                String choiceC = pangutanas.getString("c");
                                String choiceD = pangutanas.getString("d");

                                itemsList.add(new Item(question,choiceA,choiceB,choiceC,choiceD));
                            }
                            adapter = new Adapter(getApplicationContext(), itemsList);
                            recyclerView.setAdapter(adapter);
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        requestQueue.add(request);
    }
}