package common.baselibrary.choosecity;

/**
 * Created by chasen on 2018/3/22.
 */

public class CityModel {
    private String name;
    private int code;
    private int provinceCode;

    public CityModel() {
    }

    public CityModel(String name, int code, int provinceCode) {
        this.name = name;
        this.code = code;
        this.provinceCode = provinceCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(int provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getPickerViewText() {
        return name;
    }
}
