package farsight.solutions.cryptopork.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import farsight.solutions.cryptopork.App;
import farsight.solutions.cryptopork.R;
import farsight.solutions.cryptopork.api.CoinbinService;
import farsight.solutions.cryptopork.api.model.Coin;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements Callback<List<Coin>> {

    @Inject
    CoinbinService service;

    private static final String TAG = MainActivity.class.getCanonicalName();
    @BindView(R.id.text)
    TextView textView;
    Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.getInstance().getAppComponent().inject(this);

        setContentView(R.layout.activity_main);
        Unbinder unbinder = ButterKnife.bind(this);

        Call<List<Coin>> call = service.coins();
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<List<Coin>> call, Response<List<Coin>> response) {
        Log.d(TAG, "onResponse " + response.body().size());
    }

    @Override
    public void onFailure(Call<List<Coin>> call, Throwable t) {
        Log.d(TAG, "onFailure ");
        t.printStackTrace();
    }
}
