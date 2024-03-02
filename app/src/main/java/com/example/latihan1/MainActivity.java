package com.example.latihan1;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.latihan1.R;

public class MainActivity extends AppCompatActivity {

    EditText Nama, Nol1, Nol2, Nol3;
    Button Bayar;
    RadioGroup radioGroup;
    RadioButton rbGold, rbSilver, rbReguler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Nama = findViewById(R.id.Nama);
        Nol1 = findViewById(R.id.Nol1);
        Nol2 = findViewById(R.id.Nol2);
        Nol3 = findViewById(R.id.Nol3);
        Bayar = findViewById(R.id.Bayar);
        rbReguler = findViewById(R.id.rbReguler);
        rbGold = findViewById(R.id.rbGold);
        rbSilver = findViewById(R.id.rbSilver);
        radioGroup = findViewById(R.id.radioGroup);

        Bayar.setOnClickListener(v -> {
            String NamaPembeli = Nama.getText().toString().trim();
            String barang1 = Nol1.getText().toString().trim();
            String barang2 = Nol2.getText().toString().trim();
            String barang3 = Nol3.getText().toString().trim();

            if (barang1.isEmpty() || barang2.isEmpty() || barang3.isEmpty()) {
                Toast.makeText(MainActivity.this, "Harap isi semua field!", Toast.LENGTH_SHORT).show();
                return;
            }

            createReceipt();
        });
    }

    private void createReceipt() {
        double totalHarga = hitungTotalHarga(Integer.parseInt(Nol1.getText().toString()), Integer.parseInt(Nol2.getText().toString()), Integer.parseInt(Nol3.getText().toString()));
        double diskon = hitungDiskon(totalHarga);
        double potonganHarga = hitungPotonganHarga();
        tampilkanBon(totalHarga, diskon, potonganHarga);
    }

    private double hitungTotalHarga(int jumlahBarang1, int jumlahBarang2, int jumlahBarang3) {
        double hargaBarang1 = 250000;
        double hargaBarang2 = 240000;
        double hargaBarang3 = 270000;

        double totalHarga = (hargaBarang1 * jumlahBarang1) + (hargaBarang2 * jumlahBarang2) + (hargaBarang3 * jumlahBarang3);
        return totalHarga;
    }

    private double hitungDiskon(double totalHarga) {
        double diskon = 0;
        if (totalHarga >= 240000) {
            diskon = 0.1 * totalHarga;
        }
        return diskon;
    }

    private double hitungPotonganHarga() {
        double potonganHarga = 0;
        int selectedRadioButtonId = radioGroup.getCheckedRadioButtonId();
        if (selectedRadioButtonId == R.id.rbGold) {
            potonganHarga = 50000;
        } else if (selectedRadioButtonId == R.id.rbSilver) {
            potonganHarga = 30000;
        } else if (selectedRadioButtonId == R.id.rbReguler) {
            potonganHarga = 10000;
        }
        return potonganHarga;
    }

    private void tampilkanBon(double totalHarga, double diskon, double potonganHarga) {
        double totalPembayaran = totalHarga - diskon - potonganHarga;

        // Buat pesan bon belanja dalam format teks
        String bonMessage = "Total Harga: " + totalHarga + "\n"
                + "Diskon: " + diskon + "\n"
                + "Potongan Harga: " + potonganHarga + "\n"
                + "Total Pembayaran: " + totalPembayaran;

        // Buat AlertDialog untuk menampilkan bon belanja
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Bon Belanja");
        builder.setMessage(bonMessage);
        builder.setPositiveButton("OK", new     DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // Kosongkan atau tambahkan tindakan yang ingin Anda lakukan setelah pengguna menekan tombol "OK"
            }
        });

        // Tampilkan AlertDialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}