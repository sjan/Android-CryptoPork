package farsight.solutions.cryptopork.injection;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import farsight.solutions.cryptopork.api.CoinMarketCapService;
import farsight.solutions.cryptopork.data.CoinPersistence;
import farsight.solutions.cryptopork.data.DataManager;
import io.reactivex.schedulers.Schedulers;

@Module
public class DataModule {
    @Singleton
    @Provides
    DataManager provideDataManager(CoinMarketCapService service, CoinPersistence persistence) {
        return new DataManager(service, persistence, Schedulers.computation());
    }

    @Singleton
    @Provides
    CoinPersistence provideDataPersistence(@ForApplication Context context) {
        return new CoinPersistence(context);
    }
}
