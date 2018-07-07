package me.prokawsar.madrashamanagement;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class AboutTeacherActivity extends AppCompatActivity {
    private ListView listView;
    private List<Teacher> teacherList;
    private TeacherAdapter adapter;
    private FirebaseFirestore mDatabase;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_teacher);
        mDatabase = FirebaseFirestore.getInstance();
        teacherList = new ArrayList<>();
        adapter = new TeacherAdapter(this,teacherList);
        listView = findViewById(R.id.about_teacher_list_id);
        listView.setAdapter(adapter);

        toolbar = findViewById(R.id.tl_tool_id);
        //set toolbar
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorWhite));
        setSupportActionBar(toolbar);
        String appname = getString(R.string.app_name);
        getSupportActionBar().setTitle("Teacher Identity");
    }

    @Override
    protected void onStart() {
        super.onStart();
       mDatabase.collection("Teacher").get()
               .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                   @Override
                   public void onSuccess(QuerySnapshot documentSnapshots) {
                       teacherList.clear();
                       if (!documentSnapshots.isEmpty()){
                           List<DocumentSnapshot> list = documentSnapshots.getDocuments();
                           for (DocumentSnapshot data : list){
                               Teacher teacher = data.toObject(Teacher.class);
                               teacherList.add(teacher);
                           }
                           adapter.notifyDataSetChanged();
                       }

                   }
               })
               .addOnFailureListener(new OnFailureListener() {
                   @Override
                   public void onFailure(@NonNull Exception e) {
                       Toast.makeText(AboutTeacherActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                   }
               });

    }
}
