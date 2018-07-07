package me.prokawsar.madrashamanagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class AdminActivity extends AppCompatActivity implements View.OnClickListener{
    private Button vortiButton,addTeaButton,addComiteeButton,addFandButton,bayButton;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        vortiButton = findViewById(R.id.add_new_student_id);
        addTeaButton = findViewById(R.id.add_new_teacher_id);
        addComiteeButton = findViewById(R.id.add_new_commitee_id);
        addFandButton = findViewById(R.id.fand_collect_id);
        bayButton = findViewById(R.id.khoroc_id);

        toolbar = findViewById(R.id.ad_tool_id);
        //set toolbar
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorWhite));
        setSupportActionBar(toolbar);
        String appname = getString(R.string.app_name);
        getSupportActionBar().setTitle(appname);

        vortiButton.setOnClickListener(this);
        addTeaButton.setOnClickListener(this);
        addComiteeButton.setOnClickListener(this);
        addFandButton.setOnClickListener(this);
        bayButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add_new_student_id:
                startActivity(new Intent(AdminActivity.this,VortiActivity.class));
                break;
             case R.id.add_new_teacher_id:
                startActivity(new Intent(AdminActivity.this,TeacherAddActivity.class));
                break;
             case R.id.add_new_commitee_id:
                startActivity(new Intent(AdminActivity.this,CommiteeAddActivity.class));
                break;
              case R.id.fand_collect_id:
                startActivity(new Intent(AdminActivity.this,AddFancActivity.class));
                break;
             case R.id.khoroc_id:
                startActivity(new Intent(AdminActivity.this,CostActivity.class));
                break;
        }
    }
}
