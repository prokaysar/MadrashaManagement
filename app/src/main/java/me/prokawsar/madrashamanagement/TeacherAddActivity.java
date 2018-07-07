package me.prokawsar.madrashamanagement;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class TeacherAddActivity extends AppCompatActivity {
    private EditText nameText,podobiText,qualificationText,phoneText,addrText,etcText;
    private Button button;
    private FirebaseFirestore mDatabase;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taecher_add);
        mDatabase = FirebaseFirestore.getInstance();
        toolbar = findViewById(R.id.tea_tool_id);
        //set toolbar
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorWhite));
        setSupportActionBar(toolbar);
        String appname = getString(R.string.app_name);
        getSupportActionBar().setTitle("Add a New Teacher");

        nameText = findViewById(R.id.tname_id);
        podobiText = findViewById(R.id.tpodobi_id);
        qualificationText = findViewById(R.id.tjoggota_id);
        phoneText = findViewById(R.id.tphone_id);
        addrText = findViewById(R.id.taddress_id);
        etcText = findViewById(R.id.tetc_id);
        button = findViewById(R.id.save_teacher_id);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTeacher();
            }
        });
    }

    private void addTeacher() {
        String name = nameText.getText().toString().trim();
        String podobi = podobiText.getText().toString().trim();
        String qualification = qualificationText.getText().toString().trim();
        String phone = phoneText.getText().toString().trim();
        String adddress = addrText.getText().toString().trim();
        String etc = etcText.getText().toString().trim();

        if (!validateInput(name,podobi,qualification,phone,adddress,etc)){
            CollectionReference addStudent = mDatabase.collection("Teacher");
           Teacher teacher = new Teacher(name,podobi,qualification,phone,adddress,etc);
            addStudent.add(teacher)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                           /* Snackbar.make(mView, "New Student Are Added.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                            Toast.makeText(TeacherAddActivity.this, "নতুন শিক্ষক যুক্ত করা হয়েছে।", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            String error = e.getMessage();
                          /*  Snackbar.make(mView, error, Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();*/
                            Toast.makeText(TeacherAddActivity.this, error, Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }

    private boolean validateInput(String name, String podobi, String qualification, String phone, String adddress, String etc) {
         if (name.isEmpty()) {
            nameText.setError("নাম দিন");
             nameText.requestFocus();
            return true;
        }
        if (podobi.isEmpty()) {
            podobiText.setError("পদবী দিন");
            podobiText.requestFocus();
            return true;
        }

        if (qualification.isEmpty()) {
            qualificationText.setError("যোগ্যতা দিন");
            qualificationText.requestFocus();
            return true;
        }


        if (phone.isEmpty()) {
            phoneText.setError("ফোন নাম্বার দিন");
            phoneText.requestFocus();
            return true;
        }

        if (adddress.isEmpty()) {
            addrText.setError("ঠিকানা দিন");
            addrText.requestFocus();
            return true;
        }


        return false;
    }
}
