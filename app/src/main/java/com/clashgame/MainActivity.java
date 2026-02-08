package com.clashgame;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    
    private GameManager gameManager;
    private TextView goldText, elixirText, gemsText, levelText;
    private TextView townHallText, barracksText, cannonsText;
    private Button buildTownHallBtn, buildBarracksBtn, buildCannonBtn;
    private Button collectGoldBtn, trainTroopsBtn, attackBtn;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        gameManager = new GameManager();
        
        initializeViews();
        updateUI();
        setupButtons();
    }
    
    private void initializeViews() {
        goldText = findViewById(R.id.goldText);
        elixirText = findViewById(R.id.elixirText);
        gemsText = findViewById(R.id.gemsText);
        levelText = findViewById(R.id.levelText);
        
        townHallText = findViewById(R.id.townHallText);
        barracksText = findViewById(R.id.barracksText);
        cannonsText = findViewById(R.id.cannonsText);
        
        buildTownHallBtn = findViewById(R.id.buildTownHallBtn);
        buildBarracksBtn = findViewById(R.id.buildBarracksBtn);
        buildCannonBtn = findViewById(R.id.buildCannonBtn);
        
        collectGoldBtn = findViewById(R.id.collectGoldBtn);
        trainTroopsBtn = findViewById(R.id.trainTroopsBtn);
        attackBtn = findViewById(R.id.attackBtn);
    }
    
    private void setupButtons() {
        buildTownHallBtn.setOnClickListener(v -> {
            if (gameManager.upgradeTownHall()) {
                showMessage("Centro da Vila melhorado!");
                updateUI();
            } else {
                showMessage("Recursos insuficientes!");
            }
        });
        
        buildBarracksBtn.setOnClickListener(v -> {
            if (gameManager.buildBarracks()) {
                showMessage("Quartel construído!");
                updateUI();
            } else {
                showMessage("Recursos insuficientes ou limite atingido!");
            }
        });
        
        buildCannonBtn.setOnClickListener(v -> {
            if (gameManager.buildCannon()) {
                showMessage("Canhão construído!");
                updateUI();
            } else {
                showMessage("Recursos insuficientes ou limite atingido!");
            }
        });
        
        collectGoldBtn.setOnClickListener(v -> {
            gameManager.collectGold();
            showMessage("Ouro coletado!");
            updateUI();
        });
        
        trainTroopsBtn.setOnClickListener(v -> {
            if (gameManager.trainTroops()) {
                showMessage("Tropas treinadas!");
                updateUI();
            } else {
                showMessage("Elixir insuficiente ou sem quartéis!");
            }
        });
        
        attackBtn.setOnClickListener(v -> {
            if (gameManager.attack()) {
                int loot = gameManager.getLastLoot();
                showMessage("Ataque realizado! Saqueou " + loot + " de ouro!");
                updateUI();
            } else {
                showMessage("Sem tropas disponíveis!");
            }
        });
    }
    
    private void updateUI() {
        goldText.setText("💰 Ouro: " + gameManager.getGold());
        elixirText.setText("⚗️ Elixir: " + gameManager.getElixir());
        gemsText.setText("💎 Gemas: " + gameManager.getGems());
        levelText.setText("⭐ Nível: " + gameManager.getLevel());
        
        townHallText.setText("🏰 Centro da Vila: Nv " + gameManager.getTownHallLevel());
        barracksText.setText("⚔️ Quartéis: " + gameManager.getBarracksCount());
        cannonsText.setText("🎯 Canhões: " + gameManager.getCannonsCount());
    }
    
    private void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
