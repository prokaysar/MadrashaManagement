package me.prokawsar.madrashamanagement;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class VortiActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText cromicText,dakhelaText,nameText,fnameText,phoneText,addressText,bodingText,beton;
    private Button button;
    private Spinner spinner;
    private String[] jamat;
    private ArrayAdapter<String> arrayAdapter;
    private String jamatname;
    private FirebaseFirestore mDatabase;
    private View mView;
    private Toolbar toolbar;
    private AutoCompleteTextView autoCompleteTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vorti);
        mDatabase = FirebaseFirestore.getInstance();
        toolbar = findViewById(R.id.vorti_tool_id);
        //set toolbar
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorWhite));
        setSupportActionBar(toolbar);
        String appname = getString(R.string.app_name);
        getSupportActionBar().setTitle("Registration Form");

        autoCompleteTextView = findViewById(R.id.autoCompleteTextView);
        //for jamat list on spinner
        jamat = getResources().getStringArray(R.array.jamat);
        arrayAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,jamat);
        autoCompleteTextView.setThreshold(1);
        autoCompleteTextView.setAdapter(arrayAdapter);


        cromicText = findViewById(R.id.tpodobi_id);
        dakhelaText = findViewById(R.id.tjoggota_id);
        nameText = findViewById(R.id.tname_id);
        fnameText = findViewById(R.id.tetc_id);
        phoneText = findViewById(R.id.tphone_id);
        addressText = findViewById(R.id.taddress_id);
        button = findViewById(R.id.save_student_id);
        bodingText = findViewById(R.id.boding_fee_id);
        beton = findViewById(R.id.monthly_fee_id);

        button.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.save_student_id:
                registerAnewStuent();
                break;
        }

    }

    private void registerAnewStuent() {

        String corimic   = cromicText.getText().toString().trim();
        String dakhela   = dakhelaText.getText().toString().trim();
        String name      = nameText.getText().toString().trim();
        String fname     = fnameText.getText().toString().trim();
        String phone     = phoneText.getText().toString().trim();
        String address   = addressText.getText().toString().trim();
        String bodingFee = bodingText.getText().toString().trim();
        String monthlyFee = beton.getText().toString().trim();
        jamatname = String.valueOf(autoCompleteTextView.getOnItemClickListener());

        if (!validateInputs(corimic,dakhela,name,fname,jamatname,phone,address)){
            CollectionReference addStudent = mDatabase.collection(jamatname);
            Student student = new Student(corimic,dakhela,name,fname,jamatname,phone,address,bodingFee,monthlyFee);
            addStudent.add(student)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                           /* Snackbar.make(mView, "New Student Are Added.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                            Toast.makeText(VortiActivity.this, "New Student Are Added", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            String error = e.getMessage();
                          /*  Snackbar.make(mView, error, Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();*/
                            Toast.makeText(VortiActivity.this, error, Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }


    private boolean validateInputs(String corimic, String dakhela, String name, String fname, String jamat, String phone, String address) {
        if (corimic.isEmpty()) {
            cromicText.setError("Cromic required");
            cromicText.requestFocus();
            return true;
        }
        if (dakhela.isEmpty()) {
            dakhelaText.setError("Dakhela required");
            dakhelaText.requestFocus();
            return true;
        }

        if (name.isEmpty()) {
            nameText.setError("ForListName required");
            nameText.requestFocus();
            return true;
        }


        if (fname.isEmpty()) {
            fnameText.setError("Father required");
            fnameText.requestFocus();
            return true;
        }

        if (phone.isEmpty()) {
            phoneText.setError("Phone required");
            phoneText.requestFocus();
            return true;
        }
        if (address.isEmpty()) {
            addressText.setError("Address required");
            addressText.requestFocus();
            return true;
        }
        if (autoCompleteTextView.length()==0) {
            addressText.setError("Jamat required");
            addressText.requestFocus();
            return true;
        }

        return false;
    }
}
