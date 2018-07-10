package pet.project.pet;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import pet.project.pet.fragment.GroupFragment;
import pet.project.pet.fragment.RecentFragment;
import pet.project.pet.fragment.SubjectFragment;

public class PagerAdapter extends FragmentStatePagerAdapter {

    PagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }
    @Override
    public Fragment getItem(int position) {
        Fragment frag=null;
        switch (position){
            case 0:
                frag = new RecentFragment();
                break;
            case 1:
                frag = new GroupFragment();
                break;
            case 2:
                frag = new SubjectFragment();
                break;
        }
        return frag;
    }

    @Override
    public int getCount() {
        return 3;
    }
    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position){
            case 0:
                title = "Recent";
                break;
            case 1:
                title = "Group";
                break;
            case 2:
                title = "Subject";
                break;
        }
        return title;
    }
}
