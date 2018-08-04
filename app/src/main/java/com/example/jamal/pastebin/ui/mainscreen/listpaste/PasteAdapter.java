package com.example.jamal.pastebin.ui.mainscreen.listpaste;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jamal.pastebin.R;
import com.example.jamal.pastebin.data.models.PasteNetwork;

import java.util.List;

public class PasteAdapter extends RecyclerView.Adapter<PasteAdapter.PasteViewHolder> {

    private List<PasteNetwork> pasteNetworkList;

    public PasteAdapter(List<PasteNetwork> pasteNetworkList) {
        this.pasteNetworkList = pasteNetworkList;
    }

    @NonNull
    @Override
    public PasteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_paste_by_user,
                parent, false);
        return new PasteViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PasteViewHolder holder, int position) {
        holder.setPasteData(pasteNetworkList.get(position));
    }

    @Override
    public int getItemCount() {
        return pasteNetworkList.size();
    }

    class PasteViewHolder extends RecyclerView.ViewHolder {

        private TextView titleTextView;
        private TextView privateTextView;
        private TextView languageTextView;
        private TextView sizeTextView;

        PasteViewHolder(View itemView) {
            super(itemView);
            initViews(itemView);
        }

        private void initViews(View itemView) {
            titleTextView=itemView.findViewById(R.id.TextView_paste_user_item_title);
            privateTextView= itemView.findViewById(R.id.TextView_paste_user_item_private);
            languageTextView= itemView.findViewById(R.id.TextView_paste_user_item_language);
            sizeTextView= itemView.findViewById(R.id.TextView_paste_user_item_size);
        }

        private void setPasteData(PasteNetwork pasteNetworkData){
            titleTextView.setText(String.valueOf(pasteNetworkData.getTitle()));
            privateTextView.setText(String.valueOf(pasteNetworkData.getPastePrivate()));
            languageTextView.setText(String.valueOf(pasteNetworkData.getFormatLong()));
            sizeTextView.setText(String.valueOf(pasteNetworkData.getSize()));
        }
    }
}