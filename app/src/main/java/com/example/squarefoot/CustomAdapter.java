package com.example.squarefoot;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    private Context context;
    private DatabaseHelper db;
    Activity activity;
    private int lastPosition;
    private List<CustomItem> dataList;

    public CustomAdapter(DatabaseHelper db,Activity activity,Context context, List<CustomItem> dataList) {
        this.db = db;
        this.activity = activity;
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the custom layout
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.custom_list, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Set data to the views
        final CustomItem data = dataList.get(position);
        holder.showDate.setText(data.getDate());
        holder.showLength.setText(data.getLength());
        holder.showWidth.setText(data.getWidth());
        holder.showPPU.setText(data.getPpu());
        holder.showResult.setText(data.getResult());
        setAnimation(holder.itemView,position); //animation

        // Set click listener for Row11
        holder.Row11.setOnLongClickListener(new View.OnLongClickListener() {
            final int position = holder.getAdapterPosition();
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                builder.setMessage("Are you sure you want to Delete?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                deleteItem(position);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // User clicked "No," so dismiss the dialog
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
                return true;
            }
        });
        holder.Row11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open a new activity and pass data to it
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("id",data.getId());
                intent.putExtra("date", data.getDate());
                intent.putExtra("length", data.getLength());
                intent.putExtra("width", data.getWidth());
                intent.putExtra("ppu", data.getPpu());
                intent.putExtra("result", data.getResult());
                activity.startActivityForResult(intent,1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView showDate;
        TextView showLength;
        TextView showWidth;
        TextView showPPU;
        TextView showResult;
        LinearLayout Row11;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            showDate = itemView.findViewById(R.id.showDate);
            showLength = itemView.findViewById(R.id.showLength);
            showWidth = itemView.findViewById(R.id.showWidth);
            showPPU = itemView.findViewById(R.id.showPPU);
            showResult = itemView.findViewById(R.id.showResult);
            Row11 = itemView.findViewById(R.id.row11);
        }
    }
    public void deleteItem(int position) {
        CustomItem item = dataList.get(position);
        db.deleteDataFromDatabase(item.getId());
        dataList.remove(position);
        notifyItemRemoved(position);
    }
    private void setAnimation(View viewToAnimate, int position){
        if(position>lastPosition){
            Animation slideIn = AnimationUtils.loadAnimation(context, R.anim.slideinleft);
            viewToAnimate.startAnimation(slideIn);
            lastPosition = position;
        }
    }
}
