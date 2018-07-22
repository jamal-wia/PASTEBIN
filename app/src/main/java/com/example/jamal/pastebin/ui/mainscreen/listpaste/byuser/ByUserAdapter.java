package com.example.jamal.pastebin.ui.mainscreen.listpaste.byuser;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jamal.pastebin.R;
import com.example.jamal.pastebin.data.models.PasteByUser;

import java.util.List;

public class ByUserAdapter extends RecyclerView.Adapter<ByUserAdapter.PasteViewHolder> {

    private List<PasteByUser> pasteByUserList;

    ByUserAdapter(List<PasteByUser> pasteByUserList) {
        this.pasteByUserList = pasteByUserList;
    }

    @NonNull
    @Override
    public PasteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.paste_by_user_item,
                parent, false);
        return new PasteViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PasteViewHolder holder, int position) {
        holder.setPasteData(pasteByUserList.get(position));
    }

    @Override
    public int getItemCount() {
        return pasteByUserList.size();
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

        private void setPasteData(PasteByUser pasteData){
            titleTextView.setText(String.valueOf(pasteData.getTitle()));
            privateTextView.setText(String.valueOf(pasteData.getPastePrivate()));
            languageTextView.setText(String.valueOf(pasteData.getFormatLong()));
            sizeTextView.setText(String.valueOf(pasteData.getSize()));
        }
    }
}