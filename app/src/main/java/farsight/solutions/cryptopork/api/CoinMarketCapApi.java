package farsight.solutions.cryptopork.api;

import java.util.List;

import io.reactivex.Single;
import farsight.solutions.cryptopork.api.model.Coin;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CoinMarketCapApi {

    @GET("v1/ticker/")
    Single<List<Coin>> coins(@Query("limit") int limit);
}
