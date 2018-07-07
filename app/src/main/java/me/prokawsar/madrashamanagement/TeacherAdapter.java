package me.prokawsar.madrashamanagement;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class TeacherAdapter extends BaseAdapter {
    Context context;
    List<Teacher> teacherList;

    public TeacherAdapter(Context context, List<Teacher> teacherList) {
        this.context = context;
        this.teacherList = teacherList;
    }

    @Override
    public int getCount() {
        return teacherList.size();
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
        convertView = inflater.inflate(R.layout.about_teacher_list_layout,parent,false);
        TextView name = convertView.findViewById(R.id.show_teacher_name_id);
        TextView podobi = convertView.findViewById(R.id.show_teacher_pdobi_id);
        TextView qualification = convertView.findViewById(R.id.show_teacher_qlt_id);
        TextView phone = convertView.findViewById(R.id.show_teacher_phone_id);
        TextView address = convertView.findViewById(R.id.show_teacher_address_id);
        TextView etc = convertView.findViewById(R.id.show_teacher_etc_id);

        name.setText("নামঃ "+teacherList.get(position).getName());
        podobi.setText("পদবিঃ "+teacherList.get(position).getPodobi());
        qualification.setText("যোগ্যতাঃ "+teacherList.get(position).getQualification());
        phone.setText("মোবাইলঃ "+teacherList.get(position).getPhone());
        address.setText("ঠিকানাঃ "+teacherList.get(position).getAddress());
        etc.setText("অন্যান্যঃ "+teacherList.get(position).getEtc());
        return convertView;
    }
}
