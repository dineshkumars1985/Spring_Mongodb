package com.rest.app.util;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;
import com.rest.app.dao.IexDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * @author DineshKumar
 */
public class AppUtils {
    private static final Logger logger = LoggerFactory.getLogger(AppUtils.class);

    /**
     *
     * @param pojo
     * @return
     */
  public static DBObject toDBObject(Object pojo) {
    String json = new Gson().toJson(pojo);
    return (DBObject) JSON.parse(json);
  }

    /**
     *
     * @param dbObj
     * @param clazz
     * @return
     */
  public static Object fromDBObject(DBObject dbObj, Class clazz) {
    String json = dbObj.toString();
    return new Gson().fromJson(json, clazz);
  }

    /**
     *
     * @param obj
     * @return
     */
    public static Object objectToJson(Object obj) {
        Gson gson = new Gson();
        return  gson.toJson(obj);
    }

    /**
     *
     * @param jsonVal
     * @param key
     * @return
     */
  public static String getStringFromJson(String jsonVal, String key){
      JsonObject jobj = new Gson().fromJson(jsonVal, JsonObject.class);
      return jobj.get(key).getAsString();
  }

    /**
     *
     * @param ts
     * @return
     */
  public static String changeTsFormate(Timestamp ts){
      String timestamp = null;
      try {
          SimpleDateFormat outputFormat = new SimpleDateFormat("MMM dd, yyyy hh:mm:ss.S a");
          timestamp = outputFormat.format(ts);
      }catch(Exception e){
          logger.error(e.getMessage());
      }
      return timestamp;
  }

    /**
     *
     * @return
     */
  public static Timestamp getCurrentTimeStamp(){
      return new Timestamp(System.currentTimeMillis());
  }

    /**
     *
     * @param url
     * @return
     * @throws Exception
     */
  public static String getHttpResponse(String url) throws Exception{
      URL obj = new URL(url);
      HttpURLConnection con = (HttpURLConnection) obj.openConnection();
      int responseCode = con.getResponseCode();

      BufferedReader in = new BufferedReader(
              new InputStreamReader(con.getInputStream()));
      String inputLine;
      StringBuffer response = new StringBuffer();

      while ((inputLine = in.readLine()) != null) {
        response.append(inputLine);
      }
      in.close();
      return response.toString();
  }
}
