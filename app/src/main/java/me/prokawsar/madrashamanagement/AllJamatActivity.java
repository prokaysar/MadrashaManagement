package me.prokawsar.madrashamanagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class AllJamatActivity extends AppCompatActivity {
    private ListView listView;
    private String[] jamatName;
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_jamat);
        listView = findViewById(R.id.vorti_info_list);

        toolbar = findViewById(R.id.all_jamat_tool_id);
        //set toolbar
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorWhite));
        setSupportActionBar(toolbar);
        String appname = getString(R.string.app_name);
        getSupportActionBar().setTitle("Class List");

        jamatName = getResources().getStringArray(R.array.jamatname);
        AllJamatAdapter allJamatAdapter = new AllJamatAdapter(this,jamatName);
        listView.setAdapter(allJamatAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String value = jamatName[position];
                Intent intent = new Intent(AllJamatActivity.this,StudentNameListActivity.class);
                intent.putExtra("jamatName",value);
                startActivity(intent);
            }
        });
    }
}
