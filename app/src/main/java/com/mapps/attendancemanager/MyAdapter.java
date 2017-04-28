package com.mapps.attendancemanager;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;
import java.util.List;


public class MyAdapter extends ArrayAdapter<ForumData> {


    public MyAdapter(@NonNull Context context, List<ForumData> data) {
        super(context,R.layout.mylist, data);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = LayoutInflater.from(getContext()).inflate(R.layout.mylist,parent, false);

        ForumData data = getItem(position);

        TextView t1 = (TextView) view.findViewById(R.id.ListSub);
        TextView t2 = (TextView) view.findViewById(R.id.ListContent);
        TextView t3 = (TextView) view.findViewById(R.id.ListName);

        t1.setText(data.getSubject());
        t2.setText(data.getContent());
        t3.setText("Posted by: "+data.getName());

        return view;
    }
}

class MyAppointmentAdapter extends ArrayAdapter<Appointment> {

     MyAppointmentAdapter(@NonNull Context context, List<Appointment> appointments) {
        super(context, R.layout.appointmentlist, appointments);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.appointmentlist,parent, false);

        Appointment data = getItem(position);

        TextView t1 = (TextView) view.findViewById(R.id.AppStudent);
        TextView t2 = (TextView) view.findViewById(R.id.AppFaculty);
        TextView t3 = (TextView) view.findViewById(R.id.AppSub);

        t3.setText("Topic: " + data.getSubject());
        t1.setText("Requested To: " + data.getFacultyName());
        t2.setText("Requested By: " + data.getStudentName());

        return view;
    }
}

class MyCommentsAdapter extends ArrayAdapter<CommentThread> {

    public MyCommentsAdapter(@NonNull Context context, List<CommentThread> commentThreads) {
        super(context, R.layout.comment_view, commentThreads);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.comment_view, parent, false);

        CommentThread data = getItem(position);

        TextView t1 = (TextView) view.findViewById(R.id.comment);
        TextView t2 = (TextView) view.findViewById(R.id.postedby);

        t1.setText(data.getComment());
        t2.setText(data.getName());

        return view;    }
}
