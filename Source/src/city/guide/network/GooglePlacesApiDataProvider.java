package city.guide.network;

import java.util.ArrayList;
import java.util.List;

import city.guide.ui.SearchParametersFragment;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;
import city.guide.util.GlobalConstants;

import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;

public class GooglePlacesApiDataProvider extends DataProvider {

  private static final String GOOGLE_PLACES_API_KEY = "AIzaSyBGauP8EKQijnDV1I_cBZ6VEwaGZOfn-tU";

  private static final String NEARBY_SEARCH_BASE_URL =
      "https://maps.googleapis.com/maps/api/place/nearbysearch/";

  private static final String OUTPUT_TYPE = "json";

  public GooglePlacesApiDataProvider(Context context, DataProviderListener dataProviderListener,
                                                                String loadingStringTitle) {
    super(context, dataProviderListener, loadingStringTitle);
  }

  public ArrayList<String> getNearByPlaces(String city, String establishment) {

    String url =
        NEARBY_SEARCH_BASE_URL + OUTPUT_TYPE + "?"
            + "location=-33.8670522,151.1957362&radius=500&types=food&name=harbour&sensor=false"
            + "&" + "key=" + GOOGLE_PLACES_API_KEY;

    mAQuery.progress(mProgressDialog).ajax(url, JSONObject.class, new AjaxCallback<JSONObject>() {

      // aQuery.ajax(url, JSONObject.class, new AjaxCallback<JSONObject>() {

      @Override
      public void callback(String url, JSONObject json, AjaxStatus status) {

        String response = null;

        if (json != null) {
          // successful ajax call, show status code and json content
            response = status.getCode() + ":" + json.toString();

            ArrayList<String> searchResults = new ArrayList<String>();

            for(int i = 0; i < 5; i++) {
                searchResults.add(i, "String" + i);
            }

            // Inform the caller the data has been fetched and pass the results
            mDataProviderListener.onDataFetched(searchResults);

        } else {
          // ajax error, show error code
          response = "Error:" + status.getCode();
        }

        Log.d(GlobalConstants.TAG, response);
      }
    });

    return null;
  }
}
