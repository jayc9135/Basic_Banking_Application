package com.jayc.banking;

import android.database.Cursor;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SelectBeneficiaryActivity extends AppCompatActivity {

    public String senderId;
    RecyclerView recyclerView;
    List<model> dataHolder = new ArrayList<>();
    RecyclerView.LayoutManager layoutManager;
    SelectBeneficiaryAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_users);
        getSupportActionBar().hide();

        recyclerView = findViewById(R.id.users_recyclerview);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        senderId = getIntent().getExtras().getString("UserID");

        rowSelecteduser(senderId);
        adapter.senderID = senderId;


    }

    private void rowSelecteduser(String senderId) {
        dataHolder.clear();
        Cursor cursor = new Database(this).readselectuserdata(senderId);

        while(cursor.moveToNext()){
            model md = new model(cursor.getString(0),cursor.getString(1), cursor.getString(3),cursor.getString(6));
            this.dataHolder.add(md);
        }

        adapter = new SelectBeneficiaryAdapter(SelectBeneficiaryActivity.this, dataHolder);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}