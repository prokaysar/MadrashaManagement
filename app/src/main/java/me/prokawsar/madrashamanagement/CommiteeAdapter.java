package me.prokawsar.madrashamanagement;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class CommiteeAdapter extends BaseAdapter {
    Context context;
    List<Commitee> commiteeList;

    public CommiteeAdapter(Context context, List<Commitee> commiteeList) {
        this.context = context;
        this.commiteeList = commiteeList;
    }

    @Override
    public int getCount() {
        return commiteeList.size();
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
        convertView = inflater.inflate(R.layout.commitee_list_layout,parent,false);

        TextView name = convertView.findViewById(R.id.show_Commitee_name_id);
        TextView podobi = convertView.findViewById(R.id.show_Commitee_pdobi_id);
        TextView phone = convertView.findViewById(R.id.show_Commitee_phone_id);
        TextView address = convertView.findViewById(R.id.show_Commitee_address_id);
        TextView etc = convertView.findViewById(R.id.show_Commitee_etc_id);

        name.setText("নামঃ "+commiteeList.get(position).getName());
        podobi.setText("পদবিঃ "+commiteeList.get(position).getPodobi());
        phone.setText("মোবাইলঃ "+commiteeList.get(position).getPhone());
        address.setText("ঠিকানাঃ "+commiteeList.get(position).getAddress());
        etc.setText("অন্যান্যঃ "+commiteeList.get(position).getEtc());
        return convertView;
    }
}
