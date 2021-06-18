package com.unicomg.baghdadmunicipality.data.LocalSqlite.scheduled_works;

import android.provider.BaseColumns;

public class ScheduledWorkContract {

    public static final class ItemEntry implements BaseColumns {
        public static final String SCHEDULED_TABLE_NAME = "sheduled_work";
        public static final String sheduled_work_id = "sheduled_work_id";
        public static final String USER_ID = "user_id" ;
        public static final String shop_data_id = "shop_data_id";
        public static final String visit_date = "visit_date";
        public static final String billboard_name = "billboard_name";
        public static final String shop_address = "shop_address";

    }

}
