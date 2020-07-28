package common.networkrequestlibrary.interfaces;

/**
 * Created by chasen on 2018/3/13.
 */
@FunctionalInterface
public interface Progress {
    void progress(long readLength, long totallength, String fileName);
}
