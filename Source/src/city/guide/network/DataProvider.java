package city.guide.network;

import java.util.List;

import android.app.ProgressDialog;
import android.content.Context;

import com.androidquery.AQuery;

public abstract class DataProvider {

    protected Context mContext = null;
    protected AQuery mAQuery;
    protected ProgressDialog mProgressDialog;
    protected DataProviderListener mDataProviderListener;

    public DataProvider(Context context, DataProviderListener dataProviderListener, String loadingStringTitle) {
    // TODO Auto-generated constructor stub

        mContext = context;

        mDataProviderListener = dataProviderListener;

        mAQuery = new AQuery(context);

        mProgressDialog = new ProgressDialog(context);

        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setCancelable(true);
        mProgressDialog.setInverseBackgroundForced(false);
        mProgressDialog.setCanceledOnTouchOutside(true);
        mProgressDialog.setTitle(loadingStringTitle);
    }

    public abstract List<String> getNearByPlaces(String city, String establishment);

    public void cleanUp() {
    mAQuery.dismiss(mProgressDialog);
    }
}
