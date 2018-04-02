package farsight.solutions.cryptopork;

import org.junit.Test;

import org.junit.Before;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.List;

import farsight.solutions.cryptopork.api.model.Coin;
import farsight.solutions.cryptopork.data.DataManager;
import farsight.solutions.cryptopork.ui.MainPresenter;
import farsight.solutions.cryptopork.ui.MainView;
import io.reactivex.Single;
import io.reactivex.schedulers.TestScheduler;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class MainPresenterUnitTest {
    final List<Coin> TEST_COIN_LIST_SIZE_TWO = Arrays.asList(new Coin(), new Coin());
    final String TEST_CURRENCY_ID = "TEST_CURRENCY_ID";

    @Mock
    DataManager dataManager;

    @Mock
    MainView mainView;

    MainPresenter mainPresenter;
    TestScheduler scheduler;


    @Before public void setUp() {
        initMocks(this);
        scheduler = new TestScheduler();

        mainPresenter = new MainPresenter(dataManager, scheduler ,scheduler );
        mainPresenter.attachView(mainView);
    }

    @Test public void createView_triggers_populateCoins() {
        when(dataManager.getCoinList()).thenReturn(Single.just(TEST_COIN_LIST_SIZE_TWO));

        mainPresenter.onCreateView();

        scheduler.triggerActions();
        verify(mainView).populateCoins(TEST_COIN_LIST_SIZE_TWO);
    }

    @Test public void refreshClick_triggers_populateCoins() {
        when(dataManager.getCoinList()).thenReturn(Single.just(TEST_COIN_LIST_SIZE_TWO));

        mainPresenter.onRefreshClick();

        scheduler.triggerActions();
        verify(mainView).populateCoins(TEST_COIN_LIST_SIZE_TWO);
    }

    @Test public void selectCoin_triggers_dialog_popup() {
        mainPresenter.onClickCoin(TEST_CURRENCY_ID);

        scheduler.triggerActions();
        verify(mainView).popupDialog(TEST_CURRENCY_ID);
    }
}