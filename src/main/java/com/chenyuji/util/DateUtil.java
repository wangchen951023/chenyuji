package com.chenyuji.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class DateUtil {
    private static final HashMap<String, SimpleDateFormat> _simpleDateFormat = new HashMap<String, SimpleDateFormat>();
    public static String formatDate(Date date, String pattern) {
        SimpleDateFormat sdf = getSimpleDateFormat(pattern);
        return sdf.format(date);
    }

    public static SimpleDateFormat getSimpleDateFormat(String form) {
        SimpleDateFormat result = null;
        if (_simpleDateFormat.containsKey(form)) {
            result = _simpleDateFormat.get(form);
        }
        if (result == null) {
            try {
                result = new SimpleDateFormat(form);
            } catch (Exception e) {
            }
            if (result != null) {
                _simpleDateFormat.put(form, result);
            }
        }
        return result;
    }
}
