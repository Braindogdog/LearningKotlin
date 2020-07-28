package common.baselibrary.baseutil.ioc;

import android.content.Context;

import java.util.Map;

/**
 * Created by chasen on 2018/4/4.
 */

public interface Injector {
    void inject(Context context, Map<Object, Object> ioc);
}
