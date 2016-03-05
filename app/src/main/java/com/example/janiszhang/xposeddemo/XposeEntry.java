package com.example.janiszhang.xposeddemo;

import android.util.Log;

import de.robv.android.xposed.IXposedHookInitPackageResources;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.callbacks.XC_InitPackageResources;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;
/**
 * Created by janiszhang on 2016/3/5.
 */
public class XposeEntry implements IXposedHookLoadPackage{

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam param) throws Throwable {
        //        if(param.packageName.equals("apk")) {
        try {
            findAndHookMethod("android.content.res.Resources", param.classLoader, "getColor", new my_getColor());
        } catch (Exception e) {
            e.printStackTrace();
        }
//        }
    }
}

class my_getColor extends XC_MethodHook{
    @Override
    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
        super.beforeHookedMethod(param);
    }

    @Override
    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
        super.afterHookedMethod(param);
        int rel = (int) param.getResult();
        rel = rel & ~ 0x0000ff00 | 0x00ff0000;
        param.setResult(rel);
    }
}
