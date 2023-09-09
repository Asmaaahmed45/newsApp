package com.example.pervasiev.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.pervasiev.Model.DataClass;
import com.example.pervasiev.R;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.annotations.NonNull;
public class UserAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private Context context;
    private List<DataClass> dataList;
    public UserAdapter(Context context, List<DataClass> dataList) {
        this.context = context;
        this.dataList = dataList;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.section_items, parent, false);
        return new MyViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Glide.with(context).load(dataList.get(position).getImageUrl()).into(holder.imgsection);
        holder.namesection.setText(dataList.get(position).getTitle());
        holder.dessection.setText(dataList.get(position).getDescription());
    }
    @Override
    public int getItemCount() {
        return dataList.size();
    }
    public void searchDataList(ArrayList<DataClass> searchList){
        dataList = searchList;
        notifyDataSetChanged();
    }
}
class MyViewHolder extends RecyclerView.ViewHolder{
    ImageView imgsection;
    TextView namesection,dessection;
    Button favbtn;
    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        imgsection=itemView.findViewById(R.id.image_profile);
        namesection=itemView.findViewById(R.id.textView_name);
        dessection=itemView.findViewById(R.id.textView_des);
        favbtn=itemView.findViewById(R.id.fav_button);

        favbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                favbtn.setBackgroundResource(R.drawable.ic_baseline_favoritered_24);
            }
        });
    }
}
