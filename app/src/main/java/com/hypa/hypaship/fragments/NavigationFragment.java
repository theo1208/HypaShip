package com.hypa.hypaship.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hypa.hypaship.R;

import java.util.HashMap;
import java.util.Map;

public class NavigationFragment extends Fragment {
    View currentItem,previousItem;
    static Map<Integer,String> iconsMap=new HashMap<Integer, String>() {{
        put(R.id.menu_about, "baseline_info_24px");
        put(R.id.menu_logout, "baseline_power_settings_new_24px");
        put(R.id.menu_home, "baseline_home_24px");
        put(R.id.menu_jobs, "baseline_format_list_bulleted_24px");
        put(R.id.menu_memos, "baseline_description_24px");
        put(R.id.menu_load_vehicle, "truck_loading");
        put(R.id.menu_return_depot, "dolly");
        put(R.id.menu_summary, "baseline_notes_24px");
    }};
    final static String icon_name_suffix="_active";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_navigation, null);
        return view;
    }

    public void setCurrentItem(View view) {

        if(currentItem!=null&&view.getId()==currentItem.getId())return;

        previousItem=currentItem;

        currentItem=view;

        String activeIcon=iconsMap.get(currentItem.getId())+icon_name_suffix;

        int drawableResourceId = this.getResources().getIdentifier(activeIcon, "drawable", getContext().getPackageName());

        ((TextView)currentItem).setCompoundDrawablesWithIntrinsicBounds(drawableResourceId,0,0,0);

        if(previousItem!=null)
        {
            String previousIcon=iconsMap.get(previousItem.getId());

            int prevdrawableResourceId = this.getResources().getIdentifier(previousIcon, "drawable", getContext().getPackageName());

            ((TextView)previousItem).setCompoundDrawablesWithIntrinsicBounds(prevdrawableResourceId,0,0,0);
        }


    }

    public View getCurrentItem() {
        return currentItem;
    }
}
