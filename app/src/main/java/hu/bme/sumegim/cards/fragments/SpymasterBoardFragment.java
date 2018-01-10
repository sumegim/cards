package hu.bme.sumegim.cards.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import hu.bme.sumegim.cards.R;
import hu.bme.sumegim.cards.adapters.SpymasterBoardAdapter;
import hu.bme.sumegim.cards.data.CahWhiteCard;
import hu.bme.sumegim.cards.games.SpymasterActivity;

/**
 * Created by mars on 2018.01.10..
 */

public class SpymasterBoardFragment extends Fragment {

    public SpymasterBoardFragment() {
    }

    public static SpymasterBoardFragment newInstance() {
        SpymasterBoardFragment fragment = new SpymasterBoardFragment();
        return fragment;
    }

    private RecyclerView recyclerViewWhiteCards;
    private SpymasterBoardAdapter whiteCardsAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (whiteCardsAdapter.getWhiteCardsList() != null)
            outState.putParcelableArrayList("key", new ArrayList<CahWhiteCard>(whiteCardsAdapter.getWhiteCardsList()));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_hand, container, false);

        whiteCardsAdapter = new SpymasterBoardAdapter(getActivity());
        recyclerViewWhiteCards = (RecyclerView) rootView.findViewById(R.id.recyclerViewWhiteCards);

        GridLayoutManager layoutManager = new GridLayoutManager(recyclerViewWhiteCards.getContext(), 5);
        //layoutManager.setReverseLayout(true);
        //layoutManager.setStackFromEnd(true);

        if (savedInstanceState != null){
            whiteCardsAdapter.setWhiteCardsList(savedInstanceState.<CahWhiteCard>getParcelableArrayList("key"));
            whiteCardsAdapter.notifyDataSetChanged();
        }

        recyclerViewWhiteCards.setLayoutManager(layoutManager);
        recyclerViewWhiteCards.setAdapter(whiteCardsAdapter);

        recyclerViewWhiteCards.setItemAnimator(new DefaultItemAnimator());

        if (savedInstanceState == null) {
            initCardsListener_mock();
        }


        return rootView;
    }

    private void initCardsListener_mock() {

        for (int i = 0; i < 25; i++) {
            CahWhiteCard newCard = new CahWhiteCard(i, "Card_" + (i+1), ((SpymasterActivity)getActivity()).getUid());
            whiteCardsAdapter.addWhiteCard(newCard);
        }

    }
}
