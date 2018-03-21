package com.project.lostfound.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.project.lostfound.model.Report;
import com.project.lostfound.model.User;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class LostFoundDatabase extends SQLiteOpenHelper {

    private SQLiteDatabase sqLiteDatabase;

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "lostandfound";

    // Tables
    private static final String USERS = "users";
    private static final String REPORT = "report";

    // Columns - Users
    private static final String USERID = "userid";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String FIRSTNAME = "firstname";
    private static final String LASTNAME = "lastname";
    private static final String EMAIL = "email";
    private static final String USERTYPE = "type";

    // Columns - ReportMain
    private static final String REPORTID = "reportid";
    private static final String TYPE = "type";
    private static final String DATEREPORTED = "datereported";
    private static final String ITEM_NAME = "itemname";
    private static final String DETAILS = "details";
    private static final String BRAND = "brand";
    private static final String CATEGORY = "category";
    private static final String LOCATION = "location";
    private static final String CONTACTINFORMATION = "contactinformation";
    private static final String REMARKS = "remarks";

    // Create Users table
    private String createAccount = "CREATE TABLE " + USERS + "("
            + USERID + " INTEGER PRIMARY KEY,"
            + USERNAME + " TEXT,"
            + PASSWORD + " TEXT,"
            + FIRSTNAME + " TEXT,"
            + LASTNAME + " TEXT,"
            + EMAIL + " TEXT,"
            + USERTYPE + " TEXT" + ")";

    // Create ReportMain table
    private String createReport = "CREATE TABLE " + REPORT + "("
            + REPORTID + " INTEGER PRIMARY KEY,"
            + TYPE + " TEXT,"
            + DATEREPORTED + " TEXT,"
            + ITEM_NAME + " TEXT,"
            + DETAILS + " TEXT,"
            + BRAND + " TEXT,"
            + CATEGORY + " TEXT,"
            + LOCATION + " TEXT,"
            + CONTACTINFORMATION + " TEXT,"
            + REMARKS + " TEXT" + ")";

    private String generateUser = "INSERT INTO " + USERS + " VALUES "
            + "(1, 'admin','admin', 'ReportLost and ReportFound', 'Admin', 'admin@gmail.com', '0'),"
            + "(23,'user', 'user', 'John', 'Doe', 'johndoe@gmail.com', '1');" + ")";

    private String generateItems = "INSERT INTO " + REPORT + " VALUES "
            + "(1, 'found', '2018-03-05', 'Physics Book', 'College Physics Textbook by Raymond Serway', '', 'School Supply', 'Room 302', 'Please visit SAO', 'ReportFound on Room 303'),"
            + "(2, 'lost', '2018-02-22', 'Purse', 'Blue striped with keychain', '', 'Accessories', 'Canteen', 'Please contact me if you saw this 12345678', ''),"
            + "(3, 'lost', '2018-03-15', 'Umbrella', 'Yellow Color', '', 'Others', 'ST Bldg', '', ''),"
            + "(4, 'lost', '2018-03-18', 'Reading Glasses', 'Black', '', 'Accessories', 'Library', 'Call 32003113', ''),"
            + "(5, 'lost', '2018-03-10', 'Iphone7', 'If you saw please return', 'Iphone', 'Gadgets', 'Cark Park', '', ''),"
            + "(6, 'found', '2018-01-20', 'Water Bottle', 'Natures Spring', '', 'Others', 'Canteen', '', ''),"
            + "(7, 'lost', '2018-02-05', 'Half face helmet', 'If you saw please return', 'RXR', 'Accessories', 'Cark Park', '', ''),"
            + "(8, 'lost', '2018-03-10', 'Bracelet', 'Sentimental value', '', 'Accessories', 'Cark Park', 'Contact Abby Jane', ''),"
            + "(9, 'found', '2018-02-11', 'Sketchpad', 'ABGM owner', 'Corona', 'Others', 'Room 301', 'Please proceed to SAO', ''),"
            + "(10, 'lost', '2018-03-10', 'PSP', '', 'PSP', 'Gadgets', 'Mining Bldg', '', ''),"
            + "(11, 'found', '2018-03-13', 'Gaming Mouse', 'RGB Gaming Mouse', 'Razer', 'Gadgets', 'ST Bldg', '', ''),"
            + "(12, 'lost', '2018-03-06', 'Shoulder Bag', 'White color large', '', 'Accessories', 'Nursing Bldg', '', ''),"
            + "(13, 'found', '2018-03-20', 'Pouch', 'with cellphone accessories', '', 'Gadgets', 'Room 402', 'Please proceed to SAO', '');"+ ")";


    /**
     * Constructor
     *
     * @param context
     */
    public LostFoundDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        // Execute SQL
        sqLiteDatabase.execSQL(createAccount);
        sqLiteDatabase.execSQL(createReport);
        sqLiteDatabase.execSQL(generateUser);
        sqLiteDatabase.execSQL(generateItems);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

        // Drop table if exist
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + USERS);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + REPORT);

        // Create tables again
        onCreate(sqLiteDatabase);
    }


    // Count all reports
    public int countReports() {
        String count = "SELECT * FROM " + REPORT;
        sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(count, null);
        cursor.close();

        return cursor.getCount();
    }

    /**
     * This method is to create user record
     *
     * @param user
     */
    public void addUser(User user) {
        sqLiteDatabase = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(USERNAME, user.getUsername());
        values.put(FIRSTNAME, user.getFirstname());
        values.put(LASTNAME, user.getLastname());
        values.put(EMAIL, user.getEmail());
        values.put(PASSWORD, user.getPassword());

        // Inserting row
        sqLiteDatabase.insert(USERS, null, values);
        sqLiteDatabase.close();
    }

    /**
     * This method is to fetch all user and return the list of user records
     *
     * @return list
     */
    public List<User> getAllUser() {
        // array of columns to fetch
        String[] columns = {
                USERID,
                USERNAME,
                FIRSTNAME,
                LASTNAME,
                EMAIL,
                PASSWORD

        };
        // sorting orders
        String sortOrder =
                USERNAME + " ASC";
        List<User> userList = new ArrayList<User>();

        sqLiteDatabase = this.getReadableDatabase();

        // query the user table
        /*
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id,user_name,user_email,user_password FROM user ORDER BY user_name;
         */
        Cursor cursor = sqLiteDatabase.query(USERS, //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order


        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(USERID))));
                user.setUsername(cursor.getString(cursor.getColumnIndex(USERNAME)));
                user.setFirstname(cursor.getString(cursor.getColumnIndex(FIRSTNAME)));
                user.setLastname(cursor.getString(cursor.getColumnIndex(LASTNAME)));
                user.setEmail(cursor.getString(cursor.getColumnIndex(EMAIL)));
                user.setPassword(cursor.getString(cursor.getColumnIndex(PASSWORD)));
                // Adding user record to list
                userList.add(user);
            } while (cursor.moveToNext());
        }
        cursor.close();
        sqLiteDatabase.close();

        // return user list
        return userList;
    }

    /**
     * This method to update user record
     *
     * @param user
     */
    public void updateUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(USERNAME, user.getUsername());
        values.put(FIRSTNAME, user.getFirstname());
        values.put(LASTNAME, user.getLastname());
        values.put(EMAIL, user.getEmail());
        values.put(PASSWORD, user.getPassword());

        // updating row
        db.update(USERS, values, USERID + " = ?",
                new String[]{String.valueOf(user.getId())});
        db.close();
    }

    /**
     * This method is to delete user record
     *
     * @param user
     */
    public void deleteUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        // delete user record by id
        db.delete(USERS, USERID + " = ?",
                new String[]{String.valueOf(user.getId())});
        db.close();
    }

    /**
     * This method to check user exist or not
     *
     * @param email
     * @return true/false
     */
    public boolean checkUser(String email) {

        // array of columns to fetch
        String[] columns = {
                USERID
        };
        SQLiteDatabase db = this.getReadableDatabase();

        // selection criteria
        String selection = EMAIL + " = ?";

        // selection argument
        String[] selectionArgs = {email};

        // query user table with condition
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com';
         */
        Cursor cursor = db.query(USERS, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount > 0) {
            return true;
        }

        return false;
    }

    /**
     * This method to check user exist or not
     *
     * @param email
     * @param password
     * @return true/false
     */
    public boolean checkUser(String email, String password) {

        // array of columns to fetch
        String[] columns = {
                USERID
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = EMAIL + " = ?" + " AND " + PASSWORD + " = ?";

        // selection arguments
        String[] selectionArgs = {email, password};

        // query user table with conditions
        /*
          Here query function is used to fetch records from user table this function works like we use sql query.
          SQL query equivalent to this query function is
          SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com' AND user_password = 'qwerty';
         */
        Cursor cursor = db.query(USERS, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order

        int cursorCount = cursor.getCount();

        cursor.close();
        db.close();

        if (cursorCount > 0) {
            return true;
        }
        return false;

    }

    /**
     * This method to check username exist or not
     *
     * @param username
     * @return true/false
     */
    public boolean checkUsername(String username) {

        // array of columns to fetch
        String[] columns = {
                USERID
        };
        SQLiteDatabase db = this.getReadableDatabase();

        // selection criteria
        String selection = USERNAME + " = ?";

        // selection argument
        String[] selectionArgs = {username};

        // query user table with condition
        Cursor cursor = db.query(USERS, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount > 0) {
            return true;
        }

        return false;
    }

    /**
     * This method is to create report record
     *
     * @param report
     */
    public void addReport(Report report) {
        sqLiteDatabase = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ITEM_NAME, report.getItemname());
        values.put(DETAILS, report.getDetails());
        values.put(LOCATION, report.getLocation());
        values.put(TYPE, report.getType());
        values.put(DATEREPORTED, report.getDatereported());
        values.put(BRAND, report.getBrand());
        values.put(CATEGORY, report.getCategory());
        values.put(CONTACTINFORMATION, report.getContactinformation());
        values.put(REMARKS, report.getRemarks());

        // Inserting row
        sqLiteDatabase.insert(REPORT, null, values);
        sqLiteDatabase.close();
    }

    /**
     * This method is to fetch all user and return the list of report records
     *
     * @return list
     */
    public List<Report> getAllReports() {
        // array of columns to fetch
        String[] columns = {
                REPORTID,
                ITEM_NAME,
                DETAILS,
                LOCATION,
                TYPE,
                DATEREPORTED,
                BRAND,
                CATEGORY,
                CONTACTINFORMATION,
                REMARKS
        };
        // sorting orders
        String sortOrder =
                ITEM_NAME + " ASC";
        List<Report> reportList = new ArrayList<Report>();

        sqLiteDatabase = this.getReadableDatabase();

        // query the user table
        /*
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id,user_name,user_email,user_password FROM user ORDER BY user_name;
         */
        Cursor cursor = sqLiteDatabase.query(REPORT, //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order


        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Report report = new Report();
                report.setReportid(cursor.getString(cursor.getColumnIndex(REPORTID)));
                report.setItemname(cursor.getString(cursor.getColumnIndex(ITEM_NAME)));
                report.setDetails(cursor.getString(cursor.getColumnIndex(DETAILS)));
                report.setLocation(cursor.getString(cursor.getColumnIndex(LOCATION)));
                report.setType(cursor.getString(cursor.getColumnIndex(TYPE)));
                report.setDatereported(cursor.getString(cursor.getColumnIndex(DATEREPORTED)));
                report.setBrand(cursor.getString(cursor.getColumnIndex(BRAND)));
                report.setCategory(cursor.getString(cursor.getColumnIndex(CATEGORY)));
                report.setContactinformation(cursor.getString(cursor.getColumnIndex(CONTACTINFORMATION)));
                report.setRemarks(cursor.getString(cursor.getColumnIndex(REMARKS)));
                // Adding user record to list
                reportList.add(report);
            } while (cursor.moveToNext());
        }
        cursor.close();
        sqLiteDatabase.close();
        Log.d(TAG, sqLiteDatabase.toString());

        // return user list
        return reportList;
    }

    public List<Report> getReportLost(){
        String lost = "lost";
        List<Report> reportList = new ArrayList<>();
        String query = "SELECT * FROM " + REPORT + " WHERE "+ TYPE
                + " = '" + lost + "'";

        sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                Report report = new Report();
                report.setReportid(cursor.getString(cursor.getColumnIndex(REPORTID)));
                report.setItemname(cursor.getString(cursor.getColumnIndex(ITEM_NAME)));
                report.setDetails(cursor.getString(cursor.getColumnIndex(DETAILS)));
                report.setLocation(cursor.getString(cursor.getColumnIndex(LOCATION)));
                report.setType(cursor.getString(cursor.getColumnIndex(TYPE)));
                report.setDatereported(cursor.getString(cursor.getColumnIndex(DATEREPORTED)));
                report.setBrand(cursor.getString(cursor.getColumnIndex(BRAND)));
                report.setCategory(cursor.getString(cursor.getColumnIndex(CATEGORY)));
                report.setContactinformation(cursor.getString(cursor.getColumnIndex(CONTACTINFORMATION)));
                report.setRemarks(cursor.getString(cursor.getColumnIndex(REMARKS)));;
                reportList.add(report);
            } while (cursor.moveToNext());
        }
        cursor.close();
        sqLiteDatabase.close();
        return reportList;
    }

    public List<Report> getReportFound(){
        String found = "found";
        List<Report> reportList = new ArrayList<>();
        String query = "SELECT * FROM " + REPORT + " WHERE "+ TYPE
                + " = '" + found + "'";

        sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                Report report = new Report();
                report.setReportid(cursor.getString(cursor.getColumnIndex(REPORTID)));
                report.setItemname(cursor.getString(cursor.getColumnIndex(ITEM_NAME)));
                report.setDetails(cursor.getString(cursor.getColumnIndex(DETAILS)));
                report.setLocation(cursor.getString(cursor.getColumnIndex(LOCATION)));
                report.setType(cursor.getString(cursor.getColumnIndex(TYPE)));
                report.setDatereported(cursor.getString(cursor.getColumnIndex(DATEREPORTED)));
                report.setBrand(cursor.getString(cursor.getColumnIndex(BRAND)));
                report.setCategory(cursor.getString(cursor.getColumnIndex(CATEGORY)));
                report.setContactinformation(cursor.getString(cursor.getColumnIndex(CONTACTINFORMATION)));
                report.setRemarks(cursor.getString(cursor.getColumnIndex(REMARKS)));;
                reportList.add(report);
            } while (cursor.moveToNext());
        }
        cursor.close();
        sqLiteDatabase.close();
        return reportList;
    }

    public Report getReportDetails(String id) {
        sqLiteDatabase = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + REPORT + " WHERE "
                + REPORTID + " = '" + id + "'";
        Cursor cursor = sqLiteDatabase.rawQuery(selectQuery, null);

        if (cursor != null)
            cursor.moveToFirst();

        Report rep = new Report();
        rep.setReportid(cursor.getString(cursor.getColumnIndex(REPORTID)));
        rep.setItemname(cursor.getString(cursor.getColumnIndex(ITEM_NAME)));
        rep.setDetails(cursor.getString(cursor.getColumnIndex(DETAILS)));
        rep.setLocation(cursor.getString(cursor.getColumnIndex(LOCATION)));
        rep.setType(cursor.getString(cursor.getColumnIndex(TYPE)));
        rep.setDatereported(cursor.getString(cursor.getColumnIndex(DATEREPORTED)));
        rep.setBrand(cursor.getString(cursor.getColumnIndex(BRAND)));
        rep.setCategory(cursor.getString(cursor.getColumnIndex(CATEGORY)));
        rep.setContactinformation(cursor.getString(cursor.getColumnIndex(CONTACTINFORMATION)));
        rep.setRemarks(cursor.getString(cursor.getColumnIndex(REMARKS)));
        cursor.close();
        return rep;
    }
}