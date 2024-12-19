package com.example.chriechic.utils;

import android.text.format.DateFormat;

import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class FunctionHelper {
    public static String rupiahFormat(int value) {
        NumberFormat format = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
        return format.format(value).replace("Rp", "Rp ");
    }

    public static String getToday() {
        Date date = Calendar.getInstance().getTime();
        return (String) DateFormat.format("d MMMM yyyy", date);
    }
}
