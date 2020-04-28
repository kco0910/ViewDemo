package com.fly.viewdemo.visualizer;

import android.content.Context;
import android.graphics.Canvas;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.audiofx.Visualizer;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.fly.viewdemo.R;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by Fj on 2018/10/18.
 */

public class VisualizerActivity extends AppCompatActivity {
    private static final String TAG = "VisualizerActivity";
    private Visualizer mMVisualizer;
    private boolean visualizerState;
    private MediaPlayer mMediaPlayer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.visualizer_layout);

        AndPermission.with(this).permission(Permission.WRITE_EXTERNAL_STORAGE,
                Permission.RECORD_AUDIO,
                Permission.READ_EXTERNAL_STORAGE,Permission.READ_PHONE_STATE)
                .onGranted(new Action() {
                    @Override
                    public void onAction(List<String> permissions) {
                        createVisualizerView();
                    }
                })
                .onDenied(new Action() {
                    @Override
                    public void onAction(List<String> permissions) {

                    }
                })
                .start();
    }

    public void pause(View view) {
        if (mMediaPlayer != null){
            mMediaPlayer.pause();
        }
        if (mMVisualizer != null){
            mMVisualizer.setEnabled(false);
        }
    }

    public void cleanShow(View view) {
        if (mMediaPlayer != null){
            mMediaPlayer.pause();
        }
        if (mMVisualizer != null){
            mMVisualizer.release();
        }
    }

    public void play(View view) {
        if (mMediaPlayer != null && !mMediaPlayer.isPlaying()){
            mMediaPlayer.start();
            initVisualizer(mMediaPlayer.getAudioSessionId());
        }
    }

    private void createVisualizerView(){
        File musicFile = new File(Environment.getExternalStorageDirectory(),"source/thatGirl.mp3");
        try {
            mMediaPlayer = new MediaPlayer();
            mMediaPlayer.setDataSource(musicFile.getAbsolutePath());
            mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mMediaPlayer.prepareAsync();
            mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    // 装载完毕回调
                    mMediaPlayer.start();
                    initVisualizer(mp.getAudioSessionId());
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initVisualizer(int audioSessionId){
        final int maxCR = Visualizer.getMaxCaptureRate();
        mMVisualizer = new Visualizer(audioSessionId);
        //采样,需要是2指数倍,数字越大FFT算法运行时间更长
        mMVisualizer.setCaptureSize(Visualizer.getCaptureSizeRange()[0]);
        //rate， 表示采样的周期，即隔多久采样一次
        //iswave，是波形信号
        //isfft，是FFT信号(频域信号)
        mMVisualizer.setDataCaptureListener(new Visualizer.OnDataCaptureListener() {
            @Override
            public void onWaveFormDataCapture(Visualizer visualizer, byte[] waveform, int samplingRate) {//获取波形数据
            }

            @Override
            public void onFftDataCapture(Visualizer visualizer, byte[] fft, int samplingRate) {
                Log.i(TAG, "onFftDataCapture: ");
//                            updateVisualizer(fft);
            }
        }, maxCR / 2, false, true);
        mMVisualizer.setEnabled(true);
    }


    private class  VisualizerView  extends View{

        public VisualizerView(Context context) {
            this(context,null);
        }

        public VisualizerView(Context context, @Nullable AttributeSet attrs) {
            this(context, attrs,0);
        }

        public VisualizerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

        }
    }
}
