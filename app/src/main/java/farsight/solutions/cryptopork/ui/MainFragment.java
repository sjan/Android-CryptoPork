package farsight.solutions.cryptopork.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import farsight.solutions.cryptopork.App;
import farsight.solutions.cryptopork.R;
import farsight.solutions.cryptopork.api.model.Coin;

public class MainFragment extends Fragment implements MainView {
    private static final String TAG = MainFragment.class.getSimpleName();

    @Inject
    MainPresenter presenter;

    @BindView(R.id.data)
    ListView listView;

    ArrayAdapter listAdapter;

    Unbinder unbinder;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.getInstance().getAppComponent().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        unbinder = ButterKnife.bind(this, view);
        presenter.attachView(this);

        listAdapter =  new CoinAdapter(getContext(), new ArrayList<>());
        listView.setAdapter(listAdapter);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.detachView();
        unbinder.unbind();
    }


    public void populateCoins(List<Coin> coins) {
        listAdapter.clear();
        listAdapter.addAll(coins);
    }

    @Override
    public void popupDialog(String currencyId) {
        //popup fragment transition
    }
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
                    Log.d(TAG, "click " + position);
                }
            });

            return v;
        }
    }
}

