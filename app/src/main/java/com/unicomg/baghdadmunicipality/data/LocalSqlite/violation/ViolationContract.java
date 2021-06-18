package com.unicomg.baghdadmunicipality.data.LocalSqlite.violation;

import android.provider.BaseColumns;

public class ViolationContract {
    public static final class ItemEntry implements BaseColumns {
        public static final String VIOLATION_TABLE_TABLE = "violation" ;
        public static final String ID2 = "id";
        public static final String USER_ID = "user_id" ;
        public static final String SHOP_ID = "SHOP_ID";
        public static final String SHOP_NAME = "shop_name";
        public static final String CATEGORY_ID = "category_id";
        public static final String VIOLATION_ID = "violation_id";
        public static final String ACTION_ID = "action_id";
        public static final String NOTE = "note";
    }
}
