package hu.bme.sumegim.cards.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import hu.bme.sumegim.cards.R;
import hu.bme.sumegim.cards.adapters.WhiteCardsAdapter;
import hu.bme.sumegim.cards.data.CahWhiteCard;

/**
 * Created by mars on 2017.11.09..
 */

public class HandFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */


    public HandFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
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

        initCardsListener();

        return rootView;
    }

    private void initCardsListener() {

        for (int i = 0; i < 7; i++) {
            CahWhiteCard newCard = new CahWhiteCard(i, "Card_" + (i+1));
            whiteCardsAdapter.addWhiteCard(newCard, "");
        }

    }
}
