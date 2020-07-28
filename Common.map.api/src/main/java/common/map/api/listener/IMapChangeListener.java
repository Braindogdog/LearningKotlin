package common.map.api.listener;

/**
 * Created by zhangmh on 2018/3/12.
 * 地图变化监听
 */
public interface IMapChangeListener {
    /**
     * 地图移动
     * @param xmin
     * @param ymin
     * @param xmax
     * @param ymax
     */
    void mapChanged(double xmin,double ymin,double xmax,double ymax );
}
