package tn.esprit.project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.database.CursorWindow;

import java.lang.reflect.Field;

import android.app.AlertDialog;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

import tn.esprit.project.DAO.UserDAO;
import tn.esprit.project.database.MyDataBase;
import tn.esprit.project.models.User;


@SuppressWarnings("ALL")
public class ProfileActivity extends AppCompatActivity {

    public static User userprofile;
    private ImageView profileiv;
    private TextView name;
    private EditText firstname;
    private EditText lastname;
    private EditText email;
    private EditText password;
    private EditText phonenumber;
    private Button btnedit;

    final int REQUEST_CODE_GALLERY = 999;
@Override
    public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_profile);

        btnedit = findViewById(R.id.idtnedit);
        profileiv =findViewById(R.id.avatarimageid);
        name =  findViewById(R.id.idname);
        firstname =  findViewById(R.id.idfirstname);
        lastname =  findViewById(R.id.idlastname);
        email = findViewById(R.id.idemail);
        password =  findViewById(R.id.idpassword);
        phonenumber = findViewById(R.id.idphonenumber);

        firstname.setText(userprofile.getFirstname());
        lastname.setText(userprofile.getLastname());
        email.setText(userprofile.getEmail());
        password.setText(userprofile.getPassword());
        phonenumber.setText(userprofile.getPhonenumber());
        name.setText(userprofile.getFirstname()+" "+userprofile.getLastname());

       // displayProfileImage();


        profileiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectImage(ProfileActivity.this);
            }
        });

        try {
            @SuppressLint("DiscouragedPrivateApi") Field field = CursorWindow.class.getDeclaredField("sCursorWindowSize");
            field.setAccessible(true);
            field.set(null, 100 * 1024 * 1024); //the 100MB is the new size
        } catch (Exception e) {
            if (e != null) {
                e.printStackTrace();
            }
        }


        btnedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            String firstnamestring = firstname.getText().toString();
            String lastnamestring = lastname.getText().toString();
            String emailstring = email.getText().toString();
            String passwordstring = password.getText().toString();
            String phonenumberstring = phonenumber.getText().toString();

            if (firstnamestring.isEmpty()|| lastnamestring.isEmpty() || emailstring.isEmpty() || passwordstring.isEmpty()

                    || phonenumberstring.isEmpty() )
            {
                Toast.makeText(getApplicationContext(), "Some Fields are empty !", Toast.LENGTH_SHORT).show();

            }
            else {

                MyDataBase db = MyDataBase.getDataBase(getApplicationContext());
                db.userdao().updateUserProfile(emailstring, firstnamestring, lastnamestring, passwordstring, phonenumberstring, ProfileActivity.userprofile.getUserId());
                Toast.makeText(getApplicationContext(), "Profile updated successfully!", Toast.LENGTH_SHORT).show();
                Intent profileintent = new Intent(ProfileActivity.this, Welcome.class);
                startActivity(profileintent);
            }
            }
        });

    }


    public void displayProfileImage()
    {

        MyDataBase database = MyDataBase.getDataBase(getApplicationContext());
        byte[] imageprofile = database.userdao().selectProfileImage(userprofile.getFirstname());

        Bitmap bitmap = BitmapFactory.decodeByteArray(imageprofile, 0, imageprofile.length);

        profileiv.setImageBitmap(bitmap);
    }

    private void selectImage(Context context) {
        final CharSequence[] options = {"Take Photo", "Choose from Gallery", "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Choose your profile picture");

        builder.setItems(options, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int item) {

                if (options[item].equals("Take Photo")) {
                    Intent takePicture = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(takePicture, 0);

                } else if (options[item].equals("Choose from Gallery")) {
                    Intent pickPhoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(pickPhoto, 1);

                } else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_CANCELED) {
            switch (requestCode) {
                case 0:

                    if (resultCode == RESULT_OK && data != null) {
                        Bitmap selectedImage = (Bitmap) data.getExtras().get("data");
                        profileiv.setImageBitmap(selectedImage);
                        MyDataBase db = MyDataBase.getDataBase(getApplicationContext());
                        UserDAO userdao = db.userdao();
                        userdao.updateUserImage(imageViewToByte(profileiv), userprofile.getFirstname());
                    }

                    break;
                case 1:
                    if (resultCode == RESULT_OK && data != null) {
                        Uri selectedImage = data.getData();
                        String[] filePathColumn = {MediaStore.Images.Media.DATA};
                        if (selectedImage != null) {
                            Cursor cursor = getApplicationContext().getContentResolver().query(selectedImage,
                                    filePathColumn, null, null, null);
                            if (cursor != null) {
                                cursor.moveToFirst();
                                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                                String picturePath = cursor.getString(columnIndex);
                                profileiv.setImageBitmap(BitmapFactory.decodeFile(picturePath));
                                MyDataBase db = MyDataBase.getDataBase(getApplicationContext());
                                UserDAO userdao = db.userdao();
                                userdao.updateUserImage(imageViewToByte(profileiv), userprofile.getFirstname());
                                cursor.close();
                            }
                        }

                    }
                    break;
            }
        }
    }

    public static byte[] imageViewToByte(ImageView image) {
        Bitmap bitmap = ((BitmapDrawable) image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }


}