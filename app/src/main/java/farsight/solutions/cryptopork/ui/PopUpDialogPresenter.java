package farsight.solutions.cryptopork.ui;

import farsight.solutions.cryptopork.api.model.Coin;
import farsight.solutions.cryptopork.data.DataManager;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class PopUpDialogPresenter {
    private static final String TAG = PopUpDialogPresenter.class.getSimpleName();

    private PopUpDialogView popupDialogView;
    private DataManager dataManager;

    public PopUpDialogPresenter (DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public void createView(String currencyId) {
        dataManager.getCoinData(currencyId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Coin>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onSuccess(Coin coin) {
                        popupDialogView.populateCoinData(coin);
                    }

                    @Override
                    public void onError(Throwable e) {}
                });
    }

    public void detachView() {
        this.popupDialogView = null;
    }

    public void attachView(PopUpDialogView view) {
        this.popupDialogView = view;
    }
}
