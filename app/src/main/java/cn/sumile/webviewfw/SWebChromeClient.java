package cn.sumile.webviewfw;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.FrameLayout;

/**
 * Created by sumile on 2015/8/28.
 * Blog:<a href="http://sumile.cn">sumile</a>
 * ���������� ���ޱ��ӡ� ����������
 * ���������� ������BUG������������
 */
public class SWebChromeClient extends WebChromeClient {
    private View xprogressvideo;
    private Context context;
    private View xCustomView;
    private CustomViewCallback xCustomViewCallback;
    private Activity activity;
    /**
     * û��ȫ������ʱ��webview
     */
    private WebView webView;
    private FrameLayout video_fullView;// ȫ��ʱ��Ƶ����view

    public SWebChromeClient(Context context, final WebView webView, FrameLayout video_fullView) {
        this.context = context;
        this.webView = webView;
        if (context instanceof  Activity){
            this.activity=(Activity)context;
        }
        this.video_fullView = video_fullView;
    }
    public SWebChromeClient(Context context, final WebView webView, final Activity activity, FrameLayout video_fullView) {
        this.context = context;
        this.webView = webView;
        this.activity = activity;
        this.video_fullView = video_fullView;
    }

    // 显示
    @Override
    public void onShowCustomView(View view, CustomViewCallback callback) {
        activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        webView.setVisibility(View.INVISIBLE);
        // ���һ����ͼ�Ѿ����ڣ���ô������ֹ���½�һ��
        if (xCustomView != null) {
            callback.onCustomViewHidden();
            return;
        }
        video_fullView.addView(view);
        xCustomView = view;
        xCustomViewCallback = callback;
        video_fullView.setVisibility(View.VISIBLE);
    }

    // 隐藏
    @Override
    public void onHideCustomView() {
        if (xCustomView == null)// ����ȫ������״̬
            return;

        activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        xCustomView.setVisibility(View.GONE);
        video_fullView.removeView(xCustomView);
        xCustomView = null;
        video_fullView.setVisibility(View.GONE);
        xCustomViewCallback.onCustomViewHidden();
        webView.setVisibility(View.VISIBLE);
    }

    // 设置视频的加载loading
    @Override
    public View getVideoLoadingProgressView() {
        if (xprogressvideo == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            xprogressvideo = inflater.inflate(R.layout.video_loading_progress, null);
        }
        return xprogressvideo;
    }
//    @Override
//    public boolean onJsAlert(WebView view, String url, String message, final JsResult result) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(context);
//        builder.setMessage(message).setNeutralButton("确定", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface arg0, int arg1) {
//                arg0.dismiss();
//                }
//            }).show();
//        result.cancel();
//        return true;
//    }

//    @Override
//    public boolean onJsConfirm(WebView view, String url,
//                                   String message, final JsResult result) {
//        // TODO Auto-generated method stub
//        Log.i("TAG", "onJsConfirm" + "," + "url: " + url);

//        DialogUtils.dialogBuilder(context, "温馨提示", message,
//                new DialogCallBack() {
//
//             @Override
//             public void onCompate() {
//                Log.i("TAG", "onJsConfirm,onCompate");
//                result.confirm();
//             }
//            @Override
//            public void onCancel() {
//                 Log.i("TAG", "onJsConfirm,onCancel");
//                 result.cancel();
//            }
//         });
//        return true;
//    }
    /**
     * �ж��Ƿ���ȫ��
     *
     * @return
     */
    public boolean inCustomView() {
        return (xCustomView != null);
    }

    /**
     * ȫ��ʱ�����Ӽ�ִ���˳�ȫ������
     */
    public void hideCustomView() {
        onHideCustomView();
        activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }
}
