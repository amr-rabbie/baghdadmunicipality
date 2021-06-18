package com.unicomg.baghdadmunicipality.data.LocalSqlite.shops;

import android.provider.BaseColumns;

public class ShopsContract {

    public static final class ItemEntry implements BaseColumns {

        public static final String SHOP_TABLE_NAME = "shopdata";
        public static final String USER_ID = "user_id" ;
        public static final String SHOP_ID = "shop_id";
        public static final String owner_name = "owner_name";
        public static final String type = "type";
        public static final String shop_activity_id = "shop_activity_id";
        public static final String width = "width";
        public static final String length = "length";
        public static final String employee_number = "employee_number";
        public static final String floor_number = "floor_number";

        public static final String area = "area";
        public static final String street = "street";
        public static final String locality = "locality";
        public static final String alley = "alley";
        public static final String locality_number = "locality_number";
        public static final String license_number = "license_number";
        public static final String license_date = "license_date";

        public static final String license_end_date = "license_end_date";
        public static final String license_type = "license_type";
        public static final String billboard_name = "billboard_name";
        public static final String billboard_type = "billboard_type";
        public static final String billboard_width = "billboard_width";
        public static final String billboard_length = "billboard_length";
        public static final String billboard_font_type = "billboard_font_type";
        public static final String billboard_height = "billboard_height";
        public static final String SENDE = "sended";

        public static final String longitude = "longitude";
        public static final String latitude = "latitude";
        public static final String shop_notes = "shop_notes";
    }
}

