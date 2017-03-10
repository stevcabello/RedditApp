package com.rappi.test.redditapp.businessobject;

import android.app.ProgressDialog;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.rappi.test.redditapp.fragments.FeedFragment;
import com.rappi.test.redditapp.models.Feed;
import com.rappi.test.redditapp.networking.Controller;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.rappi.test.redditapp.utils.Globals.jsonUrl;
import static com.rappi.test.redditapp.utils.Utility.handleJsonObject;
import static com.rappi.test.redditapp.utils.Utility.isInternetAvailable;
import static com.rappi.test.redditapp.utils.Utility.readFromFile;
import static com.rappi.test.redditapp.utils.Utility.writeToFile;

/**
 * Class that handles the business logic of retrieving feed data
 */

public class boFeed {
    private static String TAG = boFeed.class.getSimpleName();
    private String tag_json_obj = "jobj_req";

    ProgressDialog pDialog;



    public void getFeedData(final FeedFragment feedFragment){

        pDialog = new ProgressDialog(feedFragment.getActivity());
        pDialog.setMessage("Loading topics...");
        pDialog.show();

        final ArrayList<Feed> feeds = new ArrayList<>();

        if (isInternetAvailable()) { //online mode
            JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, jsonUrl, null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                            //save the json response to a file
                            writeToFile(response.toString(), feedFragment.getContext());

                            handleJsonObject(response,feedFragment,feeds,pDialog);
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.i(TAG, error.getMessage());

                }

            });

            Controller.getInstance(feedFragment.getActivity()).addToRequestQueue(req, tag_json_obj);

        }else{ //offline mode

            String jsonString = readFromFile(feedFragment.getContext());
            try {
                JSONObject response = new JSONObject(jsonString);
                handleJsonObject(response,feedFragment,feeds,pDialog);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

    }




}
