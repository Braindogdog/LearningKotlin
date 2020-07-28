package common.baselibrary.choosecity;

/**
 * Created by chasen on 2018/3/22.
 */

public class ProvinceModel {
    private String name;
    private int code;
    private boolean selected;

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
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

    public ProvinceModel() {

    }

    public ProvinceModel(String name, int code) {

        this.name = name;
        this.code = code;
    }

    public String getPickerViewText() {
        return name;
    }
}
