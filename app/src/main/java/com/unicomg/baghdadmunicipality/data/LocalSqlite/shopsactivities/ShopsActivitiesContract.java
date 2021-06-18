package com.unicomg.baghdadmunicipality.data.LocalSqlite.shopsactivities;

import android.provider.BaseColumns;

public class ShopsActivitiesContract {

    public static final class ItemEntry implements BaseColumns {

        public static final String Shop_Activities_TABLE_NAME = "shopactivities";
        public static final String id = "id";
        public static final String name = "name";
        public static final String code = "code";

    }
}
