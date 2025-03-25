package com.example.aplikasiandroidsederhana;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText editTextPanjang, editTextLebar, editTextTinggi, activeEditText;
    private Button buttonHitung, buttonReset;
    private TextView textViewHasil;
    private GridLayout keyboardLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextPanjang = findViewById(R.id.editTextPanjang);
        editTextLebar = findViewById(R.id.editTextLebar);
        editTextTinggi = findViewById(R.id.editTextTinggi);
        buttonHitung = findViewById(R.id.buttonHitung);
        buttonReset = findViewById(R.id.buttonReset);
        textViewHasil = findViewById(R.id.textViewHasil);
        keyboardLayout = findViewById(R.id.keyboardLayout);

        // Set listener agar EditText yang aktif bisa menerima input
        View.OnClickListener setActiveEditText = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activeEditText = (EditText) v;
            }
        };

        editTextPanjang.setOnClickListener(setActiveEditText);
        editTextLebar.setOnClickListener(setActiveEditText);
        editTextTinggi.setOnClickListener(setActiveEditText);

        // Inisialisasi tombol keyboard numerik
        setupKeyboard();

        // Event tombol Hitung
        buttonHitung.setOnClickListener(v -> hitungVolume());

        // Event tombol Reset
        buttonReset.setOnClickListener(v -> resetInput());
    }

    private void setupKeyboard() {
        int[] buttonIds = {
                R.id.btn1, R.id.btn2, R.id.btn3,
                R.id.btn4, R.id.btn5, R.id.btn6,
                R.id.btn7, R.id.btn8, R.id.btn9,
                R.id.btn0, R.id.btnDot, R.id.btnDelete
        };

        for (int id : buttonIds) {
            Button button = findViewById(id);
            button.setOnClickListener(v -> {
                if (activeEditText != null) {
                    String text = button.getText().toString();
                    if (text.equals("⌫")) {
                        String currentValue = activeEditText.getText().toString();
                        if (!currentValue.isEmpty()) {
                            activeEditText.setText(currentValue.substring(0, currentValue.length() - 1));
                        }
                    } else {
                        activeEditText.append(text);
                    }
                }
            });
        }
    }

    private void hitungVolume() {
        String panjangStr = editTextPanjang.getText().toString();
        String lebarStr = editTextLebar.getText().toString();
        String tinggiStr = editTextTinggi.getText().toString();

        if (!panjangStr.isEmpty() && !lebarStr.isEmpty() && !tinggiStr.isEmpty()) {
            double panjang = Double.parseDouble(panjangStr);
            double lebar = Double.parseDouble(lebarStr);
            double tinggi = Double.parseDouble(tinggiStr);
            double volume = panjang * lebar * tinggi;

            textViewHasil.setText("Hasil Volume: " + volume);
        } else {
            textViewHasil.setText("Mohon isi semua bidang!");
        }
    }

    private void resetInput() {
        editTextPanjang.setText("");
        editTextLebar.setText("");
        editTextTinggi.setText("");
        textViewHasil.setText("Hasil Volume: -");
    }
}
