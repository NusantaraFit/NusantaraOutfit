package com.example.nusafit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.nusafit.UI.ViewHome;

import java.util.ArrayList;

public class adapterHomeProduk extends RecyclerView.Adapter<adapterHomeProduk.ViewHolder> {

    private ArrayList<MyDataProduct> myDataProduct;
    private Context context;

    public adapterHomeProduk(ArrayList<MyDataProduct> myDataProduct, Context activity) {
        this.myDataProduct = myDataProduct;
        this.context = activity;

    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_product, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        final  MyDataProduct myDataProductList = myDataProduct[position];
        final String Nama = myDataProduct.get(position).getNamaProduk();
        holder.imageProduk.setImageResource(myDataProduct.get(position).getSrc());
        holder.namaProduk.setText(myDataProduct.get(position).getNamaProduk());
        holder.hargaProduk.setText(myDataProduct.get(position).getHargaProduk());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,myDataProduct.get(position).getNamaProduk(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return myDataProduct.size();
    }

    public void setFilter(ArrayList<MyDataProduct> filterList){
        myDataProduct = new ArrayList<>();
        myDataProduct.addAll(filterList);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageProduk;
        TextView namaProduk ;
        TextView hargaProduk;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageProduk = itemView.findViewById(R.id.imgProduk);
            namaProduk = itemView.findViewById(R.id.txtNamaProduk);
            hargaProduk = itemView.findViewById(R.id.txtHargaProduk);
        }
    }
}
