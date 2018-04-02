package farsight.solutions.cryptopork.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

    @BindView(R.id.refresh_button)
    Button refreshButton;

    @BindView(R.id.refresh_label)
    TextView textView;

    ArrayAdapter <Coin> listAdapter;

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
        listAdapter =  new CoinAdapter(getContext(), new ArrayList<>());
        listView.setAdapter(listAdapter);
        refreshButton.setOnClickListener(v -> presenter.onRefreshClick());
        presenter.attachView(this);
        presenter.onCreateView();
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
        PopUpDialogFragment.
                newInstance(currencyId).show(this.getFragmentManager(), "popup_dialog");
    }

    @Override
    public void updateRefreshTime(Long time) {
        String titleFormat = getContext().getString(R.string.last_refreshed_title_format);
        String dateFormat = new SimpleDateFormat(getContext().getString(R.string.time_format)).format(new Date(time*1000));
        textView.setText(String.format(titleFormat, dateFormat));
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
            if(convertView == null)
                convertView = LayoutInflater.from(context).inflate(R.layout.row,parent,false);

            ((TextView)convertView.findViewById(R.id.currency_label)).setText(list.get(position).getName());
            ((TextView)convertView.findViewById(R.id.currency_sub_label)).setText(list.get(position).getSymbol());
            String formattedFloat  = String.format("%.4f", list.get(position).getPriceUsd());
            ((TextView)convertView.findViewById(R.id.value_label)).setText(formattedFloat);

            convertView.setOnClickListener(v1 -> presenter.onClickCoin(list.get(position).getId()));

            return convertView;
        }
    }

}

