package com.suqareapps.Shopping_Cart;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ServerConnection {
    public static String makeHTTPCall (String path, String parameters) throws ClientProtocolException, IOException {

        HttpPost httppost;
        DefaultHttpClient httpclient;
        ResponseHandler<String> res=new BasicResponseHandler();
        String bytesSent;

        httppost = new HttpPost(path);
        HttpParams params = new BasicHttpParams();

        HttpProtocolParams.setContentCharset(params, "UTF-8");

        httpclient = new DefaultHttpClient(params);

        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
        nameValuePairs.add(new BasicNameValuePair("params", parameters));
        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

        bytesSent = httpclient.execute(httppost, res);

        return bytesSent;
    }
}