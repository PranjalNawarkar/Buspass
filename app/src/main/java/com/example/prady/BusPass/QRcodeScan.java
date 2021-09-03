package com.example.prady.BusPass;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Vibrator;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import java.io.IOException;

public class QRcodeScan extends AppCompatActivity {
    String TAG = "GenerateQRCode";
    DatabaseHelper db;
    SurfaceView svQR;
    CameraSource cameraSource;
    TextView tvQR;
    static  int count=1;
    BarcodeDetector barcodeDetector;
Button proceed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode_scan);
proceed=findViewById(R.id.btnProceed);
proceed.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {


        Toast.makeText(QRcodeScan.this,"Verified", Toast.LENGTH_SHORT).show();
        try{
            Thread.sleep(1000);
        }catch (Exception e){
            e.printStackTrace();
        }

        tvQR.setText("");
        //Intent intent = new Intent(QRcodeScan.this, ConductorLogin.class);
        //startActivity(intent);
    }
});
        db=new DatabaseHelper(this);
        svQR=(SurfaceView)findViewById(R.id.svQR);
        tvQR=(TextView)findViewById(R.id.tvQR);

        barcodeDetector=new BarcodeDetector.Builder(this).setBarcodeFormats(Barcode.QR_CODE).build();
        cameraSource=new CameraSource.Builder(this,barcodeDetector).setRequestedPreviewSize(640,480).build();

        svQR.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {

                if(ActivityCompat.checkSelfPermission(getApplicationContext(),Manifest.permission.CAMERA)!=PackageManager.PERMISSION_GRANTED){
                    return;
                }
                try{
                    cameraSource.start(holder);
                }catch (IOException e){
                    e.printStackTrace();
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {

            }
        });


        barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() {

            }

            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {

                final SparseArray<Barcode> qrCodes=detections.getDetectedItems();


                    if(qrCodes.size()!=0){
                        tvQR.post(new Runnable() {
                            @Override
                            public void run() {
                                try {

                                Vibrator vibrator=(Vibrator)getApplicationContext().getSystemService(VIBRATOR_SERVICE);
                                vibrator.vibrate(100);
                                    Thread.sleep(2000);

                                    tvQR.setText(qrCodes.valueAt(0).displayValue);
                                    String QRname = tvQR.getText().toString();
                                    Log.e(TAG, "onClick1: " + QRname);
                                    // Toast.makeText(QRcodeScan.this, "AAA"+QRname, Toast.LENGTH_SHORT).show();
                                }catch (Exception e){
                                    e.printStackTrace();
                                }


                            }
                        });
                    }



            }

        });
    }

}
