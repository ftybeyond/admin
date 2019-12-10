package com.ftysoft.project.face.megvii.component;

import com.alibaba.fastjson.JSON;
import com.ftysoft.project.face.megvii.domain.FaceCommonRsp;
import com.ftysoft.project.face.megvii.domain.FaceDetectRsp;
import com.ftysoft.project.face.megvii.domain.FaceSearchRsp;
import com.ftysoft.project.face.megvii.domain.GroupListRsp;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import sun.misc.BASE64Decoder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Component
public class FaceHttpUtil {

    Logger log = Logger.getLogger(FaceHttpUtil.class.getName());

    @Autowired
    HttpClient httpClient;

    @Value("${busi.url.faceExtract}")
    String extractUrl;

    @Value("${busi.url.faceAdd}")
    String faceAddUrl;

    @Value("${busi.url.faceSearch}")
    String faceSearchUrl;

    @Value("${busi.url.faceDelete}")
    String faceDeleteUrl;

    @Value("${busi.url.faceGroupInit}")
    String groupAddUrl;

    @Value("${busi.url.faceGroupList}")
    String groupListUrl;

    @Value("${busi.url.faceGroupClean}")
    String groupCleanUrl;

    @Value("${busi.url.faceGroupFree}")
    String groupFreeUrl;

    @Value("${busi.url.faceGroupSize}")
    String groupSizeUrl;

    @Value("${busi.face.group}")
    String defaultGroup;

    @Value("${busi.access.API_Key}")
    String api_key;

    @Value("${busi.access.API_Secret}")
    String api_secret;

    @Value("${busi.url.liveness_fmp}")
    String fmp_url;

    @Value("${busi.face.threshold}")
    float threshold;

