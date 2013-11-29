package com.example.rememberdontforget;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;

/**
 * Created by Luis on 29/11/13.
 */
public class ReadableDbTask extends AsyncTask<Password,Integer,ArrayList<Password>>  {
    private Context context;
    private UserPasswordContract.PasswordsDBHelper mDbhelper = null;
    LinearLayout progress;
    public ReadableDbTask(Context context,LinearLayout progress) {
        this.context = context;
        this.progress = progress;
    }

    @Override
    protected void onPreExecute() {
        progress.setVisibility(View.VISIBLE);
    }

    @Override
    protected ArrayList doInBackground(Password... passwords) {
/*        mDbhelper = new UserPasswordContract().new PasswordsDBHelper(context);
        SQLiteDatabase db = mDbhelper.getReadableDatabase();
        String[] projection = {
                UserPasswordContract.PasswordEntry._ID,
                UserPasswordContract.PasswordEntry.COLUMN_NAME_SITE,
                UserPasswordContract.PasswordEntry.COLUMN_NAME_USERNAME,
                UserPasswordContract.PasswordEntry.COLUMN_NAME_PASSWORD
        };
        Cursor c = db.query(
                UserPasswordContract.PasswordEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );
        if (c.moveToFirst()) {
            ArrayList<Password> list = new ArrayList<Password>();
            do {
                list.add(new Password(c.getString(1),c.getString(2),c.getString(3)));
            } while(c.moveToNext());
            return list;
        }*/
        for (int i=0; i<1000000000; i++) {

        }
        return null;
    }
    @Override
    protected void onPostExecute(ArrayList<Password> result) {
        progress.setVisibility(View.GONE);
    }
}
