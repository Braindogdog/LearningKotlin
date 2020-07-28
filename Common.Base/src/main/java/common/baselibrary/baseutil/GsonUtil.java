package common.baselibrary.baseutil;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * gson 工具类
 */
public class GsonUtil {
    private static Gson mGson;

    static {
        if (mGson == null) {
            mGson = new Gson();
        }
    }

    /**
     * 将 json 数据转化为 bean
     *
     * @param jsonStr json 字符串
     * @param cls     转换成的 bean 类型
     * @param <T>     返回的 bean 类型
     * @return bean
     */
    public static <T> T json2Bean(String jsonStr, Class<T> cls) {
        T t = mGson.fromJson(jsonStr, cls);
        return t;
    }

    public static <T> List<T> json2List(String jsonStr, Class<T> cls) {
        Gson gson = new Gson();
        List<T> list = new ArrayList<T>();
        JsonArray array = new JsonParser().parse(jsonStr).getAsJsonArray();
        for (final JsonElement elem : array) {
            list.add(gson.fromJson(elem, cls));
        }
        return list;
    }

    /**
     * 将对象转换成 string 数据
     *
     * @param obj
     * @return string 数据
     */
    public static String obj2String(Object obj) {
        return createLongGson().toJson(obj);
    }


    public static Gson createLongGson() {
        GsonBuilder gb = new GsonBuilder();
        gb.registerTypeAdapter(java.util.Date.class, new DateSerializer()).setDateFormat(DateFormat.LONG);
        gb.registerTypeAdapter(java.util.Date.class, new DateDeserializer()).setDateFormat(DateFormat.LONG);
        Gson gson = gb.create();
        return gson;
    }

    public static class DateSerializer implements JsonSerializer<Date> {
        public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(src.getTime());
        }
    }

    public static class DateDeserializer implements JsonDeserializer<Date> {

        public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            return new java.util.Date(json.getAsJsonPrimitive().getAsLong());
        }
    }
}
