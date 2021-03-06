package wia.soft.official.pastebin.ui.mainscreen.listpaste.trending;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jamal.pastebin.R;

import java.util.List;

import wia.soft.official.pastebin.data.models.PasteNetwork;
import wia.soft.official.pastebin.data.models.PasteRoom;

import static wia.soft.official.pastebin.utils.MathUtil.round;


public class PasteTrendingAdapter extends RecyclerView.Adapter<PasteTrendingAdapter.PasteViewHolder> {
    private List<PasteNetwork> pasteNetworkList;

    private OnItemLongClickListener itemLongClickListener;
    private OnItemClickListener itemClickListener;

    public PasteTrendingAdapter(List<PasteNetwork> pasteNetworkList) {
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


    public void setItemLongClickListener(OnItemLongClickListener itemLongClickListener) {
        this.itemLongClickListener = itemLongClickListener;
    }

    public void setItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
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
            titleTextView = itemView.findViewById(R.id.TextView_paste_user_item_title);
            privateTextView = itemView.findViewById(R.id.TextView_paste_user_item_private);
            languageTextView = itemView.findViewById(R.id.TextView_paste_user_item_language);
            sizeTextView = itemView.findViewById(R.id.TextView_paste_user_item_size);
        }

        private void setPasteData(PasteNetwork pasteNetworkData) {
            titleTextView.setText(String.valueOf(pasteNetworkData.getTitle()));
            Double size = Double.valueOf(pasteNetworkData.getSize()) / 1024.0;
            sizeTextView.setText(round(size,2).toString()+" mb");

            if (pasteNetworkData.getPastePrivate() == 0) {
                privateTextView.setText("Public");
            } else {
                privateTextView.setText("Private");
            }

            if (pasteNetworkData.getFormatLong().equals("None")) {
                languageTextView.setText("Text");
            } else {
                languageTextView.setTextColor(itemView.getResources().getColor(R.color.colorAccent));
                languageTextView.setText(String.valueOf(pasteNetworkData.getFormatLong()));
            }

            itemView.setOnLongClickListener(v -> {
                itemLongClickListener.onItemLongClick(new PasteRoom(pasteNetworkData.getKey(),
                        pasteNetworkData.getDate(),
                        pasteNetworkData.getTitle(),
                        pasteNetworkData.getSize(),
                        pasteNetworkData.getExpireDate(),
                        pasteNetworkData.getPastePrivate(),
                        pasteNetworkData.getFormatLong(),
                        pasteNetworkData.getFormatShort(),
                        pasteNetworkData.getUrl(),
                        pasteNetworkData.getHits()));
                return false;
            });

            itemView.setOnClickListener(v ->
                    itemClickListener.onItemClick(new PasteRoom(pasteNetworkData.getKey(),
                            pasteNetworkData.getDate(),
                            pasteNetworkData.getTitle(),
                            pasteNetworkData.getSize(),
                            pasteNetworkData.getExpireDate(),
                            pasteNetworkData.getPastePrivate(),
                            pasteNetworkData.getFormatLong(),
                            pasteNetworkData.getFormatShort(),
                            pasteNetworkData.getUrl(),
                            pasteNetworkData.getHits())));
        }
    }

    public interface OnItemLongClickListener {
        void onItemLongClick(PasteRoom pasteRoom);
    }

    public interface OnItemClickListener {
        void onItemClick(PasteRoom pasteRoom);
    }
}