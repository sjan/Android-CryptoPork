package farsight.solutions.cryptopork.injection;

import javax.inject.Singleton;

import dagger.Component;
import farsight.solutions.cryptopork.App;
import farsight.solutions.cryptopork.ui.MainActivity;
import farsight.solutions.cryptopork.ui.MainFragment;
import farsight.solutions.cryptopork.ui.PopUpDialogFragment;

@Singleton
@Component(modules = {AppModule.class, NetworkModule.class, DataModule.class})
public interface AppComponent {
    void inject(App app);

    void inject(MainActivity activity);

    void inject(MainFragment mainFragment);

    void inject(PopUpDialogFragment popUpDialogFragment);
}