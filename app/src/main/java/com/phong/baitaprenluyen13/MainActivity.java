package com.phong.baitaprenluyen13;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText edtUser, edtPassword;
    CheckBox chkSave;
    String nameShare = "thongtindangnhap";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
    }

    private void addControls() {
        edtUser = (EditText) findViewById(R.id.edtUser);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        chkSave = (CheckBox) findViewById(R.id.chkSave);
    }

    public void XuLyDangNhap(View view) {
        //Giả sử User và Password nhập đúng:
        if (edtUser.getText().toString().equals("admin") && edtPassword.getText().toString().equals("123"))
        {
            Intent intent = new Intent(MainActivity.this, QuanLyActivity.class);
            startActivity(intent);
        }
    }

    public void XuLyThoat(View view) {
    }

    //onPause để lưu thông tin
    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences sharedPreferences = getSharedPreferences(nameShare, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        //Lưu thông tin:
        editor.putString("user", edtUser.getText().toString());
        editor.putString("password", edtPassword.getText().toString());
        editor.putBoolean("save", chkSave.isChecked());
        editor.commit();
    }

    //onResume để đọc/phục hồi
    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences preferences = getSharedPreferences(nameShare, MODE_PRIVATE);
        String user = preferences.getString("user", "");
        String pass = preferences.getString("password", "");
        boolean save = preferences.getBoolean("save", false);
        //Kiểm tra nếu đúng có lưu thì show lên:
        if (save)
        {
            edtUser.setText(user);
            edtPassword.setText(pass);
        }
        chkSave.setChecked(save);
    }
}