    public String doPost(String url, String body){
        try {
            HttpPost httpPost = new HttpPost(url);
            httpPost.setHeader("Content-Type","application/json; encoding=utf-8");
            StringEntity se = new StringEntity(body.toString(),"UTF-8");
            se.setContentType("application/json");
            se.setContentEncoding(new BasicHeader("text/json", "application/json"));
            httpPost.setEntity(se);
            HttpResponse response = httpClient.execute(httpPost);
            String responseBody = EntityUtils.toString(response.getEntity());
            return responseBody;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String doPost(String url, HttpEntity entity){
        try {
            HttpPost httpPost = new HttpPost(url);
            httpPost.setEntity(entity);
            HttpResponse response = httpClient.execute(httpPost);
            String responseBody = EntityUtils.toString(response.getEntity());
            return responseBody;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public FaceDetectRsp faceExtract(byte[] file){
        try {
            HttpPost httpPost;
            httpPost = new HttpPost(extractUrl);
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.setCharset(java.nio.charset.Charset.forName("UTF-8"));
            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
            builder.addBinaryBody("image",file,ContentType.MULTIPART_FORM_DATA,"image");
            httpPost.setEntity(builder.build());
            HttpResponse response = httpClient.execute(httpPost);
            String responseBody = EntityUtils.toString(response.getEntity());
            return JSON.parseObject(responseBody, FaceDetectRsp.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public FaceCommonRsp faceAdd(byte[] file,String group, String tag,String id){
        if(file==null||file.length<1024){
            log.info(tag+"文件格式错误!");
            return new FaceCommonRsp("false","文件格式错误!");
        }
        FaceDetectRsp extractRsp = faceExtract(file);
        if(extractRsp.getError()!=null&&extractRsp.getError().length()>0){
            log.info(extractRsp.getError());
            return new FaceCommonRsp("false",extractRsp.getError());
        }else{
            try {
                HttpPost httpPost = new HttpPost(faceAddUrl);
                List<BasicNameValuePair> param=new ArrayList<>();
                param.add(new BasicNameValuePair("groupname", group==null?defaultGroup:group));
                param.add(new BasicNameValuePair("featureid",id));
                param.add(new BasicNameValuePair("feature",extractRsp.getFaces()[0].getFeature()));
                if(tag!=null&&tag.length()>0){
                    param.add(new BasicNameValuePair("tag",tag));
                }
                UrlEncodedFormEntity he=new UrlEncodedFormEntity(param,"UTF-8");
                httpPost.setEntity(he);
                HttpResponse response = httpClient.execute(httpPost);
                String responseBody = EntityUtils.toString(response.getEntity());
                FaceCommonRsp faceCommonRsp = JSON.parseObject(responseBody,FaceCommonRsp.class);
                return faceCommonRsp;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    public FaceCommonRsp faceAdd(byte[] file,String group,String id){
        return faceAdd(file,group,id);
    }

    public FaceCommonRsp faceDelete(String group,String id){
        try {
            HttpPost httpPost = new HttpPost(faceDeleteUrl);
            List<BasicNameValuePair> param=new ArrayList<>();
            param.add(new BasicNameValuePair("groupname", group));
            param.add(new BasicNameValuePair("featureid ", id));
            UrlEncodedFormEntity he=new UrlEncodedFormEntity(param,"UTF-8");
            httpPost.setEntity(he);
            HttpResponse response = httpClient.execute(httpPost);
            String responseBody = EntityUtils.toString(response.getEntity());
            FaceCommonRsp faceCommonRsp = JSON.parseObject(responseBody,FaceCommonRsp.class);
            return faceCommonRsp;
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public FaceCommonRsp faceSearch(byte[] file){
        FaceDetectRsp extractRsp = faceExtract(file);
        if(extractRsp.getError()!=null&&extractRsp.getError().length()>0){
            log.info(extractRsp.getError());
            return new FaceCommonRsp("false",extractRsp.getError());
        }else{
            try {
                HttpPost httpPost = new HttpPost(faceSearchUrl);
                List<BasicNameValuePair> param=new ArrayList<>();
                param.add(new BasicNameValuePair("groupname", defaultGroup));
                param.add(new BasicNameValuePair("limit","1"));
                param.add(new BasicNameValuePair("feature",extractRsp.getFaces()[0].getFeature()));
                UrlEncodedFormEntity he=new UrlEncodedFormEntity(param,"UTF-8");
                httpPost.setEntity(he);
                HttpResponse response = httpClient.execute(httpPost);
                String responseBody = EntityUtils.toString(response.getEntity());
                FaceSearchRsp faceCommonRsp = JSON.parseObject(responseBody,FaceSearchRsp.class);
                return faceCommonRsp;
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    public FaceCommonRsp faceSearch(String base64File){
        try {
            byte[] content = new BASE64Decoder().decodeBuffer(base64File);
//            FileUtils.writeByteArrayToFile(new File("d:/123.jpg"),content);
            return faceSearch(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new FaceCommonRsp("false","base64解析失败!");
    }

    public FaceCommonRsp faceGroupAdd(String group){
        try {
            HttpPost httpPost = new HttpPost(groupAddUrl);
            List<BasicNameValuePair> param=new ArrayList<>();
            param.add(new BasicNameValuePair("groupname", group));
            UrlEncodedFormEntity he=new UrlEncodedFormEntity(param,"UTF-8");
            httpPost.setEntity(he);
            HttpResponse response = httpClient.execute(httpPost);
            String responseBody = EntityUtils.toString(response.getEntity());
            FaceCommonRsp faceCommonRsp = JSON.parseObject(responseBody,FaceCommonRsp.class);
            return faceCommonRsp;
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public FaceCommonRsp faceGroupFree(String group){
        try {
            HttpPost httpPost = new HttpPost(groupFreeUrl);
            List<BasicNameValuePair> param=new ArrayList<>();
            param.add(new BasicNameValuePair("groupname", group));
            UrlEncodedFormEntity he=new UrlEncodedFormEntity(param,"UTF-8");
            httpPost.setEntity(he);
            HttpResponse response = httpClient.execute(httpPost);
            String responseBody = EntityUtils.toString(response.getEntity());
            FaceCommonRsp faceCommonRsp = JSON.parseObject(responseBody,FaceCommonRsp.class);
            return faceCommonRsp;
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public FaceCommonRsp faceGroupClean(String group){
        try {
            HttpPost httpPost = new HttpPost(groupCleanUrl);
            List<BasicNameValuePair> param=new ArrayList<>();
            param.add(new BasicNameValuePair("groupname", group));
            UrlEncodedFormEntity he=new UrlEncodedFormEntity(param,"UTF-8");
            httpPost.setEntity(he);
            HttpResponse response = httpClient.execute(httpPost);
            String responseBody = EntityUtils.toString(response.getEntity());
            FaceCommonRsp faceCommonRsp = JSON.parseObject(responseBody,FaceCommonRsp.class);
            return faceCommonRsp;
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public FaceCommonRsp faceGroupList(){
        try {
            HttpGet httpGet = new HttpGet(groupListUrl);
            HttpResponse response = httpClient.execute(httpGet);
            String responseBody = EntityUtils.toString(response.getEntity());
            FaceCommonRsp faceCommonRsp = JSON.parseObject(responseBody, GroupListRsp.class);
            return faceCommonRsp;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public FaceCommonRsp faceGroupSize(String group){
        try {
            HttpPost httpPost = new HttpPost(groupSizeUrl);
            List<BasicNameValuePair> param=new ArrayList<>();
            param.add(new BasicNameValuePair("groupname", group));
            UrlEncodedFormEntity he=new UrlEncodedFormEntity(param,"UTF-8");
            httpPost.setEntity(he);
            HttpResponse response = httpClient.execute(httpPost);
            String responseBody = EntityUtils.toString(response.getEntity());
            FaceCommonRsp faceCommonRsp = JSON.parseObject(responseBody,FaceCommonRsp.class);
            return faceCommonRsp;
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
