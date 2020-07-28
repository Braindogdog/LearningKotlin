package com.firebaselibrary.model;

import android.content.Context;
import android.support.annotation.Nullable;

import com.firebaselibrary.bean.CommonResultBean;
import com.firebaselibrary.bean.KeyUnitSimpleBean;
import com.firebaselibrary.bean.StationBean;
import com.firebaselibrary.bean.WaterBean;

import java.util.List;

import common.baselibrary.baseutil.GsonUtil;

/**
 * Created by chasen on 2018/5/21.
 */

public class CollectDataOfflineModelImpl implements CollectDataModel {
    private Context context;
    private String baseUrl;

    public CollectDataOfflineModelImpl(Context context, String baseUrl) {
        this.context = context;
        this.baseUrl = baseUrl;
    }

    @Override
    public void getKeyUnitList(final ILoadingResultListener<List<KeyUnitSimpleBean>> listener, @Nullable String name, @Nullable String unit, String pageIndex, String pageSize) {
        String result = "{\"isSuccess\":true,\"result\":[{\"phone\":\"13907030151\",\"person\":\"姚华根\",\"FirePrincipal\":\"姚华根\",\"HoldArea\":\"0.0\",\"name\":\"聚龙网吧\",\"id\":\"0000026d9dfd438ba8bb9d0f766ee988\"},{\"phone\":\"5827200\",\"person\":\"刘勤平\",\"FirePrincipal\":\"\",\"HoldArea\":\"0.0\",\"name\":\"万安县崇文中学\",\"id\":\"001417d163814866adc4c40be3ae8431\"},{\"phone\":\"5827200\",\"person\":\"刘勤平\",\"FirePrincipal\":\"\",\"HoldArea\":\"0.0\",\"name\":\"中国银行宜春市分行\",\"id\":\"001ed337544544d599767948a605f950\"},{\"phone\":\"5827200\",\"person\":\"刘勤平\",\"FirePrincipal\":\"\",\"HoldArea\":\"0.0\",\"name\":\"横峰县社会福利中心预案\",\"id\":\"002d1b930c1f42a5bfdaeb1bbe087fd3\"},{\"phone\":\"13907072563\",\"person\":\"程明丽\",\"FirePrincipal\":\"\",\"HoldArea\":\"0.0\",\"name\":\"白天鹅宾馆\",\"id\":\"002d9233ae3843fcafaa611911cc8636\"},{\"phone\":\"13576543566\",\"person\":\"廖立安\",\"FirePrincipal\":\"\",\"HoldArea\":\"0.0\",\"name\":\"奉新县超时空网络俱乐部\",\"id\":\"002dc29743004972baddc4bbd3aaa417\"},{\"phone\":\"13576543566\",\"person\":\"丁业铭\",\"FirePrincipal\":\"\",\"HoldArea\":\"0.0\",\"name\":\"中石化吉安分公司遂川经营部水南站灭火预案\",\"id\":\"0035d740a7fe4723b25fe5198441903d\"},{\"phone\":\"2511392\",\"person\":\"欧阳勇\",\"FirePrincipal\":\"\",\"HoldArea\":\"0.0\",\"name\":\"永丰县博物馆\",\"id\":\"003d05eaec694e32b6f428d2bccff7fe\"},{\"phone\":\"2176666\",\"person\":\"廖妙生\",\"FirePrincipal\":\"\",\"HoldArea\":\"0.0\",\"name\":\"如家酒楼（浔阳路店）\",\"id\":\"00415e36f8b04a85b218df6e618415f7\"},{\"phone\":\"2176666\",\"person\":\"廖妙生\",\"FirePrincipal\":\"\",\"HoldArea\":\"0.0\",\"name\":\"鄱阳县公路路政管理大队\",\"id\":\"00597a19ee424f90943533f6c038e598\"}],\"exceptionString\":\"\"}";
        CommonResultBean resultBean = GsonUtil.json2Bean(result, CommonResultBean.class);
        if (resultBean.isSuccess()) {
            List<KeyUnitSimpleBean> list = GsonUtil.json2List(GsonUtil.obj2String(resultBean.getResult()), KeyUnitSimpleBean.class);
            listener.success(list);
        } else {
            listener.error(resultBean.getExceptionString());
        }

    }

