package com.course.cases;

import com.course.config.TestConfig;
import com.course.model.GetUserInfoCase;
import com.course.model.User;
import com.course.utils.DatabaseUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GetUserInfoTest {

    @Test(dependsOnGroups = "loginTrue",description = "获取userId为1的用户信息")
    public void getUserInfo() throws IOException, InterruptedException {
        SqlSession session= DatabaseUtil.getSqlSession();
        GetUserInfoCase getUserInfoCase = session.selectOne("getUserInfoCase", 1);
        System.out.println(getUserInfoCase);
        System.out.println(TestConfig.getUserInfoUrl);

        //发送请求获取结果
        JSONArray resultJson=getJsonResult(getUserInfoCase);
        System.out.println(resultJson);

        Thread.sleep(3000);

        //验证
        User user=session.selectOne(getUserInfoCase.getExpected(),getUserInfoCase);
        System.out.println(user);

        List userList = new ArrayList();
        userList.add(user);
        System.out.println(11223);
        JSONArray jsonArray = new JSONArray(userList);
        System.out.println(jsonArray);
        JSONArray jsonArray1=new JSONArray(resultJson.getString(0));
        System.out.println(jsonArray1);
        Assert.assertEquals(jsonArray.toString(),jsonArray1.toString());
    }

    private JSONArray getJsonResult(GetUserInfoCase getUserInfoCase) throws IOException {
        HttpPost post=new HttpPost(TestConfig.getUserInfoUrl);
        JSONObject param=new JSONObject();
        param.put("id",getUserInfoCase.getUserId());
        post.setHeader("content-type","application/json");
        StringEntity entity=new StringEntity(param.toString(),"utf-8");
        post.setEntity(entity);
        TestConfig.defaultHttpClient.setCookieStore(TestConfig.store);

        String result;
        HttpResponse response=TestConfig.defaultHttpClient.execute(post);
        result= EntityUtils.toString(response.getEntity(),"utf-8");
        List resultList= Arrays.asList(result);
        JSONArray array=new JSONArray(resultList);

        return array;
    }
}
