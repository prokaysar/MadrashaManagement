package me.prokawsar.madrashamanagement;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class AboutCommiteeActivity extends AppCompatActivity {
    private ListView listView;
    private List<Commitee> commiteeList;
    private CommiteeAdapter adapter;
    private FirebaseFirestore mDatabase;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_commitee);
        mDatabase = FirebaseFirestore.getInstance();
        commiteeList = new ArrayList<>();
        adapter = new CommiteeAdapter(this,commiteeList);
        listView = findViewById(R.id.about_commitee_list_id);
        listView.setAdapter(adapter);

        toolbar = findViewById(R.id.ac_tool_id);
        //set toolbar
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorWhite));
        setSupportActionBar(toolbar);
        String appname = getString(R.string.app_name);
        getSupportActionBar().setTitle("Comitee Identity");
    }
    @Override
    protected void onStart() {
        super.onStart();
        mDatabase.collection("Commitee").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot documentSnapshots) {
                        commiteeList.clear();
                        if (!documentSnapshots.isEmpty()){
                            List<DocumentSnapshot> list = documentSnapshots.getDocuments();
                            for (DocumentSnapshot data : list){
                                Commitee commitee = data.toObject(Commitee.class);
                                commiteeList.add(commitee);
                            }
                            adapter.notifyDataSetChanged();
                        }

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AboutCommiteeActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

    }
}

