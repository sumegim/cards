package hu.bme.sumegim.cards.adapters;

/**
 * Created by mars on 2017.11.09..
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import hu.bme.sumegim.cards.fragments.HandFragment;
import hu.bme.sumegim.cards.fragments.PlaceholderFragment;
import hu.bme.sumegim.cards.fragments.TableFragment;

/**
 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentStatePagerAdapter {

    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).

        if (position == 0) return HandFragment.newInstance();
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
                return "HAND";
            case 1:
                return "TABLE";
        }
        return null;
    }
}