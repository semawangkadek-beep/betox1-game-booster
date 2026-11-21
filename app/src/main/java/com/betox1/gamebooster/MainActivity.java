
package com.betox1.gamebooster;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;
import android.app.ActivityManager;
import android.content.Context;

public class MainActivity extends Activity {

    LinearLayout root;

    int red = Color.rgb(255, 35, 45);
    int dark = Color.rgb(10, 10, 12);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showDashboard();
    }

    TextView text(String value, int size) {
        TextView t = new TextView(this);
        t.setText(value);
        t.setTextColor(Color.WHITE);
        t.setTextSize(size);
        t.setPadding(12, 12, 12, 12);
        return t;
    }

    void showDashboard() {
        root = new LinearLayout(this);
        root.setOrientation(1);
        root.setPadding(20, 24, 20, 20);
        root.setBackgroundColor(dark);

        TextView title = text("BETOx1", 32);
        title.setTextColor(red);
        title.setTypeface(null, Typeface.BOLD);
        root.addView(title);

        root.addView(text(
            "GAME BOOSTER ULTIMATE v1.0", 13));

        TextView status = text(
            "● SYSTEM ONLINE", 14);
        status.setTextColor(Color.GREEN);
        root.addView(status);

        addPanel("PERFORMANCE MONITOR",
            "RAM: " + getRamInfo() +
            "\nCPU: Informasi sistem tersedia");

        addPanel("GAME MODE",
            "Gaming session manager siap digunakan");

        addButton("REFRESH MONITOR", () -> {
            showDashboard();
            Toast.makeText(this,
                "Monitor diperbarui",
                Toast.LENGTH_SHORT).show();
        });

        addButton("GAME LIBRARY", () ->
            Toast.makeText(this,
                "Game Library segera hadir",
                Toast.LENGTH_SHORT).show());

        addButton("SYSTEM INFORMATION", () ->
            Toast.makeText(this,
                "Android " +
                android.os.Build.VERSION.RELEASE,
                Toast.LENGTH_LONG).show());

        setContentView(root);
    }

    String getRamInfo() {
        ActivityManager am =
            (ActivityManager)getSystemService(
                Context.ACTIVITY_SERVICE);

        ActivityManager.MemoryInfo info =
            new ActivityManager.MemoryInfo();

        am.getMemoryInfo(info);

        long total = info.totalMem / 1048576;
        long available = info.availMem / 1048576;
        long used = total - available;

        return used + " MB / " + total + " MB";
    }

    void addPanel(String heading, String detail) {
        TextView panel = text(
            heading + "\n" + detail, 15);
        panel.setBackgroundColor(
            Color.rgb(30, 18, 20));
        panel.setPadding(18, 20, 18, 20);
        root.addView(panel);
    }

    void addButton(String label, Runnable action) {
        Button button = new Button(this);
        button.setText(label);
        button.setTextColor(Color.WHITE);
        button.setBackgroundTintList(
            android.content.res.ColorStateList.valueOf(red));
        button.setOnClickListener(v -> action.run());
        root.addView(button);
    }
                   }
