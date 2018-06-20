package com.example.asus.csproject;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.asus.csproject.classifier.Classifier;
import com.example.asus.csproject.classifier.ImageClassifier;
import com.example.asus.csproject.classifier.TensorFlowImageClassifier;
import com.example.asus.csproject.translation.Language;
import com.wonderkiln.camerakit.CameraKitError;
import com.wonderkiln.camerakit.CameraKitEvent;
import com.wonderkiln.camerakit.CameraKitEventListener;
import com.wonderkiln.camerakit.CameraKitImage;
import com.wonderkiln.camerakit.CameraKitVideo;
import com.wonderkiln.camerakit.CameraView;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    private static final String MODEL_PATH = "optimized_graph.lite";
    private static final String LABEL_PATH = "retrained_labels.txt";
    private static final int INPUT_SIZE = 224;

    private Classifier classifier;
    private ImageClassifier imageClassifier;
    private Language language;
    private Button detectButton;
    private CameraView cameraView;
    private TextView translatedTextView;
    private Executor executor = Executors.newSingleThreadExecutor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        String lang = intent.getStringExtra("language");
        if(lang != null) setLanguage(lang);
        setUI();
        initTensorFlowAndLoadModel();
        try {
            imageClassifier = new ImageClassifier(MainActivity.this);
        } catch (IOException e) {
            throw new RuntimeException("Error initializing TensorFlow!", e);
        }


    }

    private void setLanguage(String lang){
        switch (lang.toUpperCase()){
            case "POLISH":
                language = Language.POLISH;
                break;
            case "ENGLISH":
                language = Language.ENGLISH;
                break;
            case "GERMAN":
                language = Language.GERMAN;
                break;
        }
    }
    private void setUI(){

        cameraView = findViewById(R.id.cameraView);
        detectButton = findViewById(R.id.btnDetect);
        translatedTextView = findViewById(R.id.translatedTextView);

        cameraView.addCameraKitListener(new CameraKitEventListener() {
            @Override
            public void onEvent(CameraKitEvent cameraKitEvent) {

                cameraKitEvent.getType();
            }

            @Override
            public void onError(CameraKitError cameraKitError) {

            }

            @Override
            public void onImage(CameraKitImage cameraKitImage) {

                Bitmap bitmap = cameraKitImage.getBitmap();
                bitmap = Bitmap.createScaledBitmap(bitmap, INPUT_SIZE, INPUT_SIZE, false);

               final List<Classifier.Recognition> results = classifier.recognizeImage(bitmap);
               translatedTextView.setText(results.get(0).getTitle().toString());

//                Bitmap bitmap = cameraKitImage.getBitmap();
//                bitmap = Bitmap.createScaledBitmap (bitmap, ImageClassifier.DIM_IMG_SIZE_X, ImageClassifier.DIM_IMG_SIZE_Y, false);
//                String textToShow = imageClassifier.classifyFrame(bitmap);
//                bitmap.recycle();
//                translatedTextView.setText(textToShow);
            }

            @Override
            public void onVideo(CameraKitVideo cameraKitVideo) {

            }

        });

        detectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cameraView.captureImage();
                Log.d("MAIN_ACTIVITY", "detect button clicked");
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        cameraView.start();
    }

    @Override
    protected void onPause() {
        cameraView.stop();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                classifier.close();
            }
        });
    }

    private void initTensorFlowAndLoadModel() {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    classifier = TensorFlowImageClassifier.create(
                            getAssets(),
                            MODEL_PATH,
                            LABEL_PATH,
                            INPUT_SIZE);
                } catch (final Exception e) {
                    throw new RuntimeException("Error initializing TensorFlow!", e);
                }
            }
        });
    }

}
