package hu.bme.sumegim.cards.adapters;

/**
 * Created by mars on 2017.11.10..
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import hu.bme.sumegim.cards.R;
import hu.bme.sumegim.cards.data.CahBlackCard;
import hu.bme.sumegim.cards.data.CahWhiteCard;

public class TableAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;

    public List<CahBlackCard> blackDeck;

    CahBlackCard blackCard;
    List<CahWhiteCard> whiteCards;
    Context context;

    public TableAdapter(Context context) {
        this.context = context;
        this.whiteCards = new ArrayList<CahWhiteCard>();
        this.blackDeck = new ArrayList<>();
        this.blackCard = new CahBlackCard(1, "LONG PRESS ME TO DRAW A CARD");
    }

    public TableAdapter(CahBlackCard header, List<CahWhiteCard> listItems)
    {
        this.blackCard = header;
        this.whiteCards = listItems;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == TYPE_HEADER)
        {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_black_card, parent, false);
            return  new VHHeader(v);
        }
        else if(viewType == TYPE_ITEM)
        {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_white_card, parent, false);
            return new VHItem(v);
        }
            throw new RuntimeException("there is no type that matches the type " + viewType + " + make sure your using types correctly");
    }

    private CahWhiteCard getItem(int position)
        {
        return whiteCards.get(position);
        }


    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if(holder instanceof VHHeader)
        {
            VHHeader VHheader = (VHHeader)holder;
            VHheader.txtTitle.setText(blackCard.getText());

            VHheader.cardView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {

                    CahBlackCard newBlackCard = drawBlackCard();
                    newBlackCard.setOwnerUid(FirebaseAuth.getInstance().getCurrentUser().getUid());

                    FirebaseDatabase.getInstance().getReference().child("bc_post").removeValue();

                    String key = FirebaseDatabase.getInstance().getReference().child("bc_post").push().getKey();
                    FirebaseDatabase.getInstance().getReference().child("bc_post").child(key).setValue(newBlackCard);

                    FirebaseDatabase.getInstance().getReference().child("posts").removeValue();
                    removeWhiteCards();

                    return false;
                }
            });
        }
        else if(holder instanceof VHItem)
        {
            final CahWhiteCard currentItem = getItem(position-1);
            final VHItem VHitem = (VHItem)holder;

            if (currentItem.isRevealed())
                VHitem.txtName.setText(currentItem.getText());
            else
                VHitem.txtName.setText(" ??? ");

            VHitem.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // ITEM LISTENER IDE
                    currentItem.setRevealed(true);
                    notifyItemChanged(holder.getAdapterPosition());
                }
            });

            VHitem.cardView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (currentItem.isRevealed()){
                        if (blackCard.getOwnerUid().equals(FirebaseAuth.getInstance().getCurrentUser().getUid()))
                            Toast.makeText(context, currentItem.getOwnerUid() + " +1" , Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(context, currentItem.getOwnerUid(), Toast.LENGTH_SHORT).show();
                    }

                    return false;
                }
            });
        }
    }

    public void addWhiteCard(CahWhiteCard whiteCard){
        whiteCards.add(whiteCard);
        notifyItemInserted(whiteCards.size());
        //notifyDataSetChanged();
    }

    public CahBlackCard drawBlackCard(){
        int r = new Random().nextInt(blackDeck.size());
        addBlackCard(blackDeck.get(r));
        return blackDeck.get(r);
    }

    public void addBlackCard(CahBlackCard blackCard){
        this.blackCard = blackCard;
        notifyItemChanged(0);
    }

    public void removeWhiteCards(){
        int n = whiteCards.size();
        whiteCards.clear();
        notifyItemRangeRemoved(1, n);
    }

    //    need to override this method
    @Override
    public int getItemViewType(int position) {
        if(isPositionHeader(position))
            return TYPE_HEADER;
        return TYPE_ITEM;
    }

    private boolean isPositionHeader(int position)
    {
        return position == 0;
    }

    //increasing getItemcount to 1. This will be the row of header.
    @Override
    public int getItemCount() {
        return whiteCards.size()+1;
    }

    class VHHeader extends RecyclerView.ViewHolder{
        TextView txtTitle;
        View cardView;
        public VHHeader(View itemView) {
            super(itemView);
            cardView = itemView;
            this.txtTitle = (TextView)itemView.findViewById(R.id.tvTextBlackCard);
        }
    }

    class VHItem extends RecyclerView.ViewHolder{
        TextView txtName;
        View cardView;

        //ImageView iv;
        public VHItem(View itemView) {
            super(itemView);
            cardView = itemView;
            this.txtName = (TextView)itemView.findViewById(R.id.tvText);

            //this.iv = (ImageView)itemView.findViewById(R.id.tvText);
        }
    }
}


