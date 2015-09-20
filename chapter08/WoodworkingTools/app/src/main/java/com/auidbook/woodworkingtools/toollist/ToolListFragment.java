package com.auidbook.woodworkingtools.toollist;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.AdapterView;

import com.auidbook.woodworkingtools.Tool;
import com.auidbook.woodworkingtools.tooldetails.ToolDetailActivity;

import java.util.List;


/**
 * A placeholder fragment containing a simple view.
 */
public class ToolListFragment extends ListFragment implements AdapterView.OnItemClickListener {


    private static final String ARG_TOOL_TAB = "toolTab";

    private ToolTab mToolTab;
    private ToolArrayAdapter mToolArrayAdapter;

    public static ToolListFragment newInstance(ToolTab toolTab) {
        final ToolListFragment fragment = new ToolListFragment();
        final Bundle args = new Bundle();
        args.putParcelable(ARG_TOOL_TAB, toolTab);
        fragment.setArguments(args);
        return fragment;
    }

    public ToolListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle args = getArguments();
        if (args == null) {
            throw new IllegalStateException("No arguments set; use newInstance when constructing!");
        }
        mToolTab = args.getParcelable(ARG_TOOL_TAB);
        final List<Tool> tools = ToolListGenerator.getTools(mToolTab);
        mToolArrayAdapter = new ToolArrayAdapter(getActivity(), tools);
        setListAdapter(mToolArrayAdapter);
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getListView().setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        ToolDetailActivity.startActivity(getActivity(), mToolArrayAdapter.getItem(position));
    }
}