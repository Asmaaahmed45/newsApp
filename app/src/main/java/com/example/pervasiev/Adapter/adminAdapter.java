package com.example.pervasiev.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.pervasiev.Model.DataClass;
import com.example.pervasiev.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.reactivex.rxjava3.annotations.NonNull;

public class adminAdapter extends RecyclerView.Adapter<adminAdapter.MyViewHolder> {
    private Context c;
    private List<DataClass> dataList;
    public adminAdapter(Context context, List<DataClass> dataList) {
        c = context;
        this.dataList = dataList;
    }
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Glide.with(c).load(dataList.get(position).getImageUrl()).into(holder.imgsection);
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
    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imgsection;
        TextView namesection, dessection;
        Button editbtn;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imgsection = itemView.findViewById(R.id.image);
            namesection = itemView.findViewById(R.id.textView_PostName);
            dessection = itemView.findViewById(R.id.textView_PostDes);
            editbtn = itemView.findViewById(R.id.edit);
            editbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LayoutInflater layoutInflater = LayoutInflater.from(c);
                    View view1 = layoutInflater.inflate(R.layout.edit_post, null);
                    EditText txttitle = view1.findViewById(R.id.edit_title);
                    EditText txtdescription = view1.findViewById(R.id.edit_description);
                    EditText txtimage = view1.findViewById(R.id.edit_image);
                    Button button = view1.findViewById(R.id.edit_btn);
                    BottomSheetDialog dialog = new BottomSheetDialog(c);
                    dialog.setContentView(view1);
                    dialog.show();
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String PostTitle = txttitle.getText().toString();
                            String PostDescription = txtdescription.getText().toString();
                            String PostImage = txtimage.getText().toString();
                            updatedata(PostTitle, PostDescription, PostImage);

                            dialog.cancel();

                        }
                    });

                }
            });

        }
    }
    void updatedata(String PostTitle, String PostDesc, String PostImage) {

        HashMap User = new HashMap();
        User.put("description", PostDesc);
        User.put("imageUrl", PostImage);
        User.put("title", PostTitle);
        DatabaseReference databaseReference;
        databaseReference = FirebaseDatabase.getInstance().getReference("news");
        databaseReference.child("n1").updateChildren(User).addOnCompleteListener(new OnCompleteListener() {
            @Override
            public void onComplete(@NonNull Task task) {

                if (task.isSuccessful()) {
                    Toast.makeText(c, "Successfully Updated", Toast.LENGTH_SHORT).show();

                } else {

                    Toast.makeText(c, "Failed to Update", Toast.LENGTH_SHORT).show();

                }

            }
        });

    }
}

