package hu.bme.sumegim.cards.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import hu.bme.sumegim.cards.R;
import hu.bme.sumegim.cards.adapters.TableAdapter;
import hu.bme.sumegim.cards.data.CahBlackCard;
import hu.bme.sumegim.cards.games.CardsAgainstActivity;

/**
 * Created by mars on 2017.11.10..
 */

public class TableFragment extends Fragment{

    public TableFragment() {
    }

    public static TableFragment newInstance() {
        TableFragment fragment = new TableFragment();
        return fragment;
    }

    private RecyclerView recyclerViewTableWhiteCards;
    private TableAdapter tableAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_table, container, false);

        tableAdapter = new TableAdapter(getActivity());
        recyclerViewTableWhiteCards = (RecyclerView) rootView.findViewById(R.id.recyclerViewTableWhiteCards);

        GridLayoutManager layoutManager = new GridLayoutManager(recyclerViewTableWhiteCards.getContext(), 2);

        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                    return position == 0 ? 2 : 1;
            }
        });
        //layoutManager.setReverseLayout(true);
        //layoutManager.setStackFromEnd(true);

        recyclerViewTableWhiteCards.setLayoutManager(layoutManager);
        recyclerViewTableWhiteCards.setAdapter(tableAdapter);

        recyclerViewTableWhiteCards.setItemAnimator(new DefaultItemAnimator());


        initCardsListener();

        return rootView;
    }

    private void initCardsListener_mock() {

        CahBlackCard newBCard = new CahBlackCard(-1, "LONG PRESS ME TO DRAW A CARD", ((CardsAgainstActivity)getActivity()).getUid());
        tableAdapter.addBlackCard(newBCard);

        /*
        for (int i = 0; i < 7; i++) {
            CahWhiteCard newCard = new CahWhiteCard(i, "TableCard_" + (i+1), ((CardsAgainstActivity)getActivity()).getUid());
            tableAdapter.addWhiteCard(newCard);
        }
    */
    }

    private void initCardsListener() {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("cards/cards_against_base/whiteCards");
        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                //CahWhiteCard newCard = new CahWhiteCard(0, dataSnapshot.getValue(String.class));
                //tableAdapter.addWhiteCard(newCard);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                // remove post from adapter
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
