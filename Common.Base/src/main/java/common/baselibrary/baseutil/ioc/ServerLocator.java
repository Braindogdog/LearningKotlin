package common.baselibrary.baseutil.ioc;

import android.content.Context;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by chasen on 2018/4/4.
 */

public class ServerLocator {
    private static Map<Object, Object> ioc;

    static {
        ioc = new HashMap<Object, Object>();

    }

    public static void init(Context context, Injector injector)
    {
        if (injector == null)
        {
            throw new IllegalArgumentException("injector can't be null.");
        }

        injector.inject(context, ioc);
    }

    public static <T> T getInstance(Class<?> cls) {

        return (T) ioc.get(cls);
    }

    public static int getIocSize(){
        return ioc == null ? 0 : ioc.size();
    }
}
