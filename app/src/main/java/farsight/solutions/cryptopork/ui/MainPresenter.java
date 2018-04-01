package farsight.solutions.cryptopork.ui;

import java.util.List;

import javax.inject.Inject;

import farsight.solutions.cryptopork.api.CoinMarketCapService;
import farsight.solutions.cryptopork.api.model.Coin;
import farsight.solutions.cryptopork.data.DataManager;
import io.reactivex.Scheduler;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainPresenter {
    private final DataManager dataManager;
    private final Scheduler androidScheduler;
    private final Scheduler ioScheduler;

    private MainView mainView;

    @Inject
    CoinMarketCapService service;

    public void attachView(MainView mainView) {
        this.mainView= mainView;

        service.coins()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<Coin>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(List<Coin> coin) {
                        mainView.populateCoins(coin);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    @Inject
    public MainPresenter(CoinMarketCapService service, DataManager dataManager, Scheduler androidScheduler, Scheduler ioScheduler ) {
        this.service = service;
        this.dataManager = dataManager;
        this.androidScheduler = androidScheduler;
        this.ioScheduler = ioScheduler;
    }

    public void loadBitcoin(String type) {
        mainView.popupDialog(type);
    }

    public void detachView() {

    }
}
