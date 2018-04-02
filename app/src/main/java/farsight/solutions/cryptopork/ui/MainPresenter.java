package farsight.solutions.cryptopork.ui;

import java.util.List;

import farsight.solutions.cryptopork.api.model.Coin;
import farsight.solutions.cryptopork.data.DataManager;
import io.reactivex.Scheduler;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

public class MainPresenter {
    private final Scheduler androidScheduler;
    private final Scheduler ioScheduler;

    private MainView mainView;
    private DataManager dataManager;

    public MainPresenter(DataManager dataManager, Scheduler androidScheduler, Scheduler ioScheduler) {
        this.dataManager = dataManager;
        this.androidScheduler = androidScheduler;
        this.ioScheduler = ioScheduler;
    }

    private void refreshCoins() {
        dataManager.getCoinList()
                .subscribeOn(ioScheduler)
                .observeOn(androidScheduler)
                .subscribe(new SingleObserver<List<Coin>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        //todo loading bar
                    }

                    @Override
                    public void onSuccess(List<Coin> coin) {
                        mainView.populateCoins(coin);
                        mainView.updateRefreshTime(coin.get(0).getLastUpdated());
                    }

                    @Override
                    public void onError(Throwable e) {
                        //todo error handling
                    }
                });
    }


    public void onRefreshClick() {
        this.refreshCoins();
    }

    public void onCreateView() {
        this.refreshCoins();
    }

    public void onClickCoin(String currencyId) {
        mainView.popupDialog(currencyId);
    }

    public void attachView(MainView mainView) {
        this.mainView= mainView;
    }

    public void detachView() {
        this.mainView = null;
    }
}
