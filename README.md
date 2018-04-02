# App: CryptoPork

Requirements

* Reads a JSON feed from the Internet
* Parses it and shows the contents in a list
* Clicking on a list item shows a detailed view of that item
* Persists the contents of the feed locally, so if the app is used without Internet connection it will show previously downloaded content
* Compiles and runs from Android Studio

This App renders the top 100 ranked crypto currencies as a list. When the user taps on a list item, a popup dialog will display some details about the cryptocurrency. The persistence is handled by setting a SharedPreference entry every time it's fetched. If there is a network error on the fetch, the app will render the  most recently fetched data.

* CryptoPork follows a MVP strategy and leverages RxJava programming.
* Unit tests cover core logic in Presentation layer and Datamanager.class
* Internal modularity is implemented with Daggar2
* Retrofit and OkHttp handles api binding with 3rd part REST interface.
* 3rd party API is coinmarketcap.com
* Dialog show and dismiss transition is configured in a styles.xml entry
* Butterknife for view binding
