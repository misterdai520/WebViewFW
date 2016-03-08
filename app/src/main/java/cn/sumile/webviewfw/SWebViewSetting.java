package cn.sumile.webviewfw;

import android.app.Activity;
import android.content.Context;
import android.webkit.WebSettings;
import android.webkit.WebViewClient;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebView;
import android.widget.FrameLayout;

/**
 * @author <a href="http://www.sumile.cn">sumile</a>
 */
public class SWebViewSetting {
    private WebView mWebView;
    private Context context;
    //要实例化SWebChromeClient的时候要传入的参数////////////
    private Activity activity = null;
    private FrameLayout mFrameLayout = null;
    ///////////////////////////////////////////////////

    public SWebViewSetting(WebView webView, Context context) {
        this.mWebView = webView;
        this.context = context;
        wSet = mWebView.getSettings();
    }

    private WebSettings wSet;

    private boolean mJavaScriptCanOpenWindowAutoMaticallyBoolean = true;
    private LayoutAlgorithm mLayoutAlgorithmId = LayoutAlgorithm.SINGLE_COLUMN;
    private boolean mDisplayZoomControls = false;
    private boolean mJavaScriptEnabled = true;
    private boolean mAllowFileAccess = true;
    private boolean mSupportZoom = true;
    private boolean mUseWideViewPort = true;
    private boolean mBuiltInZoomControls = false;
    private boolean mLoadWithOverviewMode = false;
    private boolean mLoadInSelfWebview = true;
    private boolean mSavePassword = true;
    private boolean mSaveFromData = true;
    private boolean mGeolocationEnabled = true;
    private boolean mSupportMultipleWindows = true;
    private boolean mDomStorageEnabled = true;
    private boolean mJavaScriptCanOpenWindowsAutomatically = true;
    //mWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
    public void setmLoadInSelfWebview(boolean mLoadInSelfWebview) {
        this.mLoadInSelfWebview = mLoadInSelfWebview;
    }

    public SWebViewSetting setJavaScriptCanOpenWindowAutoMaticallyBoolean(
            boolean mJavaScriptCanOpenWindowAutoMaticallyBoolean) {
        this.mJavaScriptCanOpenWindowAutoMaticallyBoolean = mJavaScriptCanOpenWindowAutoMaticallyBoolean;
        return this;
    }

    /**
     * @param mDisplayZoomControls 隐藏webview缩放按钮
     * @return
     */
    public SWebViewSetting setDisplayZoomControls(
            boolean mDisplayZoomControls) {
        this.mDisplayZoomControls = mDisplayZoomControls;
        return this;
    }

    /**
     * @param mJavaScriptEnabled 启用js
     * @return
     */
    public SWebViewSetting setJavaScriptEnabled(boolean mJavaScriptEnabled) {
        this.mJavaScriptEnabled = mJavaScriptEnabled;
        return this;
    }
    public SWebViewSetting setJavaScriptCanOpenWindowsAutomatically(boolean mJavaScriptCanOpenWindowsAutomatically){
        this.mJavaScriptCanOpenWindowsAutomatically = mJavaScriptCanOpenWindowsAutomatically;
        return this;
    }
    /**
     * @param mAllowFileAccess 是否允许访问文件
     * @return
     */
    public SWebViewSetting setAllowFileAccess(boolean mAllowFileAccess) {
        this.mAllowFileAccess = mAllowFileAccess;
        return this;
    }

    /**
     * @param mSupportZoom 是否支持缩放
     * @return
     */
    public SWebViewSetting setSupportZoom(boolean mSupportZoom) {
        this.mSupportZoom = mSupportZoom;
        return this;
    }

    /**
     * @param mUseWideViewPort 设置是否根据网页中的meta标签来将页面显示到webview中
     * @return
     */
    public SWebViewSetting setUseWideViewPort(boolean mUseWideViewPort) {
        this.mUseWideViewPort = mUseWideViewPort;
        return this;
    }

    /**
     * @param mBuiltInZoomControls 是否显示缩放按钮 <ul>
     *                             <li>高版本不适用</li>
     *                             </ul>
     * @return
     */
    public SWebViewSetting setBuiltInZoomControls(
            boolean mBuiltInZoomControls) {
        this.mBuiltInZoomControls = mBuiltInZoomControls;
        return this;
    }

    public SWebViewSetting setLoadWithOverviewMode(
            boolean mLoadWithOverviewMode) {
        this.mLoadWithOverviewMode = mLoadWithOverviewMode;
        return this;
    }

    /**
     * @param mDomStorageEnabled
     */
    public SWebViewSetting setDomStorageEnabled(boolean mDomStorageEnabled) {
        this.mDomStorageEnabled = mDomStorageEnabled;
        return this;
    }


    /**
     * @param mSupportMultipleWindows 是否支持多窗口显示
     */
    public SWebViewSetting setSupportMultipleWindows(boolean mSupportMultipleWindows) {
        this.mSupportMultipleWindows = mSupportMultipleWindows;
        return this;
    }


