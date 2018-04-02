package farsight.solutions.cryptopork.api.model;

import com.google.gson.annotations.SerializedName;

public class Coin {
    private String id;
    private String name;
    private String symbol;
    private Integer rank;
    @SerializedName("price_usd")
    private Float priceUsd;
    @SerializedName("price_btc")
    private Float priceBtc;
    @SerializedName("24h_volume_usd")
    private Float _24hVolumeUsd;
    @SerializedName("market_cap_usd")
    private Float marketCapUsd;
    @SerializedName("available_supply")
    private Float availableSupply;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Float getPriceUsd() {
        return priceUsd;
    }

    public void setPriceUsd(Float priceUsd) {
        this.priceUsd = priceUsd;
    }

    public Float getPriceBtc() {
        return priceBtc;
    }

    public void setPriceBtc(Float priceBtc) {
        this.priceBtc = priceBtc;
    }

    public Float get_24hVolumeUsd() {
        return _24hVolumeUsd;
    }

    public void set_24hVolumeUsd(Float _24hVolumeUsd) {
        this._24hVolumeUsd = _24hVolumeUsd;
    }

    public Float getMarketCapUsd() {
        return marketCapUsd;
    }

    public void setMarketCapUsd(Float marketCapUsd) {
        this.marketCapUsd = marketCapUsd;
    }

    public Float getAvailableSupply() {
        return availableSupply;
    }

    public void setAvailableSupply(Float availableSupply) {
        this.availableSupply = availableSupply;
    }

    public Float getTotalSupply() {
        return totalSupply;
    }

    public void setTotalSupply(Float totalSupply) {
        this.totalSupply = totalSupply;
    }

    public Float getMaxSupply() {
        return maxSupply;
    }

    public void setMaxSupply(Float maxSupply) {
        this.maxSupply = maxSupply;
    }

    public Float getPercentChange1h() {
        return percentChange1h;
    }

    public void setPercentChange1h(Float percentChange1h) {
        this.percentChange1h = percentChange1h;
    }

    public Float getPercentChange24h() {
        return percentChange24h;
    }

    public void setPercentChange24h(Float percentChange24h) {
        this.percentChange24h = percentChange24h;
    }

    public Float getPercentChange7d() {
        return percentChange7d;
    }

    public void setPercentChange7d(Float percentChange7d) {
        this.percentChange7d = percentChange7d;
    }

    public Long getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Long lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    @SerializedName("total_supply")

    private Float totalSupply;
    @SerializedName("max_supply")
    private Float maxSupply;
    @SerializedName("percent_change_1h")
    private Float percentChange1h;
    @SerializedName("percent_change_24h")
    private Float percentChange24h;
    @SerializedName("percent_change_7d")
    private Float percentChange7d;
    @SerializedName("last_updated")
    private Long lastUpdated;


}
