package com.unicomg.baghdadmunicipality.data.LocalSqlite.categories;

import android.provider.BaseColumns;

public class CategoriesContract {

    public static final class ItemEntry implements BaseColumns {

        public static final String Categories_TABLE_NAME = "categories";
        public static final String id = "id";
        public static final String name = "name";
        public static final String code = "code";

    }
}
