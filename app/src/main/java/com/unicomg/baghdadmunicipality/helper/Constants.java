package com.unicomg.baghdadmunicipality.helper;


import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

//this class created for holding  all constant for all application
public class Constants {
    public static final String MyPREFERENCES = "PREF2" ;


    public static final String SHARED_PREFERENCES_PROJECT = "PREF_PROJECT2" ;

    public static final String SHARED_PREFERENCES_PROJECT_NAME = "PREF_PROJECT_NAME2" ;


    public static final String SHARED_PREFERENCES_UPDATE_OFFICE_ID = "office_id_preference2" ;

//
//    public  static  void saveLoginData(String userId, String uname ,String token ,Context context )
//    {
//        SharedPreferences.Editor editor = context.getSharedPreferences(MyPREFERENCES,MODE_PRIVATE).edit();
//        editor.putString("userId", userId);
//        editor.putString("uname", uname);
//        editor.putString("token", token);
//        editor.apply();
//
//    }

    public  static  void saveUnPw(String un, String pw ,Context context )
    {
        SharedPreferences.Editor editor = context.getSharedPreferences(MyPREFERENCES,MODE_PRIVATE).edit();
        editor.putString("un", un);
        editor.putString("pw", pw);
        editor.apply();
    }

    public  static  String getUn( Context context)
    {
        SharedPreferences prefs = context.getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
        return prefs.getString("un", "");
    }

    public  static  String getPw( Context context)
    {
        SharedPreferences prefs = context.getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
        return prefs.getString("pw", "");
    }

    public  static  void savelanglat(String lang, String lat ,Context context )
    {
        SharedPreferences.Editor editor = context.getSharedPreferences(MyPREFERENCES,MODE_PRIVATE).edit();
        editor.putString("lang", lang);
        editor.putString("lat", lat);
        editor.apply();
    }

    public  static  String getlang( Context context)
    {
        SharedPreferences prefs = context.getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
        return prefs.getString("lang", "");
    }

    public  static  String getlat( Context context)
    {
        SharedPreferences prefs = context.getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
        return prefs.getString("lat", "");
    }

    public  static  String getuserId( Context context)
    {
        SharedPreferences prefs = context.getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
        return prefs.getString("user_id", "");
    }

    public  static  String getuser_Id( Context context)
    {
        SharedPreferences prefs = context.getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
        return prefs.getString("user_id", "");
    }

    public  static  String getuserToken( Context context)
    {
        SharedPreferences prefs = context.getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
        return prefs.getString("token", "");


    }


    public static void saveLoginData(String user_id, String first_name, String last_name, String username, String email, String permission, String is_active, Context mContext) {
        SharedPreferences.Editor editor = mContext.getSharedPreferences(MyPREFERENCES,MODE_PRIVATE).edit();
        editor.putString("user_id", user_id);
        editor.putString("first_name", first_name);
        editor.putString("last_name", last_name);
        editor.putString("username", username);
        editor.putString("email", email);
        editor.putString("permission", permission);
        editor.putString("is_active", is_active);
        editor.apply();
    }



    public static void saveAcessToked(String access_token, Context mContext) {
        SharedPreferences.Editor editor = mContext.getSharedPreferences(MyPREFERENCES,MODE_PRIVATE).edit();
        editor.putString("token", access_token);
        editor.apply();
    }

    public static void saveAccessToken(String access_token, Context mContext) {
        SharedPreferences.Editor editor = mContext.getSharedPreferences(MyPREFERENCES,MODE_PRIVATE).edit();
        editor.putString("token", access_token);
        editor.apply();
    }


    public  static  String getuserName( Context context)
    {
        SharedPreferences prefs = context.getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
        return String.format("%s %s", prefs.getString("first_name", ""), prefs.getString("last_name", ""));
    }

    public  static  String getUserIdByElamary( Context context)
    {
        SharedPreferences prefs = context.getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
        return prefs.getString("user_id", "") ;
    }
}
