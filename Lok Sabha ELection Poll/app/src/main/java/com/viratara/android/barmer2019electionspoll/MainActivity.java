package com.viratara.android.barmer2019electionspoll;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    


    int count = 0;
    public static ArrayList<Integer> voteList = new ArrayList<>();
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mVotesRef;
    private ProgressBar spinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.poll);


        mFirebaseDatabase = FirebaseDatabase.getInstance();
        spinner = (ProgressBar)findViewById(R.id.progressBar1);
        spinner.setVisibility(View.VISIBLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        mVotesRef = mFirebaseDatabase.getReference("votes");

        mVotesRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                count=0;
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    int vote = postSnapshot.getValue(Integer.class);
                    voteList.add(count,vote);
                    count++;

                }
                if(count >= dataSnapshot.getChildrenCount()){
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            // Do something after 5s = 5000ms
                            spinner.setVisibility(View.GONE);
                            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                        }
                    }, 2000);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });



        ImageButton bjpButton = (ImageButton)findViewById(R.id.bjp);
        bjpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();

                if(pref.getInt("flag", 0) !=1) {
                    mVotesRef.child("bjp").setValue(voteList.get(0) + 1);
                    editor.putInt("flag", 1);
                    editor.apply();
                }
                else
                {
                    Toast.makeText(getBaseContext(), "You have already participated in the poll!",
                            Toast.LENGTH_LONG).show();

                }

                Intent i = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(i);
                =
            }
        });
        ImageButton congressButton = (ImageButton)findViewById(R.id.congress);
        congressButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();

                if(pref.getInt("flag", 0) !=1) {
                    mVotesRef.child("congress").setValue(voteList.get(1)+1);
                    editor.putInt("flag", 1);
                    editor.apply();
                }
                else
                {
                    Toast.makeText(getBaseContext(), "You have already participated in the poll!",
                            Toast.LENGTH_LONG).show();
                }

                Intent i = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(i);
                =
            }
        });
         ImageButton othersButton = (ImageButton)findViewById(R.id.others);
        othersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();

                if(pref.getInt("flag", 0) !=1) {
                    mVotesRef.child("others").setValue(voteList.get(2)+1);
                    editor.putInt("flag", 1);
                    editor.apply();
                }
                else
                {
                    Toast.makeText(getBaseContext(), "You have already participated in the poll!",
                            Toast.LENGTH_LONG).show();
                }

                Intent i = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(i);
                

            }
        });
         ImageButton natoButton = (ImageButton)findViewById(R.id.nato);
        natoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();

                if(pref.getInt("flag", 0) !=1) {
                    mVotesRef.child("nato").setValue(voteList.get(3)+1);
                    editor.putInt("flag", 1);
                    editor.apply();
                }
                else
                {
                    Toast.makeText(getBaseContext(), "You have already participated in the poll!",
                            Toast.LENGTH_LONG).show();
                }

                Intent i = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(i);
                

            }
        });

    }

    public static ArrayList<Integer> getList() {
        return voteList;
    }

}
