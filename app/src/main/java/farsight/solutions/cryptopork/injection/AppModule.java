package farsight.solutions.cryptopork.injection;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import farsight.solutions.cryptopork.data.DataManager;
import farsight.solutions.cryptopork.ui.MainPresenter;
import farsight.solutions.cryptopork.ui.PopUpDialogPresenter;
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
    MainPresenter provideMainPresenter(DataManager dataManager) {
        return new MainPresenter(dataManager, AndroidSchedulers.mainThread(), Schedulers.io());
    }

    @Provides
    PopUpDialogPresenter providePopupDialogPresenter(DataManager dataManager) {
        return new PopUpDialogPresenter (dataManager);
    }
}