    @Override
    public void getKeyUnit() {

    }

    @Override
    public void createKeyUnit() {

    }

    @Override
    public void getWaterList(final ILoadingResultListener<List<WaterBean>> listener, @Nullable String name, @Nullable String unit, String pageIndex, String pageSize) {
        String result = "{\"isSuccess\":true,\"result\":[{\"name\":\"水源001\",\"stype\":{\"name\":\"消火栓\",\"id\":\"1100\"},\"unit\":{\"name\":\"测试地址1\",\"id\":\"20180604040425959\"},\"id\":\"20180604040425967\"},{\"name\":\"水源002\",\"stype\":{\"name\":\"消防栓\",\"id\":\"1100\"},\"unit\":{\"name\":\"测试地址2\",\"id\":\"20180525114043098\"},\"id\":\"00008cbe85c04b01932c2c6f0f5de294\"},{\"name\":\"水源003\",\"stype\":{\"name\":\"消防栓\",\"id\":\"1100\"},\"unit\":{\"name\":\"测试地址3\",\"id\":\"20180426103935793\"},\"id\":\"20180426103935920\"},{\"name\":\"水源004\",\"stype\":{\"name\":\"消防栓\",\"id\":\"1100\"},\"unit\":{\"name\":\"测试地址4\",\"id\":\"20180524100931583\"},\"id\":\"1111111\"},{\"name\":\"水源005\",\"stype\":{\"name\":\"消防栓\",\"id\":\"1100\"},\"unit\":{\"name\":\"测试地址5\",\"id\":\"20180411105444437\"},\"id\":\"0928c2c3-b3be-4333-a507-d19e8a36faf0\"},{\"name\":\"水源006\",\"stype\":{\"name\":\"消防栓\",\"id\":\"1100\"},\"state\":\"1\",\"unit\":{\"name\":\"测试地址6\",\"id\":\"20180411105444437\"},\"id\":\"00056130856b49e78d30bea66cdd80ea\"},{\"name\":\"水源007\",\"stype\":{\"name\":\"消防栓\",\"id\":\"1100\"},\"state\":\"1\",\"unit\":{\"name\":\"测试地址7\",\"id\":\"20180411105444437\"},\"id\":\"0005a4e75fc64f849cf5048074cac4c6\"},{\"name\":\"水源008\",\"stype\":{\"name\":\"消防栓\",\"id\":\"1100\"},\"unit\":{\"name\":\"测试地址8\",\"id\":\"20180411105444437\"},\"id\":\"0006628d-76d9-47b6-a717-b422a26c1399\"},{\"name\":\"水源009\",\"stype\":{\"name\":\"消防栓\",\"id\":\"1100\"},\"unit\":{\"name\":\"测试地址9\",\"id\":\"20180411105444437\"},\"id\":\"0006c6da-d746-433d-8f7f-c53117bab074\"},{\"name\":\"水源010\",\"stype\":{\"name\":\"消防栓\",\"id\":\"1100\"},\"state\":\"1\",\"unit\":{\"name\":\"测试地址10\",\"id\":\"20180411105444437\"},\"id\":\"000b157c5e06433098b02a5ab914b9f4\"}],\"exceptionString\":\"\"}";
        CommonResultBean resultBean = GsonUtil.json2Bean(result, CommonResultBean.class);
        if (resultBean.isSuccess()) {
            List<WaterBean> list = GsonUtil.json2List(GsonUtil.obj2String(resultBean.getResult()), WaterBean.class);
            listener.success(list);
        } else {
            listener.error(resultBean.getExceptionString());
        }
    }

