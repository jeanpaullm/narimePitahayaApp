package com.example.jeanpaul.hylointerface;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.util.ArrayList;

public class Chores extends AppCompatActivity {

    String serverUrl = "https://hwthon18.herokuapp.com/hylo";
    ArrayList<Informe> informes = new ArrayList<>();
    String url = "https://hwthon18.herokuapp.com/hylo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chores);
        NetworkManager.getInstance(this);
        getTareas();
    }

    public void getTareas() {
        NetworkManager.getInstance().post("{\"tareaPendiente\":true}", new customListener<String>() {
            @Override
            public void getResult(String result) {
                if (!result.isEmpty()) {

                    JsonParser parser = new JsonParser();
                    JsonElement tradeElement = parser.parse(result);
                    JsonArray response = tradeElement.getAsJsonArray();

                    Gson gson = new Gson();
                    informes.clear();
                    Informe informe;
                    for(int i = 0; i < response.size(); i++) {
                        informe = gson.fromJson(response.get(i).toString(), Informe.class);
                        if(informe.isTareaPendiente()) {
                            informes.add(informe);
                        }
                    }
                    createTareasButtons();
                }
            }
        });
    }

    public void createTareasButtons() {
        Log.d("sup", "inicio creacion de botones \n");
        final LinearLayout choreContainer = (LinearLayout) findViewById(R.id.ll_choreContainer);
        choreContainer.removeAllViews();
        //BotonDinamico button;
        for(int i = 0; i < informes.size(); i++) {
            Informe informe = informes.get(i);
            if(informe.getTareas() != null) {
                for(int j = 0; j < informe.getTareas().size(); j++) {
                    Tarea tarea = informe.getTareas().get(j);
                    if(tarea.isTareaPendiente()) {
                        final BotonDinamico button = new BotonDinamico(this);
                        button.setIdInforme(informe.getIdInforme());
                        button.setIdTarea(tarea.getIdTarea());
                        button.setText("Planta: " + informe.getIdPlanta() + "\nTarea: " + tarea.getPatogeno());
                        button.setOnClickListener(new View.OnClickListener() {
                                                      @Override
                                                      public void onClick(View v) {
                                                          realertMiedo(button);
                                                      }
                                                  });
                        choreContainer.addView(button);
                    }
                }
            }
        }
    }

    public void realertMiedo(final BotonDinamico buton){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Desea confirmar que se realizo esta tarea?");
        //builder.setMessage("You are about to delete all records of database. Do you really want to proceed ?");
        builder.setCancelable(false);
        builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "La tarea se ha actualizado exitosamente", Toast.LENGTH_SHORT).show();
                buton.completeChore();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                getTareas();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {/*
                Toast.makeText(getApplicationContext(), "You've changed your mind to delete all records", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
          */}
        });

        builder.show();
    }

}

class BotonDinamico extends android.support.v7.widget.AppCompatButton {

    long idInforme;
    int idTarea;

    public BotonDinamico(Context context) {
        super(context);
    }

    public void completeChore() {

        String jsonStringRequest = "{\"idInforme\":" + idInforme + ",\"idTarea\":" + idTarea + "}";

        NetworkManager.getInstance().put(jsonStringRequest, new customListener<String>() {
            @Override
            public void getResult(String result) {
                if (!result.isEmpty()) {

                }
            }
        });
    }

    public long getIdInforme() {
        return idInforme;
    }

    public void setIdInforme(long idInforme) {
        this.idInforme = idInforme;
    }

    public int getIdTarea() {
        return idTarea;
    }

    public void setIdTarea(int idTarea) {
        this.idTarea = idTarea;
    }

}
