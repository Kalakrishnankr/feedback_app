package com.example.raghulsn.feedback_new.model;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Log;
import android.view.View;

import com.example.raghulsn.feedback_new.Login;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xml.sax.SAXException;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import javax.net.ssl.HttpsURLConnection;
import javax.xml.parsers.ParserConfigurationException;

/**
 * FactoryClass  is used to handle all the Api's used in the Application
 */
public class FactoryClass {
    private static final String PARTNER_KEY = "X-PartnerKey";
    public String url = "http://192.168.1.156:8091/";
    public String key;
    private Context context;
    static FactoryClass sInstance;

    /*constructor for intialise the class */
    public FactoryClass(Context applicationContext) {

        key = "qagate123";
        sInstance = new FactoryClass();
    }

    private FactoryClass() {
    }

    public static FactoryClass getInstance() {
        return sInstance;
    }


    public ResponseMessage executeRequest(String urlString, String nm, String pass) throws JSONException, IOException, GeneralSecurityException {

        ResponseMessage responseMessage = null;
        String REQUEST;
        String json = "";
        HttpURLConnection conn = null;
        int statusCode;
        InputStream in = null;
        OutputStream os;

        try {
            responseMessage = new ResponseMessage();
            URL httpUrl = new URL(url.concat(urlString));
            conn = (HttpURLConnection) httpUrl.openConnection();
            conn.setReadTimeout(60 * 1000 /*milliseconds*/);
            conn.setConnectTimeout(60 * 1000  /*milliseconds */);
            System.setProperty("http.keepAlive", "false");
            //make some HTTP header nicety
            conn.setRequestProperty("Content-Type", "application/json;charset=utf-8");
            conn.setRequestMethod("GET");

            conn.setRequestProperty(PARTNER_KEY, key);
            conn.setRequestProperty("Authorization", "Basic " + Base64Coder.encodeString(nm + ":" + pass));

            conn.setDoInput(true);
            conn.setDoOutput(false);
            conn.connect();
            statusCode = conn.getResponseCode();
            BufferedReader reader;

            if (statusCode == 200 || statusCode == 201) {

                in = new BufferedInputStream(conn.getInputStream());
                reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
                json = reader.readLine();
                responseMessage.message = json;
                responseMessage.responseCode = statusCode;
            } else {
                responseMessage.message = json;
                responseMessage.responseCode = statusCode;
                in = new BufferedInputStream(conn.getErrorStream());
                reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
                json = reader.readLine();
            }

        } catch (Exception e) {

            if (e.getMessage().contains("authentication challenge")) {
            } else {
                if (conn != null) {
                }
            }

        } finally {
            if (conn != null)
                conn.disconnect();
            if (in != null) {
                in.close();
            }

        }
        return responseMessage;
    }

    public ResponseMessage mail(String urlString, String email) throws JSONException, IOException, GeneralSecurityException {

        ResponseMessage responseMessage = null;
        String REQUEST;
        String json = "";
        HttpURLConnection conn = null;
        int statusCode;
        InputStream in = null;
        OutputStream os;

        try {
            responseMessage = new ResponseMessage();
            URL httpUrl = new URL(url.concat(urlString));
            conn = (HttpURLConnection) httpUrl.openConnection();
            conn.setReadTimeout(60 * 1000 /*milliseconds*/);
            conn.setConnectTimeout(60 * 1000 /* milliseconds */);
            System.setProperty("http.keepAlive", "false");
            conn.setRequestMethod("GET");
            conn.setRequestProperty("mail", email);
            //conn.setRequestProperty("Authorization","Basic "+Base64Coder.encodeString(nm + ":" + pass));

            conn.setDoInput(true);
            conn.setDoOutput(false);
            conn.connect();
            statusCode = conn.getResponseCode();
            BufferedReader reader;

            if (statusCode == 200 || statusCode == 201) {

                in = new BufferedInputStream(conn.getInputStream());
                reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
                json = reader.readLine();
                responseMessage.message = json;
                responseMessage.responseCode = statusCode;
            } else {
                responseMessage.message = json;
                responseMessage.responseCode = statusCode;
                in = new BufferedInputStream(conn.getErrorStream());
                reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
                json = reader.readLine();
            }

        } catch (Exception e) {

            if (e.getMessage().contains("authentication challenge")) {
            } else {
                if (conn != null) {
                }
            }

        } finally {
            if (conn != null)
                conn.disconnect();
            if (in != null) {
                in.close();
            }

        }
        return responseMessage;
    }


    public ResponseMessage changepass(String urlString, String nm, String pass,JSONObject jsonObj) throws JSONException, IOException, GeneralSecurityException {

        ResponseMessage responseMessage = null;
        String REQUEST;
        String message ;
        String json= "";
        HttpURLConnection conn = null;
        int statusCode;
        InputStream in = null;
        OutputStream os;

        try {
            responseMessage = new ResponseMessage();
            message =jsonObj.toString();
            URL httpUrl = new URL(url.concat(urlString));
            conn = (HttpURLConnection) httpUrl.openConnection();
            conn.setReadTimeout(60 * 1000 /*milliseconds*/);
            conn.setConnectTimeout(60 * 1000 /* milliseconds */);
            System.setProperty("http.keepAlive", "false");
            conn.setRequestMethod("POST");
            conn.setRequestProperty(PARTNER_KEY, key);
            //make some HTTP header nicety
            conn.setRequestProperty("Content-Type", "application/json;charset=utf-8");
          //  conn.setRequestProperty("newPassword", newp);
            conn.setRequestProperty("Authorization","Basic "+Base64Coder.encodeString(nm + ":" + pass));

            conn.setDoInput(true);
            conn.setDoOutput(false);
            conn.connect();
            //setup send
            os = new BufferedOutputStream(conn.getOutputStream());
            os.write(message.getBytes());
            //clean up
            os.flush();
            statusCode = conn.getResponseCode();
            BufferedReader reader;

            if (statusCode == 200 || statusCode == 201) {

                in = new BufferedInputStream(conn.getInputStream());
                reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
                json = reader.readLine();
                responseMessage.message = json;
                responseMessage.responseCode = statusCode;
            } else {
                responseMessage.message = json;
                responseMessage.responseCode = statusCode;
                in = new BufferedInputStream(conn.getErrorStream());
                reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
                json = reader.readLine();
            }

        } catch (Exception e) {

            if (e.getMessage().contains("authentication challenge")) {
            } else {
                if (conn != null) {
                }
            }

        } finally {
            if (conn != null)
                conn.disconnect();
            if (in != null) {
                in.close();
            }

        }
        return responseMessage;
    }

    public static class ResponseMessage {
        public int responseCode;
        public String message;


    }
    private String buildURLData(String request, Map<String, String> map) {
        if (!request.contains("?")) {
            request = request + "?format=json";
        }
        if (map != null) {
            // Iterating the LinkedHashMap elements and adding it as url parameters.
            for (Map.Entry<String, String> entry : map.entrySet()) {
                request = request + "&" + entry.getKey() + "=" + entry.getValue();
            }
        }
        return request;
    }

}


