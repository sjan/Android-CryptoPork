package farsight.solutions.cryptopork.injection;

import javax.inject.Singleton;

import dagger.Component;
import farsight.solutions.cryptopork.App;
import farsight.solutions.cryptopork.ui.MainActivity;

@Singleton
@Component(modules = {AppModule.class, NetworkModule.class})
public interface AppComponent {
    void inject(App app);

    void inject(MainActivity activity);
}