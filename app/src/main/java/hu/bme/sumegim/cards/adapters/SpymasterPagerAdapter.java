package hu.bme.sumegim.cards.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import hu.bme.sumegim.cards.fragments.PlaceholderFragment;
import hu.bme.sumegim.cards.fragments.SpymasterBoardFragment;
import hu.bme.sumegim.cards.fragments.TableFragment;

/**
 * Created by mars on 2018.01.10..
 */

public class SpymasterPagerAdapter extends FragmentStatePagerAdapter {

    public SpymasterPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).

        if (position == 0) return SpymasterBoardFragment.newInstance();
        if (position == 1) return TableFragment.newInstance();


        return PlaceholderFragment.newInstance(position + 1);
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "BOARD";
            case 1:
                return "SECRET";
        }
        return null;
    }
}
