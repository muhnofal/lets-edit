package com.example.projectse;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class UserProfile extends AppCompatActivity {

    UserPhotoProfile userPhotoProfile = new UserPhotoProfile();
    Uri uri;
    ImageView photo;
    TextView FullName, Email, Gender;
//    Button saveBtn;
    private DatabaseReference databaseReference, ref;
    private FirebaseUser user;
    String uid;
    private StorageReference msStorageRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        photo = (ImageView) findViewById(R.id.UserPhoto);
//        addPhoto = (TextView) findViewById(R.id.addphoto);
        FullName = (TextView) findViewById(R.id.FullnameUser);
        Email = (TextView) findViewById(R.id.EmailUser);
        Gender = (TextView) findViewById(R.id.GenderUser);
//        saveBtn = (Button) findViewById(R.id.SaveButton);
        user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();

//        getImage("imageprofile/"+userPhotoProfile.getImage() + getFileExtension(uri),photo);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        msStorageRef = FirebaseStorage.getInstance().getReference("imageprofile");
        ref = FirebaseDatabase.getInstance().getReference().child("imageprofile");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String fullname = dataSnapshot.child("user").child(uid).child("nama").getValue(String.class);
                String email = dataSnapshot.child("user").child(uid).child("email").getValue(String.class);
                String gender = dataSnapshot.child("user").child(uid).child("gender").getValue(String.class);

                FullName.setText(fullname);
                Email.setText(email);
                Gender.setText(gender);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
//
//        addPhoto.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                chooseFile();
//                saveBtn.setVisibility(View.VISIBLE);
//            }
//        });

//        saveBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                uploadFile();
//                saveBtn.setVisibility(View.INVISIBLE);
//            }
//        });

    }


    public void chooseFile(){
        Intent intent1 = new Intent();
        intent1.setType("image/*");
        intent1.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent1,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1 && resultCode == RESULT_OK
                && data != null && data.getData() != null){
            uri = data.getData();

            Picasso.get().load(uri).into(photo);
//            iv_attach.setImageURI(uri);
        }
        photo.setImageURI(uri);
    }

    private String getFileExtension(Uri uri){

        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));

    }

    private void uploadFile(){
        if(uri != null){
            StorageReference fileReference = msStorageRef.child(System.currentTimeMillis() + "." + getFileExtension(uri));

            fileReference.putFile(uri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Toast.makeText(UserProfile.this, "Image changed", Toast.LENGTH_LONG).show();
                            UserPhotoProfile photoProfile = new UserPhotoProfile(taskSnapshot.getUploadSessionUri().toString());
                            String uploadId = ref.push().getKey();
                            ref.child(uploadId).setValue(photoProfile);

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(UserProfile.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
//                    double progress = (100.0 * taskSnapshot.getBytesTransferred()/ taskSnapshot.getTotalByteCount());
//
                }
            });
        }else{
            Toast.makeText(this, "No File Selected", Toast.LENGTH_SHORT).show();
        }
    }

//    public void getImage(String data, final ImageView){
//        msStorageRef.child(data).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//            @Override
//            public void onSuccess(Uri uri) {
//
//            }
//        })
//    }

}
