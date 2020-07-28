package common.networkrequestlibrary.interceptor;

import java.util.Map;

/**
 * Created by chasen on 2018/3/13.
 */
@FunctionalInterface
public interface ParamsInterceptor {
    Map checkParams(Map params);
}
