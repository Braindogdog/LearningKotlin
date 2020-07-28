package common.baselibrary.baseview;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by chasen on 2018/3/30.
 * 所有Fragment基类
 */

public abstract class BaseFragment extends Fragment {
    protected FragmentActivity context;
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        context = getActivity();
        if (getLayoutResID() != 0) {
            view = inflater.inflate(getLayoutResID(), container, false);
            initWidget(view);
            init();
            setListener();
            return view;
        } else {
            return super.onCreateView(inflater, container, savedInstanceState);
        }
    }

    public View catchView() {
        return this.view;
    }

    @NonNull
    protected abstract int getLayoutResID();

    protected abstract void initWidget(View view);

    protected abstract void setListener();

    protected abstract void init();
}
