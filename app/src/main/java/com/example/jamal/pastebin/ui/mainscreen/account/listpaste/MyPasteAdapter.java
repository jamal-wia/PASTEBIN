package com.example.jamal.pastebin.ui.mainscreen.account.listpaste;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jamal.pastebin.R;
import com.example.jamal.pastebin.data.models.PasteNetwork;
import com.example.jamal.pastebin.data.models.PasteRoom;

import java.util.List;

public class MyPasteAdapter extends RecyclerView.Adapter<MyPasteAdapter.Holder> {
    private List<PasteNetwork> pasteNetworks;

    private OnItemLongClickListener itemLongClickListener;
    private OnItemClickListener itemClickListener;

    public MyPasteAdapter(List<PasteNetwork> pasteNetworks) {
        this.pasteNetworks = pasteNetworks;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_paste_by_user,
                parent, false);
        return new Holder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.setData(pasteNetworks.get(position));
    }

    @Override
    public int getItemCount() {
        return pasteNetworks.size();
    }

    public void setItemLongClickListener(OnItemLongClickListener itemLongClickListener) {
        this.itemLongClickListener = itemLongClickListener;
    }

    public void setItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    class Holder extends RecyclerView.ViewHolder {

        private TextView titleTextView;
        private TextView privateTextView;
        private TextView languageTextView;
        private TextView sizeTextView;

        public Holder(View itemView) {
            super(itemView);
            initViews();
        }

        private void initViews() {
            titleTextView = itemView.findViewById(R.id.TextView_paste_user_item_title);
            privateTextView = itemView.findViewById(R.id.TextView_paste_user_item_private);
            languageTextView = itemView.findViewById(R.id.TextView_paste_user_item_language);
            sizeTextView = itemView.findViewById(R.id.TextView_paste_user_item_size);
        }

        private void setData(PasteNetwork pasteNetwork) {
            titleTextView.setText(String.valueOf(pasteNetwork.getTitle()));
            privateTextView.setText(String.valueOf(pasteNetwork.getPastePrivate()));
            languageTextView.setText(String.valueOf(pasteNetwork.getFormatLong()));
            sizeTextView.setText(String.valueOf(pasteNetwork.getSize()));

            itemView.setOnLongClickListener(v -> {
                itemLongClickListener.onItemLongClick(new PasteRoom(
                        pasteNetwork.getKey(),
                        pasteNetwork.getDate(),
                        pasteNetwork.getTitle(),
                        pasteNetwork.getSize(),
                        pasteNetwork.getExpireDate(),
                        pasteNetwork.getPastePrivate(),
                        pasteNetwork.getFormatLong(),
                        pasteNetwork.getFormatShort(),
                        pasteNetwork.getUrl(),
                        pasteNetwork.getHits()));
                return false;
            });

            itemView.setOnClickListener(v -> itemClickListener.onItemClick(new PasteRoom(
                    pasteNetwork.getKey(),
                    pasteNetwork.getDate(),
                    pasteNetwork.getTitle(),
                    pasteNetwork.getSize(),
                    pasteNetwork.getExpireDate(),
                    pasteNetwork.getPastePrivate(),
                    pasteNetwork.getFormatLong(),
                    pasteNetwork.getFormatShort(),
                    pasteNetwork.getUrl(),
                    pasteNetwork.getHits()
            )));
        }
    }

    interface OnItemLongClickListener {
        void onItemLongClick(PasteRoom paste);
    }

    interface OnItemClickListener {
        void onItemClick(PasteRoom paste);
    }
}