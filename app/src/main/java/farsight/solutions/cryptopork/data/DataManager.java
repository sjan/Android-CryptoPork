package farsight.solutions.cryptopork.data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import farsight.solutions.cryptopork.api.CoinMarketCapService;
import farsight.solutions.cryptopork.api.model.Coin;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.Single;

public class DataManager {
    private static final String TAG = DataManager.class.getName();
    final private Map<String, Coin> coinCache = new HashMap<>();
    final private CoinMarketCapService service;
    final private DataPersistence persistence;
    final private Scheduler scheduler;

    public DataManager(CoinMarketCapService service, DataPersistence persistence, Scheduler scheduler) {
        this.service = service;
        this.persistence = persistence;
        this.scheduler = scheduler;
    }

    public Single<List<Coin>> getCoinList() {
        Single<List<Coin>> result =  service.coins()
                .onErrorReturnItem(persistence.getLatest());

        return result
                .map(coinList -> {
                    Observable.create(subscriber -> {
                        for(Coin c : coinList) {
                            coinCache.put(c.getId(), c);
                        }
                        persistence.saveLatest(coinList);
                        subscriber.onComplete();
                    }).subscribeOn(scheduler).subscribe();
                    return coinList;
                });
    }

    public Single<Coin> getCoinData(String id) {
        return Single.just(coinCache.get(id));
    }
}
