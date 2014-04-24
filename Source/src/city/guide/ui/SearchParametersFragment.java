package city.guide.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import city.guide.R;
import city.guide.network.DataProvider;
import city.guide.network.DataProviderListener;
import city.guide.network.GooglePlacesApiDataProvider;

import java.util.ArrayList;

/**
 * Created Rajaraman Subramanian on 07-Apr-2014.
 */
public class SearchParametersFragment extends Fragment implements View.OnClickListener,
                                                                  DataProviderListener {

    private DataProvider mGooglePlacesApiDataProvider;

    public SearchParametersFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_search_parameters, container, false);

        mGooglePlacesApiDataProvider =
                new GooglePlacesApiDataProvider(getActivity(), this, getActivity().getResources().getString(
                        R.string.string_fetching_data));

        initUiControls(rootView);

        Button btnSearch = (Button) rootView.findViewById(R.id.button_search);
        btnSearch.setOnClickListener(this);

        return rootView;
    }

    private void initUiControls(View rootView) {
        Spinner spinner = (Spinner) rootView.findViewById(R.id.spinner_city);
        populateDropDownControl(spinner, R.array.city_array);

        spinner = (Spinner) rootView.findViewById(R.id.spinner_category);
        populateDropDownControl(spinner, R.array.category_array);
    }

    private void populateDropDownControl(Spinner spinner, int resId) {

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter =
                ArrayAdapter.createFromResource(getActivity(), resId,
                        android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
    }

    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();

        mGooglePlacesApiDataProvider.cleanUp();
    }

    @Override
    public void onClick(View v) {

        int viewId = v.getId();

        switch(viewId) {
            case R.id.button_search: {
                mGooglePlacesApiDataProvider.getNearByPlaces("Chennai", "restaurant");
                break;
            }
        }
    }

    // SearchFragmentController callbacks
    @Override
    public void onDataFetched(ArrayList<String> data) {

        SearchFragmentController searchFragmentController =
                                                (SearchFragmentController)getActivity();

        searchFragmentController.onSearchResults(data);
    }

    @Override
    public void onError() {
        // TODO Auto-generated method stub

    }
}