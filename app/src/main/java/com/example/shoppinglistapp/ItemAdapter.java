package com.example.shoppinglistapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

public class ItemAdapter extends FirebaseRecyclerAdapter<Item,ItemAdapter.ItemViewHolder> {
   Context context;
   LottieLoader lottieLoader;
    public ItemAdapter(Context context, @NonNull FirebaseRecyclerOptions<Item> options) {
        super(options);
        this.context=context;
        lottieLoader=new LottieLoader(context);
        lottieLoader.show();
    }

    @Override
    protected void onBindViewHolder(@NonNull ItemAdapter.ItemViewHolder holder, int position, @NonNull Item model) {
         holder.tvname.setText(model.getName());
         holder.tvprice.setText(model.getPrice());
         holder.tvquantity.setText(model.getQuantity());

        holder.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the current user's UID
                String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

                // Get the document ID of the current item
                String documentId = getSnapshots().getSnapshot(holder.getAbsoluteAdapterPosition()).getKey();
                assert documentId != null;
                FirebaseDatabase.getInstance().getReference()
                        .child("users")
                        .child(userId)
                        .child("shoppinglist")
                        .child(documentId)
                        .removeValue()
                        .addOnSuccessListener(aVoid -> {
                            // Show a success message
                            Toast.makeText(context, "Item deleted successfully!", Toast.LENGTH_SHORT).show();
                        })
                        .addOnFailureListener(e -> {
                            // Show an error message
                            Toast.makeText(context, "Failed to delete item: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        });
            }
        });
    }

    @NonNull
    @Override
    public ItemAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context)
                .inflate(R.layout.single_item, parent, false);
        return new ItemViewHolder(v);
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView tvname,tvprice,tvquantity;
        ImageView ivDelete;
        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            tvname=itemView.findViewById(R.id.item_name);
            tvprice=itemView.findViewById(R.id.item_price);
            tvquantity=itemView.findViewById(R.id.item_quantity);
            ivDelete=itemView.findViewById(R.id.item_delete_icon);
        }



    }

    @Override
    public void onDataChanged() {
        super.onDataChanged();
lottieLoader.dismiss();
    }
}
