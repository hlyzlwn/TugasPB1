package com.example.latihan1;

import android.content.ClipData;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;

public class ReceiptActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Di sini Anda dapat mengambil data belanjaan dari intent
        // Jika Anda telah meneruskan data dari MainActivity
        List<ClipData.Item> shoppingItems = (List<ClipData.Item>) getIntent().getSerializableExtra("shopping_items");
        double totalPrice = getIntent().getDoubleExtra("total_price", 0.0);

        // Selanjutnya, Anda dapat menampilkan data belanjaan dan total harga di tata letak bon belanja
        // Misalnya, Anda dapat mengatur teks pada TextView untuk menampilkan detail belanjaan dan total harga
    }
}