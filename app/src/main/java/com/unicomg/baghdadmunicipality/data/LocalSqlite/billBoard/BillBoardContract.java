package com.unicomg.baghdadmunicipality.data.LocalSqlite.billBoard;

import android.provider.BaseColumns;

public class BillBoardContract {
    public static final class ItemEntry implements BaseColumns {

        public static final String      BILL_BOARD_TABLE_NAME = "bill_board";
        public static final String      bill_id = "bill_id" ;
        public static final String      USER_ID = "user_id";
        public static final String      OWNER_NAME = "owner_name" ;
        public static final String      BILLBOARD_NAME = "billboard_name" ;
        public static final String      BILLBOARD_TYPE = "billboard_type" ;
        public static final String      WIDTH = "width" ;
        public static final String      LENGTH  = "length" ;
        public static final String      HEIGHT = "height";
        public static final String      FONT_LANGUAGE = "font_language" ;
        public static final String      AREA = "area" ;
        public static final String      LOCALITY = "locality";
        public static final String      AILLEY = "ailley" ;
        public static final String      STREET = "street" ;
        public static final String      BULDING_NUMBER = "bulding_number" ;
        public static final String      BILLBOARD_LICENSE = "billboard_license" ;
        public static final String      BILLBOARD_LICENSE_NUMBER = "billboard_license_number" ;
        public static final String      LICENSE_DATE  = "license_date" ;
        public static final String      LICENSE_END_DATE = "license_end_date" ;

        public static final String      latitude = "latitude" ;
        public static final String      longitude = "longitude" ;
        public static final String      send = "send" ;

    }
}
