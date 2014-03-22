package city.guide.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import city.guide.util.GlobalConstants;

public class CityGuideSqliteOpenHelper extends SQLiteOpenHelper {

  private static CityGuideSqliteOpenHelper cityGuideSqliteOpenHelper = null;

  private static final String DB_NAME = "cityguide.db";
  private static final int DB_VERSION = 1;

  public static final String TABLE_CITY = "city";
  public static final String TABLE_CITY_COLUMN_ID = "_id";
  public static final String TABLE_CITY_COLUMN_NAME = "name";

  private static final String CREATE_CITY_TABLE_STATEMENT = "create table " + TABLE_CITY + "("
      + TABLE_CITY_COLUMN_ID + " integer primary key autoincrement, " + TABLE_CITY_COLUMN_NAME
      + " text not null);";

  private CityGuideSqliteOpenHelper(Context context) {
    super(context, DB_NAME, null, DB_VERSION);
  }

  public static CityGuideSqliteOpenHelper getInstance(Context context) {

    if (cityGuideSqliteOpenHelper == null) {
      cityGuideSqliteOpenHelper = new CityGuideSqliteOpenHelper(context);
      return cityGuideSqliteOpenHelper;
    } else
      return cityGuideSqliteOpenHelper;
  }

  @Override
  public void onCreate(SQLiteDatabase db) {
    // TODO Auto-generated method stub

    // Create city guide database by creating its first table
    db.execSQL(CREATE_CITY_TABLE_STATEMENT);
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    // TODO Auto-generated method stub

    Log.w(GlobalConstants.TAG, "Upgrading database from version " + oldVersion + " to "
        + newVersion + ", which will destroy all old data");

    db.execSQL("DROP TABLE IF EXISTS " + TABLE_CITY);
    onCreate(db);
  }
}
