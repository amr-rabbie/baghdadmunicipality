package com.unicomg.baghdadmunicipality.data.LocalSqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.unicomg.baghdadmunicipality.data.LocalSqlite.allviolences.AllViolenceContract;
import com.unicomg.baghdadmunicipality.data.LocalSqlite.billBoard.BillBoardContract;
import com.unicomg.baghdadmunicipality.data.LocalSqlite.categories.CategoriesContract;
import com.unicomg.baghdadmunicipality.data.LocalSqlite.scheduled_works.ScheduledWorkContract;
import com.unicomg.baghdadmunicipality.data.LocalSqlite.shops.ServerShopsContract;
import com.unicomg.baghdadmunicipality.data.LocalSqlite.shops.ShopsContract;
import com.unicomg.baghdadmunicipality.data.LocalSqlite.shopsactivities.ShopsActivitiesContract;
import com.unicomg.baghdadmunicipality.data.LocalSqlite.violation.ViolationContract;
import com.unicomg.baghdadmunicipality.data.LocalSqlite.violation.ViolationImageContract;
import com.unicomg.baghdadmunicipality.data.models.billboard.BillboardModel;
import com.unicomg.baghdadmunicipality.data.models.billboard.BillboardModel2;
import com.unicomg.baghdadmunicipality.data.models.category.Category;
import com.unicomg.baghdadmunicipality.data.models.scheduled_works.ScheduledWorkModel;
import com.unicomg.baghdadmunicipality.data.models.serverViolations.ServerViolation;
import com.unicomg.baghdadmunicipality.data.models.shops.ShopModel;
import com.unicomg.baghdadmunicipality.data.models.shops_activities.ShopsActivitiesDetailsResponse;
import com.unicomg.baghdadmunicipality.data.models.violation.ViolationImage;
import com.unicomg.baghdadmunicipality.data.models.violation.ViolationModel;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import static com.unicomg.baghdadmunicipality.data.LocalSqlite.allviolences.AllViolenceContract.ItemEntry.All_Violences_TABLE_NAME;
import static com.unicomg.baghdadmunicipality.data.LocalSqlite.billBoard.BillBoardContract.ItemEntry.BILL_BOARD_TABLE_NAME;
import static com.unicomg.baghdadmunicipality.data.LocalSqlite.categories.CategoriesContract.ItemEntry.Categories_TABLE_NAME;
import static com.unicomg.baghdadmunicipality.data.LocalSqlite.shops.ServerShopsContract.ItemEntry.SERVER_SHOP_TABLE_NAME;
import static com.unicomg.baghdadmunicipality.data.LocalSqlite.shops.ShopsContract.ItemEntry.SHOP_TABLE_NAME;
import static com.unicomg.baghdadmunicipality.data.LocalSqlite.shops.ShopsContract.ItemEntry.owner_name;
import static com.unicomg.baghdadmunicipality.data.LocalSqlite.shopsactivities.ShopsActivitiesContract.ItemEntry.Shop_Activities_TABLE_NAME;
import static com.unicomg.baghdadmunicipality.data.LocalSqlite.violation.ViolationContract.ItemEntry.VIOLATION_TABLE_TABLE;
import static com.unicomg.baghdadmunicipality.data.LocalSqlite.violation.ViolationImageContract.ItemEntry.VIOLATION_IMAGE_TABLE_TABLE;

