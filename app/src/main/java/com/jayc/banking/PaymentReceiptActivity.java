package com.jayc.banking;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class PaymentReceiptActivity extends AppCompatActivity {

    CardView cardSuccess;
    TextView successText;
    TextView transactionId, senderAcc, receiverAcc, amount, date, status;
    String TransactionID, pystatus;
    String senderId, receiverId;

    Button homeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_receipt);
        getSupportActionBar().hide();

        cardSuccess = findViewById(R.id.card_success);
        successText = findViewById(R.id.success_text);
        transactionId = findViewById(R.id.transactionid);
        senderAcc = findViewById(R.id.sender_account);
        receiverAcc = findViewById(R.id.receiver_account);
        amount = findViewById(R.id.amount);
        date = findViewById(R.id.date_time);
        status = findViewById(R.id.status);

        TransactionID = getIntent().getExtras().getString("TransactionID");
        pystatus = getIntent().getExtras().getString("PaymentStatus");

        if (pystatus.equals("Failed")) {
            cardSuccess.setBackgroundColor(Color.RED);
            successText.setText("Payment Unsuccessful!");
            status.setTextColor(Color.parseColor("#fb7268"));
        }

        showTransData(TransactionID);

        homeBtn = findViewById(R.id.home_button);
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PaymentReceiptActivity.this, MainActivity.class));
            }
        });

    }

    private void showTransData(String transactionID) {
        Cursor cursor = new Database(this).readtransferdata(transactionID);
        while (cursor.moveToNext()) {
            transactionId.setText(cursor.getString(1));
            senderId = cursor.getString(3);
            receiverId = cursor.getString(4);
            amount.setText(cursor.getString(5));
            status.setText(cursor.getString(6));
            date.setText(cursor.getString(2));
        }
        Cursor cursor1 = new Database(this).readparticulardata(senderId);
        while (cursor1.moveToNext()) {
            senderAcc.setText(cursor1.getString(4));
        }
        Cursor cursor2 = new Database(this).readparticulardata(receiverId);
        while (cursor2.moveToNext()) {
            receiverAcc.setText(cursor2.getString(4));
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}