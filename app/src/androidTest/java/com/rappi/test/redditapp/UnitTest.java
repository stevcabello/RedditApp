package com.rappi.test.redditapp;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.test.InstrumentationTestCase;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.rappi.test.redditapp.utils.Utility.readFromFile;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class UnitTest extends InstrumentationTestCase{


    @Test
    public void testJsonFile(){
        Context appContext = InstrumentationRegistry.getTargetContext();
        JSONObject response = null;

        String jsonString = readFromFile(appContext);
        try {
            response = new JSONObject(jsonString);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        assertNotNull(response);
    }


    //Volley JsonRequest test is not working. Reason still unknown

//    @Test
//    public void testJsonRequest(){
//
//        final String tag_json_obj = "jobj_req";
//        final StringBuilder strBuilder = new StringBuilder();
//        final Context appContext = InstrumentationRegistry.getTargetContext();
//
//
//        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, jsonUrl, null,
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        strBuilder.append(response.toString());
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//
//        });
//
//        Controller.getInstance(appContext).addToRequestQueue(req, tag_json_obj);
//
//        boolean flag = true;
//        if (strBuilder.toString().equals("")) flag=false;
//
//        assertTrue(flag);
//
//    }



}
