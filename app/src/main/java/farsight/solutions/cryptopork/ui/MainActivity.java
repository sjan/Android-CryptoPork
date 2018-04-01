package farsight.solutions.cryptopork.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import farsight.solutions.cryptopork.App;
import farsight.solutions.cryptopork.R;

public class MainActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.getInstance().getAppComponent().inject(this);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container, MainFragment.newInstance())
                .commit();
    }
}
