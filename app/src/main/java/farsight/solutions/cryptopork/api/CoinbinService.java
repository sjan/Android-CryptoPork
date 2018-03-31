package farsight.solutions.cryptopork.api;

import java.util.List;

import javax.inject.Inject;

import farsight.solutions.cryptopork.api.model.Coin;
import retrofit2.Call;

public class CoinbinService {

    @Inject
    CoinbinApi api;

    @Inject
    public CoinbinService() {
    }

    public Call<List<Coin>> coins() {
        return api.coins(100);
    }

}
