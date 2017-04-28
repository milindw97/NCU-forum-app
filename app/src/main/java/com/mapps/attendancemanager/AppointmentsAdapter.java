package com.mapps.attendancemanager;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;


public class AppointmentsAdapter extends ArrayAdapter<String> {

    public AppointmentsAdapter(@NonNull Context context, @LayoutRes int resource) {
        super(context, resource);
    }
}
