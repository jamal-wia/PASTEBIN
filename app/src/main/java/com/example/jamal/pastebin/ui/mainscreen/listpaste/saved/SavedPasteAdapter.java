package com.example.jamal.pastebin.ui.mainscreen.listpaste.saved;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jamal.pastebin.R;
import com.example.jamal.pastebin.data.models.PasteRoom;

import java.util.List;

public class SavedPasteAdapter extends RecyclerView.Adapter<SavedPasteAdapter.Holder> {
    private List<PasteRoom> pasteRooms;

    private OnItemLongClickListener itemLongClickListener;
    private OnItemClickListener itemClickListener;

    SavedPasteAdapter(List<PasteRoom> pasteRooms) {
        this.pasteRooms = pasteRooms;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_savepaste,
                parent, false);
        return new Holder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.setPasteData(pasteRooms.get(position));
    }

    @Override
    public int getItemCount() {
        return pasteRooms.size();
    }

    public void setItemLongClickListener(OnItemLongClickListener itemLongClickListener) {
        this.itemLongClickListener = itemLongClickListener;
    }

    public void setItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public List<PasteRoom> getPasteRooms() {
        return pasteRooms;
    }

    public void setPasteRooms(List<PasteRoom> pasteRooms) {
        this.pasteRooms = pasteRooms;
    }

    class Holder extends RecyclerView.ViewHolder {

        private TextView titleTextView;
        private TextView privateTextView;
        private TextView languageTextView;
        private TextView sizeTextView;

        Holder(View itemView) {
            super(itemView);
            initViews(itemView);
        }

        private void initViews(View itemView) {
            titleTextView = itemView.findViewById(R.id.TextView_savedPaste_title);
            privateTextView = itemView.findViewById(R.id.TextView__savedPaste_private);
            languageTextView = itemView.findViewById(R.id.TextView_savedPaste_language);
            sizeTextView = itemView.findViewById(R.id.TextView_savedPaste_size);
        }

        private void setPasteData(PasteRoom pasteRoom) {
            titleTextView.setText(String.valueOf(pasteRoom.getTitle()));
            sizeTextView.setText(String.valueOf(pasteRoom.getSize()));

            if (pasteRoom.getPastePrivate() == 0) {
                privateTextView.setText("Public");
            } else {
                privateTextView.setText("Private");
            }

            if (pasteRoom.getFormatLong().equals("None")) {
                languageTextView.setText("Text");
            } else {
                languageTextView.setText(String.valueOf(pasteRoom.getFormatLong()));
            }

            itemView.setOnLongClickListener(v -> {
                itemLongClickListener.onItemLongClick(pasteRoom);
                return false;
            });

            itemView.setOnClickListener(v -> {
                itemClickListener.onItemClick(pasteRoom);
            });
        }
    }

    public interface OnItemLongClickListener {
        void onItemLongClick(PasteRoom pasteRoom);
    }

    public interface OnItemClickListener {
        void onItemClick(PasteRoom pasteRoom);
    }
}