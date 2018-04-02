package farsight.solutions.cryptopork;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import farsight.solutions.cryptopork.api.model.Coin;
import farsight.solutions.cryptopork.data.DataManager;
import farsight.solutions.cryptopork.ui.PopUpDialogPresenter;
import farsight.solutions.cryptopork.ui.PopUpDialogView;
import io.reactivex.Single;
import io.reactivex.schedulers.TestScheduler;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class PopUpDialogPresenterUnitTest {
    final String TEST_CURRENCY_ID = "TEST_CURRENCY_ID";

    @Mock
    DataManager dataManager;

    @Mock
    PopUpDialogView popUpDialogView;

    PopUpDialogPresenter popUpDialogPresenter ;
    TestScheduler scheduler;
    final Coin TEST_COIN = new Coin();

    @Before public void setUp() {
        initMocks(this);
        scheduler = new TestScheduler();

        popUpDialogPresenter = new PopUpDialogPresenter(dataManager);
        popUpDialogPresenter.attachView(popUpDialogView);
    }

    @Test public void createView_triggers_populateCoinData() {
        when(dataManager.getCoinData(TEST_CURRENCY_ID)).thenReturn(Single.just(TEST_COIN ));

        popUpDialogPresenter.createView(TEST_CURRENCY_ID);

        scheduler.triggerActions();
        verify(popUpDialogView).populateCoinData(TEST_COIN );
    }
}