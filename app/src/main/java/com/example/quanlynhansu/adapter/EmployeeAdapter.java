package com.example.quanlynhansu.adapter;


import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlynhansu.R;
import com.example.quanlynhansu.entity.Employee;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder> {

    Context context;
    ArrayList<Employee> listEmployee;

    public EmployeeAdapter(Context context, ArrayList<Employee> listEmployee) {
        this.context = context;
        this.listEmployee = listEmployee;
    }

    @NonNull
    @Override
    public EmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_list_employee,parent,false);

        return new EmployeeViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeViewHolder holder, int position) {

        Employee employee = listEmployee.get(position);
        holder.name.setText(employee.getName());
        holder.position.setText(employee.getPosition());
        holder.age.setText(String.valueOf(employee.getAge()));
//        try {
//            holder.image.setImageBitmap(BitmapFactory.decodeStream(new URL(employee.getImage()).openConnection().getInputStream()));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    @Override
    public int getItemCount() {
        return listEmployee.size();
    }

    public static  class EmployeeViewHolder extends RecyclerView.ViewHolder{

        TextView name, position, age;
        ImageView image;
        public EmployeeViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.txtName_item);
            position = itemView.findViewById(R.id.txtPosition_item);
            age = itemView.findViewById(R.id.txtAge_item);
            image = itemView.findViewById(R.id.imgAvartar_item);
        }
    }
}