    /**
     * @param mGeolocationEnabled 是否启用地理定位
     */
    public SWebViewSetting setGeolocationEnabled(boolean mGeolocationEnabled) {
        this.mGeolocationEnabled = mGeolocationEnabled;
        return this;
    }


    /**
     * @param mSaveFromData 是否保存数据表单
     */
    public SWebViewSetting setSaveFromData(boolean mSaveFromData) {
        this.mSaveFromData = mSaveFromData;
        return this;
    }


    public SWebViewSetting setSavePassword(boolean mSavePassword) {
        this.mSavePassword = mSavePassword;
        return this;
    }

    public WebSettings getWebSetting() {
        return wSet;
    }

    /**
     * 用WebView显示图片，可使用这个参数 设置网页布局类型：
     * <ul>
     * 本方法已过时，尽量使用{@link #setmUseWideViewPort(boolean)}和
     * {@link #setmLoadWithOverviewMode(boolean)}并将两者都设置为true
     * <li>1.NARROW_COLUMNS：可能的话使所有列的宽度不超过屏幕宽度</li>
     * <li>2.NORMAL：正常显示不做任何渲染</li>
     * <li>3.SINGLE_COLUMN：把所有内容放大webview等宽的一列中</li>
     * </ul>
     *
     * @return
     */
    public SWebViewSetting layoutAlgorithm(LayoutAlgorithm LayoutAlgorithmId) {
        mLayoutAlgorithmId = LayoutAlgorithmId;
        return this;
    }

    public WebView build() {
        if (mLoadInSelfWebview) {
            mWebView.setWebViewClient(new WebViewClient() {
                public boolean shouldOverrideUrlLoading(WebView view, String url) { // 重写此方法表明点击网页里面的链接还是在当前的webview里跳转，不跳到浏览器那边
//                    view.loadUrl(url);//使用当前的webview来打开网页中的链接
//                    return true;
                    return super.shouldOverrideUrlLoading(view,url);
                }
            });
        }
        if (mFrameLayout != null) {
            if (activity != null) {
                SWebChromeClient client = new SWebChromeClient(context, mWebView, activity, mFrameLayout);
                mWebView.setWebChromeClient(client);
            } else {
                SWebChromeClient client = new SWebChromeClient(context, mWebView, mFrameLayout);
                mWebView.setWebChromeClient(client);
            }
        }
        wSet.setJavaScriptCanOpenWindowsAutomatically(mJavaScriptCanOpenWindowAutoMaticallyBoolean);
        wSet.setUseWideViewPort(mUseWideViewPort);// 可任意比例缩放
        wSet.setLayoutAlgorithm(mLayoutAlgorithmId);
        wSet.setLoadWithOverviewMode(mLoadWithOverviewMode);// setUseWideViewPort方法设置webview推荐使用的窗口。setLoadWithOverviewMode方法是设置webview加载的页面的模式。
        wSet.setDisplayZoomControls(mDisplayZoomControls);
        wSet.setJavaScriptEnabled(mJavaScriptEnabled); // 设置支持javascript脚本
        wSet.setAllowFileAccess(mAllowFileAccess); // 允许访问文件
        wSet.setBuiltInZoomControls(mBuiltInZoomControls); // 设置显示缩放按钮
        wSet.setSupportZoom(mSupportZoom); // 支持缩放
        wSet.setJavaScriptCanOpenWindowsAutomatically(true);//支持js的点击事件
        //wSet.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);// 排版适应屏幕

        wSet.setSavePassword(mSavePassword);
        wSet.setSaveFormData(mSaveFromData);// 保存表单数据
        wSet.setGeolocationEnabled(mGeolocationEnabled);// 启用地理定位
        wSet.setGeolocationDatabasePath("/data/data/org.itri.html5webview/databases/");// 设置定位的数据库路径
        wSet.setDomStorageEnabled(mDomStorageEnabled);
        wSet.setSupportMultipleWindows(mSupportMultipleWindows);// 新加
        return mWebView;
    }


    /**
     * 支持全屏播放
     *
     * @param video_fullView 一个和webview同级的FrameLayout
     *                       另参考{@link #supportVideoFullScreen(FrameLayout, Activity)}
     */
//    public SWebViewSetting supportVideoFullScreen(FrameLayout video_fullView) {
//        this.mFrameLayout = video_fullView;
//        return this;
//    }

    /**
     * 支持全屏播放
     *
     * @param video_fullView 一个和webview同级的FrameLayout
     * @param activity       是一个activity，如果不传入的话，会根据实例化类的时候传入的context进行强转，如果确定那里传入的context是一个activity的话，就不需要传入了
     */
    public SWebViewSetting supportVideoFullScreen(FrameLayout video_fullView, Activity activity) {
        this.mFrameLayout = video_fullView;
        this.activity = activity;
        return this;
    }
}
