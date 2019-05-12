package com.example.myapplication;

import android.provider.BaseColumns;

public class TableConstants {
    class VolumeInfo implements BaseColumns{

    }
    class ImageLinks implements BaseColumns{

    }
    class UserDetails implements BaseColumns{
        public static final String TABLE_NAME= "userdetails";
        public static final String NAME_COLUMN = "name";
        public static final String BIRTHDAY_COLUMN = "birthday";
        public static final String EMAIL_COLUMN = "email";
        public static final String PASS_COLUMN = "pass";
        public static final String COUNTRY_COLUMN = "country";
        public static final String ADDRESS_COLUMN = "address";
        public static final String GENDER_COLUMN = "gender";
    }
}
/* name
        * birday
        * email
        * pswrd
        * country
        * address
        * gender*/