package ir.pkokabi.datepicker;

/**
 * Created by p.kokabi on 1/3/18.
 */

interface DatePickerViewInterface {
    void prepareDate();

    void prepareTime();

    String getDate();

    String getDateTime();

    String getTime();

    int getHour();

    int getMinute();

    int dateDifference();
}
