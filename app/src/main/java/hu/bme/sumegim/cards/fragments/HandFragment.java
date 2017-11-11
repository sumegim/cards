package hu.bme.sumegim.cards.fragments;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
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

import java.util.ArrayList;

import hu.bme.sumegim.cards.R;
import hu.bme.sumegim.cards.adapters.WhiteCardsAdapter;
import hu.bme.sumegim.cards.data.CahWhiteCard;
import hu.bme.sumegim.cards.games.CardsAgainstActivity;

/**
 * Created by mars on 2017.11.09..
 */

public class HandFragment extends Fragment {

    public HandFragment() {
    }

    public static HandFragment newInstance() {
        HandFragment fragment = new HandFragment();
        return fragment;
    }

    private RecyclerView recyclerViewWhiteCards;
    private WhiteCardsAdapter whiteCardsAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_hand, container, false);

        whiteCardsAdapter = new WhiteCardsAdapter(getActivity());
        recyclerViewWhiteCards = (RecyclerView) rootView.findViewById(R.id.recyclerViewWhiteCards);

        GridLayoutManager layoutManager = new GridLayoutManager(recyclerViewWhiteCards.getContext(), 2);
        //layoutManager.setReverseLayout(true);
        //layoutManager.setStackFromEnd(true);

        recyclerViewWhiteCards.setLayoutManager(layoutManager);
        recyclerViewWhiteCards.setAdapter(whiteCardsAdapter);

        recyclerViewWhiteCards.setItemAnimator(new DefaultItemAnimator());
        initCardsDownloader();

        final FloatingActionButton fab = (FloatingActionButton) getActivity().findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "Drawing Cards", Snackbar.LENGTH_LONG)
                //        .setAction("Action", null).show();

                try {
                    fab.hide();
                    whiteCardsAdapter.drawStartingHand();
                }catch (Exception e){
                    fab.show();
                }

            }
        });

        return rootView;
    }

    private void initCardsListener_mock() {

        for (int i = 0; i < 7; i++) {
            CahWhiteCard newCard = new CahWhiteCard(i, "Card_" + (i+1), ((CardsAgainstActivity)getActivity()).getUid());
            whiteCardsAdapter.addWhiteCard(newCard);
        }

    }


    private void initCardsDownloader(){
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("cards/cards_against_base/whiteCards");
        whiteCardsAdapter.whiteDeck = new ArrayList<>();

        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                whiteCardsAdapter.whiteDeck.add(dataSnapshot.getValue(String.class));
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

