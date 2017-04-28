package com.mapps.attendancemanager;

import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextClock;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.TimeZone;

public class HomeFragment extends Fragment {

    TextView date,quote;
    TextClock textClock;
    FirebaseAuth firebaseAuth;
    List<String> quotes = new ArrayList<>();

    public HomeFragment(){}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        date = (TextView) view.findViewById(R.id.date);
        quote = (TextView) view.findViewById(R.id.quote);

        firebaseAuth = FirebaseAuth.getInstance();


        Typeface customFont = Typeface.createFromAsset(getActivity().getAssets(),"fonts/medium.ttf");
        Typeface customFont2 = Typeface.createFromAsset(getActivity().getAssets(),"fonts/thin.ttf");
        Typeface customFont3 = Typeface.createFromAsset(getActivity().getAssets(),"fonts/regular.ttf");

        date.setTypeface(customFont2);
        quote.setTypeface(customFont3);

        quotes.add("“One swallow does not make a summer, nor does one day; and so too one day, or a short time, does not make a man blessed and happy.” —Aristotle");
        quotes.add("“We are not trapped or locked up in these bones. No, no. We are free to change. And love changes us. And if we can love one another, we can break open the sky.” —Walter Mosley");
        quotes.add("“Time was passing like a hand waving from a train I wanted to be on. I hope you never have to think about anything as much as I think about you.” —Jonathan Safran Foer");
        quotes.add("“The tragedy of life is not that it ends so soon, but that we wait so long to begin it.” —W. M. Lewis");
        quotes.add("“Maybe. Everything’s a maybe, isn’t it?” —John Green, Looking for Alaska");
        quotes.add("“Why can’t people have what they want? The things were all there to content everybody; yet everybody has the wrong thing.” —Ford Madox Ford, The Good Soldier");
        quotes.add("“I’ve learned that people will forget what you said, people will forget what you did, but people will never forget how you made them feel.” —Maya Angelou");
        quotes.add("“We know so little about one another. We embrace a shadow and love a dream.” —Hjalmar Söderberg, Doctor Glas");
        quotes.add("“A dream, all a dream, that ends in nothing, and leaves the sleeper where he lay down, but I wish you to know that you inspired it.” —Charles Dickens, A Tale of Two Cities");
        quotes.add("“Of all the hurdles you will need to face in this life time the hardest will be your self imposed limitations” —Jan Hellriegel");
        quotes.add("“What’s money? A man is a success if he gets up in the morning and goes to bed at night and in between does what he wants to do.” —Bob Dylan");

        textClock = (TextClock) view.findViewById(R.id.Clock);
        textClock.setTypeface(customFont);
        textClock.setFormat12Hour("HH:mm:ss a");

        Random random = new Random();
        int x = random.nextInt(11);

        quote.setText(quotes.get(x));

        String myDate = DateFormat.getDateInstance().format(new Date());

        date.setText(myDate);

        return view;
    }
}
