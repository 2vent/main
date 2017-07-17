package com.example.win.a2vent;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class test extends AppCompatActivity {

    TextView messageText;
    Button uploadButton;
    int serverResponseCode = 0;
    ProgressDialog dialog = null;


    String upLoadServerUri = null;

    /**********  File Path *************/
//    final String uploadFilePath = "/storage/emulated/0/DCIM/Camera/";//경로를 모르겠으면, 갤러리 어플리케이션 가서 메뉴->상세 정보
//    final String uploadFileName = "123.jpg"; //전송하고자하는 파일 이름
//     final String uploadFilePath = FileProvider.getUriForFile(MainActivity.this,
//            "com.example.win.myapplication.provider",null).toString();//경로를 모르겠으면, 갤러리 어플리케이션 가서 메뉴->상세 정보
//     String uploadFileName=null; //전송하고자하는 파일 이름



    //사진 관련
    ImageView iv;
    Button btn_gall;
    Button btn_picture;
    Uri photoUri;
    String file_name;
    String file_dir;

    private static final int PICK_FROM_CAMERA = 1; //카메라 촬영으로 사진 가져오기
    private static final int PICK_FROM_ALBUM = 2; //앨범에서 사진 가져오기
    private static final int CROP_FROM_CAMERA = 3; //가져온 사진을 자르기 위한 변수

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        uploadButton = (Button)findViewById(R.id.uploadButton);
        messageText  = (TextView)findViewById(R.id.messageText);



        //퍼미션 리스너
        PermissionListener permissionListener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                Toast.makeText(test.this, "Permission Granted", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPermissionDenied(ArrayList<String> deniedPermissions) {
                Toast.makeText(test.this, "Permission Denied\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
            }
        };

        new TedPermission(this).setPermissionListener(permissionListener)
                .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.ACCESS_NETWORK_STATE,Manifest.permission.INTERNET,Manifest.permission.CAMERA)
                .check();








//        messageText.setText("Uploading file path :- '/mnt/sdcard/"+uploadFileName+"'");


        /************* Php script path ****************/
        upLoadServerUri = "http://192.168.0.106/eventApp/jaeyun.php";//서버컴퓨터의 ip주소

        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


//                final String uploadFilePath = photoUri.toString();//경로를 모르겠으면, 갤러리 어플리케이션 가서 메뉴->상세 정보

                dialog = ProgressDialog.show(test.this, "", "Uploading file...", true);

                Log.e("파일 위치 : ",file_dir);
                Log.e("파일 명 : ",file_name);

                new Thread(new Runnable() {
                    public void run() {
                        runOnUiThread(new Runnable() {
                            public void run() {
                                messageText.setText("uploading started.....");
                            }
                        });

                        uploadFile(file_dir + "" + file_name,file_dir,file_name);



                    }
                }).start();
            }
        });
        ///////////////////////////// 이미지 //////////////////////////
        iv = (ImageView) findViewById(R.id.imageView);
        btn_gall = (Button) findViewById(R.id.gall);
        btn_picture = (Button) findViewById(R.id.take_picture);

        btn_gall.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {

                                            goToAlbum();

                                        }
                                    }
        );
        btn_picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePhoto();
            }
        });










    }

    public int uploadFile(String sourceFileUri,String file_dir,String file_name) {

        String fileName = sourceFileUri;
        String uploadFilePath=file_dir;
        String uploadFileName=file_name;
        HttpURLConnection conn = null;
        DataOutputStream dos = null;
        String lineEnd = "\r\n";
        String twoHyphens = "--";
        String boundary = "*****";
        int bytesRead, bytesAvailable, bufferSize;
        byte[] buffer;
        int maxBufferSize = 1 * 1024 * 1024;
        File sourceFile = new File(sourceFileUri);

        if (!sourceFile.isFile()) {

            dialog.dismiss();


            Log.e("uploadFile", "Source File not exist :"
                    +uploadFilePath + "" + uploadFileName);

            runOnUiThread(new Runnable() {
                public void run() {
//                    messageText.setText("Source File not exist :"
//                            +uploadFilePath + "" + uploadFileName);
                }
            });

            return 0;

        }
        else
        {
            try {

                // open a URL connection to the Servlet
                FileInputStream fileInputStream = new FileInputStream(sourceFile);
                URL url = new URL(upLoadServerUri);

                // Open a HTTP  connection to  the URL
                conn = (HttpURLConnection) url.openConnection();
                conn.setDoInput(true); // Allow Inputs
                conn.setDoOutput(true); // Allow Outputs
                conn.setUseCaches(false); // Don't use a Cached Copy
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Connection", "Keep-Alive");
                conn.setRequestProperty("ENCTYPE", "multipart/form-data");
                conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
                conn.setRequestProperty("uploaded_file", fileName);

                dos = new DataOutputStream(conn.getOutputStream());

                dos.writeBytes(twoHyphens + boundary + lineEnd);
                dos.writeBytes("Content-Disposition: form-data; name=\"uploaded_file\";filename=\""
                        + fileName + "\"" + lineEnd);

                dos.writeBytes(lineEnd);

                // create a buffer of  maximum size
                bytesAvailable = fileInputStream.available();

                bufferSize = Math.min(bytesAvailable, maxBufferSize);
                buffer = new byte[bufferSize];

                // read file and write it into form...
                bytesRead = fileInputStream.read(buffer, 0, bufferSize);

                while (bytesRead > 0) {

                    dos.write(buffer, 0, bufferSize);
                    bytesAvailable = fileInputStream.available();
                    bufferSize = Math.min(bytesAvailable, maxBufferSize);
                    bytesRead = fileInputStream.read(buffer, 0, bufferSize);

                }

                // send multipart form data necesssary after file data...
                dos.writeBytes(lineEnd);
                dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

                // Responses from the server (code and message)
                serverResponseCode = conn.getResponseCode();
                String serverResponseMessage = conn.getResponseMessage();

                Log.i("uploadFile", "HTTP Response is : "
                        + serverResponseMessage + ": " + serverResponseCode);

                if(serverResponseCode == 200){

                    runOnUiThread(new Runnable() {
                        public void run() {

//                            String msg = "File Upload Completed.\n\n See uploaded file here : \n\n"
//                                    +uploadFileName;

//                            messageText.setText(msg);
                            Toast.makeText(test.this, "File Upload Complete.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    });
                }

                //close the streams //
                fileInputStream.close();
                dos.flush();
                dos.close();

            } catch (MalformedURLException ex) {

                dialog.dismiss();
                ex.printStackTrace();

                runOnUiThread(new Runnable() {
                    public void run() {
                        messageText.setText("MalformedURLException Exception : check script url.");
                        Toast.makeText(test.this, "MalformedURLException",
                                Toast.LENGTH_SHORT).show();
                    }
                });

                Log.e("Upload file to server", "error: " + ex.getMessage(), ex);
            } catch (Exception e) {

                dialog.dismiss();
                e.printStackTrace();

                runOnUiThread(new Runnable() {
                    public void run() {
                        messageText.setText("Got Exception : see logcat ");
                        Toast.makeText(test.this, "Got Exception : see logcat ",
                                Toast.LENGTH_SHORT).show();
                    }
                });
                Log.e("Upload file to server Exception", "Exception : "
                        + e.getMessage(), e);
            }
            dialog.dismiss();
            return serverResponseCode;


        } // End else block

    }

    private void takePhoto() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE); //사진을 찍기 위하여 설정합니다.
        File photoFile = null;
        try {
            photoFile = createImageFile();
        } catch (IOException e) {
            Toast.makeText(test.this, "이미지 처리 오류! 다시 시도해주세요.", Toast.LENGTH_SHORT).show();              finish();
        }
        if (photoFile != null) {
            photoUri = FileProvider.getUriForFile(test.this,
                    "com.example.win.a2vent.provider", photoFile); //FileProvider의 경우 이전 포스트를 참고하세요.
            Toast.makeText(this, photoUri.toString(), Toast.LENGTH_SHORT).show();
            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri); //사진을 찍어 해당 Content uri를 photoUri에 적용시키기 위함
            startActivityForResult(intent, PICK_FROM_CAMERA);
        }
    }

    // Android M에서는 Uri.fromFile 함수를 사용하였으나 7.0부터는 이 함수를 사용할 시 FileUriExposedException이
    // 발생하므로 아래와 같이 함수를 작성합니다. 이전 포스트에 참고한 영문 사이트를 들어가시면 자세한 설명을 볼 수 있습니다.
    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("HHmmss").format(new Date());
        String imageFileName = "IP" + timeStamp + "_";
        File storageDir = new File(Environment.getExternalStorageDirectory() + "/test/"); //test라는 경로에 이미지를 저장하기 위함
        if (!storageDir.exists()) {
            storageDir.mkdirs();
        }
        File image = File.createTempFile(
                imageFileName,
                ".jpg",
                storageDir
        );
        file_dir=storageDir.toString()+"/";
        file_name=image.getName();

        return image;
    }

    private void goToAlbum() {
        Intent intent = new Intent(Intent.ACTION_PICK); //ACTION_PICK 즉 사진을 고르겠다!
        intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
        startActivityForResult(intent, PICK_FROM_ALBUM);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK) {
            Toast.makeText(test.this, "이미지 처리 오류! 다시 시도해주세요.", Toast.LENGTH_SHORT).show();
        }
        if (requestCode == PICK_FROM_ALBUM) {
            if(data==null){
                return;
            }
            photoUri = data.getData();
            cropImage();
        } else if (requestCode == PICK_FROM_CAMERA) {
            cropImage();
            MediaScannerConnection.scanFile(test.this, //앨범에 사진을 보여주기 위해 Scan을 합니다.
                    new String[]{photoUri.getPath()}, null,
                    new MediaScannerConnection.OnScanCompletedListener() {
                        public void onScanCompleted(String path, Uri uri) {
                        }
                    });
        } else if (requestCode == CROP_FROM_CAMERA) {
            try { //저는 bitmap 형태의 이미지로 가져오기 위해 아래와 같이 작업하였으며 Thumbnail을 추출하였습니다.
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), photoUri);
                Bitmap thumbImage = ThumbnailUtils.extractThumbnail(bitmap, 128, 128);
                ByteArrayOutputStream bs = new ByteArrayOutputStream();
                thumbImage.compress(Bitmap.CompressFormat.JPEG, 100, bs); //이미지가 클 경우 OutOfMemoryException 발생이 예상되어 압축

                //여기서는 ImageView에 setImageBitmap을 활용하여 해당 이미지에 그림을 띄우시면 됩니다.
                iv.setImageBitmap(thumbImage);

            } catch (Exception e) {
                Log.e("ERROR", e.getMessage().toString());

            }
        }
    }

    //Android N crop image (이 부분에서 몇일동안 정신 못차렸습니다 ㅜ)
