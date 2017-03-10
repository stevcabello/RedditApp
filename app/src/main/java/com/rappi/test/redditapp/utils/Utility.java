package com.rappi.test.redditapp.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import com.rappi.test.redditapp.fragments.FeedFragment;
import com.rappi.test.redditapp.models.Feed;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import static com.rappi.test.redditapp.utils.Globals.jsonFile;


public class Utility {

    public static void handleJsonObject(JSONObject response, FeedFragment feedFragment, ArrayList<Feed> feeds , ProgressDialog pDialog){
        try {
            JSONArray array = response.getJSONObject("data").getJSONArray("children");

            for (int i = 0; i < array.length(); i++) {
                String title = array.getJSONObject(i).getJSONObject("data").getString("title");
                String user = array.getJSONObject(i).getJSONObject("data").getString("display_name");
                int subscribers = array.getJSONObject(i).getJSONObject("data").getInt("subscribers");
                int comments = array.getJSONObject(i).getJSONObject("data").getInt("comment_score_hide_mins");
                String detail = array.getJSONObject(i).getJSONObject("data").getString("description_html");

                feeds.add(new Feed(title, user,subscribers,comments,detail));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        feedFragment.addFeedData(feeds);

        if (pDialog!=null) pDialog.dismiss();
    }

    public static boolean isInternetAvailable()  {
        String command = "ping -c 1 google.com";
        try {
            return (Runtime.getRuntime().exec(command).waitFor()==0);
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void writeToFile(String data, Context context) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput(jsonFile, Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }


    public static String readFromFile(Context context) {

        String ret = "";

        try {
            InputStream inputStream = context.openFileInput(jsonFile);

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                ret = stringBuilder.toString();
            }
        }
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }

        return ret;
    }
}
