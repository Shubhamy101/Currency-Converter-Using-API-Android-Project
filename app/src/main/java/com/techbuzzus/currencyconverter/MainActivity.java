package com.techbuzzus.currencyconverter;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    String[] currency = {"USD", "EUR", "GBP", "INR", "BTC", "AED", "CHF", "JPY", "RUB", "ZAR"};

    String[] country = {"US Dollar", "Euro", "British Pound", "Indian Rupee", "BitCoin", "Emirati Dirham",
                        "Swiss Franc", "Japanese Yen", "Russian Ruble", "South African Rand"};

    Integer[] icons = {R.mipmap.usd, R.mipmap.euro, R.mipmap.gbp, R.mipmap.inr, R.mipmap.btc, R.mipmap.aed, R.mipmap.chf,
                        R.mipmap.jpy, R.mipmap.rub, R.mipmap.zar};

    Spinner spinner1, spinner2;
    Button b1;
    EditText e1;

    public static ExchangeValues values;
    double[] valuesList = new double[10];
    String From_To;
    double selectedValue;

    char warning = '\u26A0';         // Warning Unicode Character
    int i = 0;

    public static final String Json_URL_USD = "http://apilayer.net/api/live?"  +  "access_key=2d636eb3d762409ce469bb7887c19f6e"+
                                                "&source=USD"  +  "&format=1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Will Disable Screen Rotation
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        StringRequest stringRequest = new StringRequest(Json_URL_USD, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {

                    JSONObject object = new JSONObject(response);               // Getting Whole Object from response
                    JSONObject jsonObject = object.getJSONObject("quotes");     // Getting object quotes from previous created JSON object

                    valuesList[i++] = jsonObject.getDouble("USDEUR");   //0
                    valuesList[i++] = jsonObject.getDouble("USDGBP");   //1
                    valuesList[i++] = jsonObject.getDouble("USDINR");   //2
                    valuesList[i++] = jsonObject.getDouble("USDBTC");   //3
                    valuesList[i++] = jsonObject.getDouble("USDAED");   //4
                    valuesList[i++] = jsonObject.getDouble("USDCHF");   //5
                    valuesList[i++] = jsonObject.getDouble("USDJPY");   //6
                    valuesList[i++] = jsonObject.getDouble("USDRUB");   //7
                    valuesList[i++] = jsonObject.getDouble("USDZAR");   //8

                    values = new ExchangeValues(valuesList[0],valuesList[1],valuesList[2],valuesList[3],valuesList[4],
                            valuesList[5],valuesList[6],valuesList[7],valuesList[8],1 / valuesList[0], valuesList[1] / valuesList[0],
                            valuesList[2] / valuesList[0], valuesList[3] / valuesList[0], valuesList[4] / valuesList[0],
                            valuesList[5] / valuesList[0], valuesList[6] / valuesList[0], valuesList[7] / valuesList[0],
                            valuesList[8] / valuesList[0], 1 / valuesList[1], valuesList[0] / valuesList[1],
                            valuesList[2] / valuesList[1], valuesList[3] / valuesList[1], valuesList[4] / valuesList[1],
                            valuesList[5] / valuesList[1], valuesList[6] / valuesList[1], valuesList[7] / valuesList[1],
                            valuesList[8] / valuesList[1], 1 / valuesList[2], valuesList[0] / valuesList[2],
                            valuesList[1] / valuesList[2], valuesList[3] / valuesList[2], valuesList[4] / valuesList[2],
                            valuesList[5] / valuesList[2], valuesList[6] / valuesList[2], valuesList[7] / valuesList[2],
                            valuesList[8] / valuesList[2], 1 / valuesList[3], valuesList[0] / valuesList[3],
                            valuesList[1] / valuesList[3], valuesList[2] / valuesList[3], valuesList[4] / valuesList[3],
                            valuesList[5] / valuesList[3], valuesList[6] / valuesList[3], valuesList[7] / valuesList[3],
                            valuesList[8] / valuesList[3],1 / valuesList[4], valuesList[0] / valuesList[4],
                            valuesList[1] / valuesList[4], valuesList[2] / valuesList[4], valuesList[3] / valuesList[4],
                            valuesList[5] / valuesList[4], valuesList[6] / valuesList[4], valuesList[7] / valuesList[4],
                            valuesList[8] / valuesList[4], 1 / valuesList[5], valuesList[0] / valuesList[5],
                            valuesList[1] / valuesList[5], valuesList[2] / valuesList[5], valuesList[3] / valuesList[5],
                            valuesList[4] / valuesList[5], valuesList[6] / valuesList[5], valuesList[7] / valuesList[5],
                            valuesList[8] / valuesList[5],1 / valuesList[6], valuesList[0] / valuesList[6],
                            valuesList[1] / valuesList[6], valuesList[2] / valuesList[6], valuesList[3] / valuesList[6],
                            valuesList[4] / valuesList[6], valuesList[5] / valuesList[6], valuesList[7] / valuesList[6],
                            valuesList[8] / valuesList[6],1 / valuesList[7], valuesList[0] / valuesList[7],
                            valuesList[1] / valuesList[7], valuesList[2] / valuesList[7], valuesList[3] / valuesList[7],
                            valuesList[4] / valuesList[7], valuesList[5] / valuesList[7], valuesList[6] / valuesList[7],
                            valuesList[8] / valuesList[7],1 / valuesList[8], valuesList[0] / valuesList[8],
                            valuesList[1] / valuesList[8], valuesList[2] / valuesList[8], valuesList[3] / valuesList[8],
                            valuesList[4] / valuesList[8], valuesList[5] / valuesList[8], valuesList[6] / valuesList[8],
                            valuesList[7] / valuesList[8]);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("  Connection Failed");
                builder.setMessage("Please Make Sure that your Device is Connected to Internet.");
                builder.setCancelable(false);
                builder.setIcon(R.drawable.disconnected);
                builder.setPositiveButton('\u274C' + "  Close App", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                        System.exit(0);
                    }
                });


                AlertDialog alertDialog = builder.create();
                alertDialog.show();

            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(stringRequest);

        //---------------------------------------------------------------------------------------------------------------------------------------//

        b1 = findViewById(R.id.b1);
        e1 = findViewById(R.id.e1);
        spinner1 = findViewById(R.id.s1);
        spinner2 = findViewById(R.id.s2);

        spinner1.setOnItemSelectedListener(this);
        spinner2.setOnItemSelectedListener(this);

        AdapterClass adapter = new AdapterClass(this, currency, country, icons);

        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner1.setAdapter(adapter);

        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner2.setAdapter(adapter);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (From_To) {
                    //Cases For USD Conversions
                    case "USD_TO_EUR":
                        selectedValue = values.USD_TO_EUR;
                        break;
                    case "USD_TO_GBP":
                        selectedValue = values.USD_TO_GBP;
                        break;
                    case "USD_TO_INR":
                        selectedValue = values.USD_TO_INR;
                        break;
                    case "USD_TO_BTC":
                        selectedValue = values.USD_TO_BTC;
                        break;
                    case "USD_TO_AED":
                        selectedValue = values.USD_TO_AED;
                        break;
                    case "USD_TO_CHF":
                        selectedValue = values.USD_TO_CHF;
                        break;
                    case "USD_TO_JPY":
                        selectedValue = values.USD_TO_JPY;
                        break;
                    case "USD_TO_RUB":
                        selectedValue = values.USD_TO_RUB;
                        break;
                    case "USD_TO_ZAR":
                        selectedValue = values.USD_TO_ZAR;
                        break;

                    //Cases For EUR Conversions
                    case "EUR_TO_USD":
                        selectedValue = values.EUR_TO_USD;
                        break;
                    case "EUR_TO_GBP":
                        selectedValue = values.EUR_TO_GBP;
                        break;
                    case "EUR_TO_INR":
                        selectedValue = values.EUR_TO_INR;
                        break;
                    case "EUR_TO_BTC":
                        selectedValue = values.EUR_TO_BTC;
                        break;
                    case "EUR_TO_AED":
                        selectedValue = values.EUR_TO_AED;
                        break;
                    case "EUR_TO_CHF":
                        selectedValue = values.EUR_TO_CHF;
                        break;
                    case "EUR_TO_JPY":
                        selectedValue = values.EUR_TO_JPY;
                        break;
                    case "EUR_TO_RUB":
                        selectedValue = values.EUR_TO_RUB;
                        break;
                    case "EUR_TO_ZAR":
                        selectedValue = values.EUR_TO_ZAR;
                        break;

                    //Cases For GBP Conversions
                    case "GBP_TO_USD":
                        selectedValue = values.GBP_TO_USD;
                        break;
                    case "GBP_TO_EUR":
                        selectedValue = values.GBP_TO_EUR;
                        break;
                    case "GBP_TO_INR":
                        selectedValue = values.GBP_TO_INR;
                        break;
                    case "GBP_TO_BTC":
                        selectedValue = values.GBP_TO_BTC;
                        break;
                    case "GBP_TO_AED":
                        selectedValue = values.GBP_TO_AED;
                        break;
                    case "GBP_TO_CHF":
                        selectedValue = values.GBP_TO_CHF;
                        break;
                    case "GBP_TO_JPY":
                        selectedValue = values.GBP_TO_JPY;
                        break;
                    case "GBP_TO_RUB":
                        selectedValue = values.GBP_TO_RUB;
                        break;
                    case "GBP_TO_ZAR":
                        selectedValue = values.GBP_TO_ZAR;
                        break;

                    //Cases For INR Conversions
                    case "INR_TO_USD":
                        selectedValue = values.INR_TO_USD;
                        break;
                    case "INR_TO_EUR":
                        selectedValue = values.INR_TO_EUR;
                        break;
                    case "INR_TO_GBP":
                        selectedValue = values.INR_TO_GBP;
                        break;
                    case "INR_TO_BTC":
                        selectedValue = values.INR_TO_BTC;
                        break;
                    case "INR_TO_AED":
                        selectedValue = values.INR_TO_AED;
                        break;
                    case "INR_TO_CHF":
                        selectedValue = values.INR_TO_CHF;
                        break;
                    case "INR_TO_JPY":
                        selectedValue = values.INR_TO_JPY;
                        break;
                    case "INR_TO_RUB":
                        selectedValue = values.INR_TO_RUB;
                        break;
                    case "INR_TO_ZAR":
                        selectedValue = values.INR_TO_ZAR;
                        break;

                    //Cases For BTC Conversions
                    case "BTC_TO_USD":
                        selectedValue = values.BTC_TO_USD;
                        break;
                    case "BTC_TO_EUR":
                        selectedValue = values.BTC_TO_EUR;
                        break;
                    case "BTC_TO_GBP":
                        selectedValue = values.BTC_TO_GBP;
                        break;
                    case "BTC_TO_INR":
                        selectedValue = values.BTC_TO_INR;
                        break;
                    case "BTC_TO_AED":
                        selectedValue = values.BTC_TO_AED;
                        break;
                    case "BTC_TO_CHF":
                        selectedValue = values.BTC_TO_CHF;
                        break;
                    case "BTC_TO_JPY":
                        selectedValue = values.BTC_TO_JPY;
                        break;
                    case "BTC_TO_RUB":
                        selectedValue = values.BTC_TO_RUB;
                        break;
                    case "BTC_TO_ZAR":
                        selectedValue = values.BTC_TO_ZAR;
                        break;

                    //Cases For AED Conversions
                    case "AED_TO_USD":
                        selectedValue = values.AED_TO_USD;
                        break;
                    case "AED_TO_EUR":
                        selectedValue = values.AED_TO_EUR;
                        break;
                    case "AED_TO_GBP":
                        selectedValue = values.AED_TO_GBP;
                        break;
                    case "AED_TO_INR":
                        selectedValue = values.AED_TO_INR;
                        break;
                    case "AED_TO_BTC":
                        selectedValue = values.AED_TO_BTC;
                        break;
                    case "AED_TO_CHF":
                        selectedValue = values.AED_TO_CHF;
                        break;
                    case "AED_TO_JPY":
                        selectedValue = values.AED_TO_JPY;
                        break;
                    case "AED_TO_RUB":
                        selectedValue = values.AED_TO_RUB;
                        break;
                    case "AED_TO_ZAR":
                        selectedValue = values.AED_TO_ZAR;
                        break;

                    //Cases For CHF Conversions
                    case "CHF_TO_USD":
                        selectedValue = values.CHF_TO_USD;
                        break;
                    case "CHF_TO_EUR":
                        selectedValue = values.CHF_TO_EUR;
                        break;
                    case "CHF_TO_GBP":
                        selectedValue = values.CHF_TO_GBP;
                        break;
                    case "CHF_TO_INR":
                        selectedValue = values.CHF_TO_INR;
                        break;
                    case "CHF_TO_BTC":
                        selectedValue = values.CHF_TO_BTC;
                        break;
                    case "CHF_TO_AED":
                        selectedValue = values.CHF_TO_AED;
                        break;
                    case "CHF_TO_JPY":
                        selectedValue = values.CHF_TO_JPY;
                        break;
                    case "CHF_TO_RUB":
                        selectedValue = values.CHF_TO_RUB;
                        break;
                    case "CHF_TO_ZAR":
                        selectedValue = values.CHF_TO_ZAR;
                        break;

                    //Cases For JPY Conversions
                    case "JPY_TO_USD":
                        selectedValue = values.JPY_TO_USD;
                        break;
                    case "JPY_TO_EUR":
                        selectedValue = values.JPY_TO_EUR;
                        break;
                    case "JPY_TO_GBP":
                        selectedValue = values.JPY_TO_GBP;
                        break;
                    case "JPY_TO_INR":
                        selectedValue = values.JPY_TO_INR;
                        break;
                    case "JPY_TO_BTC":
                        selectedValue = values.JPY_TO_BTC;
                        break;
                    case "JPY_TO_AED":
                        selectedValue = values.JPY_TO_AED;
                        break;
                    case "JPY_TO_CHF":
                        selectedValue = values.JPY_TO_CHF;
                        break;
                    case "JPY_TO_RUB":
                        selectedValue = values.JPY_TO_RUB;
                        break;
                    case "JPY_TO_ZAR":
                        selectedValue = values.JPY_TO_ZAR;
                        break;

                    //Cases For RUB Conversions
                    case "RUB_TO_USD":
                        selectedValue = values.RUB_TO_USD;
                        break;
                    case "RUB_TO_EUR":
                        selectedValue = values.RUB_TO_EUR;
                        break;
                    case "RUB_TO_GBP":
                        selectedValue = values.RUB_TO_GBP;
                        break;
                    case "RUB_TO_INR":
                        selectedValue = values.RUB_TO_INR;
                        break;
                    case "RUB_TO_BTC":
                        selectedValue = values.RUB_TO_BTC;
                        break;
                    case "RUB_TO_AED":
                        selectedValue = values.RUB_TO_AED;
                        break;
                    case "RUB_TO_CHF":
                        selectedValue = values.RUB_TO_CHF;
                        break;
                    case "RUB_TO_JPY":
                        selectedValue = values.RUB_TO_JPY;
                        break;
                    case "RUB_TO_ZAR":
                        selectedValue = values.RUB_TO_ZAR;
                        break;

                    //Cases For ZAR Conversions
                    case "ZAR_TO_USD":
                        selectedValue = values.ZAR_TO_USD;
                        break;
                    case "ZAR_TO_EUR":
                        selectedValue = values.ZAR_TO_EUR;
                        break;
                    case "ZAR_TO_GBP":
                        selectedValue = values.ZAR_TO_GBP;
                        break;
                    case "ZAR_TO_INR":
                        selectedValue = values.ZAR_TO_INR;
                        break;
                    case "ZAR_TO_BTC":
                        selectedValue = values.ZAR_TO_BTC;
                        break;
                    case "ZAR_TO_AED":
                        selectedValue = values.ZAR_TO_AED;
                        break;
                    case "ZAR_TO_CHF":
                        selectedValue = values.ZAR_TO_CHF;
                        break;
                    case "ZAR_TO_JPY":
                        selectedValue = values.ZAR_TO_JPY;
                        break;
                    case "ZAR_TO_RUB":
                        selectedValue = values.ZAR_TO_RUB;
                        break;

                    //Cases For SAME_TO_SAME Conversions
                    default:
                        selectedValue = -1;
                        break;
                }

                if (e1.getText().length() <= 0) {
                    Toast.makeText(MainActivity.this, warning + "  Amount Field is Empty", Toast.LENGTH_SHORT).show();
                }else {

                    if (selectedValue < 0) {
                        Toast.makeText(MainActivity.this, warning + "  Base And Conversion Formats Are Same", Toast.LENGTH_SHORT).show();
                        selectedValue = 1;
                    }

                    selectedValue = Double.parseDouble(e1.getText().toString()) * selectedValue;
                    String result = e1.getText().toString() + "  " + currency[spinner1.getSelectedItemPosition()] + "  =  " + (float) selectedValue + "  " + currency[spinner2.getSelectedItemPosition()];

                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle(result);
                    builder.setCancelable(true);
                    builder.setIcon(R.mipmap.coin);
                    builder.setPositiveButton('\u274C' + "  Close", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });

                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();

                }
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        From_To = currency[spinner1.getSelectedItemPosition()] + "_TO_" + currency[spinner2.getSelectedItemPosition()];
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Toast.makeText(this, warning + "  Please Select a Currency Formats From list !!", Toast.LENGTH_SHORT).show();
    }
}