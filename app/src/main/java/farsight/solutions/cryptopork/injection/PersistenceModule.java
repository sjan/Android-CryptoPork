package farsight.solutions.cryptopork.injection;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import farsight.solutions.cryptopork.data.DataPersistence;

@Module
public class PersistenceModule {

    @Provides
    @Singleton
    DataPersistence provideCoinPersistence(@ForApplication Context context) {
        return new DataPersistence(context);
    }
}
