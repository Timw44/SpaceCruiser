package com.example.spacecruiser;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class HighscoreScreen extends AppCompatActivity
{
    //variables
    //TextView first, second, third, fourth, fifth, sixth, seventh, eighth, ninth, tenth;
    TextView[] positions = new TextView[10];
    String name, score;
    //ArrayList<String> keys;
    FirebaseDatabase database;
    DatabaseReference reference;
    //ArrayList<DatabaseReference> refs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.highscore);
        getSupportActionBar().hide();

        database = FirebaseDatabase.getInstance();
        reference = database.getReference("Flight");
        setLeaderBoard();
    }



    public void setLeaderBoard()
    {
        setPositions();

        /*first = (TextView) findViewById(R.id.scoreTX1);
        second = (TextView) findViewById(R.id.scoreTX2);
        third = (TextView) findViewById(R.id.scoreTX3);
        fourth = (TextView) findViewById(R.id.scoreTX4);
        fifth = (TextView) findViewById(R.id.scoreTX5);
        sixth = (TextView) findViewById(R.id.scoreTX6);
        seventh = (TextView) findViewById(R.id.scoreTX7);
        eighth = (TextView) findViewById(R.id.scoreTX8);
        ninth = (TextView) findViewById(R.id.scoreTX9);
        tenth = (TextView) findViewById(R.id.scoreTX10);*/

        //Query queryRef = reference.orderByKey().limitToLast(10);

        reference.orderByKey().limitToLast(10).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                sort(Integer.parseInt(snapshot.getKey()));
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                sort(Integer.parseInt(snapshot.getKey()));
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                sort(Integer.parseInt(snapshot.getKey()));
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                sort(Integer.parseInt(snapshot.getKey()));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        /*for(int i = 0; i<10; i++)
        {
            if(reference.getKey().equals(""))
            {
                keys.add("---");
            }
            else {
                keys.add(reference.child("Flight").getKey());
            }
        }

        tenth.setText("10. " + keys.get(9));
        ninth.setText("9. " + keys.get(8));
        eighth.setText("8. " + keys.get(7));
        seventh.setText("7. " + keys.get(6));
        sixth.setText("6. " + keys.get(5));
        fifth.setText("5. " + keys.get(4));
        fourth.setText("4. " + keys.get(3));
        third.setText("3. " + keys.get(2));
        second.setText("2. " + keys.get(1));
        first.setText("1. " + keys.get(0));*/

    }

    public void setPositions()
    {
        positions[0] = findViewById(R.id.scoreTX1);
        positions[1] = findViewById(R.id.scoreTX2);
        positions[2] = findViewById(R.id.scoreTX3);
        positions[3] = findViewById(R.id.scoreTX4);
        positions[4] = findViewById(R.id.scoreTX5);
        positions[5] = findViewById(R.id.scoreTX6);
        positions[6] = findViewById(R.id.scoreTX7);
        positions[7] = findViewById(R.id.scoreTX8);
        positions[8] = findViewById(R.id.scoreTX9);
        positions[9] = findViewById(R.id.scoreTX10);
    }

    public void sort(int value)
    {
        for (int i = 0; i<positions.length; i++)
        {

            if(value > Integer.parseInt(positions[i].getText().toString()))
            {
                positions[i].setText((i+1) + ". " + value);
            }
        }
    }


    public void back(View view)
    {
        finish();
    }
}
