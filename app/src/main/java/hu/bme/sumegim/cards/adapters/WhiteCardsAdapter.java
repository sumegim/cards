package hu.bme.sumegim.cards.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import hu.bme.sumegim.cards.R;
import hu.bme.sumegim.cards.data.CahWhiteCard;

/**
 * Created by mars on 2017.11.09..
 */

public class WhiteCardsAdapter extends RecyclerView.Adapter<WhiteCardsAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvText;

        public ViewHolder(View itemView){
            super(itemView);
            tvText = (TextView) itemView.findViewById(R.id.tvText);
        }
    }

    private Context context;
    private List<CahWhiteCard> whiteCardsList;
    private int lastPosition = -1;

    public WhiteCardsAdapter(Context context) {
        this.context = context;
        this.whiteCardsList = new ArrayList<CahWhiteCard>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_white_card, viewGroup, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        CahWhiteCard tmpWhiteCard = whiteCardsList.get(position);
        viewHolder.tvText.setText(tmpWhiteCard.getText());
        setAnimation(viewHolder.itemView, position);
    }

    @Override
    public int getItemCount() {
        return whiteCardsList.size();
    }

    public void addWhiteCard(CahWhiteCard whiteCard, String key){
        whiteCardsList.add(whiteCard);
        notifyDataSetChanged();
    }

    private void setAnimation(View viewToAnimate, int position) {
        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(context,
                    android.R.anim.slide_in_left);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }

}
