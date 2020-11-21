package com.xpring.edu.util;

import java.time.YearMonth;

public class DaysFromMonth {
    
    public int getDays(String date) {
        int len = date.length();
        int month = Integer.parseInt(date.substring(len - 2, len));
        int year = Integer.parseInt(date.substring(0, 4));
        YearMonth yearMonthObject = YearMonth.of(year, month);
        return yearMonthObject.lengthOfMonth();
    }
}
