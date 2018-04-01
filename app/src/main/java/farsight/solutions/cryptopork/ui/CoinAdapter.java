package farsight.solutions.cryptopork.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import farsight.solutions.cryptopork.R;
import farsight.solutions.cryptopork.api.model.Coin;

public class CoinAdapter extends ArrayAdapter<Coin> {
    private List<Coin> list;
    private Context context;

    public CoinAdapter(@NonNull Context context, ArrayList<Coin> list) {
        super(context, 0, list);
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if(v== null)
            v = LayoutInflater.from(context).inflate(R.layout.row,parent,false);

        ((TextView)v.findViewById(R.id.label)).setText(list.get(position).getName());

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return v;
    }


}
