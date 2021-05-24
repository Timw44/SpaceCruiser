package com.example.spacecruiser;

import android.os.Bundle;
import android.text.TextUtils;
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
import com.google.firebase.database.core.EventRegistration;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class HighscoreScreen extends AppCompatActivity
{
    //variables
    TextView firstK, secondK, thirdK, fourthK, fifthK, sixthK, seventhK, eighthK, ninthK, tenthK,
             firstV, secondV, thirdV, fourthV, fifthV, sixthV, seventhV, eighthV, ninthV, tenthV, topN, topS;
    TextView[] positions = new TextView[10];
    TextView[] names = new TextView[10];
    String[] keys = new String[10];
    FirebaseDatabase database;
    DatabaseReference reference;
    int[] top10 = new int[10];
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

        /*firstK = (TextView) findViewById(R.id.scoreTX1Key);
        secondK = (TextView) findViewById(R.id.scoreTX2Key);
        thirdK = (TextView) findViewById(R.id.scoreTX3Key);
        fourthK = (TextView) findViewById(R.id.scoreTX4Key);
        fifthK = (TextView) findViewById(R.id.scoreTX5Key);
        sixthK = (TextView) findViewById(R.id.scoreTX6Key);
        seventhK = (TextView) findViewById(R.id.scoreTX7Key);
        eighthK = (TextView) findViewById(R.id.scoreTX8Key);
        ninthK = (TextView) findViewById(R.id.scoreTX9Key);
        tenthK = (TextView) findViewById(R.id.scoreTX10Key);
        firstV = (TextView) findViewById(R.id.scoreTX1Value);
        secondV = (TextView) findViewById(R.id.scoreTX2Value);
        thirdV = (TextView) findViewById(R.id.scoreTX3Value);
        fourthV = (TextView) findViewById(R.id.scoreTX4Value);
        fifthV = (TextView) findViewById(R.id.scoreTX5Value);
        sixthV = (TextView) findViewById(R.id.scoreTX6Value);
        seventhV = (TextView) findViewById(R.id.scoreTX7Value);
        eighthV = (TextView) findViewById(R.id.scoreTX8Value);
        ninthV = (TextView) findViewById(R.id.scoreTX9Value);
        tenthV = (TextView) findViewById(R.id.scoreTX10Value);*/

        Query queryRef = reference.orderByKey().limitToLast(1);

        queryRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                //long count = snapshot.getChildrenCount();
                sort(Integer.parseInt(snapshot.getKey()), snapshot.getValue().toString());
                //System.out.println(count);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                //sort(Integer.parseInt(snapshot.getKey()), snapshot.getValue().toString());
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                //sort(Integer.parseInt(snapshot.getKey()), snapshot.getValue().toString());
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
               // sort(Integer.parseInt(snapshot.getKey()), snapshot.getValue().toString());
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
        positions[0] = findViewById(R.id.scoreTX1Key);
        positions[1] = findViewById(R.id.scoreTX2Key);
        positions[2] = findViewById(R.id.scoreTX3Key);
        positions[3] = findViewById(R.id.scoreTX4Key);
        positions[4] = findViewById(R.id.scoreTX5Key);
        positions[5] = findViewById(R.id.scoreTX6Key);
        positions[6] = findViewById(R.id.scoreTX7Key);
        positions[7] = findViewById(R.id.scoreTX8Key);
        positions[8] = findViewById(R.id.scoreTX9Key);
        positions[9] = findViewById(R.id.scoreTX10Key);
        names[0] = findViewById(R.id.scoreTX1Value);
        names[1] = findViewById(R.id.scoreTX2Value);
        names[2] = findViewById(R.id.scoreTX3Value);
        names[3] = findViewById(R.id.scoreTX4Value);
        names[4] = findViewById(R.id.scoreTX5Value);
        names[5] = findViewById(R.id.scoreTX6Value);
        names[6] = findViewById(R.id.scoreTX7Value);
        names[7] = findViewById(R.id.scoreTX8Value);
        names[8] = findViewById(R.id.scoreTX9Value);
        names[9] = findViewById(R.id.scoreTX10Value);
        topN = (TextView) findViewById(R.id.highNameTX);
        topS = (TextView) findViewById(R.id.highValueTX);
    }

    public void sort(int value, String name)
    {
        for (int i = 0; i<positions.length; i++)
        {
            topS.setText(value + "");
            topN.setText(name);

//            if(value > Integer.parseInt(positions[i].getText().toString())) {
//                positions[i].setText(value + "");
//                names[i].setText(name);
//            }
//
//            if(checkRepeat(name))
//            {
//                break;
//            }
        }
    }

    public boolean checkRepeat(String name)
    {
        for (int i = 0; i<names.length; i++)
        {
            if(name.equals(names[i].getText().toString()))
            {
                return true;
            }
        }
        return false;
    }


    public void back(View view)
    {
        finish();
    }
}
