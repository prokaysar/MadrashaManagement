package me.prokawsar.madrashamanagement;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ProgressBar;
import android.widget.TextView;


public class StudentInfoActivity extends AppCompatActivity {
    private TextView cromicText,dakhelaText,nameText,fnameText,jamatText,phoneText,addressText;
    private Toolbar toolbar;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_info);

        cromicText = findViewById(R.id.show_cromic_id);
        dakhelaText = findViewById(R.id.show_dakhela_id);
        nameText = findViewById(R.id.show_name_id);
        fnameText = findViewById(R.id.show_fname_id);
        jamatText = findViewById(R.id.show_jamat_id);
        phoneText = findViewById(R.id.show_phone_id);
        addressText = findViewById(R.id.show_address_id);

        toolbar = findViewById(R.id.ad_tool_id);
        //set toolbar
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorWhite));
        setSupportActionBar(toolbar);
        String appname = getString(R.string.app_name);
        getSupportActionBar().setTitle(appname);

        Bundle bundle = getIntent().getExtras();
        String cromic = bundle.getString("cromic");
        String dakhela = bundle.getString("dakhela");
        String name = bundle.getString("name");
        String fname = bundle.getString("fname");
        String jamat = bundle.getString("jamat");
        String phone = bundle.getString("phone");
        String address = bundle.getString("address");

        cromicText.setText("ক্রমিক নংঃ "+cromic);
        dakhelaText.setText("দাখেলা নংঃ "+dakhela);
        nameText.setText("নামঃ "+name);
        fnameText.setText("পিতার নামঃ "+fname);
        jamatText.setText("জামাতঃ "+jamat);
        phoneText.setText("মোবাইলঃ "+phone);
        addressText.setText("ঠিকানাঃ "+address);

    }
}
