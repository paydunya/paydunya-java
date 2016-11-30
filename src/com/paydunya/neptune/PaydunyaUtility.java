package com.paydunya.neptune;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import tk.json.simple.JSONObject;
import tk.json.simple.parser.ContainerFactory;
import tk.json.simple.parser.JSONParser;
import tk.json.simple.parser.ParseException;

public class PaydunyaUtility {

    PaydunyaSetup setup;

    public PaydunyaUtility(PaydunyaSetup paramPaydunyaSetup) {
        this.setup = paramPaydunyaSetup;
    }

    public JSONObject jsonRequest(String paramString1, String paramString2) {
        String str1 = "";
        try {
            URL localURL = new URL(paramString1);
            HttpURLConnection localHttpURLConnection = (HttpURLConnection) localURL
                    .openConnection();
            localHttpURLConnection.setDoOutput(true);
            localHttpURLConnection.setRequestMethod("POST");
            localHttpURLConnection.setRequestProperty("Content-Type",
                    "application/json");
            localHttpURLConnection.setRequestProperty("User-Agent",
                    "PAYDUNYA Checkout API Java client v1 aka Neptune");
            localHttpURLConnection.setRequestProperty("PAYDUNYA-MASTER-KEY",
                    this.setup.getMasterKey());
            localHttpURLConnection.setRequestProperty("PAYDUNYA-PRIVATE-KEY",
                    this.setup.getPrivateKey());
            localHttpURLConnection.setRequestProperty("PAYDUNYA-PUBLIC-KEY",
                    this.setup.getPublicKey());
            localHttpURLConnection.setRequestProperty("PAYDUNYA-TOKEN",
                    this.setup.getToken());
            localHttpURLConnection.setRequestProperty("PAYDUNYA-MODE",
                    this.setup.getMode());

            OutputStream localOutputStream = localHttpURLConnection
                    .getOutputStream();
            localOutputStream.write(paramString2.getBytes("UTF-8"));
            localOutputStream.flush();

            int i = localHttpURLConnection.getResponseCode();
            if ((i != 201) && (i != 200)) {
                throw new RuntimeException("Failed : HTTP error code : " + i);
            }
            BufferedReader localBufferedReader = new BufferedReader(
                    new InputStreamReader(
                            localHttpURLConnection.getInputStream()));
            String str2;
            while ((str2 = localBufferedReader.readLine()) != null) {
                str1 = str1 + str2;
            }
            localHttpURLConnection.disconnect();
        } catch (MalformedURLException localMalformedURLException) {
            localMalformedURLException.printStackTrace();
        } catch (IOException localIOException) {
            localIOException.printStackTrace();
        }
        return jsonParse(str1);
    }

    public JSONObject getRequest(String paramString) {
        String str1 = "";
        try {
            URL localURL = new URL(paramString);
            HttpURLConnection localHttpURLConnection = (HttpURLConnection) localURL
                    .openConnection();
            localHttpURLConnection.setRequestMethod("GET");
            localHttpURLConnection.setRequestProperty("Content-Type",
                    "application/json");
            localHttpURLConnection.setRequestProperty("User-Agent",
                    "PAYDUNYA Checkout API Java client v1 aka Neptune");
            localHttpURLConnection.setRequestProperty("PAYDUNYA-MASTER-KEY",
                    this.setup.getMasterKey());
            localHttpURLConnection.setRequestProperty("PAYDUNYA-PRIVATE-KEY",
                    this.setup.getPrivateKey());
            localHttpURLConnection.setRequestProperty("PAYDUNYA-PUBLIC-KEY",
                    this.setup.getPublicKey());
            localHttpURLConnection.setRequestProperty("PAYDUNYA-TOKEN",
                    this.setup.getToken());
            localHttpURLConnection.setRequestProperty("PAYDUNYA-MODE",
                    this.setup.getMode());
            if (localHttpURLConnection.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + localHttpURLConnection.getResponseCode());
            }
            BufferedReader localBufferedReader = new BufferedReader(
                    new InputStreamReader(
                            localHttpURLConnection.getInputStream()));
            String str2;
            while ((str2 = localBufferedReader.readLine()) != null) {
                str1 = str1 + str2;
            }
            localHttpURLConnection.disconnect();
        } catch (MalformedURLException localMalformedURLException) {
            localMalformedURLException.printStackTrace();
        } catch (IOException localIOException) {
            localIOException.printStackTrace();
        }
        return jsonParse(str1);
    }

    public static JSONObject jsonParse(String paramString) {
        Object localObject = null;
        try {
            JSONParser localJSONParser = new JSONParser();
            localObject = localJSONParser.parse(paramString);
        } catch (ParseException localParseException) {
            localParseException.printStackTrace();
        }
        return (JSONObject) localObject;
    }

    public JSONObject pushJSON(String paramString) {
        return pushJSONObject(paramString);
    }

    public JSONObject pushJSON(Object paramObject) {
        String str;
        try {
            str = paramObject.toString();
        } catch (NullPointerException localNullPointerException) {
            str = "";
        }
        return pushJSONObject(str);
    }

    public JSONObject pushJSONObject(String paramString) {
        JSONObject localJSONObject = new JSONObject();
        JSONParser localJSONParser = new JSONParser();
        ContainerFactory local1 = new ContainerFactory() {
            public List creatArrayContainer() {
                return new LinkedList();
            }

            public Map createObjectContainer() {
                return new LinkedHashMap();
            }
        };
        try {
            Map localMap = (Map) localJSONParser.parse(paramString, local1);
            Iterator localIterator = localMap.entrySet().iterator();
            while (localIterator.hasNext()) {
                Map.Entry localEntry = (Map.Entry) localIterator.next();
                localJSONObject.put(localEntry.getKey(), localEntry.getValue());
            }
        } catch (ParseException localParseException) {
        } catch (NullPointerException localNullPointerException) {
        }
        return localJSONObject;
    }
}
