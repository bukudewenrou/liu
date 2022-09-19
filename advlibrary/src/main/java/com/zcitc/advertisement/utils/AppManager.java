//package com.zcitc.advertisement.utils;
//
//
//import android.app.Activity;
//import android.app.Application;
//import android.content.Intent;
//
//import androidx.annotation.Nullable;
//
//
//import com.zcitc.advertisement.BuildConfig;
//
//import java.util.Arrays;
//import java.util.Iterator;
//import java.util.LinkedList;
//import java.util.List;
//
///**
// * ================================================
// * 用于管理所有 {@link Activity},和在前台的 {@link Activity}
// * 可以通过直接持有 {@link AppManager} 对象执行对应方法
// *
// * @see <a href="https://github.com/JessYanCoding/MVPArms/wiki#3.11">AppManager wiki 官方文档</a>
// * Created by JessYan on 14/12/2016 13:50
// * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
// * <a href="https://github.com/JessYanCoding">Follow me</a>
// * ================================================
// */
//public final class AppManager {
//    protected final String TAG = this.getClass().getSimpleName();
//    /**
//     * true 为不需要加入到 Activity 容器进行统一管理,默认为 false
//     */
//    public static final String IS_NOT_ADD_ACTIVITY_LIST = "is_not_add_activity_list";
//    private static volatile AppManager sAppManager;
//    private Application mApplication;
//    /**
//     * 管理所有存活的 Activity, 容器中的顺序仅仅是 Activity 的创建顺序, 并不能保证和 Activity 任务栈顺序一致
//     */
//    private List<Activity> mActivityList;
//    /**
//     * 当前在前台的 Activity
//     */
//    private Activity mCurrentActivity;
//
//    private AppManager() {
//    }
//
//    public static AppManager getAppManager() {
//        if (sAppManager == null) {
//            synchronized (AppManager.class) {
//                if (sAppManager == null) {
//                    sAppManager = new AppManager();
//                }
//            }
//        }
//        return sAppManager;
//    }
//
//    public AppManager init(Application application) {
//        this.mApplication = application;
//        return sAppManager;
//    }
//
//
//    public void setCurrentActivity(Activity currentActivity) {
//        this.mCurrentActivity = currentActivity;
//    }
//
//
//    @Nullable
//    public Activity getCurrentActivity() {
//        return mCurrentActivity;
//    }
//
//
//    @Nullable
//    public Activity getTopActivity() {
//        if (mActivityList == null) {
//            return null;
//        }
//        return mActivityList.size() > 0 ? mActivityList.get(mActivityList.size() - 1) : null;
//    }
//    @Nullable
//    public Activity getNextActivity() {
//        if (mActivityList == null) {
//            return null;
//        }
//        return mActivityList.size() > 2 ? mActivityList.get(mActivityList.size() - 2) : null;
//    }
//
//    /**
//     * 返回一个存储所有未销毁的 {@link Activity} 的集合
//     *
//     * @return
//     */
//    public List<Activity> getActivityList() {
//        if (mActivityList == null) {
//            mActivityList = new LinkedList<>();
//        }
//        return mActivityList;
//    }
//
//
//    /**
//     * 添加 {@link Activity} 到集合
//     */
//    public void addActivity(Activity activity) {
//        synchronized (AppManager.class) {
//            List<Activity> activities = getActivityList();
//            if (!activities.contains(activity)) {
//                activities.add(activity);
//            }
//        }
//    }
//
//    /**
//     * 删除集合里的指定的 {@link Activity} 实例
//     *
//     * @param {@link Activity}
//     */
//    public void removeActivity(Activity activity) {
//        if (mActivityList == null) {
//            return;
//        }
//        synchronized (AppManager.class) {
//            if (mActivityList.contains(activity)) {
//                mActivityList.remove(activity);
//            }
//        }
//    }
//
//    /**
//     * 删除集合里的指定位置的 {@link Activity}
//     *
//     * @param location
//     */
//    public Activity removeActivity(int location) {
//        if (mActivityList == null) {
//            return null;
//        }
//        synchronized (AppManager.class) {
//            if (location > 0 && location < mActivityList.size()) {
//                return mActivityList.remove(location);
//            }
//        }
//        return null;
//    }
//
//    /**
//     * 关闭指定的 {@link Activity} class 的所有的实例
//     *
//     * @param activityClass
//     */
//    public void killActivity(Class<?> activityClass) {
//        if (mActivityList == null) {
//            return;
//        }
//        synchronized (AppManager.class) {
//            Iterator<Activity> iterator = getActivityList().iterator();
//            while (iterator.hasNext()) {
//                Activity next = iterator.next();
//
//                if (next.getClass().equals(activityClass)) {
//                    iterator.remove();
//                    next.finish();
//                }
//            }
//        }
//    }
//
//
//    /**
//     * 指定的 {@link Activity} 实例是否存活
//     *
//     * @param {@link Activity}
//     * @return
//     */
//    public boolean activityInstanceIsLive(Activity activity) {
//        if (mActivityList == null) {
//            return false;
//        }
//        return mActivityList.contains(activity);
//    }
//
//
//    /**
//     * 指定的 {@link Activity} class 是否存活(同一个 {@link Activity} class 可能有多个实例)
//     *
//     * @param activityClass
//     * @return
//     */
//    public boolean activityClassIsLive(Class<?> activityClass) {
//        if (mActivityList == null) {
//            return false;
//        }
//        for (Activity activity : mActivityList) {
//            if (activity.getClass().equals(activityClass)) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//
//    /**
//     * 获取指定 {@link Activity} class 的实例,没有则返回 null(同一个 {@link Activity} class 有多个实例,则返回最早创建的实例)
//     *
//     * @param activityClass
//     * @return
//     */
//    public Activity findActivity(Class<?> activityClass) {
//        if (mActivityList == null) {
//            return null;
//        }
//        for (Activity activity : mActivityList) {
//            if (activity.getClass().equals(activityClass)) {
//                return activity;
//            }
//        }
//        return null;
//    }
//
//
//    /**
//     * 关闭所有 {@link Activity}
//     */
//    public void killAll() {
//        synchronized (AppManager.class) {
//            Iterator<Activity> iterator = getActivityList().iterator();
//            while (iterator.hasNext()) {
//                Activity next = iterator.next();
//                iterator.remove();
//                next.finish();
//            }
//        }
//    }
//
//    /**
//     * 关闭所有 {@link Activity},排除指定的 {@link Activity}
//     *
//     * @param excludeActivityClasses activity class
//     */
//    public void killAll(Class<?>... excludeActivityClasses) {
//        List<Class<?>> excludeList = Arrays.asList(excludeActivityClasses);
//        synchronized (AppManager.class) {
//            Iterator<Activity> iterator = getActivityList().iterator();
//            while (iterator.hasNext()) {
//                Activity next = iterator.next();
//
//                if (excludeList.contains(next.getClass())) {
//                    continue;
//                }
//
//                iterator.remove();
//                next.finish();
//            }
//        }
//    }
//
//    /**
//     * 关闭所有 {@link Activity},排除指定的 {@link Activity}
//     *
//     * @param excludeActivityName {@link Activity} 的完整全路径
//     */
//    public void killAll(String... excludeActivityName) {
//        List<String> excludeList = Arrays.asList(excludeActivityName);
//        synchronized (AppManager.class) {
//            Iterator<Activity> iterator = getActivityList().iterator();
//            while (iterator.hasNext()) {
//                Activity next = iterator.next();
//
//                if (excludeList.contains(next.getClass().getName())) {
//                    continue;
//                }
//
//                iterator.remove();
//                next.finish();
//            }
//        }
//    }
//
//
//    /**
//     * 退出应用程序
//     * <p>
//     * 此方法经测试在某些机型上并不能完全杀死 App 进程, 几乎试过市面上大部分杀死进程的方式, 但都发现没卵用, 所以此
//     * 方法如果不能百分之百保证能杀死进程, 就不能贸然调用 release() 释放资源, 否则会造成其他问题, 如果您
//     * 有测试通过的并能适用于绝大多数机型的杀死进程的方式, 望告知
//     */
//    public void appExit() {
//        try {
//            killAll();
//            android.os.Process.killProcess(android.os.Process.myPid());
//            System.exit(0);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * 让在栈顶的 {@link Activity} ,打开指定的 {@link Activity}
//     *
//     * @param intent
//     */
//    public void startActivity(Intent intent) {
//        if (getTopActivity() == null) {
//            //如果没有前台的activity就使用new_task模式启动activity
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            mApplication.startActivity(intent);
//            return;
//        }
//        getTopActivity().startActivity(intent);
//    }
//
//    /**
//     * 让在栈顶的 {@link Activity} ,打开指定的 {@link Activity}
//     *
//     * @param activityClass
//     */
//    public void startActivity(Class activityClass) {
//        startActivity(new Intent(mApplication, activityClass));
//    }
//
//    public void startActivityForResult(Intent intent, int requestCode) {
//
//        if (getTopActivity() == null) {
//            return;
//        }
//        getTopActivity().startActivityForResult(intent, requestCode);
//    }
//    public void startActivityForResult(Class activityClass, int requestCode) {
//
//        if (getTopActivity() == null) {
//            return;
//        }
//        getTopActivity().startActivityForResult(new Intent(mApplication, activityClass), requestCode);
//    }
//    public void printActivityTree() {
//        if (BuildConfig.DEBUG) {
//            for (Activity activity : mActivityList) {
//
//            }
//        }
//    }
//
//    /**********************************************************************************************/
//
//    public void startWeb(String url, String text) {
//
//        startWeb(url, false, text,"");
//    }
//    public void startWeb(String url, String text,String isTransparent,String titleBgColor, String titleColor) {
//
//        startWeb(url, false, text,isTransparent,titleBgColor,titleColor);
//    }
//    public void startWeb(String url, String text,boolean searchMode,String isTransparent,String titleBgColor, String titleColor) {
//
//        startWeb(url, searchMode, text,isTransparent,titleBgColor,titleColor);
//    }
//    public void startWeb(String url, String text,String keyWord) {
//
//        startWeb(url, false, text,keyWord);
//    }
//
//    public void startWeb(String url,boolean searchMode, String text) {
//
//        startWeb(url, searchMode, text,"");
//    }
//    public void startWeb(String url, boolean searchMode, String text,String keyWord,String isTransparent,String titleBgColor, String titleColor) {
//        //url = url.trim();
//        //if(url.startsWith("http")||url.startsWith("https")){
//        //Intent intent = new Intent(mApplication, WebPageActivity.class);
//        //intent.putExtra(IntentTags.Companion.getURL(), url);
//        //intent.putExtra(IntentTags.Companion.getTITLE(), text);
//        //intent.putExtra(IntentTags.Companion.getSCAN_RESULT(), "");
//        //intent.putExtra(IntentTags.Companion.getSEARCH_MODE(), searchMode);
//        //intent.putExtra(IntentTags.Companion.getISTRANSPARENT(), isTransparent);
//        //intent.putExtra(IntentTags.Companion.getTITLEBGCOLOR(), titleBgColor);
//        //intent.putExtra(IntentTags.Companion.getTITLECOLOR(), titleColor);
//        //intent.putExtra(IntentTags.Companion.getKEYWORD(), keyWord);
//        //intent.putExtra(IntentTags.Companion.getSEARCHTYPE(), 1);
//        //startActivityForResult(intent,2000);}
//    }
//    public void startWeb(String url, boolean searchMode, String text,String isTransparent,String titleBgColor, String titleColor) {
//        //url = url.trim();
//        //if(url.startsWith("http")||url.startsWith("https")){
//        //
//        //Intent intent = new Intent(mApplication, WebPageActivity.class);
//        //intent.putExtra(IntentTags.Companion.getURL(), url);
//        //intent.putExtra(IntentTags.Companion.getTITLE(), text);
//        //intent.putExtra(IntentTags.Companion.getSCAN_RESULT(), "");
//        //intent.putExtra(IntentTags.Companion.getSEARCH_MODE(), searchMode);
//        //intent.putExtra(IntentTags.Companion.getISTRANSPARENT(), isTransparent);
//        //intent.putExtra(IntentTags.Companion.getTITLEBGCOLOR(), titleBgColor);
//        //intent.putExtra(IntentTags.Companion.getTITLECOLOR(), titleColor);
//        //intent.putExtra(IntentTags.Companion.getKEYWORD(), "");
//        //intent.putExtra(IntentTags.Companion.getSEARCHTYPE(), 1);
//        //startActivityForResult(intent,2000);}
//    }
//    public void startScanResultWeb(String url,String result){
//        //url = url.trim();
//        //if(url.startsWith("http")||url.startsWith("https")){
//        //Intent intent = new Intent(mApplication, WebPageActivity.class);
//        //intent.putExtra(IntentTags.Companion.getURL(), url);
//        //intent.putExtra(IntentTags.Companion.getTITLE(), "扫描结果");
//        //intent.putExtra(IntentTags.Companion.getSCAN_RESULT(), result);
//        //intent.putExtra(IntentTags.Companion.getSEARCH_MODE(), false);
//        //intent.putExtra(IntentTags.Companion.getISTRANSPARENT(), "false");
//        //intent.putExtra(IntentTags.Companion.getTITLEBGCOLOR(), "#ffffff");
//        //intent.putExtra(IntentTags.Companion.getTITLECOLOR(), "1");
//        //intent.putExtra(IntentTags.Companion.getKEYWORD(), "");
//        //intent.putExtra(IntentTags.Companion.getSEARCHTYPE(), 1);
//        //startActivityForResult(intent,2000);}
//    }
//
//    public void startWeb(String url, boolean searchMode, String text,String keyword) {
//        //url = url.trim();
//        //if(url.startsWith("http")||url.startsWith("https")){
//        //    Intent intent = new Intent(mApplication, WebPageActivity.class);
//        //    intent.putExtra(IntentTags.Companion.getURL(), url);
//        //    intent.putExtra(IntentTags.Companion.getTITLE(), text);
//        //    intent.putExtra(IntentTags.Companion.getSCAN_RESULT(), "");
//        //    intent.putExtra(IntentTags.Companion.getSEARCH_MODE(), searchMode);
//        //    intent.putExtra(IntentTags.Companion.getISTRANSPARENT(), "false");
//        //    intent.putExtra(IntentTags.Companion.getTITLEBGCOLOR(), "#ffffff");
//        //    intent.putExtra(IntentTags.Companion.getTITLECOLOR(), "1");
//        //    intent.putExtra(IntentTags.Companion.getKEYWORD(), keyword);
//        //    intent.putExtra(IntentTags.Companion.getSEARCHTYPE(), 1);
//        //    intent.putExtra("isNotShowTitle", false);
//        //    startActivityForResult(intent,2000);
//        //}
//
//
//    }
//    public void startWebAD(String url, String text,String gotoUrl) {
//        //url = url.trim();
//        //if(url.startsWith("http")||url.startsWith("https")){
//        //Intent intent = new Intent(mApplication, WebPageActivity.class);
//        //intent.putExtra(IntentTags.Companion.getURL(), url);
//        //intent.putExtra("gotoUrl", gotoUrl);
//        //intent.putExtra(IntentTags.Companion.getTITLE(), text);
//        //intent.putExtra(IntentTags.Companion.getSCAN_RESULT(), "");
//        //intent.putExtra(IntentTags.Companion.getSEARCH_MODE(), false);
//        //intent.putExtra(IntentTags.Companion.getISTRANSPARENT(), "false");
//        //intent.putExtra(IntentTags.Companion.getTITLEBGCOLOR(), "#ffffff");
//        //intent.putExtra(IntentTags.Companion.getTITLECOLOR(), "1");
//        //intent.putExtra(IntentTags.Companion.getKEYWORD(), "");
//        //intent.putExtra(IntentTags.Companion.getSEARCHTYPE(), 1);
//        //startActivityForResult(intent,2000);}
//    }
//
//    public void startOpenNewWeb(String url, boolean searchMode, String text,String keyword) {
//        //url = url.trim();
//        //if(url.startsWith("http")||url.startsWith("https")){
//        //
//        //Intent intent = new Intent(mApplication, WebPageActivity.class);
//        //intent.putExtra(IntentTags.Companion.getURL(), url);
//        //intent.putExtra(IntentTags.Companion.getTITLE(), text);
//        //intent.putExtra(IntentTags.Companion.getSCAN_RESULT(), "");
//        //intent.putExtra(IntentTags.Companion.getSEARCH_MODE(), searchMode);
//        //intent.putExtra(IntentTags.Companion.getISTRANSPARENT(), "false");
//        //intent.putExtra(IntentTags.Companion.getTITLEBGCOLOR(), "#ffffff");
//        //intent.putExtra(IntentTags.Companion.getTITLECOLOR(), "1");
//        //intent.putExtra(IntentTags.Companion.getKEYWORD(), keyword);
//        //intent.putExtra(IntentTags.Companion.getSEARCHTYPE(), 1);
//        //intent.putExtra(IntentTags.Companion.getOPENNEWWEBVIWE(), true);
//        //startActivityForResult(intent,2000);}
//    }
//
//
//    public void startOpenNewWeb(String url, boolean searchMode, String text,String isTransparent,String titleBgColor, String titleColor) {
//        //url = url.trim();
//        //if(url.startsWith("http")||url.startsWith("https")){
//        //Intent intent = new Intent(mApplication, WebPageActivity.class);
//        //intent.putExtra(IntentTags.Companion.getURL(), url);
//        //intent.putExtra(IntentTags.Companion.getTITLE(), text);
//        //intent.putExtra(IntentTags.Companion.getSCAN_RESULT(), "");
//        //intent.putExtra(IntentTags.Companion.getSEARCH_MODE(), searchMode);
//        //intent.putExtra(IntentTags.Companion.getISTRANSPARENT(), isTransparent);
//        //intent.putExtra(IntentTags.Companion.getTITLEBGCOLOR(), titleBgColor);
//        //intent.putExtra(IntentTags.Companion.getTITLECOLOR(), titleColor);
//        //intent.putExtra(IntentTags.Companion.getKEYWORD(), "");
//        //intent.putExtra(IntentTags.Companion.getSEARCHTYPE(), 1);
//        //intent.putExtra(IntentTags.Companion.getOPENNEWWEBVIWE(), true);
//        //startActivityForResult(intent,2000);}
//    }
//
//
//}
