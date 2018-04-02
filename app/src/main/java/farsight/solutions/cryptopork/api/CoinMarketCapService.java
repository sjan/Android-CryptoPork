package farsight.solutions.cryptopork.api;

import java.util.List;

import javax.inject.Inject;

import farsight.solutions.cryptopork.api.model.Coin;
import io.reactivex.Single;

public class CoinMarketCapService {

    @Inject
    CoinMarketCapApi api;

    @Inject
    public CoinMarketCapService  () {
    }

    public Single<List<Coin>> coins() {
        return api.coins(100);
    }
}
