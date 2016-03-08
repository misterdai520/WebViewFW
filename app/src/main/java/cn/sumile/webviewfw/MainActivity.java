package cn.sumile.webviewfw;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.FrameLayout;


public class MainActivity extends Activity implements SensorEventListener {
    private WebView webView;
    private FrameLayout video_fullView;
    private SensorManager sensorManager;

    @Override
    protected void onResume() {
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_FASTEST);
        super.onResume();
        //�ָ�����
        webView.resumeTimers();
    }

    @Override
    protected void onPause() {
        // acitivity��̨ʱȡ�����
        sensorManager.unregisterListener(this);
        super.onPause();
        //��ͣ����
        webView.pauseTimers();
    }


    @Override
    protected void onDestroy() {
        sensorManager.unregisterListener(this);
        super.onDestroy();
        //һ��Ҫ��٣������޷�ֹͣ����
        webView.destroy();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
            webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);// 隐藏标题
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        //、设置屏幕方向
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        webView = (WebView) findViewById(R.id.webView);
        video_fullView = (FrameLayout) findViewById(R.id.video_fullView);
        //֧��ȫ������
        SWebViewSetting setting = new SWebViewSetting(webView, MainActivity.this);
        setting.supportVideoFullScreen(video_fullView,MainActivity.this);
        webView = setting.build();
       // webView.loadUrl("http://v.qq.com/iframe/player.html?vid=e01264pnkg9&tiny=0&auto=0");
        webView.loadUrl("http://m.mtime.cn/#!/news/movie/1553089/");
//        webView.loadUrl("http://v.youku.com/v_show/id_XMTMxNDM1MzA4MA==_ev_1.html?from=y1.3-idx-uhome-1519-20887.205805-205902.1-1");
//        webView.loadUrl("http://www.tudou.com/albumplay/YkXp849rWok/0MoWF_g8S5c.html");

    }

    String TAG = "sumile";
    //传感器
    @Override
    public void onSensorChanged(SensorEvent event) {
        final int sensorType = event.sensor.getType();
        //values[0]:X�ᣬvalues[1]��Y�ᣬvalues[2]��Z��
        final float[] values = event.values;
        float x = values[0];
        float y = values[1];
        float z = values[2];
        x = Math.abs(x);
        y = Math.abs(y);
        z = Math.abs(z);
//        ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE,//横屏
//        ActivityInfo.SCREEN_ORIENTATION_PORTRAIT,//竖屏
//        ActivityInfo.SCREEN_ORIENTATION_USER,//用户当前的首选方向

        if ((x >= 6 && z <= 6) || (x >= 6 && y >= 6)) {
            Log.i(TAG, "heng" + x + "--" + y + "--" + z);
            if (getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE) {
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
            }
        } else if ((x <= 3 && z <= 6) || (z <= 6 && y <= 3)) {
            Log.i(TAG, "shu" + x + "--" + y + "--" + z);
            if (getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}