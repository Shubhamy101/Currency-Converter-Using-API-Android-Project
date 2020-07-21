package com.techbuzzus.currencyconverter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class AdapterClass extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] Currency;
    private final String[] Country;
    private final Integer[] Icons;



    public AdapterClass(Activity context, String[] Currency, String[] Country, Integer[] Icons) {
        super(context,R.layout.item, Currency);
        this.context = context;
        this.Currency = Currency;
        this.Country = Country;
        this.Icons = Icons;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();

        View view = inflater.inflate(R.layout.item, null, true);

        ImageView imageView = view.findViewById(R.id.icon);
        TextView textView = view.findViewById(R.id.title);
        TextView textView1 = view.findViewById(R.id.subtitle);

        imageView.setImageResource(Icons[position]);
        textView.setText(Currency[position]);
        textView1.setText(Country[position]);

        return view;
    }
}