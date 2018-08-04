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

    public SavedPasteAdapter(List<PasteRoom> pasteRooms) {
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

    class Holder extends RecyclerView.ViewHolder {

        private TextView titleTextView;
        private TextView privateTextView;
        private TextView languageTextView;
        private TextView sizeTextView;

        public Holder(View itemView) {
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
            privateTextView.setText(String.valueOf(pasteRoom.getPastePrivate()));
            languageTextView.setText(String.valueOf(pasteRoom.getFormatLong()));
            sizeTextView.setText(String.valueOf(pasteRoom.getSize()));
        }
    }
}