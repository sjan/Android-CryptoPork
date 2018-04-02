package farsight.solutions.cryptopork.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.DialogFragment;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import farsight.solutions.cryptopork.App;
import farsight.solutions.cryptopork.R;
import farsight.solutions.cryptopork.api.model.Coin;

public class PopUpDialogFragment extends DialogFragment implements PopUpDialogView {
    private static final String TAG = PopUpDialogFragment.class.getSimpleName();

    @Inject
    PopUpDialogPresenter popUpDialogPresenter;

    @BindView(R.id.coinLabel)
    TextView label;

    @BindView(R.id.coinSymbol)
    TextView symbol;

    @BindView(R.id.coinRank)
    TextView coinRank;

    @BindView(R.id.coinValue)
    TextView coinValue;

    @BindView(R.id.volume_24h)
    TextView volume;

    @BindView(R.id.market_cap)
    TextView marketCap;

    @BindView(R.id.available_supply)
    TextView supply;

    @BindView(R.id.percent_change_1h)
    TextView percentOneHour;

    @BindView(R.id.percent_change_24h)
    TextView percentChangeOneDay;

    @BindView(R.id.percent_change_7d)
    TextView percentChangeSevenDay;

    Unbinder unbinder;

    static PopUpDialogFragment newInstance(String id) {
        PopUpDialogFragment fragment = new PopUpDialogFragment();
        Bundle args = new Bundle();
        args.putString("currencyId", id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.getInstance().getAppComponent().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dialog_popup, container, false);
        unbinder = ButterKnife.bind(this, view);
        this.getDialog().getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        popUpDialogPresenter.attachView(this);
        popUpDialogPresenter.createView(getArguments().getString("currencyId"));



        return view;
    }

    @Override
    public void populateCoinData(Coin coin) {
        symbol.setText(coin.getSymbol());
        label.setText(coin.getName());

        String coinRankFormat = getContext().getString(R.string.details_page_rank_format);
        coinRank.setText(String.format(coinRankFormat, coin.getRank()));

        String coinValueFormat = getContext().getString(R.string.details_page_value_format);
        coinValue.setText(String.format(coinValueFormat, coin.getPriceUsd()));

        String coinVolume = getContext().getString(R.string.details_page_volume_format);
        volume.setText(String.format(coinVolume, coin.get_24hVolumeUsd()));

        String coinMarketCap = getContext().getString(R.string.details_page_market_cap_format);
        marketCap.setText(String.format(coinMarketCap , coin.getMarketCapUsd()));

        String coinSupply = getContext().getString(R.string.details_page_available_supply_format);
        supply.setText(String.format(coinSupply, coin.getAvailableSupply()));

        String coin1hrChange = getContext().getString(R.string.details_page_percent_change_1h_format);
        percentOneHour.setText(String.format(coin1hrChange, coin.getPercentChange1h()));

        String coin24hrChange = getContext().getString(R.string.details_page_percent_change_24h_format);
        percentChangeOneDay.setText(String.format(coin24hrChange, coin.getPercentChange24h()));

        String coin7hrChange = getContext().getString(R.string.details_page_percent_change_7d_format);
        percentChangeSevenDay.setText(String.format(coin7hrChange, coin.getPercentChange7d()));

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        popUpDialogPresenter.detachView();
        unbinder.unbind();
    }
}
