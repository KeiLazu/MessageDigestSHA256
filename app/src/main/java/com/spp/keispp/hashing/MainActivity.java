package com.spp.keispp.hashing;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    Button btn_check;
    EditText txt_input;
    TextView lbl_byte, lbl_hash, lbl_ori;
    byte[] input, outputsha256;

    public void init() {
        btn_check = (Button)findViewById(R.id.btn_Check);
        txt_input = (EditText)findViewById(R.id.txt_Input);

        lbl_byte = (TextView)findViewById(R.id.lbl_Byte);
        lbl_hash = (TextView)findViewById(R.id.lbl_Hash);
        lbl_ori = (TextView)findViewById(R.id.lbl_Original);
    }

    public void HashSHA256() throws NoSuchAlgorithmException, UnsupportedEncodingException {

        input = txt_input.getText().toString().getBytes("UTF-8");

        MessageDigest sha256 = MessageDigest.getInstance("SHA-256");

        sha256.reset();
        sha256.update(input);
        outputsha256 = sha256.digest();

        //create hex output

        StringBuffer hexStringSHA256 = new StringBuffer();

        for (int i=0;i<outputsha256.length;i++) {
            hexStringSHA256.append(Integer.toHexString(0xff & outputsha256[i]));
        }

        String converting = new String(input, "UTF-8");

        Log.d(TAG, "\nHashSHA256: " + hexStringSHA256.toString() +
        "\nInput: " + input +
        "\nPlain: " + converting);

        lbl_hash.setText("Hash: \n" + hexStringSHA256.toString());
        lbl_byte.setText("Byte: \n" + input.toString());
        lbl_ori.setText("Original: \n" + converting);

    }

    protected void configButton() {
        btn_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    HashSHA256();
                } catch (Exception e) {
                    e.getMessage();
                }
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        configButton();

    }
}
