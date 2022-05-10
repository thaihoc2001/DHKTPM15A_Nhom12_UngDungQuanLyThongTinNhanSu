package com.example.quanlynhansu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quanlynhansu.R;
import com.example.quanlynhansu.entity.Employee;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class EmployeeDetailAdapter extends BaseAdapter {
    private List<Employee> employees;
    private Context ctx;
    private int idLayout;

    public EmployeeDetailAdapter(List<Employee> employees, Context ctx, int idLayout) {
        this.employees = employees;
        this.ctx = ctx;
        this.idLayout = idLayout;
    }

    @Override
    public int getCount() {
        if(employees.size()!=0||!employees.isEmpty()){
            return employees.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view==null){
            view= LayoutInflater.from(viewGroup.getContext()).inflate(idLayout,viewGroup,false);
        }
       TextView tvAge= view.findViewById(R.id.txtAge_detail);
        TextView tvDesc= view.findViewById(R.id.txtDescription_detail);
        TextView tvName= view.findViewById(R.id.txtName_detail);
        TextView tvPosition= view.findViewById(R.id.txtPosition_detail);
       ImageView imgAvatar=view.findViewById(R.id.imgAvatar_detail);
       final Employee employee=employees.get(i);
       if(employees!=null&&!employees.isEmpty()){
           tvAge.setText(employee.getAge());
           tvDesc.setText(employee.getDescription());
           tvName.setText(employee.getName());
           tvPosition.setText(employee.getPosition());
           imgAvatar.setImageResource(Integer.parseInt(employee.getImage()));
       }
        return view;
    }

}
