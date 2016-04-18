package com.qt.utils;


import com.qt.menu.Button;
import com.qt.menu.ClickButton;
import com.qt.menu.Menu;
import com.qt.menu.ViewButton;
import com.qt.model.AccessToken;
import com.qt.model.OauthToken;
import com.qt.model.UserInfo;
import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EncodingUtils;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

/**
 * Created by defore on 16/4/3.
 */
public class WeixinUtil {
    private static String APPID = "wx4ba284f4acd4e734";
    private static String APPSECRET = "63292c808d19cabcda84ec7c8969ac6d";

    //获取access_token的接口
    private static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
    //文件上传的接口
    private static final String UPLOAD_URL = "https://api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE";
    //创建菜单接口
    private static final String CREATE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";

    //1.用户同意授权，获取code
    private static final String OAUTH_OPENID_URL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";
    //2.通过code换取网页授权access_token
    private static final String OAUTH_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";

    //3.获取用户信息接口
    private static final String GET_USER_INFO_URL = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";

    /**
     * get请求
     * @param url
     * @return
     */
    public static JSONObject doGetStr(String url) {
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(url);
        JSONObject jsonObject = null;
        try {
            HttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String result = EntityUtils.toString(entity, "UTF-8");
                jsonObject = JSONObject.fromObject(result);
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return jsonObject;
    }

    /**
     * post请求
     * @param url
     * @param outStr
     * @return
     */
    public static JSONObject doPostStr(String url, String outStr) {
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(url);
        JSONObject jsonObject = null;
        try {
            httpPost.setEntity(new StringEntity(outStr, "UTF-8"));
            HttpResponse response = httpClient.execute(httpPost);
            String result = EntityUtils.toString(response.getEntity(), "UTF-8");
            jsonObject = JSONObject.fromObject(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    /**
     * 获取access_token
     * @return
     */
    public static AccessToken getAccessToken () {
        AccessToken token = new AccessToken();
        String url = ACCESS_TOKEN_URL.replace("APPID", APPID).replace("APPSECRET", APPSECRET);
        JSONObject jsonObject = doGetStr(url);
        if (jsonObject != null) {
            token.setToken(jsonObject.getString("access_token"));
            token.setExpiresIn(jsonObject.getInt("expires_in"));
        }
        return token;
    }

    /**
     * 文件上传
     * @param filePath
     * @param accessToken
     * @param type
     * @return
     * @throws IOException
     * @throws NoSuchAlgorithmException
     * @throws NoSuchProviderException
     * @throws KeyManagementException
     */
    public static String upload(String filePath, String accessToken,String type) throws IOException, NoSuchAlgorithmException, NoSuchProviderException, KeyManagementException {
        File file = new File(filePath);
        if (!file.exists() || !file.isFile()) {
            throw new IOException("文件不存在");
        }

        String url = UPLOAD_URL.replace("ACCESS_TOKEN", accessToken).replace("TYPE",type);

        URL urlObj = new URL(url);

        HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();

        con.setRequestMethod("POST");
        con.setDoInput(true);
        con.setDoOutput(true);
        con.setUseCaches(false);

        con.setRequestProperty("Connection", "Keep-Alive");
        con.setRequestProperty("Charset", "UTF-8");


        String BOUNDARY = "----------" + System.currentTimeMillis();
        con.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);

        StringBuilder sb = new StringBuilder();
        sb.append("--");
        sb.append(BOUNDARY);
        sb.append("\r\n");
        sb.append("Content-Disposition: form-data;name=\"file\";filename=\"" + file.getName() + "\"\r\n");
        sb.append("Content-Type:application/octet-stream\r\n\r\n");

        byte[] head = sb.toString().getBytes("utf-8");

        OutputStream out = new DataOutputStream(con.getOutputStream());

        out.write(head);


        DataInputStream in = new DataInputStream(new FileInputStream(file));
        int bytes = 0;
        byte[] bufferOut = new byte[1024];
        while ((bytes = in.read(bufferOut)) != -1) {
            out.write(bufferOut, 0, bytes);
        }
        in.close();


        byte[] foot = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("utf-8");

        out.write(foot);

        out.flush();
        out.close();

        StringBuffer buffer = new StringBuffer();
        BufferedReader reader = null;
        String result = null;
        try {
            reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String line = null;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            if (result == null) {
                result = buffer.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                reader.close();
            }
        }

        JSONObject jsonObj = JSONObject.fromObject(result);
        System.out.println(jsonObj);
        String typeName = "media_id";
        if(!"image".equals(type)){
            typeName = type + "_media_id";
        }
        String mediaId = jsonObj.getString(typeName);
        return mediaId;
    }

    //定义菜单

    /**
     * 组装菜单
     * @return
     */

    public static Menu initMenu() throws UnsupportedEncodingException {
        Menu menu = new Menu();
        //主菜单1
        ViewButton viewButton1 = new ViewButton();
        viewButton1.setName("全通食堂");
        viewButton1.setType("view");
        //redirect_url为要跳入第三方的网址,点餐首页
        String redirect_url = "http://sanjin.ngrok.cc/weixin/weixin/test";
        String encodeUrl = URLEncoder.encode(redirect_url, "UTF-8");
        String url = OAUTH_OPENID_URL.replace("APPID", APPID).replace("REDIRECT_URI", encodeUrl).replace("SCOPE", "snsapi_userinfo").replace("STATE", "1");
        viewButton1.setUrl(url);

        //主菜单2
        ViewButton viewButton2 = new ViewButton();
        viewButton2.setName("我的点餐");
        viewButton2.setType("view");
        //redirect_url为要跳入第三方的网址,查询我的点餐页面
        String redirect_url2 = "http://sanjin.ngrok.cc/weixin/weixin/myLunch";
        String encodeUrl2 = URLEncoder.encode(redirect_url2, "UTF-8");
        String url2 = OAUTH_OPENID_URL.replace("APPID", APPID).replace("REDIRECT_URI", encodeUrl2).replace("SCOPE", "snsapi_userinfo").replace("STATE", "2");
        viewButton2.setUrl(url2);

        //子菜单31
        ClickButton clickButton31 = new ClickButton();
        clickButton31.setName("提示信息");
        clickButton31.setType("click");
        clickButton31.setKey("31");

        //子菜单32
        ClickButton clickButton32 = new ClickButton();
        clickButton32.setName("用户地址");
        clickButton32.setType("location_select");
        clickButton32.setKey("32");

        //子菜单31
        ClickButton clickButton33 = new ClickButton();
        clickButton33.setName("扫描事件");
        clickButton33.setType("scancode_push");
        clickButton33.setKey("33");

        //主菜单3
        Button button3 = new Button();
        button3.setName("测试菜单");
        button3.setSub_button(new Button[]{clickButton31, clickButton32, clickButton33});

        //创建菜单
        menu.setButton(new Button[]{viewButton1, viewButton2, button3});
        return menu;
    }


    /*
    public static Menu initMenu() throws UnsupportedEncodingException {
        Menu menu = new Menu();
        //主菜单1
        ClickButton button11 = new ClickButton();
        button11.setName("click菜单");
        button11.setType("click");
        button11.setKey("11");

        //主菜单2
        ViewButton button21 = new ViewButton();
        button21.setName("授权菜单5");
        button21.setType("view");
        //前后端不分离
        String redirect_url = "http://defore.ngrok.cc/weixin/test";
        //前后端分离
//        String redirect_url = "http://defore.ngrok.cc/front";

        String encodeUrl = URLEncoder.encode(redirect_url ,"UTF-8");

        String url = OAUTH_OPENID_URL.replace("APPID", APPID).replace("REDIRECT_URI", encodeUrl).replace("SCOPE", "snsapi_userinfo").replace("STATE", "1");

        button21.setUrl(url);

        //子菜单31
        ClickButton button31 = new ClickButton();
        button31.setName("扫描事件");
        button31.setType("scancode_push");
        button31.setKey("31");

        //子菜单32
        ClickButton button32 = new ClickButton();
        button32.setName("地理位置");
        button32.setType("location_select");
        button32.setKey("32");

        //主菜单3
        Button button3 = new Button();
        button3.setName("菜单3");
        button3.setSub_button(new Button[]{button31, button32});

        menu.setButton(new Button[]{button11, button21, button3});
        return menu;
    }
    */

    //创建菜单
    public static int createMenu(String token, String menu) throws ParseException, IOException {
        int result = 0;
        String url = CREATE_MENU_URL.replace("ACCESS_TOKEN", token);
        JSONObject jsonObject = doPostStr(url, menu);
        if (jsonObject != null) {
            result = jsonObject.getInt("errcode");
        }
        return result;
    }

    /**
     * 1.授权获取code
     * @return
     */
    //OAUTH_OPENID_URL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";
    public static String getCode() {
        String code = null;
        String url = OAUTH_OPENID_URL.replace("APPID", APPID).replace("REDIRECT_URI", "http://defore.ngrok.cc/weixin/test").replace("SCOPE", "snsapi_userinfo").replace("STATE","123");
        JSONObject jsonObject = doGetStr(url);
        if (jsonObject != null) {
            code = jsonObject.getString("code");
        }
        return code;
    }

    /**
     * 2.授权登陆
     * @return
     */
    //https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code
    public static OauthToken getOauthToken(String code) {
        OauthToken oauthToken = new OauthToken();
        String url = OAUTH_TOKEN_URL.replace("APPID", APPID).replace("SECRET", APPSECRET).replace("CODE", code);
        JSONObject jsonObject = doGetStr(url);
        if (jsonObject != null) {
            oauthToken.setAccess_token(jsonObject.getString("access_token"));
            oauthToken.setExpires_in(jsonObject.getInt("expires_in"));
            oauthToken.setRefresh_token(jsonObject.getString("refresh_token"));
            oauthToken.setOpenid(jsonObject.getString("openid"));
            oauthToken.setScope(jsonObject.getString("scope"));
        }
        return oauthToken;
    }

    /**
     * 3.获取用户信息
     * @param token
     * @param openId
     * @return
     * @throws ParseException
     * @throws IOException
     */
    public static UserInfo getUserInfo(String token, String openId) throws ParseException, IOException {
        UserInfo user = new UserInfo();
        String url = GET_USER_INFO_URL.replace("ACCESS_TOKEN", token).replace("OPENID", openId);
        JSONObject jsonObject = doGetStr(url);
        System.out.println(jsonObject);
        if (jsonObject != null) {
            user.setOpenid(jsonObject.getString("openid"));
            user.setNickname(jsonObject.getString("nickname"));
            user.setSex(jsonObject.getInt("sex"));
            user.setCity(jsonObject.getString("city"));
            user.setCountry(jsonObject.getString("country"));
            user.setProvince(jsonObject.getString("province"));
            user.setHeadimgurl(jsonObject.getString("headimgurl"));
            user.setLanguage(jsonObject.getString("language"));
//            user.setPrivilege(["", ""]);
        }
        return user;
    }
}
