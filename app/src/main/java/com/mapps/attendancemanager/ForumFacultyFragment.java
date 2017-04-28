package com.mapps.attendancemanager;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AlertDialogLayout;
import android.support.v7.widget.MenuPopupWindow;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by milin on 27-04-2017.
 */

public class ForumFacultyFragment extends Fragment {

    int i=0;
    ListView forumList;
    Button newTopic;
    ListAdapter listAdapter;
    List<ForumData> forumDataList = new ArrayList<>();

    public ForumFacultyFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_forum, container, false);

        forumList = (ListView) view.findViewById(R.id.forumsList);

        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("/Topics");
        forumDataList.clear();
        databaseReference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int i = 0;
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                    Log.d("KEY",postSnapshot.getKey());
                    ForumData forumData = postSnapshot.getValue(ForumData.class);
                    forumDataList.add(i,forumData);
                    i++;
                    listAdapter = new MyAdapter(getActivity(),forumDataList);
                    forumList.setAdapter(listAdapter);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        newTopic = (Button) view.findViewById(R.id.newTopic);
        newTopic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(),NewTopic.class);
                startActivity(i);
            }
        });

        forumList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                TextView Sub = (TextView) view.findViewById(R.id.ListSub);
                TextView Cont = (TextView) view.findViewById(R.id.ListContent);
                TextView Id = (TextView) view.findViewById(R.id.ListName);

                String Subject = Sub.getText().toString();
                String Content = Cont.getText().toString();
                String Email = Id.getText().toString();

                Intent i = new Intent(getActivity(),QuestionView.class);
                i.putExtra("Subject",Subject);
                i.putExtra("Content",Content);
                i.putExtra("Email",Email);

                startActivity(i);
            }
        });

        forumList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final TextView topic = (TextView) view.findViewById(R.id.ListSub);

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Options:");
                builder.setItems(new String[]{"Delete"}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        databaseReference.child(topic.getText().toString()).removeValue();
                        startActivity(new Intent(getActivity(),FacultyActivity.class));
                        Toast.makeText(getActivity(), "Topic Deleted!", Toast.LENGTH_SHORT).show();
                    }
                }).show();

                return true;

            }
        });

        return view;
    }
}
