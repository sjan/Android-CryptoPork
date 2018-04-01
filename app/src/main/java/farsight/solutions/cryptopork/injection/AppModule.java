package farsight.solutions.cryptopork.injection;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import farsight.solutions.cryptopork.api.CoinMarketCapService;
import farsight.solutions.cryptopork.data.DataManager;
import farsight.solutions.cryptopork.ui.MainPresenter;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@Module
public class AppModule {
    Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    @ForApplication
    Context provideContext() {
        return application;
    }

    @Provides
    MainPresenter provideMainPresenter(CoinMarketCapService service,DataManager dataManager) {
        return new MainPresenter(service, dataManager, AndroidSchedulers.mainThread(), Schedulers.io());
    }
}
