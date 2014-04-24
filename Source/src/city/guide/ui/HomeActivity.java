package city.guide.ui;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import city.guide.R;
import city.guide.network.DataProvider;
import city.guide.network.DataProviderListener;
import city.guide.network.GooglePlacesApiDataProvider;

public class HomeActivity extends Activity implements SearchFragmentController {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_home);

//    if (savedInstanceState == null) {
//      getFragmentManager().beginTransaction().add(R.id.container, new PlaceholderFragment())
//          .commit();
//    }
  }


  @Override
  public boolean onCreateOptionsMenu(Menu menu) {

    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();
    if (id == R.id.action_settings) {
      return true;
    }
    return super.onOptionsItemSelected(item);
  }

    @Override
    public void onSearchResults(ArrayList<String> searchResultsList) {

        FragmentManager fm = getFragmentManager();
        SearchResultsFragment searchResultsFragment = new SearchResultsFragment(searchResultsList);

        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.fragment_search_results, searchResultsFragment, "Search Results");
        //ft.addToBackStack("DialogAsARegularFragment");
        ft.commit();
    }
}
