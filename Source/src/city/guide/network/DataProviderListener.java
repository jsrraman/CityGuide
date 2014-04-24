package city.guide.network;

import java.util.ArrayList;

// Client calling data provider should implement this
public interface DataProviderListener {

  public void onDataFetched(ArrayList<String> data);

  public void onError();
}