//모든 작업에 있어 사전에 FALG_GRANT_WRITE_URI_PERMISSION과 READ 퍼미션을 줘야 uri를 활용한 작업에 지장을 받지 않는다는 것이 핵심입니다.
    public void cropImage() {
        this.grantUriPermission("com.android.camera", photoUri,
                Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(photoUri, "image/*");

        List<ResolveInfo> list = getPackageManager().queryIntentActivities(intent, 0);
        grantUriPermission(list.get(0).activityInfo.packageName, photoUri,
                Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
        int size = list.size();
        if (size == 0) {
            Toast.makeText(this, "취소 되었습니다.", Toast.LENGTH_SHORT).show();
            return;
        } else {
            Toast.makeText(this, "용량이 큰 사진의 경우 시간이 오래 걸릴 수 있습니다.", Toast.LENGTH_SHORT).show();
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            intent.putExtra("crop", "true");
            intent.putExtra("aspectX", 4);
            intent.putExtra("aspectY", 3);
            intent.putExtra("scale", true);
            File croppedFileName = null;
            try {
                croppedFileName = createImageFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

            File folder = new File(Environment.getExternalStorageDirectory() + "/test/");
            File tempFile = new File(folder.toString(), croppedFileName.getName());

            photoUri = FileProvider.getUriForFile(test.this,
                    "com.example.win.a2vent.provider", tempFile);

            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);


            intent.putExtra("return-data", false);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
            intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString()); //Bitmap 형태로 받기 위해 해당 작업 진행

            Intent i = new Intent(intent);
            ResolveInfo res = list.get(0);
            i.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            i.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            grantUriPermission(res.activityInfo.packageName, photoUri,
                    Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);

            i.setComponent(new ComponentName(res.activityInfo.packageName, res.activityInfo.name));
            startActivityForResult(i, CROP_FROM_CAMERA);


        }

    }


}