    @Override
    public void getWater(final ILoadingResultListener<WaterBean> listener, String id) {
        String result = "{\"isSuccess\":true,\"result\":{\"name\":\"消防水源\",\"stype\":{\"name\":\"消火栓\",\"id\":\"1100\"},\"unit\":{\"name\":\"测试地址1\",\"id\":\"20180604040425959\"},\"id\":\"20180604040425967\"},\"exceptionString\":\"\"}";
        CommonResultBean resultBean = GsonUtil.json2Bean(result, CommonResultBean.class);
        if (resultBean.isSuccess()) {
            WaterBean waterBean = GsonUtil.json2Bean(GsonUtil.obj2String(resultBean.getResult()), WaterBean.class);
            listener.success(waterBean);
        } else {
            listener.error(resultBean.getExceptionString());
        }
    }

    @Override
    public void createWater() {

    }

    @Override
    public void getFireStationList(final ILoadingResultListener<List<StationBean>> listener, @Nullable String name, @Nullable String unit, String pageIndex, String pageSize) {
        String result = "{\"isSuccess\":true,\"result\":[{\"name\":\"微型消防站1\",\"leader\":\"站长1\",\"leaderTele\":\"zzlxdh\",\"observer\":\"sdjdy\",\"observerTele\":\"jdylxdh\",\"unit\":{\"name\":\"江西省公安消防总队\",\"id\":\"e23a1005fe7b45c5ade1d029d8e3e\"},\"location\":{\"point\":{\"x\":115.27586,\"y\":28.72038},\"description\":\"江西省宜春市奉新县\",\"id\":\"20180620101214508\",\"srid\":8307},\"personCount\":0,\"equip\":\"zb\",\"id\":\"3\"},{\"name\":\"微型消防站2\",\"leader\":\"站长2\",\"leaderTele\":\"34343\",\"observer\":\"3434\",\"observerTele\":\"343\",\"unit\":{\"name\":\"江西省公安消防总队\",\"id\":\"e23a1005fe7b45c5ade1d029d8e3e\"},\"location\":{\"point\":{\"x\":106.05165,\"y\":38.5225},\"description\":\"宁夏回族自治区银川市西夏区\",\"id\":\"20180620101214508\",\"srid\":8307},\"personCount\":0,\"equip\":\"3434\",\"id\":\"20180425093650158\"},{\"name\":\"微型消防站3\",\"leader\":\"站长3\",\"leaderTele\":\"344\",\"observer\":\"344\",\"observerTele\":\"3434\",\"unit\":{\"name\":\"江西省公安消防总队\",\"id\":\"e23a1005fe7b45c5ade1d029d8e3e\"},\"location\":{\"point\":{\"x\":105.9542,\"y\":38.4954},\"description\":\"宁夏回族自治区银川市西夏区\",\"id\":\"20180620101214508\",\"srid\":8307},\"personCount\":0,\"equip\":\"3434\",\"id\":\"20180425112306678\"},{\"name\":\"微型消防站4\",\"leader\":\"站长4\",\"leaderTele\":\"13835716511\",\"observer\":\"45\",\"observerTele\":\"13835716511\",\"unit\":{\"name\":\"江西省公安消防总队\",\"id\":\"e23a1005fe7b45c5ade1d029d8e3ed\"},\"location\":{\"point\":{\"x\":105.9542,\"y\":38.4954},\"description\":\"宁夏回族自治区银川市西夏区\",\"id\":\"20180620101214524\",\"srid\":8307},\"personCount\":0,\"equip\":\"3434\",\"id\":\"20180425112306671\"},{\"name\":\"微型消防站5\",\"leader\":\"站长5\",\"leaderTele\":\"ds\",\"observer\":\"sdf\",\"observerTele\":\"123454\",\"unit\":{\"name\":\"江西省公安消防总队\",\"id\":\"e23a1005fe7b45c5ade1d029d8e3ed\"},\"location\":{\"point\":{\"x\":105.92258,\"y\":38.52205},\"description\":\"宁夏回族自治区银川市西夏区\",\"id\":\"20180620101214524\",\"srid\":8307},\"personCount\":0,\"equip\":\"e3r4\",\"id\":\"20180425112306672\"},{\"name\":\"微型消防站6\",\"leader\":\"站长6\",\"leaderTele\":\"ds\",\"observer\":\"sdf\",\"observerTele\":\"123454\",\"unit\":{\"name\":\"江西省公安消防总队\",\"id\":\"e23a1005fe7b45c5ade1d029d8e3ed\"},\"location\":{\"point\":{\"x\":0.0,\"y\":0.0},\"description\":\"测试地址\",\"id\":\"20180620101214524\",\"srid\":8307},\"personCount\":0,\"equip\":\"3434\",\"id\":\"20180425112306673\"},{\"name\":\"微型消防站7\",\"leader\":\"站长7\",\"leaderTele\":\"ds\",\"observer\":\"sdf\",\"observerTele\":\"123454\",\"unit\":{\"name\":\"江西省公安消防总队\",\"id\":\"e23a1005fe7b45c5ade1d029d8e3ed\"},\"location\":{\"point\":{\"x\":0.0,\"y\":0.0},\"description\":\"测试地址\",\"id\":\"20180620101214524\",\"srid\":8307},\"personCount\":0,\"equip\":\"3434\",\"id\":\"20180425112306675\"},{\"name\":\"微型消防站8\",\"leader\":\"站长8\",\"leaderTele\":\"ds\",\"observer\":\"sdf\",\"observerTele\":\"123454\",\"unit\":{\"name\":\"江西省公安消防总队\",\"id\":\"e23a1005fe7b45c5ade1d029d8e3ed\"},\"location\":{\"point\":{\"x\":0.0,\"y\":0.0},\"description\":\"测试地址\",\"id\":\"20180620101214524\",\"srid\":8307},\"personCount\":0,\"equip\":\"3434\",\"id\":\"20180425112306676\"},{\"name\":\"微型消防站9\",\"leader\":\"站长9\",\"leaderTele\":\"ds\",\"observer\":\"sdf\",\"observerTele\":\"123454\",\"unit\":{\"name\":\"江西省公安消防总队\",\"id\":\"e23a1005fe7b45c5ade1d029d8e3ed\"},\"location\":{\"point\":{\"x\":0.0,\"y\":0.0},\"description\":\"测试地址\",\"id\":\"20180620101214524\",\"srid\":8307},\"personCount\":0,\"equip\":\"3434\",\"id\":\"20180425112306679\"},{\"name\":\"微型消防站10\",\"leader\":\"站长10\",\"leaderTele\":\"13835716544\",\"observer\":\"5\",\"observerTele\":\"13835752144\",\"unit\":{\"name\":\"江西省公安消防总队\",\"id\":\"fdd5eced27624f7da0c2cfb7f\"},\"location\":{\"point\":{\"x\":0.0,\"y\":0.0},\"description\":\"宁夏回族自治区银川市西夏区\",\"id\":\"20180620101214524\",\"srid\":8307},\"personCount\":0,\"equip\":\"3434\",\"id\":\"20180425112306681\"}],\"exceptionString\":\"\"}";
        CommonResultBean resultBean = GsonUtil.json2Bean(result, CommonResultBean.class);
        if (resultBean.isSuccess()) {
            List<StationBean> list = GsonUtil.json2List(GsonUtil.obj2String(resultBean.getResult()), StationBean.class);
            listener.success(list);
        } else {
            listener.error(resultBean.getExceptionString());
        }
    }

    @Override
    public void getFireStation(final ILoadingResultListener<StationBean> listener, String id) {
        String result = "{\"isSuccess\":true,\"exceptionString\":\"\"}";
        CommonResultBean resultBean = GsonUtil.json2Bean(result, CommonResultBean.class);
        if (resultBean.isSuccess()) {
            StationBean stationBean = GsonUtil.json2Bean(GsonUtil.obj2String(resultBean.getResult()), StationBean.class);
            listener.success(stationBean);
        } else {
            listener.error(resultBean.getExceptionString());
        }
    }

    @Override
    public void createFireStation() {

    }
}
