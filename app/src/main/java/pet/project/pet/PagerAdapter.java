package pet.project.pet;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import pet.project.pet.fragment.GroupFragment;
import pet.project.pet.fragment.RecentFragment;
import pet.project.pet.fragment.SubjectFragment;
import pet.project.pet.model.Group;
import pet.project.pet.model.Subject;

public class PagerAdapter extends FragmentStatePagerAdapter {
    private ArrayList<Group> groups = new ArrayList<Group>();
    private ArrayList<Subject> subjects = new ArrayList<Subject>();
//    PagerAdapter(FragmentManager fragmentManager) {
//        super(fragmentManager);
//    }

    PagerAdapter(FragmentManager fragmentManager, List<Group> groups, List<Subject> subjects) {
        super(fragmentManager);

        this.groups.addAll(groups);
        this.subjects.addAll(subjects);
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
                Bundle bundle = new Bundle();
                bundle.putSerializable("groupList",this.groups);
                frag.setArguments(bundle);
                break;
            case 2:
                frag = new SubjectFragment();
                Bundle newBundle = new Bundle();
                newBundle.putSerializable("subjectList",this.subjects);
                frag.setArguments(newBundle);
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
