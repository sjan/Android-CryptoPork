package farsight.solutions.cryptopork.injection;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import farsight.solutions.cryptopork.data.DataManager;

@Module
public class DataModule {
    @Singleton
    @Provides
    DataManager provideDataManager() {
        return new DataManager();
    }
}
