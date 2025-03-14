package com.example.eyehelp;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadUrl {

    public String retrieveUrl(String urlString) throws IOException {
        HttpURLConnection httpURLConnection = null;
        InputStream inputStream = null;
        BufferedReader bufferedReader = null;
        StringBuilder urlData = new StringBuilder();

        try {
            URL url = new URL(urlString);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.connect();

            if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                inputStream = httpURLConnection.getInputStream();
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    urlData.append(line);
                }
            } else {
                throw new IOException("HTTP error code: " + httpURLConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e("DownloadUrl", "Error retrieving URL: " + e.toString());
            throw e; // rethrowing the exception to indicate failure
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    Log.e("DownloadUrl", "Error closing BufferedReader: " + e.toString());
                }
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    Log.e("DownloadUrl", "Error closing InputStream: " + e.toString());
                }
            }
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
        }
        return urlData.toString();
    }
}
