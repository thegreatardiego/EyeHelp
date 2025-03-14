package com.example.eyehelp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class ColorGuide extends AppCompatActivity {

    private static final int PERMISSION_CODE = 1000;
    private static final int IMAGE_CAPTURE_CODE = 1001;

    Button mCaptureButton;
    ImageView mImageView;
    TextView textView;
    Uri image_uri;
    Bitmap bitmap;
    TextToSpeech textToSpeech;


    void convertTextToSpeech(String text){
        textToSpeech.speak(text,TextToSpeech.QUEUE_FLUSH,null);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.colorguide);

        mImageView = (ImageView) findViewById(R.id.image_view);
        mCaptureButton = (Button) findViewById(R.id.capture_btn);
        textView = (TextView)findViewById(R.id.textView);

        mImageView.setDrawingCacheEnabled(true);
        mImageView.buildDrawingCache(true);



        //tts instantiation
        textToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status == TextToSpeech.SUCCESS) {
                    int result = textToSpeech.setLanguage(Locale.US);
                    if (result == TextToSpeech.LANG_MISSING_DATA
                            || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("error", "This Language is not supported");
                    } else {
                        //
                    }
                } else {
                    Log.e("error", "Initilization Failed!");
                }
            }
        });



        mImageView.setOnTouchListener(
                new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if(event.getAction() == event.ACTION_DOWN || event.getAction() == event.ACTION_MOVE){
                            bitmap = mImageView.getDrawingCache();
                            int pixel = bitmap.getPixel((int)event.getX(),(int)event.getY());

                            int red = Color.red(pixel);
                            int green = Color.green(pixel);
                            int blue = Color.blue(pixel);

                            textView.setText(getColourName(pixel));
                            convertTextToSpeech(getColourName(pixel));
                            textView.setTextColor(pixel);
                        }
                        return true;
                    }
                }
        );



    }

    public static String getColourName(int c) {
        String name = null;
        int colour = Color.BLACK;

        if(Color.red(c) > 127)  { colour |= Color.RED; }
        if(Color.green(c) > 127) { colour |= Color.GREEN; }
        if(Color.blue(c) > 127) { colour |= Color.BLUE; }

        switch (colour) {
            case Color.BLACK:
                name = "BLACK";
                break;
            case Color.BLUE:
                name = "BLUE";
                break;
            case Color.GREEN:
                name = "GREEN";
                break;
            case Color.CYAN:
                name = "CYAN";
                break;
            case Color.RED:
                name = "RED";
                break;
            case Color.MAGENTA:
                name = "MAGENTA";
                break;
            case Color.YELLOW:
                name = "YELLOW";
                break;
            case Color.WHITE:
                name = "WHITE";
                break;
            // Add more colors here
            case Color.DKGRAY:
                name = "DARK GRAY";
                break;
            case Color.LTGRAY:
                name = "LIGHT GRAY";
                break;
            case Color.GRAY:
                name = "GRAY";
                break;
            case Color.LTGRAY | Color.RED:
                name = "PINK";  // Example: Light Gray with a touch of Red
                break;
            // Add more cases as needed
        }
        return name;
    }




    public void takePicture(View view) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){

            if (checkSelfPermission(Manifest.permission.CAMERA) ==
                    PackageManager.PERMISSION_DENIED ||
                    checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
                            PackageManager.PERMISSION_DENIED){
                //permission not enabled, request it
                String[] permission = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
                //show popup to request permissions
                requestPermissions(permission, PERMISSION_CODE);
            }else{
                openCamera();
            }
        }else{
            openCamera();
        }
    }

    private void openCamera(){
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE,"New Picture");
        values.put(MediaStore.Images.Media.DESCRIPTION,"From the Camera");
        image_uri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,values);

        //load camera intent
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT,image_uri);
        startActivityForResult(cameraIntent,IMAGE_CAPTURE_CODE);
    }

//handeling user permission result


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case PERMISSION_CODE:{
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    //if permission is granted open camera
                    openCamera();
                }
                else{
                    //if permission is denied then show a popup
                    Toast.makeText(this,"Permission Denied..",Toast.LENGTH_LONG).show();
                }
            }
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        //after image is taken
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            //setting image to imageView;
            mImageView.setImageURI(image_uri);
        }
    }


    //set ontouch listeners







}
