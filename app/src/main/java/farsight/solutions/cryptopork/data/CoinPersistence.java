package farsight.solutions.cryptopork.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import farsight.solutions.cryptopork.R;
import farsight.solutions.cryptopork.api.model.Coin;

import static android.content.Context.MODE_PRIVATE;


public class CoinPersistence {
    private static final String TAG = CoinPersistence.class.getName();
    private static final String defaultDataFile = "default_data.json";

    private final SharedPreferences preference;
    private final String defaultResult;

    private final String SHARED_PREFERENCE_KEY;
    private final String SHARED_PREFERENCE;

    public CoinPersistence(Context context) {
        SHARED_PREFERENCE_KEY = context.getResources().getString(R.string.shared_preference_key);
        SHARED_PREFERENCE = context.getResources().getString(R.string.shared_preference);

        preference = context.getSharedPreferences(SHARED_PREFERENCE , MODE_PRIVATE);

        try {
            BufferedReader in
                    = new BufferedReader(
                            new InputStreamReader(context.getAssets().open(defaultDataFile)));
            StringBuilder responseData = new StringBuilder();
            while(in.ready())
            {
                responseData.append(in.readLine());
            }
            defaultResult = responseData.toString();

            Log.d(TAG, defaultResult);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Coin> getLatest() {
        Log.d(TAG, "getLatest");
        String latestPrices = preference.getString(SHARED_PREFERENCE_KEY, defaultResult);
        return new Gson().fromJson(latestPrices, new TypeToken<List<Coin>>(){}.getType());
    }

    public void saveLatest(List <Coin> coinList) {
        Log.d(TAG, "saveLatest");
        String json = new Gson().toJson(coinList, new TypeToken<List<Coin>>(){}.getType());
        SharedPreferences.Editor editor = preference.edit();
        editor.putString(SHARED_PREFERENCE_KEY, json);
        editor.apply();
    }
}
