package me.prokawsar.madrashamanagement;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class AllJamatAdapter extends BaseAdapter {
    String[] jamatName;
    Context context;

    public AllJamatAdapter(Context context,String[] jamatName) {
        this.jamatName = jamatName;
        this.context = context;
    }

    @Override
    public int getCount() {
        return jamatName.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view =inflater.inflate(R.layout.all_jmat_list_layout,parent,false);
        TextView jamat = view.findViewById(R.id.jamaneNameOnList_id);
        jamat.setText(jamatName[position]);

        return view;
    }
}
