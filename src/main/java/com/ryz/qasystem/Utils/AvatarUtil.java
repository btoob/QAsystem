package com.ryz.qasystem.Utils;

import okhttp3.*;
import org.springframework.stereotype.Component;

@Component
public class AvatarUtil {
    public String getAvatarData(){
        OkHttpClient client = new OkHttpClient.Builder().build();
        Request request = new Request.Builder()
                .url("http://bing.ioliu.cn/v1/rand?w=240&h=320&type=json")
                .get()
                .addHeader("User-Agent","Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:0.9.4)")
                .build();
        try (Response response = client.newCall(request).execute()) {
            assert response.body() != null;
            String string = response.body().string();
            return string.split("\"url\":\"")[1].split("\",\"bmiddle_pic\"")[0];
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
