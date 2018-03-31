package farsight.solutions.cryptopork.api;

import java.util.List;

import retrofit2.Call;
import farsight.solutions.cryptopork.api.model.Coin;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CoinbinApi {

    @GET("v1/ticker/")
    Call<List<Coin>> coins(@Query("limit") int limit);
}
