package com.unicomg.baghdadmunicipality.data.LocalSqlite.allviolences;

import android.provider.BaseColumns;

public class AllViolenceContract {

    public static final class ItemEntry implements BaseColumns {

        public static final String All_Violences_TABLE_NAME = "allviolences";
        public static final String id = "id";
        public static final String name = "name";
        public static final String category_id = "category_id";
        public static final String type = "type";
        public static final String financial_value = "financial_value";

    }
}
