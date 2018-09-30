package wia.soft.official.pastebin.utils;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

public class RouterUtils {

    public static void showFragmentInStack(int container, Fragment fragment,
                                           FragmentActivity fragmentActivity) {
        fragmentActivity.getSupportFragmentManager().beginTransaction()
                .addToBackStack(null).replace(container, fragment).commit();
    }

    public static Fragment showFragment(int container, Fragment fragment,
                                        FragmentActivity fragmentActivity) {
        fragmentActivity.getSupportFragmentManager().beginTransaction()
                .replace(container, fragment).commit();
        return fragment;
    }

    public static Fragment showFragmentWithTag(int container, Fragment fragment,
                                               FragmentActivity fragmentActivity, String tag) {
        fragmentActivity.getSupportFragmentManager().beginTransaction()
                .replace(container, fragment, tag).commit();
        return fragment;
    }

    public static void showActivity(Context context, Class<?> Cls) {
        context.startActivity(new Intent(context, Cls));
    }
}