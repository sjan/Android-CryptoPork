package farsight.solutions.cryptopork.ui;

import java.util.List;
import farsight.solutions.cryptopork.api.model.Coin;

public interface MainView {
    void populateCoins(List<Coin> list);
    void popupDialog(String currencyId);
    void updateRefreshTime(Long time);
}
