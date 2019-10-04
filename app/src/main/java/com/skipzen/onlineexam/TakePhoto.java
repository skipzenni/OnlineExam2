package com.skipzen.onlineexam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.method.MetaKeyKeyListener;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.annotations.Nullable;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;

public class TakePhoto extends AppCompatActivity implements View.OnClickListener {

    Button btnTake, btnChoose, btnUploud;
    ImageView imgTakke;
    private static final int PICK_IMAGE_REQUEST =234, PICK_IMAGE_CAPTURE=0;
    private Uri filepath, uri;
    private StorageReference mStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_photo);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        mStorage = FirebaseStorage.getInstance().getReference();

        btnTake =  findViewById(R.id.buttonTake);
        btnUploud =  findViewById(R.id.buttonUpload  );
        btnChoose =  findViewById(R.id.buttonChoose);
        imgTakke =  findViewById(R.id.imgTake);
    }

    private void showTakeFIle() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, PICK_IMAGE_CAPTURE);
    }
    private void showUploadFIle() {
        if(filepath != null){

            StorageReference riversRef = mStorage.child("images/profile.jpg");
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading..");
            progressDialog.show();

            riversRef.putFile(filepath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), "File Uploaded", Toast.LENGTH_LONG).show();

                            Intent intent = new Intent(TakePhoto.this, Home.class);
                            startActivity(intent);
                            finish();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), exception.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                    double progress = (100.0 * taskSnapshot.getBytesTransferred())/taskSnapshot.getTotalByteCount();
                    progressDialog.setMessage(((int) progress)+ "% Uploading...");
                }
            })
            ;
        } else if(uri != null){

            StorageReference linkImage = mStorage.child("images/profile.jpg").child(uri.getLastPathSegment());
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading..");
            progressDialog.show();
            linkImage.putFile(uri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), "File Uploaded", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(TakePhoto.this, Home.class);
                            startActivity(intent);
                            finish();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred())/taskSnapshot.getTotalByteCount();
                            progressDialog.setMessage(((int) progress)+ "% Uploading...");
                        }
                    });
        }

    }
    private void showFileChoosen() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select an Image"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null){
            filepath = data.getData();
            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filepath);
            } catch (IOException e) {
                e.printStackTrace();
            }
            imgTakke.setImageBitmap(bitmap);
        }else if(requestCode==PICK_IMAGE_CAPTURE && resultCode == RESULT_OK && data != null && data.getData() != null){
            uri = data.getData();
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imgTakke.setImageBitmap(bitmap);
        }
    }
    @Override
    public void onClick(View view) {
        if(view == btnChoose){
            showFileChoosen();
        }else if(view == btnUploud) {
            showUploadFIle();
        }
        else if(view == btnTake){
            showTakeFIle();
        }
    }
}
