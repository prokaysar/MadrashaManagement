package me.prokawsar.madrashamanagement;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class StudentNameListActivity extends AppCompatActivity {
    private ListView listView;
    private TextView textView;
    private List<Student > studentList;
    private FirebaseFirestore mDatabase;
    private ArrayAdapter<Student> adapter;

    private String jamat;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_name_list);
        mDatabase = FirebaseFirestore.getInstance();

        Bundle bundle = getIntent().getExtras();
        jamat =  bundle.get("jamatName").toString();
        studentList = new ArrayList<>();

        listView = findViewById(R.id.name_list_id);
        textView = findViewById(R.id.textView3);
        textView.setText(jamat);

        toolbar = findViewById(R.id.stn_tool_id);
        //set toolbar
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorWhite));
        setSupportActionBar(toolbar);
        String appname = getString(R.string.app_name);
        getSupportActionBar().setTitle(appname);

        adapter = new ArrayAdapter<>(this,R.layout.simple_list_item,studentList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String cromic = studentList.get(position).getCromic();
                String dakhela = studentList.get(position).getDakhela();
                String name = studentList.get(position).getName();
                String fname = studentList.get(position).getFname();
                String jamat = studentList.get(position).getJamat();
                String phone = studentList.get(position).getPhone();
                String address = studentList.get(position).getAddress();

                Intent intent = new Intent(StudentNameListActivity.this,StudentInfoActivity.class);
                intent.putExtra("cromic",cromic);
                intent.putExtra("dakhela",dakhela);
                intent.putExtra("name",name);
                intent.putExtra("fname",fname);
                intent.putExtra("jamat",jamat);
                intent.putExtra("phone",phone);
                intent.putExtra("address",address);
                startActivity(intent);

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        mDatabase.collection(jamat).get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot documentSnapshots) {
                        if (!documentSnapshots.isEmpty()){
                            studentList.clear();
                            List<DocumentSnapshot> list = documentSnapshots.getDocuments();

                            for (DocumentSnapshot data : list){
                                Student listName = data.toObject(Student.class);
                                studentList.add(listName);
                            }
                        adapter.notifyDataSetChanged();

                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        String error = e.getMessage();
                        Toast.makeText(StudentNameListActivity.this, error, Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
