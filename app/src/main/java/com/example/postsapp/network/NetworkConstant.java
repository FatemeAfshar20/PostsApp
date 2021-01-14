package com.example.postsapp.network;

import java.util.HashMap;
import java.util.Map;

public class NetworkConstant {
    public static final String BASE_URL="https://www.flickr.com/services/rest/";
    public static final String POPULAR_METHOD="flickr.photos.getPopular";
    public static final String API_KEY="acec98b624183786c6e026b9ea01d1e1";

    public static final Map<String,String> QUERY_MAP=new HashMap<String, String>(){{
        put("method",POPULAR_METHOD);
        put("api_key",API_KEY);
        put("user_id","124075276@N04");
        put("extras","url_s");
        put("format","json");
        put("nojsoncallback","1");
    }};
}
