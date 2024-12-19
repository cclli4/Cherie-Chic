package com.example.chriechic.order;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import com.example.chriechic.R;
import com.example.chriechic.utils.FunctionHelper;
import com.google.android.material.button.MaterialButton;

public class OrderActivity extends AppCompatActivity {

    public static final String DATA_TITLE = "TITLE";
    String strTitle;
    int paket1 = 10500, paket2 = 34000, paket3 = 23700, paket4 = 22500, paket5 = 16500, paket6 = 26000;
    int countP1, countP2, countP3, countP4, countP5, countP6, totalItems, totalPrice;
    ImageView imageAdd1, imageAdd2, imageAdd3, imageAdd4, imageAdd5, imageAdd6,
            imageMinus1, imageMinus2, imageMinus3, imageMinus4, imageMinus5, imageMinus6;
    Toolbar toolbar;
    TextView tvPaket1, tvPaket2, tvPaket3, tvPaket4, tvPaket5, tvPaket6,
            tvPaket11, tvJumlahBelanja, tvTotalPrice;
    MaterialButton btnCheckout;
    OrderViewModel orderViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        setStatusbar();
        setInitLayout();
        setDynamicPackageControls();
        setInputData();
    }

    private void setInitLayout() {
        tvPaket11 = findViewById(R.id.tvPaket11);
        toolbar = findViewById(R.id.toolbar);
        tvPaket1 = findViewById(R.id.tvPaket1);
        tvPaket2 = findViewById(R.id.tvPaket2);
        tvPaket3 = findViewById(R.id.tvPaket3);
        tvPaket4 = findViewById(R.id.tvPaket4);
        tvPaket5 = findViewById(R.id.tvPaket5);
        tvPaket6 = findViewById(R.id.tvPaket6);
        tvJumlahBelanja = findViewById(R.id.tvJumlahPorsi);
        tvTotalPrice = findViewById(R.id.tvTotalPrice);
        imageAdd1 = findViewById(R.id.imageAdd1);
        imageAdd2 = findViewById(R.id.imageAdd2);
        imageAdd3 = findViewById(R.id.imageAdd3);
        imageAdd4 = findViewById(R.id.imageAdd4);
        imageAdd5 = findViewById(R.id.imageAdd5);
        imageAdd6 = findViewById(R.id.imageAdd6);
        imageMinus1 = findViewById(R.id.imageMinus1);
        imageMinus2 = findViewById(R.id.imageMinus2);
        imageMinus3 = findViewById(R.id.imageMinus3);
        imageMinus4 = findViewById(R.id.imageMinus4);
        imageMinus5 = findViewById(R.id.imageMinus5);
        imageMinus6 = findViewById(R.id.imageMinus6);
        btnCheckout = findViewById(R.id.btnCheckout);

        strTitle = getIntent().getExtras().getString(DATA_TITLE);
        if (strTitle != null) {
            setSupportActionBar(toolbar);
            assert getSupportActionBar() != null;
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(strTitle);
        }

        tvPaket11.setPaintFlags(tvPaket11.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        orderViewModel = new ViewModelProvider(this).get(OrderViewModel.class);
    }

    private void setDynamicPackageControls() {
        setPackageControls(imageAdd1, imageMinus1, tvPaket1, paket1);
        setPackageControls(imageAdd2, imageMinus2, tvPaket2, paket2);
        setPackageControls(imageAdd3, imageMinus3, tvPaket3, paket3);
        setPackageControls(imageAdd4, imageMinus4, tvPaket4, paket4);
        setPackageControls(imageAdd5, imageMinus5, tvPaket5, paket5);
        setPackageControls(imageAdd6, imageMinus6, tvPaket6, paket6);
    }

    private void setPackageControls(ImageView imageAdd, ImageView imageMinus, TextView tvPaket, int paketPrice) {
        imageAdd.setOnClickListener(v -> {
            int currentCount = Integer.parseInt(tvPaket.getText().toString());
            currentCount++;
            tvPaket.setText(String.valueOf(currentCount));
            updatePackagePrice(paketPrice, currentCount);
        });

        imageMinus.setOnClickListener(v -> {
            int currentCount = Integer.parseInt(tvPaket.getText().toString());
            if (currentCount > 0) {
                currentCount--;
                tvPaket.setText(String.valueOf(currentCount));
                updatePackagePrice(paketPrice, currentCount);
            }
        });
    }

    private void updatePackagePrice(int paketPrice, int currentCount) {
        if (paketPrice == paket1) {
            countP1 = paket1 * currentCount;
        } else if (paketPrice == paket2) {
            countP2 = paket2 * currentCount;
        } else if (paketPrice == paket3) {
            countP3 = paket3 * currentCount;
        } else if (paketPrice == paket4) {
            countP4 = paket4 * currentCount;
        } else if (paketPrice == paket5) {
            countP5 = paket5 * currentCount;
        } else if (paketPrice == paket6) {
            countP6 = paket6 * currentCount;
        }

        setTotalPrice();
    }

    private void setTotalPrice() {
        // Hitung total jumlah item dari TextView langsung
        totalItems = getItemCountFromTextView(tvPaket1) +
                getItemCountFromTextView(tvPaket2) +
                getItemCountFromTextView(tvPaket3) +
                getItemCountFromTextView(tvPaket4) +
                getItemCountFromTextView(tvPaket5) +
                getItemCountFromTextView(tvPaket6);

        // Hitung total harga
        totalPrice = countP1 + countP2 + countP3 + countP4 + countP5 + countP6;

        // Update tampilan jumlah item dan harga total
        tvJumlahBelanja.setText(totalItems + " items");
        tvTotalPrice.setText(FunctionHelper.rupiahFormat(totalPrice));
    }

    private int getItemCountFromTextView(TextView textView) {
        try {
            return Integer.parseInt(textView.getText().toString());
        } catch (NumberFormatException e) {
            return 0; // Jika parsing gagal, default ke 0
        }
    }

    private void setInputData() {
        btnCheckout.setOnClickListener(v -> {
            if (totalItems == 0 || totalPrice == 0) {
                Toast.makeText(OrderActivity.this, "Ups, pilih produk dulu!", Toast.LENGTH_SHORT).show();
            } else {
                orderViewModel.addDataOrder(strTitle, totalItems, totalPrice);

                Toast.makeText(OrderActivity.this,
                        "Yeay! Pesanan Anda sedang diproses, cek di menu riwayat!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    public void setStatusbar() {
        if (Build.VERSION.SDK_INT < 21) {
            setWindowFlag(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, true);
        }
        if (Build.VERSION.SDK_INT >= 19) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            }
        }
        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
    }

    private void setWindowFlag(final int bits, boolean on) {
        Window window = getWindow();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        if (on) {
            layoutParams.flags |= bits;
        } else {
            layoutParams.flags &= ~bits;
        }
        window.setAttributes(layoutParams);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
