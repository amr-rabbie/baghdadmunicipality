package com.unicomg.baghdadmunicipality.data.LocalSqlite.violation;

import android.provider.BaseColumns;

public class ViolationImageContract {
    public static final class ItemEntry implements BaseColumns {
        public static final String VIOLATION_IMAGE_TABLE_TABLE = "violation_image";
        public static final String VIOLATION_ID = "violation_monitoring_id";
        public static final String IMAGE_SOURCE = "source";
        public static final String IMAGE_DESCREPTION = "image_desc";

    }
}
