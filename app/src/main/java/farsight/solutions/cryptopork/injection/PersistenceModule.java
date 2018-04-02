package farsight.solutions.cryptopork.injection;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import farsight.solutions.cryptopork.data.CoinPersistence;

@Module
public class PersistenceModule {

    @Provides
    @Singleton
    CoinPersistence provideCoinPersistence(@ForApplication Context context) {
        return new CoinPersistence(context);
    }
}
