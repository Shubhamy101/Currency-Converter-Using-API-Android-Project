# Currency-Converter-Using-API's-Android-Project
IBM Summer Training Project. Android Application on Currency Converter created in Android Studio 4.0 during July-August 2020.

CURRENCY CONVERTER

The App gives Revised and Correct Exchange Values of Currencies from Time to Time through the Usage of API's (JSON Parsing).

** Internet Service is Required to Run the App **

Concepts Used in Project -

  * Layouts & Views (Linear Layout, Image View, Text View, Edit Text, Button, Spinner)
  * Toasts
  * Alert Dialog Box
  * Custom Adapter (Adapter Used with Spinner)
  * JSON Parsing With API's (About API in other File)
  * Intents
  * OOP's

The App works over 10 currencies (popular) :-

  1. US Dollar
  2. Euro
  3. British Pound
  4. Indian Rupee
  5. Bit Coin
  6. Emirati Dirham
  7. Swiss Franc
  8. Japanese Yen
  9. Russian Ruble
  10. South African Rand

It can calculates conversion from any of these (in list) to any other (in list).

UI (User Interface)

Usage Interface is simple.The User just need to choose the base and conversion format and click Convert.
The User can choose the Currencies from a Spinner view on which an Custom Adapter with Currency Code, Name And Flag is Setup.
The result is shown in a Alert dialog Pop-Up.

About API 

API Used For Querying Currency Exchange Rates
--> https://currencylayer.com/

** The Free Version of API supports only upto 200 Requests / mo, Daily Updates and does not support HTTPS Encryption. **

** The Paid Version can support upto 500,000 Requests / mo, 60-second Updates, HTTPS Encryption, Source Currency Switching. **

Usage of API : (I am Using Free Version of API for this App)

http://apilayer.net/api/live

    ? access_key = OUR_PRIVATE_KEY               //Our Access Key
    & currencies = EUR,GBP,CAD,PLN               //Conversion Currency Formats (Remove it to get all conversions)
    & source = USD                               //Source Currency Format (Only USD is available in Free Version)
    & format = 1                                 //JSON Format

Resultant URL - "http://apilayer.net/api/live?access_key=OUR_PRIVATE_KEY&currencies=EUR,GBP,CAD,PLN&source=USD&format=1"