public class ItemDbHelper  extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "SurveyDB.db";
    private static final int VERSION = 1;
    public ItemDbHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        final String CREATE_BILL_BOARD_TABLE = "CREATE TABLE " + BILL_BOARD_TABLE_NAME + " (" +
                BillBoardContract.ItemEntry._ID + " INTEGER PRIMARY KEY, " +
                BillBoardContract.ItemEntry.bill_id + " TEXT , " +
                BillBoardContract.ItemEntry.USER_ID + " TEXT , " +
                BillBoardContract.ItemEntry.OWNER_NAME + " TEXT , " +
                BillBoardContract.ItemEntry.BILLBOARD_NAME + " TEXT , " +
                BillBoardContract.ItemEntry.BILLBOARD_TYPE + " TEXT , " +
                BillBoardContract.ItemEntry.WIDTH + " TEXT , " +
                BillBoardContract.ItemEntry.HEIGHT + " TEXT , " +
                BillBoardContract.ItemEntry.LENGTH + " TEXT , " +
                BillBoardContract.ItemEntry.FONT_LANGUAGE + " TEXT , " +
                BillBoardContract.ItemEntry.AREA + " TEXT , " +
                BillBoardContract.ItemEntry.LOCALITY + " TEXT , " +
                BillBoardContract.ItemEntry.AILLEY + " TEXT , " +
                BillBoardContract.ItemEntry.STREET + " TEXT , " +
                BillBoardContract.ItemEntry.BULDING_NUMBER + " TEXT , " +
                BillBoardContract.ItemEntry.BILLBOARD_LICENSE + " TEXT , " +
                BillBoardContract.ItemEntry.BILLBOARD_LICENSE_NUMBER + " TEXT , " +
                BillBoardContract.ItemEntry.LICENSE_DATE + " TEXT , " +
                BillBoardContract.ItemEntry.longitude + " TEXT , " +
                BillBoardContract.ItemEntry.latitude + " TEXT , " +
                BillBoardContract.ItemEntry.send + " TEXT , " +

                BillBoardContract.ItemEntry.LICENSE_END_DATE + " TEXT );";

        db.execSQL(CREATE_BILL_BOARD_TABLE);



        final String CREATE_ShopData_TABLE = "CREATE TABLE " + SHOP_TABLE_NAME + " (" +
                ShopsContract.ItemEntry.SHOP_ID + " TEXT PRIMARY KEY, " +
                ShopsContract.ItemEntry.owner_name + " TEXT , " +
                ShopsContract.ItemEntry.USER_ID + " TEXT , " +
                ShopsContract.ItemEntry.type + " TEXT , " +
                ShopsContract.ItemEntry.shop_activity_id + " TEXT , " +
                ShopsContract.ItemEntry.width + " TEXT , " +
                ShopsContract.ItemEntry.length + " TEXT , " +
                ShopsContract.ItemEntry.employee_number + " TEXT , " +
                ShopsContract.ItemEntry.floor_number + " TEXT , " +
                ShopsContract.ItemEntry.area + " TEXT , " +
                ShopsContract.ItemEntry.street + " TEXT , " +
                ShopsContract.ItemEntry.locality + " TEXT , " +
                ShopsContract.ItemEntry.alley + " TEXT , " +
                ShopsContract.ItemEntry.locality_number + " TEXT , " +
                ShopsContract.ItemEntry.license_number + " TEXT , " +
                ShopsContract.ItemEntry.license_date + " TEXT , " +
                ShopsContract.ItemEntry.license_end_date + " TEXT , " +
                ShopsContract.ItemEntry.license_type + " TEXT , " +
                ShopsContract.ItemEntry.billboard_name + " TEXT , " +
                ShopsContract.ItemEntry.billboard_type + " TEXT , " +
                ShopsContract.ItemEntry.billboard_width + " TEXT , " +
                ShopsContract.ItemEntry.billboard_length + " TEXT , " +
                ShopsContract.ItemEntry.billboard_height + " TEXT , " +
                ShopsContract.ItemEntry.shop_notes + " TEXT , " +
                ShopsContract.ItemEntry.SENDE + " TEXT , " +

                ShopsContract.ItemEntry.longitude + " TEXT , " +
                ShopsContract.ItemEntry.latitude + " TEXT , " +

                ShopsContract.ItemEntry.billboard_font_type + " TEXT );";
        db.execSQL(CREATE_ShopData_TABLE);


        final String CREATE_SERVER_SHOPDATA_TABLE = "CREATE TABLE " + SERVER_SHOP_TABLE_NAME + " (" +
                ServerShopsContract.ItemEntry.SHOP_ID + " INTEGER PRIMARY KEY, " +
                ServerShopsContract.ItemEntry.owner_name + " TEXT , " +
                ServerShopsContract.ItemEntry.type + " TEXT , " +
                ServerShopsContract.ItemEntry.shop_activity_id + " TEXT , " +
                ServerShopsContract.ItemEntry.width + " TEXT , " +
                ServerShopsContract.ItemEntry.length + " TEXT , " +
                ServerShopsContract.ItemEntry.employee_number + " TEXT , " +
                ServerShopsContract.ItemEntry.floor_number + " TEXT , " +
                ServerShopsContract.ItemEntry.area + " TEXT , " +
                ServerShopsContract.ItemEntry.street + " TEXT , " +
                ServerShopsContract.ItemEntry.locality + " TEXT , " +
                ServerShopsContract.ItemEntry.alley + " TEXT , " +
                ServerShopsContract.ItemEntry.locality_number + " TEXT , " +
                ServerShopsContract.ItemEntry.license_number + " TEXT , " +
                ServerShopsContract.ItemEntry.license_date + " TEXT , " +
                ServerShopsContract.ItemEntry.license_end_date + " TEXT , " +
                ServerShopsContract.ItemEntry.license_type + " TEXT , " +
                ServerShopsContract.ItemEntry.billboard_name + " TEXT , " +
                ServerShopsContract.ItemEntry.billboard_type + " TEXT , " +
                ServerShopsContract.ItemEntry.billboard_width + " TEXT , " +
                ServerShopsContract.ItemEntry.billboard_length + " TEXT , " +
                ServerShopsContract.ItemEntry.billboard_height + " TEXT , " +
                ServerShopsContract.ItemEntry.shop_notes + " TEXT , " +
                ServerShopsContract.ItemEntry.longitude + " TEXT , " +
                ServerShopsContract.ItemEntry.latitude + " TEXT , " +

                ShopsContract.ItemEntry.billboard_font_type + " TEXT );";
        db.execSQL(CREATE_SERVER_SHOPDATA_TABLE);



        final String CREATE_Shop_Activities_TABLE = "CREATE TABLE " + Shop_Activities_TABLE_NAME + " (" +
                ShopsActivitiesContract.ItemEntry._ID + " INTEGER PRIMARY KEY, " +
                ShopsActivitiesContract.ItemEntry.id + " TEXT , " +
                ShopsActivitiesContract.ItemEntry.name + " TEXT , " +
                ShopsActivitiesContract.ItemEntry.code + " TEXT );";
        db.execSQL(CREATE_Shop_Activities_TABLE);


        final String CREATE_Categories_TABLE = "CREATE TABLE " + Categories_TABLE_NAME + " (" +
                CategoriesContract.ItemEntry._ID + " INTEGER PRIMARY KEY, " +
                CategoriesContract.ItemEntry.id + " TEXT , " +
                CategoriesContract.ItemEntry.name + " TEXT , " +
                CategoriesContract.ItemEntry.code + " TEXT );";
        db.execSQL(CREATE_Categories_TABLE);


        final String CREATE_SERVER_VIOLATION_TABLE = "CREATE TABLE " + All_Violences_TABLE_NAME + " (" +
                AllViolenceContract.ItemEntry._ID + " INTEGER PRIMARY KEY, " +
                AllViolenceContract.ItemEntry.id + " TEXT , " +
                AllViolenceContract.ItemEntry.name + " TEXT , " +
                AllViolenceContract.ItemEntry.type + " TEXT , " +
                AllViolenceContract.ItemEntry.financial_value + " TEXT , " +
                AllViolenceContract.ItemEntry.category_id + " TEXT );";
        db.execSQL(CREATE_SERVER_VIOLATION_TABLE);


        final String CREATE_Violation_TABLE = "CREATE TABLE " + VIOLATION_TABLE_TABLE + " (" +
                ViolationContract.ItemEntry.ID2 + " TEXT PRIMARY KEY, " +
                ViolationContract.ItemEntry.USER_ID + " TEXT , " +
                ViolationContract.ItemEntry.SHOP_ID + " TEXT , " +
                ViolationContract.ItemEntry.SHOP_NAME + " TEXT , " +
                ViolationContract.ItemEntry.CATEGORY_ID + " TEXT , " +
                ViolationContract.ItemEntry.VIOLATION_ID + " TEXT , " +
                ViolationContract.ItemEntry.ACTION_ID + " TEXT , " +
                ViolationContract.ItemEntry.NOTE + " TEXT );";
        db.execSQL(CREATE_Violation_TABLE);


        final String CREATE_VIOLATION_IMAGE_TABLE = "CREATE TABLE " + VIOLATION_IMAGE_TABLE_TABLE + " (" +
                ViolationImageContract.ItemEntry._ID + " INTEGER PRIMARY KEY, " +
                ViolationImageContract.ItemEntry.VIOLATION_ID + " TEXT , " +
                ViolationImageContract.ItemEntry.IMAGE_SOURCE + " BLOB , " +
                ViolationImageContract.ItemEntry.IMAGE_DESCREPTION + " TEXT );";

        db.execSQL(CREATE_VIOLATION_IMAGE_TABLE);


        final String CREATE_SCHEDULED_WORK_BOARD_TABLE = "CREATE TABLE " + ScheduledWorkContract.ItemEntry.SCHEDULED_TABLE_NAME + " (" +
                ScheduledWorkContract.ItemEntry._ID + " INTEGER PRIMARY KEY, " +
                ScheduledWorkContract.ItemEntry.USER_ID + " TEXT , " +
                ScheduledWorkContract.ItemEntry.sheduled_work_id + " TEXT , " +
                ScheduledWorkContract.ItemEntry.shop_data_id + " TEXT , " +
                ScheduledWorkContract.ItemEntry.visit_date + " TEXT , " +
                ScheduledWorkContract.ItemEntry.billboard_name + " TEXT , " +
                ScheduledWorkContract.ItemEntry.shop_address + " TEXT );";

        db.execSQL(CREATE_SCHEDULED_WORK_BOARD_TABLE);



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL("DROP TABLE IF EXISTS " + GOVERNOR_TABLE_NAME);
//        onCreate(db);
    }


    public long addBillBoard(BillboardModel billboard , String user_id) {
        long rowInserted = 0 ;
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();

        try {
            ContentValues values = new ContentValues();
            values.put(BillBoardContract.ItemEntry.bill_id, billboard.getId());
            values.put(BillBoardContract.ItemEntry.USER_ID, user_id);
            values.put(BillBoardContract.ItemEntry.OWNER_NAME, billboard.getOwner_name());
            values.put(BillBoardContract.ItemEntry.BILLBOARD_NAME, billboard.getBillboard_name());
            values.put(BillBoardContract.ItemEntry.BILLBOARD_TYPE, billboard.getBillboard_type());
            values.put(BillBoardContract.ItemEntry.WIDTH, billboard.getWidth());
            values.put(BillBoardContract.ItemEntry.HEIGHT, billboard.getHeight());
            values.put(BillBoardContract.ItemEntry.LENGTH, billboard.getLength());
            values.put(BillBoardContract.ItemEntry.FONT_LANGUAGE, billboard.getFont_language());
            values.put(BillBoardContract.ItemEntry.AREA, billboard.getArea());
            values.put(BillBoardContract.ItemEntry.LOCALITY, billboard.getLocality());
            values.put(BillBoardContract.ItemEntry.AILLEY, billboard.getAilley());
            values.put(BillBoardContract.ItemEntry.STREET, billboard.getStreet());
            values.put(BillBoardContract.ItemEntry.BULDING_NUMBER, billboard.getBulding_number());
            values.put(BillBoardContract.ItemEntry.BILLBOARD_LICENSE, billboard.getBillboard_license());
            values.put(BillBoardContract.ItemEntry.BILLBOARD_LICENSE_NUMBER, billboard.getBillboard_license_number());
            values.put(BillBoardContract.ItemEntry.LICENSE_DATE, billboard.getLicense_date());
            values.put(BillBoardContract.ItemEntry.LICENSE_END_DATE, billboard.getLicense_end_date());
            values.put(BillBoardContract.ItemEntry.longitude, billboard.getLongitude());
            values.put(BillBoardContract.ItemEntry.latitude, billboard.getLatitude());
            values.put(BillBoardContract.ItemEntry.send, billboard.getSend());

         rowInserted =    db.insert(BILL_BOARD_TABLE_NAME, null, values);

            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }

        return rowInserted ;
    }
    public long updateBillBoard(BillboardModel billboard,String id) {
        long rowInserted = 0 ;
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();

        try {
            ContentValues values = new ContentValues();
            values.put(BillBoardContract.ItemEntry.OWNER_NAME, billboard.getOwner_name());
            values.put(BillBoardContract.ItemEntry.BILLBOARD_NAME, billboard.getBillboard_name());
            values.put(BillBoardContract.ItemEntry.BILLBOARD_TYPE, billboard.getBillboard_type());
            values.put(BillBoardContract.ItemEntry.WIDTH, billboard.getWidth());
            values.put(BillBoardContract.ItemEntry.HEIGHT, billboard.getHeight());
            values.put(BillBoardContract.ItemEntry.LENGTH, billboard.getLength());
            values.put(BillBoardContract.ItemEntry.FONT_LANGUAGE, billboard.getFont_language());
            values.put(BillBoardContract.ItemEntry.AREA, billboard.getArea());
            values.put(BillBoardContract.ItemEntry.LOCALITY, billboard.getLocality());
            values.put(BillBoardContract.ItemEntry.AILLEY, billboard.getAilley());
            values.put(BillBoardContract.ItemEntry.STREET, billboard.getStreet());
            values.put(BillBoardContract.ItemEntry.BULDING_NUMBER, billboard.getBulding_number());
            values.put(BillBoardContract.ItemEntry.BILLBOARD_LICENSE, billboard.getBillboard_license());
            values.put(BillBoardContract.ItemEntry.BILLBOARD_LICENSE_NUMBER, billboard.getBillboard_license_number());
            values.put(BillBoardContract.ItemEntry.LICENSE_DATE, billboard.getLicense_date());
            values.put(BillBoardContract.ItemEntry.LICENSE_END_DATE, billboard.getLicense_end_date());
            values.put(BillBoardContract.ItemEntry.longitude, billboard.getLongitude());
            values.put(BillBoardContract.ItemEntry.latitude, billboard.getLatitude());
            values.put(BillBoardContract.ItemEntry.send, billboard.getSend());

            rowInserted =    db.update(BILL_BOARD_TABLE_NAME, values, BillBoardContract.ItemEntry._ID + " = " + id , null) ;


            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }

        return rowInserted ;
    }
    public ArrayList<BillboardModel2> getBillboards( String user_id) {
        ArrayList<BillboardModel2> billboards = new ArrayList<>();
        final SQLiteDatabase db = this.getReadableDatabase();

        Cursor retCursor;
        retCursor = db.query(BILL_BOARD_TABLE_NAME,
                null,
                BillBoardContract.ItemEntry.USER_ID + "=" + user_id,
                null,
                null,
                null,
                null);


        if (retCursor != null) {
            while (retCursor.moveToNext()) {
                BillboardModel2 billboard = new BillboardModel2();
                billboard.setId(retCursor.getString(retCursor.getColumnIndex(BillBoardContract.ItemEntry._ID)));
                billboard.setBillId(retCursor.getString(retCursor.getColumnIndex(BillBoardContract.ItemEntry.bill_id)));
                billboard.setOwner_name(retCursor.getString(retCursor.getColumnIndex(BillBoardContract.ItemEntry.OWNER_NAME)));
                billboard.setBillboard_name(retCursor.getString(retCursor.getColumnIndex(BillBoardContract.ItemEntry.BILLBOARD_NAME)));
                billboard.setBillboard_type(retCursor.getString(retCursor.getColumnIndex(BillBoardContract.ItemEntry.BILLBOARD_TYPE)));
                billboard.setWidth(retCursor.getString(retCursor.getColumnIndex(BillBoardContract.ItemEntry.WIDTH)));
                billboard.setHeight(retCursor.getString(retCursor.getColumnIndex(BillBoardContract.ItemEntry.HEIGHT)));
                billboard.setLength(retCursor.getString(retCursor.getColumnIndex(BillBoardContract.ItemEntry.LENGTH)));
                billboard.setFont_language(retCursor.getString(retCursor.getColumnIndex(BillBoardContract.ItemEntry.FONT_LANGUAGE)));
                billboard.setArea(retCursor.getString(retCursor.getColumnIndex(BillBoardContract.ItemEntry.AREA)));
                billboard.setLocality(retCursor.getString(retCursor.getColumnIndex(BillBoardContract.ItemEntry.LOCALITY)));
                billboard.setAilley(retCursor.getString(retCursor.getColumnIndex(BillBoardContract.ItemEntry.AILLEY)));
                billboard.setStreet(retCursor.getString(retCursor.getColumnIndex(BillBoardContract.ItemEntry.STREET)));
                billboard.setBulding_number(retCursor.getString(retCursor.getColumnIndex(BillBoardContract.ItemEntry.BULDING_NUMBER)));
                billboard.setBillboard_license(retCursor.getString(retCursor.getColumnIndex(BillBoardContract.ItemEntry.BILLBOARD_LICENSE)));
                billboard.setBillboard_license_number(retCursor.getString(retCursor.getColumnIndex(BillBoardContract.ItemEntry.BILLBOARD_LICENSE_NUMBER)));
                billboard.setLicense_date(retCursor.getString(retCursor.getColumnIndex(BillBoardContract.ItemEntry.LICENSE_DATE)));
                billboard.setLicense_end_date(retCursor.getString(retCursor.getColumnIndex(BillBoardContract.ItemEntry.LICENSE_END_DATE)));
                billboard.setLongitude(retCursor.getString(retCursor.getColumnIndex(BillBoardContract.ItemEntry.longitude)));
                billboard.setLatitude(retCursor.getString(retCursor.getColumnIndex(BillBoardContract.ItemEntry.latitude)));
                billboard.setSend(retCursor.getString(retCursor.getColumnIndex(BillBoardContract.ItemEntry.send)));

                billboards.add(billboard);
            }
        }

        return billboards;
    }
    public ArrayList<BillboardModel2> getBillboardsById(String id ) {
        ArrayList<BillboardModel2> billboards = new ArrayList<>();
        final SQLiteDatabase db = this.getReadableDatabase();

        Cursor retCursor;
        retCursor = db.query(BILL_BOARD_TABLE_NAME,
                null,
                BillBoardContract.ItemEntry._ID + "=" + id,
                null,
                null,
                null,
                null);


        if (retCursor != null) {
            while (retCursor.moveToNext()) {
                BillboardModel2 billboard = new BillboardModel2();
                billboard.setId(retCursor.getString(retCursor.getColumnIndex(BillBoardContract.ItemEntry._ID)));
                billboard.setBillId(retCursor.getString(retCursor.getColumnIndex(BillBoardContract.ItemEntry.bill_id)));
                billboard.setOwner_name(retCursor.getString(retCursor.getColumnIndex(BillBoardContract.ItemEntry.OWNER_NAME)));
                billboard.setBillboard_name(retCursor.getString(retCursor.getColumnIndex(BillBoardContract.ItemEntry.BILLBOARD_NAME)));
                billboard.setBillboard_type(retCursor.getString(retCursor.getColumnIndex(BillBoardContract.ItemEntry.BILLBOARD_TYPE)));
                billboard.setWidth(retCursor.getString(retCursor.getColumnIndex(BillBoardContract.ItemEntry.WIDTH)));
                billboard.setHeight(retCursor.getString(retCursor.getColumnIndex(BillBoardContract.ItemEntry.HEIGHT)));
                billboard.setLength(retCursor.getString(retCursor.getColumnIndex(BillBoardContract.ItemEntry.LENGTH)));
                billboard.setFont_language(retCursor.getString(retCursor.getColumnIndex(BillBoardContract.ItemEntry.FONT_LANGUAGE)));
                billboard.setArea(retCursor.getString(retCursor.getColumnIndex(BillBoardContract.ItemEntry.AREA)));
                billboard.setLocality(retCursor.getString(retCursor.getColumnIndex(BillBoardContract.ItemEntry.LOCALITY)));
                billboard.setAilley(retCursor.getString(retCursor.getColumnIndex(BillBoardContract.ItemEntry.AILLEY)));
                billboard.setStreet(retCursor.getString(retCursor.getColumnIndex(BillBoardContract.ItemEntry.STREET)));
                billboard.setBulding_number(retCursor.getString(retCursor.getColumnIndex(BillBoardContract.ItemEntry.BULDING_NUMBER)));
                billboard.setBillboard_license(retCursor.getString(retCursor.getColumnIndex(BillBoardContract.ItemEntry.BILLBOARD_LICENSE)));
                billboard.setBillboard_license_number(retCursor.getString(retCursor.getColumnIndex(BillBoardContract.ItemEntry.BILLBOARD_LICENSE_NUMBER)));
                billboard.setLicense_date(retCursor.getString(retCursor.getColumnIndex(BillBoardContract.ItemEntry.LICENSE_DATE)));
                billboard.setLicense_end_date(retCursor.getString(retCursor.getColumnIndex(BillBoardContract.ItemEntry.LICENSE_END_DATE)));
                billboard.setLongitude(retCursor.getString(retCursor.getColumnIndex(BillBoardContract.ItemEntry.longitude)));
                billboard.setLatitude(retCursor.getString(retCursor.getColumnIndex(BillBoardContract.ItemEntry.latitude)));
                billboard.setSend(retCursor.getString(retCursor.getColumnIndex(BillBoardContract.ItemEntry.send)));

                billboards.add(billboard);
            }
        }

        return billboards;
    }
    public boolean deleteBillboard(String billboardId) {
        final SQLiteDatabase db = this.getReadableDatabase();
        return db.delete(BILL_BOARD_TABLE_NAME, BillBoardContract.ItemEntry._ID + "=" + billboardId, null) > 0; }
    public boolean updateBillboard(BillboardModel billboardModel , String billId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues args = new ContentValues();
        args.put(BillBoardContract.ItemEntry.send, "1");
        return db.update(BILL_BOARD_TABLE_NAME, args, BillBoardContract.ItemEntry._ID + " = " + billId, null) > 0;
    }

    public void addShopsActivities(ShopsActivitiesDetailsResponse shopactivity) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();

        try {
            ContentValues values = new ContentValues();
            values.put(ShopsActivitiesContract.ItemEntry.id, shopactivity.getId());
            values.put(ShopsActivitiesContract.ItemEntry.name, shopactivity.getName());
            values.put(ShopsActivitiesContract.ItemEntry.code, shopactivity.getCode());
            db.insert(Shop_Activities_TABLE_NAME, null, values);
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }
    public ArrayList<ShopsActivitiesDetailsResponse> getShopsActivities( ) {
        String columns []={ShopsActivitiesContract.ItemEntry.id,ShopsActivitiesContract.ItemEntry.name ,ShopsActivitiesContract.ItemEntry.code};
        ArrayList<ShopsActivitiesDetailsResponse> shopsactivities = new ArrayList<>();
        final SQLiteDatabase db = this.getReadableDatabase();

        Cursor retCursor;
        retCursor = db.query(Shop_Activities_TABLE_NAME,
                columns,
                null ,
                null,
                null,
                null,
                null);


        if (retCursor != null) {
            while (retCursor.moveToNext()) {
                ShopsActivitiesDetailsResponse shopsactivitie = new ShopsActivitiesDetailsResponse();
                // governor.setId(retCursor.getInt(retCursor.getColumnIndex(ItemContract.ItemEntry._ID)));
                //shop.setOwner_name(retCursor.getString(retCursor.getColumnIndex(owner_name)));
                shopsactivitie.setId(retCursor.getString(retCursor.getColumnIndex(ShopsActivitiesContract.ItemEntry.id)));
                shopsactivitie.setName(retCursor.getString(retCursor.getColumnIndex(ShopsActivitiesContract.ItemEntry.name)));
                shopsactivitie.setCode(retCursor.getString(retCursor.getColumnIndex(ShopsActivitiesContract.ItemEntry.code)));

                shopsactivities.add(shopsactivitie);
            }
        }

        return shopsactivities;
    }
    public List<ShopsActivitiesDetailsResponse> getShActivities() {
        List<ShopsActivitiesDetailsResponse> activities = new ArrayList<>();
        final SQLiteDatabase db = this.getReadableDatabase();
        Cursor retCursor;
        retCursor = db.query(Shop_Activities_TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null);


        if (retCursor != null) {
            while (retCursor.moveToNext()) {
                ShopsActivitiesDetailsResponse activity = new ShopsActivitiesDetailsResponse();
                activity.setCode(retCursor.getString(retCursor.getColumnIndex(ShopsActivitiesContract.ItemEntry.code)));
                activity.setId(retCursor.getString(retCursor.getColumnIndex(ShopsActivitiesContract.ItemEntry.id)));
                activity.setName(retCursor.getString(retCursor.getColumnIndex(ShopsActivitiesContract.ItemEntry.name)));
                activities.add(activity);
            }
        }

        return activities;
    }
    public boolean deleteShopsActivities() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(Shop_Activities_TABLE_NAME, null , null) > 0;
    }



    public void addCategories(Category category) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();

        try {
            ContentValues values = new ContentValues();
            // values.put(ViolationContract.ItemEntry.ID2, violation.getId());

            values.put(CategoriesContract.ItemEntry.id, category.getId());
            values.put(CategoriesContract.ItemEntry.name, category.getName());
            values.put(CategoriesContract.ItemEntry.code, category.getCode());


            db.insert(Categories_TABLE_NAME, null, values);

            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }
    public ArrayList<Category> getCategories( ) {
        ArrayList<Category> categories = new ArrayList<>();
        final SQLiteDatabase db = this.getReadableDatabase();

        Cursor retCursor;
        retCursor = db.query(Categories_TABLE_NAME,
                null,
                null ,
                null,
                null,
                null,
                null);


        if (retCursor != null) {
            while (retCursor.moveToNext()) {
                Category category = new Category();
                category.setId(retCursor.getString(retCursor.getColumnIndex(CategoriesContract.ItemEntry.id)));
                category.setName(retCursor.getString(retCursor.getColumnIndex(CategoriesContract.ItemEntry.name)));
                category.setCode(retCursor.getString(retCursor.getColumnIndex(CategoriesContract.ItemEntry.code)));

                categories.add(category);
            }
        }

        return categories;
    }
    public boolean deleteCategories() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(Categories_TABLE_NAME, null , null) > 0;
    }



    public void addServerViolations(ServerViolation violation) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();

        try {
            ContentValues values = new ContentValues();

            values.put(AllViolenceContract.ItemEntry.id, violation.getId());
            values.put(AllViolenceContract.ItemEntry.name, violation.getName());
            values.put(AllViolenceContract.ItemEntry.type, violation.getType());
            values.put(AllViolenceContract.ItemEntry.financial_value, violation.getFinancial_value());
            values.put(AllViolenceContract.ItemEntry.category_id, violation.getCategory_id());
            db.insert(All_Violences_TABLE_NAME, null, values);
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }
    public ArrayList<ServerViolation> getAllServerViolations() {
        ArrayList<ServerViolation> serverViolations = new ArrayList<>();
        final SQLiteDatabase db = this.getReadableDatabase();

        Cursor retCursor;
        retCursor = db.query(All_Violences_TABLE_NAME,
                null,
                null ,
                null,
                null,
                null,
                null);


        if (retCursor != null) {
            while (retCursor.moveToNext()) {
                ServerViolation serverViolation = new ServerViolation();
                serverViolation.setId(retCursor.getString(retCursor.getColumnIndex(AllViolenceContract.ItemEntry.id)));
                serverViolation.setName(retCursor.getString(retCursor.getColumnIndex(AllViolenceContract.ItemEntry.name)));
                serverViolation.setType(retCursor.getString(retCursor.getColumnIndex(AllViolenceContract.ItemEntry.type)));
                serverViolation.setCategory_id(retCursor.getString(retCursor.getColumnIndex(AllViolenceContract.ItemEntry.category_id)));
                serverViolation.setFinancial_value(retCursor.getString(retCursor.getColumnIndex(AllViolenceContract.ItemEntry.financial_value)));
                serverViolations.add(serverViolation);
            }
        }

        return serverViolations;
    }
    public ArrayList<ServerViolation> getAllServerViolationsByCatId(String catId) {
        ArrayList<ServerViolation> serverViolations = new ArrayList<>();
        final SQLiteDatabase db = this.getReadableDatabase();

        Cursor retCursor;
        retCursor = db.query(All_Violences_TABLE_NAME,
                null,
                ViolationContract.ItemEntry.CATEGORY_ID + " = " + catId,
                null,
                null,
                null,
                null);


        if (retCursor != null) {
            while (retCursor.moveToNext()) {
                ServerViolation serverViolation = new ServerViolation();
                serverViolation.setId(retCursor.getString(retCursor.getColumnIndex(AllViolenceContract.ItemEntry.id)));
                serverViolation.setName(retCursor.getString(retCursor.getColumnIndex(AllViolenceContract.ItemEntry.name)));
                serverViolation.setType(retCursor.getString(retCursor.getColumnIndex(AllViolenceContract.ItemEntry.type)));
                serverViolation.setCategory_id(retCursor.getString(retCursor.getColumnIndex(AllViolenceContract.ItemEntry.category_id)));
                serverViolation.setFinancial_value(retCursor.getString(retCursor.getColumnIndex(AllViolenceContract.ItemEntry.financial_value)));
                serverViolations.add(serverViolation);
            }
        }

        return serverViolations;
    }
    public boolean deleteAllServerViolations() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(All_Violences_TABLE_NAME, null , null) > 0;
    }


    public Long add_Shop_Data(String user_id , String shop_id, String owner_name, String type, String shop_activity_id, String width, String length, String employee_number, String floor_number,
                              String area, String street, String alley, String locality, String locality_number, String license_number, String license_type,
                              String license_date, String license_end_date, String billboard_name, String billboard_type, String billboard_width,
                              String billboard_length, String billboard_height, String billboard_font_type,String longitude , String latitude , String shopNotes) {
        long rowInserted;
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        try {

            ContentValues values = new ContentValues();
            values.put(ShopsContract.ItemEntry.SHOP_ID, shop_id);
            values.put(ShopsContract.ItemEntry.owner_name, owner_name);
            values.put(ShopsContract.ItemEntry.USER_ID, user_id);
            values.put(ShopsContract.ItemEntry.type, type);
            values.put(ShopsContract.ItemEntry.shop_activity_id, shop_activity_id);
            values.put(ShopsContract.ItemEntry.width, width);
            values.put(ShopsContract.ItemEntry.length, length);
            values.put(ShopsContract.ItemEntry.employee_number, employee_number);
            values.put(ShopsContract.ItemEntry.floor_number, floor_number);
            values.put(ShopsContract.ItemEntry.area, area);
            values.put(ShopsContract.ItemEntry.street, street);
            values.put(ShopsContract.ItemEntry.alley, alley);
            values.put(ShopsContract.ItemEntry.locality, locality);
            values.put(ShopsContract.ItemEntry.locality_number, locality_number);
            values.put(ShopsContract.ItemEntry.license_number, license_number);
            values.put(ShopsContract.ItemEntry.license_type, license_type);
            values.put(ShopsContract.ItemEntry.license_date, license_date);
            values.put(ShopsContract.ItemEntry.license_end_date, license_end_date);
            values.put(ShopsContract.ItemEntry.billboard_name, billboard_name);
            values.put(ShopsContract.ItemEntry.billboard_type, billboard_type);
            values.put(ShopsContract.ItemEntry.billboard_width, billboard_width);
            values.put(ShopsContract.ItemEntry.billboard_length, billboard_length);
            values.put(ShopsContract.ItemEntry.billboard_height, billboard_height);
            values.put(ShopsContract.ItemEntry.billboard_font_type, billboard_font_type);
            values.put(ShopsContract.ItemEntry.shop_notes, shopNotes);
            values.put(ShopsContract.ItemEntry.SENDE, "0");

            values.put(ShopsContract.ItemEntry.longitude, longitude);
            values.put(ShopsContract.ItemEntry.latitude, latitude);


            rowInserted = db.insert(SHOP_TABLE_NAME, null, values);

            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
        return rowInserted;
    }
    public long updateShopData(String shop_id, String owner_name, String type, String shop_activity_id, String width, String length, String employee_number, String floor_number,
                                  String area, String street, String alley, String locality, String locality_number, String license_number, String license_type,
                                  String license_date, String license_end_date, String billboard_name, String billboard_type, String billboard_width,
                                  String billboard_length, String billboard_height, String billboard_font_type,String longitude , String latitude , String notes) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ShopsContract.ItemEntry.SHOP_ID, shop_id);
        values.put(ShopsContract.ItemEntry.owner_name, owner_name);
        values.put(ShopsContract.ItemEntry.type, type);
        values.put(ShopsContract.ItemEntry.shop_activity_id, shop_activity_id);
        values.put(ShopsContract.ItemEntry.width, width);
        values.put(ShopsContract.ItemEntry.length, length);
        values.put(ShopsContract.ItemEntry.employee_number, employee_number);
        values.put(ShopsContract.ItemEntry.floor_number, floor_number);
        values.put(ShopsContract.ItemEntry.area, area);
        values.put(ShopsContract.ItemEntry.street, street);
        values.put(ShopsContract.ItemEntry.alley, alley);
        values.put(ShopsContract.ItemEntry.locality, locality);
        values.put(ShopsContract.ItemEntry.locality_number, locality_number);
        values.put(ShopsContract.ItemEntry.license_number, license_number);
        values.put(ShopsContract.ItemEntry.license_type, license_type);
        values.put(ShopsContract.ItemEntry.license_date, license_date);
        values.put(ShopsContract.ItemEntry.license_end_date, license_end_date);
        values.put(ShopsContract.ItemEntry.billboard_name, billboard_name);
        values.put(ShopsContract.ItemEntry.billboard_type, billboard_type);
        values.put(ShopsContract.ItemEntry.billboard_width, billboard_width);
        values.put(ShopsContract.ItemEntry.billboard_length, billboard_length);
        values.put(ShopsContract.ItemEntry.billboard_height, billboard_height);
        values.put(ShopsContract.ItemEntry.billboard_font_type, billboard_font_type);
        values.put(ShopsContract.ItemEntry.SENDE, "0");

        values.put(ShopsContract.ItemEntry.longitude, longitude);
        values.put(ShopsContract.ItemEntry.latitude, latitude);

        values.put(ShopsContract.ItemEntry.shop_notes, notes);

        return db.update(SHOP_TABLE_NAME, values, ShopsContract.ItemEntry.SHOP_ID + " = " + shop_id , null) ;
    }
    public ArrayList<ShopModel> getShopData(String user_id) {
        ArrayList<ShopModel> shops = new ArrayList<>();
        final SQLiteDatabase db = this.getReadableDatabase();

        Cursor retCursor;
        retCursor = db.query(SHOP_TABLE_NAME,
                null,
                ShopsContract.ItemEntry.USER_ID + " = " + user_id,
                null,
                null,
                null,
                null);


        if (retCursor != null) {
            while (retCursor.moveToNext()) {
                ShopModel shop = new ShopModel();
                shop.setShop_id(retCursor.getString(retCursor.getColumnIndex(ShopsContract.ItemEntry.SHOP_ID)));
                shop.setOwner_name(retCursor.getString(retCursor.getColumnIndex(owner_name)));
                shop.setType(retCursor.getString(retCursor.getColumnIndex(ShopsContract.ItemEntry.type)));
                shop.setShop_activity_id(retCursor.getString(retCursor.getColumnIndex(ShopsContract.ItemEntry.shop_activity_id)));
                shop.setWidth(retCursor.getString(retCursor.getColumnIndex(ShopsContract.ItemEntry.width)));
                shop.setLength(retCursor.getString(retCursor.getColumnIndex(ShopsContract.ItemEntry.length)));
                shop.setEmployee_number(retCursor.getString(retCursor.getColumnIndex(ShopsContract.ItemEntry.employee_number)));
                shop.setFloor_number(retCursor.getString(retCursor.getColumnIndex(ShopsContract.ItemEntry.floor_number)));
                shop.setArea(retCursor.getString(retCursor.getColumnIndex(ShopsContract.ItemEntry.area)));
                shop.setStreet(retCursor.getString(retCursor.getColumnIndex(ShopsContract.ItemEntry.street)));
                shop.setLocality(retCursor.getString(retCursor.getColumnIndex(ShopsContract.ItemEntry.locality)));
                shop.setAlley(retCursor.getString(retCursor.getColumnIndex(ShopsContract.ItemEntry.alley)));
                shop.setLocality_number(retCursor.getString(retCursor.getColumnIndex(ShopsContract.ItemEntry.locality_number)));
                shop.setLicense_number(retCursor.getString(retCursor.getColumnIndex(ShopsContract.ItemEntry.license_number)));
                shop.setLicense_date(retCursor.getString(retCursor.getColumnIndex(ShopsContract.ItemEntry.license_date)));
                shop.setLicense_end_date(retCursor.getString(retCursor.getColumnIndex(ShopsContract.ItemEntry.license_end_date)));
                shop.setLicense_type(retCursor.getString(retCursor.getColumnIndex(ShopsContract.ItemEntry.license_type)));
                shop.setBillboard_name(retCursor.getString(retCursor.getColumnIndex(ShopsContract.ItemEntry.billboard_name)));
                shop.setBillboard_type(retCursor.getString(retCursor.getColumnIndex(ShopsContract.ItemEntry.billboard_type)));
                shop.setBillboard_width(retCursor.getString(retCursor.getColumnIndex(ShopsContract.ItemEntry.billboard_width)));
                shop.setBillboard_length(retCursor.getString(retCursor.getColumnIndex(ShopsContract.ItemEntry.billboard_length)));
                shop.setBillboard_height(retCursor.getString(retCursor.getColumnIndex(ShopsContract.ItemEntry.billboard_height)));
                shop.setBillboard_font_type(retCursor.getString(retCursor.getColumnIndex(ShopsContract.ItemEntry.billboard_font_type)));
                shop.setSend(retCursor.getString(retCursor.getColumnIndex(ShopsContract.ItemEntry.SENDE)));
                shop.setShop_notes(retCursor.getString(retCursor.getColumnIndex(ShopsContract.ItemEntry.shop_notes)));
                shop.setLongitude(retCursor.getString(retCursor.getColumnIndex(ShopsContract.ItemEntry.longitude)));
                shop.setLatitude(retCursor.getString(retCursor.getColumnIndex(ShopsContract.ItemEntry.latitude)));
                shops.add(shop);
            }
        }

        return shops;
    }
    public ArrayList<ShopModel> getShopDataById(String shopId) {
        ArrayList<ShopModel> shops = new ArrayList<>();
        final SQLiteDatabase db = this.getReadableDatabase();

        Cursor retCursor;
        retCursor = db.query(SHOP_TABLE_NAME,
                null,
                ShopsContract.ItemEntry.SHOP_ID + "=" + shopId,
                null,
                null,
                null,
                null);


        if (retCursor != null) {
            while (retCursor.moveToNext()) {
                ShopModel shop = new ShopModel();
                shop.setShop_id(retCursor.getString(retCursor.getColumnIndex(ShopsContract.ItemEntry.SHOP_ID)));
                shop.setOwner_name(retCursor.getString(retCursor.getColumnIndex(owner_name)));
                shop.setType(retCursor.getString(retCursor.getColumnIndex(ShopsContract.ItemEntry.type)));
                shop.setShop_activity_id(retCursor.getString(retCursor.getColumnIndex(ShopsContract.ItemEntry.shop_activity_id)));
                shop.setWidth(retCursor.getString(retCursor.getColumnIndex(ShopsContract.ItemEntry.width)));
                shop.setLength(retCursor.getString(retCursor.getColumnIndex(ShopsContract.ItemEntry.length)));
                shop.setEmployee_number(retCursor.getString(retCursor.getColumnIndex(ShopsContract.ItemEntry.employee_number)));
                shop.setFloor_number(retCursor.getString(retCursor.getColumnIndex(ShopsContract.ItemEntry.floor_number)));
                shop.setArea(retCursor.getString(retCursor.getColumnIndex(ShopsContract.ItemEntry.area)));
                shop.setStreet(retCursor.getString(retCursor.getColumnIndex(ShopsContract.ItemEntry.street)));
                shop.setLocality(retCursor.getString(retCursor.getColumnIndex(ShopsContract.ItemEntry.locality)));
                shop.setAlley(retCursor.getString(retCursor.getColumnIndex(ShopsContract.ItemEntry.alley)));
                shop.setLocality_number(retCursor.getString(retCursor.getColumnIndex(ShopsContract.ItemEntry.locality_number)));
                shop.setLicense_number(retCursor.getString(retCursor.getColumnIndex(ShopsContract.ItemEntry.license_number)));
                shop.setLicense_date(retCursor.getString(retCursor.getColumnIndex(ShopsContract.ItemEntry.license_date)));
                shop.setLicense_end_date(retCursor.getString(retCursor.getColumnIndex(ShopsContract.ItemEntry.license_end_date)));
                shop.setLicense_type(retCursor.getString(retCursor.getColumnIndex(ShopsContract.ItemEntry.license_type)));
                shop.setBillboard_name(retCursor.getString(retCursor.getColumnIndex(ShopsContract.ItemEntry.billboard_name)));
                shop.setBillboard_type(retCursor.getString(retCursor.getColumnIndex(ShopsContract.ItemEntry.billboard_type)));
                shop.setBillboard_width(retCursor.getString(retCursor.getColumnIndex(ShopsContract.ItemEntry.billboard_width)));
                shop.setBillboard_length(retCursor.getString(retCursor.getColumnIndex(ShopsContract.ItemEntry.billboard_length)));
                shop.setBillboard_height(retCursor.getString(retCursor.getColumnIndex(ShopsContract.ItemEntry.billboard_height)));
                shop.setBillboard_font_type(retCursor.getString(retCursor.getColumnIndex(ShopsContract.ItemEntry.billboard_font_type)));
                shop.setSend(retCursor.getString(retCursor.getColumnIndex(ShopsContract.ItemEntry.SENDE)));
                shop.setLongitude(retCursor.getString(retCursor.getColumnIndex(ShopsContract.ItemEntry.longitude)));
                shop.setLatitude(retCursor.getString(retCursor.getColumnIndex(ShopsContract.ItemEntry.latitude)));
                shop.setShop_notes(retCursor.getString(retCursor.getColumnIndex(ShopsContract.ItemEntry.shop_notes)));
                shops.add(shop);
            }
        }

        return shops;
    }
    public boolean deleteShop(String shopId) {

        final SQLiteDatabase db = this.getReadableDatabase();
        return   db.delete(SHOP_TABLE_NAME, ShopsContract.ItemEntry.SHOP_ID + "=" + shopId, null) > 0;

    }
    public boolean updateShopMakeItSensed(ShopModel shopModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues args = new ContentValues();
        args.put(ShopsContract.ItemEntry.SENDE, "1");
        return db.update(SHOP_TABLE_NAME, args, ShopsContract.ItemEntry.SHOP_ID + " = " + shopModel.getShop_id(), null) > 0;
    }



    public Long add_Server_Shop_Data(String shop_id, String owner_name, String type, String shop_activity_id, String width, String length, String employee_number, String floor_number,
                                     String area, String street, String alley, String locality, String locality_number, String license_number, String license_type,
                                     String license_date, String license_end_date, String billboard_name, String billboard_type, String billboard_width,
                                     String billboard_length, String billboard_height, String billboard_font_type , String shopNotes) {
        long rowInserted;
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            values.put(ServerShopsContract.ItemEntry.SHOP_ID, shop_id);
            values.put(ServerShopsContract.ItemEntry.owner_name, owner_name);
            values.put(ServerShopsContract.ItemEntry.type, type);
            values.put(ServerShopsContract.ItemEntry.shop_activity_id, shop_activity_id);
            values.put(ServerShopsContract.ItemEntry.width, width);
            values.put(ServerShopsContract.ItemEntry.length, length);
            values.put(ServerShopsContract.ItemEntry.employee_number, employee_number);
            values.put(ServerShopsContract.ItemEntry.floor_number, floor_number);
            values.put(ServerShopsContract.ItemEntry.area, area);
            values.put(ServerShopsContract.ItemEntry.street, street);
            values.put(ServerShopsContract.ItemEntry.alley, alley);
            values.put(ServerShopsContract.ItemEntry.locality, locality);
            values.put(ServerShopsContract.ItemEntry.locality_number, locality_number);
            values.put(ServerShopsContract.ItemEntry.license_number, license_number);
            values.put(ServerShopsContract.ItemEntry.license_type, license_type);
            values.put(ServerShopsContract.ItemEntry.license_date, license_date);
            values.put(ServerShopsContract.ItemEntry.license_end_date, license_end_date);
            values.put(ServerShopsContract.ItemEntry.billboard_name, billboard_name);
            values.put(ServerShopsContract.ItemEntry.billboard_type, billboard_type);
            values.put(ServerShopsContract.ItemEntry.billboard_width, billboard_width);
            values.put(ServerShopsContract.ItemEntry.billboard_length, billboard_length);
            values.put(ServerShopsContract.ItemEntry.billboard_height, billboard_height);
            values.put(ServerShopsContract.ItemEntry.billboard_font_type, billboard_font_type);
            values.put(ServerShopsContract.ItemEntry.shop_notes, shopNotes);

            rowInserted = db.insert(SERVER_SHOP_TABLE_NAME, null, values);

            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
        return rowInserted;
    }
    public ArrayList<ShopModel> getSERVERShopData() {
        ArrayList<ShopModel> shops = new ArrayList<>();
        final SQLiteDatabase db = this.getReadableDatabase();

        Cursor retCursor;
        retCursor = db.query(SERVER_SHOP_TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null);


        if (retCursor != null) {
            while (retCursor.moveToNext()) {
                ShopModel shop = new ShopModel();
                shop.setShop_id(retCursor.getString(retCursor.getColumnIndex(ServerShopsContract.ItemEntry.SHOP_ID)));
                shop.setOwner_name(retCursor.getString(retCursor.getColumnIndex(owner_name)));
                shop.setType(retCursor.getString(retCursor.getColumnIndex(ServerShopsContract.ItemEntry.type)));
                shop.setShop_activity_id(retCursor.getString(retCursor.getColumnIndex(ServerShopsContract.ItemEntry.shop_activity_id)));
                shop.setWidth(retCursor.getString(retCursor.getColumnIndex(ServerShopsContract.ItemEntry.width)));
                shop.setLength(retCursor.getString(retCursor.getColumnIndex(ServerShopsContract.ItemEntry.length)));
                shop.setEmployee_number(retCursor.getString(retCursor.getColumnIndex(ServerShopsContract.ItemEntry.employee_number)));
                shop.setFloor_number(retCursor.getString(retCursor.getColumnIndex(ServerShopsContract.ItemEntry.floor_number)));
                shop.setArea(retCursor.getString(retCursor.getColumnIndex(ServerShopsContract.ItemEntry.area)));
                shop.setStreet(retCursor.getString(retCursor.getColumnIndex(ServerShopsContract.ItemEntry.street)));
                shop.setLocality(retCursor.getString(retCursor.getColumnIndex(ServerShopsContract.ItemEntry.locality)));
                shop.setAlley(retCursor.getString(retCursor.getColumnIndex(ServerShopsContract.ItemEntry.alley)));
                shop.setLocality_number(retCursor.getString(retCursor.getColumnIndex(ServerShopsContract.ItemEntry.locality_number)));
                shop.setLicense_number(retCursor.getString(retCursor.getColumnIndex(ServerShopsContract.ItemEntry.license_number)));
                shop.setLicense_date(retCursor.getString(retCursor.getColumnIndex(ServerShopsContract.ItemEntry.license_date)));
                shop.setLicense_end_date(retCursor.getString(retCursor.getColumnIndex(ServerShopsContract.ItemEntry.license_end_date)));
                shop.setLicense_type(retCursor.getString(retCursor.getColumnIndex(ServerShopsContract.ItemEntry.license_type)));
                shop.setBillboard_name(retCursor.getString(retCursor.getColumnIndex(ServerShopsContract.ItemEntry.billboard_name)));
                shop.setBillboard_type(retCursor.getString(retCursor.getColumnIndex(ServerShopsContract.ItemEntry.billboard_type)));
                shop.setBillboard_width(retCursor.getString(retCursor.getColumnIndex(ServerShopsContract.ItemEntry.billboard_width)));
                shop.setBillboard_length(retCursor.getString(retCursor.getColumnIndex(ServerShopsContract.ItemEntry.billboard_length)));
                shop.setBillboard_height(retCursor.getString(retCursor.getColumnIndex(ServerShopsContract.ItemEntry.billboard_height)));
                shop.setBillboard_font_type(retCursor.getString(retCursor.getColumnIndex(ServerShopsContract.ItemEntry.billboard_font_type)));
                shop.setShop_notes(retCursor.getString(retCursor.getColumnIndex(ServerShopsContract.ItemEntry.shop_notes)));
                shops.add(shop);
            }
        }

        return shops;
    }
    public boolean deleteAllServerShopData() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(SERVER_SHOP_TABLE_NAME, null, null) > 0;
    }
    public ArrayList<ShopModel> getALlShopInApp() {
        ArrayList<ShopModel> serverShops = getSERVERShopData();
      //  ArrayList<ShopModel> localShops = getShopData();
        //  serverShops.addAll(localShops);

        return serverShops;
    }



    public long addViolation(ViolationModel violation , String user_id) {
        long rowInserted;
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();

        try {
            ContentValues values = new ContentValues();
            values.put(ViolationContract.ItemEntry.ID2, violation.getId());
            values.put(ViolationContract.ItemEntry.SHOP_ID, violation.getShop_id());
            values.put(ViolationContract.ItemEntry.USER_ID, user_id);
            values.put(ViolationContract.ItemEntry.SHOP_NAME, violation.getShop_data_name());
            values.put(ViolationContract.ItemEntry.CATEGORY_ID, violation.getCategory_id());
            values.put(ViolationContract.ItemEntry.VIOLATION_ID, violation.getViolation_id());
            values.put(ViolationContract.ItemEntry.ACTION_ID, violation.getAction_id());
            values.put(ViolationContract.ItemEntry.NOTE, violation.getNote());
            rowInserted = db.insert(VIOLATION_TABLE_TABLE, null, values);
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }

        return rowInserted;
    }
    public boolean deleteOneViolation(String violation_id) {
        final SQLiteDatabase db = this.getReadableDatabase();
        return db.delete(VIOLATION_TABLE_TABLE, ViolationContract.ItemEntry.ID2 + "=" + violation_id, null) > 0;
    }
    public ArrayList<ViolationModel> getViolation(String user_id) {
        ArrayList<ViolationModel> violations = new ArrayList<>();
        final SQLiteDatabase db = this.getReadableDatabase();

        Cursor retCursor;
        retCursor = db.query(VIOLATION_TABLE_TABLE,
                null,
                ViolationContract.ItemEntry.USER_ID  +  " = " + user_id,
                null,
                null,
                null,
                null);


        if (retCursor != null) {
            while (retCursor.moveToNext()) {
                ViolationModel violation = new ViolationModel();
                violation.setId(retCursor.getString(retCursor.getColumnIndex(ViolationContract.ItemEntry.ID2)));
                violation.setShop_id(retCursor.getString(retCursor.getColumnIndex(ViolationContract.ItemEntry.SHOP_ID)));
                violation.setShop_data_name(retCursor.getString(retCursor.getColumnIndex(ViolationContract.ItemEntry.SHOP_NAME)));
                violation.setCategory_id(retCursor.getString(retCursor.getColumnIndex(ViolationContract.ItemEntry.CATEGORY_ID)));
                violation.setViolation_id(retCursor.getString(retCursor.getColumnIndex(ViolationContract.ItemEntry.VIOLATION_ID)));
                violation.setAction_id(retCursor.getString(retCursor.getColumnIndex(ViolationContract.ItemEntry.ACTION_ID)));
                violation.setNote(retCursor.getString(retCursor.getColumnIndex(ViolationContract.ItemEntry.NOTE)));


                violations.add(violation);
            }
        }

        return violations;
    }


    public void addViolationImage(ViolationImage violationImage) {
        byte[] imgByte = getBitmapAsByteArray(violationImage.getSource());
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            values.put(ViolationImageContract.ItemEntry.VIOLATION_ID, violationImage.getViolation_monitoring_id());
            values.put(ViolationImageContract.ItemEntry.IMAGE_SOURCE, imgByte);
            values.put(ViolationImageContract.ItemEntry.IMAGE_DESCREPTION, violationImage.getDescription());
            db.insert(VIOLATION_IMAGE_TABLE_TABLE, null, values);
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }
    public boolean deleteAllViolationImageWithNegativeSign() {
        final SQLiteDatabase db = this.getReadableDatabase();
        return db.delete(VIOLATION_IMAGE_TABLE_TABLE, ViolationImageContract.ItemEntry.VIOLATION_ID + "=" + "-1", null) > 0;
    }
    public boolean deleteAllViolationImageByViolationId(String violatioId) {
        final SQLiteDatabase db = this.getReadableDatabase();
        return db.delete(VIOLATION_IMAGE_TABLE_TABLE, ViolationImageContract.ItemEntry.VIOLATION_ID + "=" + violatioId, null) > 0;
    }
    public boolean updateAllViolationImageWithNegativeSign(String violationId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues args = new ContentValues();
        args.put(ViolationImageContract.ItemEntry.VIOLATION_ID, violationId);
        return db.update(VIOLATION_IMAGE_TABLE_TABLE, args, ViolationImageContract.ItemEntry.VIOLATION_ID + " = -1 ", null) > 0;
    }
    public List<ViolationImage> getViolationImages(String violationId) throws SQLException {
        List<ViolationImage> violationImageList = new ArrayList<>();

        final SQLiteDatabase db = this.getReadableDatabase();
        Cursor mCursor = db.query(true, VIOLATION_IMAGE_TABLE_TABLE, new String[]{ViolationImageContract.ItemEntry.VIOLATION_ID, ViolationImageContract.ItemEntry.IMAGE_SOURCE,
                        ViolationImageContract.ItemEntry.IMAGE_DESCREPTION},
                ViolationImageContract.ItemEntry.VIOLATION_ID + " = " + "" + violationId, null, null, null, null, null);
        if (mCursor != null) {
            while (mCursor.moveToNext()) {
                ViolationImage violationImage = new ViolationImage();
                violationImage.setViolation_monitoring_id(mCursor.getString(mCursor.getColumnIndex(ViolationImageContract.ItemEntry.VIOLATION_ID)));
                violationImage.setDescription(mCursor.getString(mCursor.getColumnIndex(ViolationImageContract.ItemEntry.IMAGE_DESCREPTION)));

                int index = mCursor.getColumnIndexOrThrow(ViolationImageContract.ItemEntry.IMAGE_SOURCE);
                byte[] imgByte = mCursor.getBlob(index);


                Bitmap bitmap = BitmapFactory.decodeByteArray(imgByte, 0, imgByte.length);

                violationImage.setSource(bitmap);

                violationImageList.add(violationImage);
            }
        }


        return violationImageList;
    }
    public List<ViolationImage> getViolationImagesWithNegative(String violationId) throws SQLException {
        List<ViolationImage> violationImageList = new ArrayList<>();

        final SQLiteDatabase db = this.getReadableDatabase();
        Cursor mCursor = db.query(true, VIOLATION_IMAGE_TABLE_TABLE, new String[]{ViolationImageContract.ItemEntry.VIOLATION_ID, ViolationImageContract.ItemEntry.IMAGE_SOURCE,
                        ViolationImageContract.ItemEntry.IMAGE_DESCREPTION},
                ViolationImageContract.ItemEntry.VIOLATION_ID + " = " + "" + violationId, null, null, null, null, null);
        if (mCursor != null) {
            while (mCursor.moveToNext()) {
                ViolationImage violationImage = new ViolationImage();
                violationImage.setViolation_monitoring_id(mCursor.getString(mCursor.getColumnIndex(ViolationImageContract.ItemEntry.VIOLATION_ID)));
                violationImage.setDescription(mCursor.getString(mCursor.getColumnIndex(ViolationImageContract.ItemEntry.IMAGE_DESCREPTION)));

                int index = mCursor.getColumnIndexOrThrow(ViolationImageContract.ItemEntry.IMAGE_SOURCE);
                byte[] imgByte = mCursor.getBlob(index);

                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = 8;

                Bitmap bitmap = BitmapFactory.decodeByteArray(imgByte, 0, imgByte.length , options);

                violationImage.setSource(bitmap);

                violationImageList.add(violationImage);
            }
        }


        return violationImageList;
    }
    public int getImagesCount(String violationId) {
        final SQLiteDatabase db = this.getReadableDatabase();
        Cursor mCursor = db.query(true, VIOLATION_IMAGE_TABLE_TABLE, new String[]{ViolationImageContract.ItemEntry.VIOLATION_ID, ViolationImageContract.ItemEntry.IMAGE_SOURCE,
                        ViolationImageContract.ItemEntry.IMAGE_DESCREPTION},
                ViolationImageContract.ItemEntry.VIOLATION_ID + " = " + "" + violationId, null, null, null, null, null);

        return mCursor.getCount();
    }
    public byte[] getBitmapAsByteArray(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
        return outputStream.toByteArray();
    }


    /*Scheduled Work operations */
    public long addScheduledWork(ScheduledWorkModel scheduledWorkModel , String user_id) {
        long rowInserted = 0 ;
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();

        try {
            ContentValues values = new ContentValues();
            values.put(ScheduledWorkContract.ItemEntry.billboard_name, scheduledWorkModel.getShopModels().getBillboard_name());
            values.put(ScheduledWorkContract.ItemEntry.sheduled_work_id, scheduledWorkModel.getId());
            values.put(ScheduledWorkContract.ItemEntry.USER_ID,user_id);
            values.put(ScheduledWorkContract.ItemEntry.shop_address,scheduledWorkModel.getShopModels().getArea() + "،" + scheduledWorkModel.getShopModels().getStreet()
                    + "،" + scheduledWorkModel.getShopModels().getLocality() + "،" +
                    scheduledWorkModel.getShopModels().getAlley() + "،" + scheduledWorkModel.getShopModels().getLocality_number());
            values.put(ScheduledWorkContract.ItemEntry.visit_date, scheduledWorkModel.getVisit_date());
            values.put(ScheduledWorkContract.ItemEntry.shop_data_id, scheduledWorkModel.getShopModels().getShop_id());


            rowInserted =    db.insert(ScheduledWorkContract.ItemEntry.SCHEDULED_TABLE_NAME, null, values);

            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }

        return rowInserted ;
    }
    public boolean deleteAllScheduledWorks( String user_id) {
        final SQLiteDatabase db = this.getReadableDatabase();
        return db.delete(ScheduledWorkContract.ItemEntry.SCHEDULED_TABLE_NAME, ScheduledWorkContract.ItemEntry.USER_ID + "=" + user_id , null) > 0;
    }
    public ArrayList<ScheduledWorkModel> getScheduledWork(String user_id) {
        ArrayList<ScheduledWorkModel> scheduledWorkModels = new ArrayList<>();
        final SQLiteDatabase db = this.getReadableDatabase();

        Cursor retCursor;
        retCursor = db.query(ScheduledWorkContract.ItemEntry.SCHEDULED_TABLE_NAME,
                null,
                ScheduledWorkContract.ItemEntry.USER_ID + "=" + user_id,
                null,
                null,
                null,
                null);


        if (retCursor != null) {
            while (retCursor.moveToNext()) {
                ScheduledWorkModel scheduledWorkModel = new ScheduledWorkModel();
                scheduledWorkModel.setId(retCursor.getString(retCursor.getColumnIndex(ScheduledWorkContract.ItemEntry.sheduled_work_id)));
                scheduledWorkModel.setShop_data_id(retCursor.getString(retCursor.getColumnIndex(ScheduledWorkContract.ItemEntry.shop_data_id)));
                scheduledWorkModel.setShop_adress(retCursor.getString(retCursor.getColumnIndex(ScheduledWorkContract.ItemEntry.shop_address)));
                scheduledWorkModel.setShop_name(retCursor.getString(retCursor.getColumnIndex(ScheduledWorkContract.ItemEntry.billboard_name)));
                scheduledWorkModel.setVisit_date(retCursor.getString(retCursor.getColumnIndex(ScheduledWorkContract.ItemEntry.visit_date)));
                scheduledWorkModels.add(scheduledWorkModel);
            }
        }

        return scheduledWorkModels;
    }

}