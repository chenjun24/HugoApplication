package com.cj.hugoapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Looper;
import android.os.SystemClock;
import android.util.Log;
import android.util.Printer;
import android.view.View;

import com.github.moduth.blockcanary.internal.BlockInfo;

import java.util.ArrayList;
import java.util.List;

import hugo.weaving.DebugLog;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // getName("chen","jun");
        //initPrinter();
//        ClassLoader classLoader = MainActivity.class.getClassLoader();
//        ClassLoader classLoader1 = Activity.class.getClassLoader();//BootClassloader
//        ClassLoader classLoader2 = AppCompatActivity.class.getClassLoader();
//        Log.d("junchen", "classLoader x:"+classLoader);
//        Log.d("junchen", "classLoader1 x:"+classLoader1);
//        Log.d("junchen", "classLoader2 x:"+classLoader2);
    }
    @DebugLog
    public String getName(String first, String last) {
        SystemClock.sleep(15); // Don't ever really do this!
        return first + " " + last;
    }
    List<byte[]> list = new ArrayList<>();
    Bitmap bitmap;
    public void click(View view) {
       // SystemClock.sleep(2000);

//        try {
//            Thread.sleep(4000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        for (int i=0;i<10000;i++){
//            list.add(new byte[1024]);
//        }
//        for (int i=0;i<100000;i++){
//
//        }
        BitmapFactory.Options options = new BitmapFactory.Options();
       // options.inJustDecodeBounds = true;
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        options.inMutable = true;
        options.inBitmap = bitmap;
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.tv_guide_1,options);
       // options.inJustDecodeBounds = false;

        Log.d("junchen", "click ByteCount---:"+bitmap.getByteCount());
    }

    //使用 printerStart 进行判断是方法执行前还是执行后调用 println
    boolean printerStart = true;
    long printerStartTime = 0L;
    private long minTime = 1000L;
    public void initPrinter() {
        Looper.getMainLooper().setMessageLogging(new Printer() {
            @Override
            public void println(String x) {
                Log.d("junchen", "println x:"+x);
                if (printerStart) {
                    printerStart = false;
//                    StringBuilder stringBuilder = new StringBuilder();
//                    for (StackTraceElement stackTraceElement : Looper.getMainLooper().getThread().getStackTrace()) {
//                        stringBuilder
//                                .append(stackTraceElement.toString())
//                                .append(BlockInfo.SEPARATOR);
//                    }
//                    Log.d("junchen", "StackTrace："+stringBuilder.toString());
                    printerStartTime = System.currentTimeMillis();
                } else {
                    printerStart = true;
                    long temp = System.currentTimeMillis()-printerStartTime;
                    Log.d("junchen", "方法运行的总时长:"+temp);
//                    if (temp>=minTime){
//                        Log.d("junchen", "方法运行的总时长:"+temp);
//                        //获取栈信息进行输出
//                        StringBuilder stringBuilder = new StringBuilder();
//                        for (StackTraceElement stackTraceElement : Looper.getMainLooper().getThread().getStackTrace()) {
//                            stringBuilder
//                                    .append(stackTraceElement.toString())
//                                    .append(BlockInfo.SEPARATOR);
//                        }
//                        Log.d("junchen", "StackTrace："+stringBuilder.toString());
//                    }
                    //Log.d("junchen", "方法运行的总时长"+(System.currentTimeMillis()-printerStartTime));
                }
            }
        });
    }
}