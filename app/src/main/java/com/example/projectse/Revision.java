package com.example.projectse;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class Revision extends AppCompatActivity {

    Uri uri;
    EditText textArea;
    ImageView iv_attach;
    Button attacbtn, submit;
    private DatabaseReference reference;
    private StorageReference msStorageRef;


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_revision);

        textArea = (EditText) findViewById(R.id.textArea);
        attacbtn = (Button) findViewById(R.id.attachment);
        submit = (Button) findViewById(R.id.SubmitRevision);
        iv_attach = (ImageView) findViewById(R.id.attachmentImage);


        textArea.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                v.getParent().requestDisallowInterceptTouchEvent(true);
                switch (event.getAction() & MotionEvent.ACTION_MASK){
                    case MotionEvent.ACTION_UP:

                    v.getParent().requestDisallowInterceptTouchEvent(false);
                    break;
                }
                return false;
            }
        });

        attacbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseFile();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadFile();
                Intent intent = new Intent(Revision.this, RevisionSent.class);
                startActivity(intent);
            }
        });

        msStorageRef = FirebaseStorage.getInstance().getReference("Revision");
        reference = FirebaseDatabase.getInstance().getReference().child("Revision");

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

            Picasso.get().load(uri).into(iv_attach);
//            iv_attach.setImageURI(uri);
        }
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
                            Toast.makeText(Revision.this, "Revision sent", Toast.LENGTH_LONG).show();
                            Upload upload = new Upload(textArea.getText().toString().trim(),
                                    taskSnapshot.getUploadSessionUri().toString());
                            String uploadId = reference.push().getKey();
                            reference.child(uploadId).setValue(upload);

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(Revision.this, e.getMessage(), Toast.LENGTH_SHORT).show();
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


}
