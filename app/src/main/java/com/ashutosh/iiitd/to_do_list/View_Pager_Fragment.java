package com.ashutosh.iiitd.to_do_list;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class View_Pager_Fragment extends Fragment {

    public static final String ARG_PAGE = "page";

    public static final String ARG_DETAILS="details";

    public static final String ARG_TITLE="title";


    private int mPageNumber;
    private String details;
    private String title;

    public static View_Pager_Fragment create(int pageNumber, String text, String title1) {
        View_Pager_Fragment fragment = new View_Pager_Fragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, pageNumber);
        args.putString(ARG_DETAILS,text);
        args.putString(ARG_TITLE,title1);
        fragment.setArguments(args);
        return fragment;
    }

    public View_Pager_Fragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPageNumber = getArguments().getInt(ARG_PAGE);
        details = getArguments().getString(ARG_DETAILS);

        title = getArguments().getString(ARG_TITLE);
        Log.d("Hello",details+" "+title);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout containing a title and body text.
        ViewGroup rootView = (ViewGroup) inflater
                .inflate(R.layout.fragment_view_pager, container, false);

        // Set the title view to show the page number.
        ((TextView) rootView.findViewById(R.id.text_title)).setText("ToDo "+(mPageNumber+1)+"-> "+title);
        ((TextView) rootView.findViewById(R.id.text_details)).setText(details);

        return rootView;
    }

    /**
     * Returns the page number represented by this fragment object.
     */
    public int getPageNumber() {
        return mPageNumber;
    }
}
