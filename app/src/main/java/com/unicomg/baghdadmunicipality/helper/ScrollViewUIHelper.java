package com.unicomg.baghdadmunicipality.helper;

import android.view.View;
import android.widget.ScrollView;

public class ScrollViewUIHelper {
    private static final ScrollViewUIHelper ourInstance = new ScrollViewUIHelper();


    public static ScrollViewUIHelper getInstance() {

        return ourInstance;
    }

    private ScrollViewUIHelper() {

    }


    public void scrollTo(View childView , ScrollView scollViewTOScroll){
        childView.getParent().requestChildFocus(childView , childView);
        //scollViewTOScroll.scrollTo(50,0);
    }
}
