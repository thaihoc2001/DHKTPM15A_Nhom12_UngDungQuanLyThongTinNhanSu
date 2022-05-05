package com.example.quanlynhansu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import com.example.quanlynhansu.R;
import com.example.quanlynhansu.entity.Employee;

import java.util.ArrayList;

public class EmployeeAdapter1 extends BaseAdapter {
    Context ctx;
    ArrayList<Employee> arrayList;

    public EmployeeAdapter1(Context ctx, ArrayList<Employee> arrayList) {
        this.ctx = ctx;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
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
        view = LayoutInflater.from(ctx).inflate(R.layout.item_list_employee,viewGroup,false);
        TextView name = view.findViewById(R.id.txtName_item);
        TextView position = view.findViewById(R.id.txtPosition_item);
        TextView age = view.findViewById(R.id.txtAge_item);
        ImageView imageView = view.findViewById(R.id.imgAvartar_item);
        name.setText(arrayList.get(i).getName());
        position.setText(arrayList.get(i).getPosition());
        age.setText(String.valueOf(arrayList.get(i).getAge()));
        Picasso.get().load(arrayList.get(i).getImage()).into(imageView);
        return view;
    }
}
