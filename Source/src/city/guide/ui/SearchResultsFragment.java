package city.guide.ui;

import android.app.Fragment;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rajaraman Subramanian on 07-Apr-2014.
 */
public class SearchResultsFragment extends ListFragment {

    ArrayList<String> mSearchResultsList = null;

    public SearchResultsFragment() {}

    public SearchResultsFragment(ArrayList<String> searchResultsList) {

        mSearchResultsList = searchResultsList;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ArrayAdapter<String> searchResultsAdapter =
                                               new ArrayAdapter<String>(getActivity(),
                                                        android.R.layout.simple_list_item_activated_1, mSearchResultsList);

        setListAdapter(searchResultsAdapter);
    }
}