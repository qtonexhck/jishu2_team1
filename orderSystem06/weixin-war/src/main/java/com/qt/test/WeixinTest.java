package com.qt.test;

/**
 * Created by defore on 16/4/3.
 */
import com.qt.model.AccessToken;
import com.qt.model.OauthToken;
import com.qt.model.UserInfo;
import com.qt.utils.WeixinUtil;
import net.sf.json.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class WeixinTest {
    public static void main(String[] args) {
        try {

//            测试获取access_token
            AccessToken token = WeixinUtil.getAccessToken();
            System.out.println("票据"+token.getToken());
            System.out.println("有效时间"+token.getExpiresIn());

            String menu = JSONObject.fromObject(WeixinUtil.initMenu()).toString();
            int result = WeixinUtil.createMenu(token.getToken(), menu);
            if (result == 0) {
                System.out.println("创建菜单成功");
            } else {
                System.out.println("错误码: "+ result);
            }
/*
            //测试上传文件
            //String path ="/Users/defore/Documents/photo/1.jpg";
            //String mediaId = WeixinUtil.upload(path, token.getToken(), "image");
            //System.out.println(mediaId);


            String menu = JSONObject.fromObject(WeixinUtil.initMenu()).toString();
            int result = WeixinUtil.createMenu(token.getToken(), menu);
            if (result == 0) {
                System.out.println("创建菜单成功");
            } else {
                System.out.println("错误码: "+ result);
            }


//            String result = WeixinUtil.translate("my name is laobi");
//            //String result = WeixinUtil.translateFull("");
//            System.out.println(result);

            */



        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
