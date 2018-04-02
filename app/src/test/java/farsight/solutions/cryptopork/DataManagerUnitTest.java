package farsight.solutions.cryptopork;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import farsight.solutions.cryptopork.api.CoinMarketCapService;
import farsight.solutions.cryptopork.api.model.Coin;
import farsight.solutions.cryptopork.data.DataPersistence;
import farsight.solutions.cryptopork.data.DataManager;
import io.reactivex.Single;
import io.reactivex.schedulers.TestScheduler;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class DataManagerUnitTest {

    @Mock
    DataPersistence persistence;

    @Mock
    CoinMarketCapService service;

    DataManager dataManager;

    TestScheduler scheduler;

    final List<Coin> TEST_COIN_LIST_SIZE_ONE = Arrays.asList(new Coin());

    @Before public void setUp() {
        initMocks(this);

        scheduler = new TestScheduler();
        dataManager = new DataManager(service, persistence, scheduler);
    }

    @Test public void getCoinList_success_returns_coinList() {
        when(service.coins()).thenReturn(Single.just(TEST_COIN_LIST_SIZE_ONE));

        Single<List<Coin>> result = dataManager.getCoinList();

        Assert.assertEquals(result.blockingGet(), TEST_COIN_LIST_SIZE_ONE);
    }


    @Test public void getCoinList_success_persistence() {
        when(service.coins()).thenReturn(Single.just(TEST_COIN_LIST_SIZE_ONE));

        dataManager.getCoinList()
                .subscribeOn(scheduler)
                .observeOn(scheduler)
                .subscribe();
        scheduler.triggerActions();

        verify(persistence).saveLatest(TEST_COIN_LIST_SIZE_ONE);
    }

    @Test public void getCoinList_failure_fetches_persisted_coinLata() {
        when(service.coins()).thenReturn(Single.error(new IOException("network problem")));
        when(persistence.getLatest()).thenReturn(TEST_COIN_LIST_SIZE_ONE);

       dataManager.getCoinList()
                .subscribeOn(scheduler)
                .observeOn(scheduler)
                .subscribe();
        scheduler.triggerActions();

        verify(persistence).getLatest();
    }
}