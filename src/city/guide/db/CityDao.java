package city.guide.db;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import city.guide.util.GlobalConstants;


public class CityDao {

  private SQLiteDatabase cityGuideDb;
  private CityGuideSqliteOpenHelper cityGuideSqliteOpenHelper;

  // Column indexes for City table
  private static final int COLUMN_ID = 0;
  private static final int COLUMN_NAME = 1;

  private String[] allColumns = {CityGuideSqliteOpenHelper.TABLE_CITY_COLUMN_ID,
      CityGuideSqliteOpenHelper.TABLE_CITY_COLUMN_NAME};

  public CityDao(Context context) {
    cityGuideSqliteOpenHelper = CityGuideSqliteOpenHelper.getInstance(context);
  }

  public void open() {
    cityGuideDb = cityGuideSqliteOpenHelper.getWritableDatabase();
  }

  public void close() {
    cityGuideSqliteOpenHelper.close();
  }

  public City createCity(String name) {
    ContentValues values = new ContentValues();
    values.put(CityGuideSqliteOpenHelper.TABLE_CITY_COLUMN_NAME, name);

    // Insert a new row
    long insertId = cityGuideDb.insert(CityGuideSqliteOpenHelper.TABLE_CITY, null, values);

    // Get the cursor
    Cursor cursor =
        cityGuideDb.query(CityGuideSqliteOpenHelper.TABLE_CITY, allColumns,
            CityGuideSqliteOpenHelper.TABLE_CITY_COLUMN_ID + " = " + insertId, null, null, null,
            null);

    cursor.moveToFirst();
    City city = getCityFromCursor(cursor);
    cursor.close();

    return city;
  }

  public void deleteCity(City city) {

    long id = city.getId();

    Log.d(GlobalConstants.TAG, "City deleted with id: " + id);

    cityGuideDb.delete(CityGuideSqliteOpenHelper.TABLE_CITY,
        CityGuideSqliteOpenHelper.TABLE_CITY_COLUMN_ID + " = " + id, null);
  }

  public List<City> getAllCities() {

    List<City> cities = new ArrayList<City>();

    Cursor cursor =
        cityGuideDb.query(CityGuideSqliteOpenHelper.TABLE_CITY, allColumns, null, null, null, null,
            null);

    cursor.moveToFirst();

    while (cursor.isAfterLast() == false) {
      City city = getCityFromCursor(cursor);
      cities.add(city);
      cursor.moveToNext();
    }

    cursor.close();

    return cities;
  }

  private City getCityFromCursor(Cursor cursor) {
    City city = new City();

    city.setId(cursor.getLong(COLUMN_ID));
    city.setName(cursor.getString(COLUMN_NAME));

    return city;
  }
}
