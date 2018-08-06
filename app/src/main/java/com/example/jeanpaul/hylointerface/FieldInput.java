package com.example.jeanpaul.hylointerface;

import android.content.DialogInterface;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.zxing.qrcode.encoder.QRCode;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Locale;

public class FieldInput extends AppCompatActivity {

    private MqttAndroidClient client;
    private Informe informe;
    private Tarea tareaBacterias;
    private Tarea tareaHongos;
    private Tarea tareaInsectos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_field_input);

        String clientId = MqttClient.generateClientId();
        client = new MqttAndroidClient(this.getApplicationContext(), "tcp://spectacular-hairdresser.cloudmqtt.com:1883",
                        clientId);

        MqttConnectOptions options = new MqttConnectOptions();
        String topic = "hwthon/hylo/+";
        options.setUserName("hkadwsqx");
        options.setPassword("BCTi-JnC_3Hg".toCharArray());

        try {
            IMqttToken token = client.connect(options);
            token.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    // We are connected
                    Log.d("V", "onSuccess");
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    // Something went wrong e.g. connection timeout or firewall problems
                    Log.d("V", "onFailure");

                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }

        informe = new Informe();
        informe.setIdPlanta(SharedValues.getTempPlantId());
        TextView textView = (TextView)findViewById(R.id.tv_idPlanta);
        textView.setText(informe.getIdPlanta());

        tareaBacterias = new Tarea();
        tareaBacterias.setPatogeno("bacterias");

        tareaHongos = new Tarea();
        tareaHongos.setPatogeno("hongos");

        tareaInsectos = new Tarea();
        tareaInsectos.setPatogeno("insectos");

    }

    public void displayValue(String string) {
        Toast.makeText(this,string, Toast.LENGTH_SHORT).show();
    }

    public void incrementPrimordios(View view) {
        this.informe.incrementPrimordios();
        TextView t = (TextView)findViewById(R.id.tv_primordiosCounter);
        t.setText(Integer.toString(this.informe.getPrimordios()));
    }

    public void decrementPrimordios(View view) {
        this.informe.decrementPrimordios();
        TextView t = (TextView)findViewById(R.id.tv_primordiosCounter);
        t.setText(Integer.toString(this.informe.getPrimordios()));
    }

    public void incrementFlores(View view) {
        this.informe.incrementFlores();
        TextView t = (TextView)findViewById(R.id.tv_floresCounter);
        t.setText(Integer.toString(this.informe.getFlores()));
    }

    public void decrementFlores(View view) {
        this.informe.decrementFlores();
        TextView t = (TextView)findViewById(R.id.tv_floresCounter);
        t.setText(Integer.toString(this.informe.getFlores()));
    }

    public void incrementFrutosVerdes(View view) {
        this.informe.incrementFrutosVerdes();
        TextView t = (TextView)findViewById(R.id.tv_frutosVerdesCounter);
        t.setText(Integer.toString(this.informe.getFrutosVerdes()));
    }

    public void decrementFrutosVerdes(View view) {
        this.informe.decrementFrutosVerdes();
        TextView t = (TextView)findViewById(R.id.tv_frutosVerdesCounter);
        t.setText(Integer.toString(this.informe.getFrutosVerdes()));
    }

    public void incrementFrutosPintones(View view) {
        this.informe.incrementFrutosPintones();
        TextView t = (TextView)findViewById(R.id.tv_frutosPintonesCounter);
        t.setText(Integer.toString(this.informe.getFrutosPintones()));
    }

    public void decrementFrutosPintones(View view) {
        this.informe.decrementFrutosPintones();
        TextView t = (TextView)findViewById(R.id.tv_frutosPintonesCounter);
        t.setText(Integer.toString(this.informe.getFrutosPintones()));
    }

    public void incrementFrutosMaduros(View view) {
        this.informe.incrementFrutosMaduros();
        TextView t = (TextView)findViewById(R.id.tv_frutosMadurosCounter);
        t.setText(Integer.toString(this.informe.getFrutosMaduros()));
    }

    public void decrementFrutosMaduros(View view) {
        this.informe.decrementFrutosMaduros();
        TextView t = (TextView)findViewById(R.id.tv_frutosMadurosCounter);
        t.setText(Integer.toString(this.informe.getFrutosMaduros()));
    }

    public void checkBacterias(View view) {
        CheckBox checkBox = (CheckBox)view;
        if(checkBox.isChecked()){
            this.informe.setBacterias(true);
            this.tareaBacterias.setTareaPendiente(true);
        }
        else {
            this.informe.setBacterias(false);
            this.tareaBacterias.setTareaPendiente(false);
        }
    }

    public void checkHongos(View view) {
        CheckBox checkBox = (CheckBox)view;
        if(checkBox.isChecked()){
            this.informe.setHongos(true);
            this.tareaHongos.setTareaPendiente(true);
        }
        else {
            this.informe.setHongos(false);
            this.tareaHongos.setTareaPendiente(false);
        }
    }

    public void checkInsectos(View view) {
        CheckBox checkBox = (CheckBox)view;
        if(checkBox.isChecked()){
            this.informe.setInsectos(true);
            this.tareaInsectos.setTareaPendiente(true);
        }
        else {
            this.informe.setInsectos(false);
            this.tareaInsectos.setTareaPendiente(false);
        }
    }

    public void enviarInformacion() {
        informe.setFecha(new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date()));
        informe.setHora(new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date()));
        informe.setIdInforme(new Timestamp(new Date().getTime()).getTime());
        String topic = "hwthon/hylo/test";

        if(tareaBacterias.isTareaPendiente()) {
            tareaBacterias.setIdTarea(informe.getTareas().size());
            informe.getTareas().add(tareaBacterias);
        }

        if(tareaHongos.isTareaPendiente()) {
            tareaHongos.setIdTarea(informe.getTareas().size());
            informe.getTareas().add(tareaHongos);
        }

        if(tareaInsectos.isTareaPendiente()) {
            tareaInsectos.setIdTarea(informe.getTareas().size());
            informe.getTareas().add(tareaInsectos);
        }

        Gson gson = new Gson();
        String payload = gson.toJson(informe);

        byte[] encodedPayload = new byte[0];
        displayValue("intentando enviar info...");

        try {
            encodedPayload = payload.getBytes("UTF-8");
            MqttMessage message = new MqttMessage(encodedPayload);
            client.publish(topic, message);
            displayValue("info enviada");
        } catch (UnsupportedEncodingException | MqttException e) {
            e.printStackTrace();
        }

        try {
            IMqttToken disconToken = client.disconnect();
            disconToken.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    // we are now successfully disconnected
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken,
                                      Throwable exception) {
                    // something went wrong, but probably we are disconnected anyway
                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }

    }

    public void alertMiedo(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirm dialog demo !");
        builder.setMessage("You are about to delete all records of database. Do you really want to proceed ?");
        builder.setCancelable(false);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "You've choosen to delete all records", Toast.LENGTH_SHORT).show();
                enviarInformacion();
                realertMiedo();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "You've changed your mind to delete all records", Toast.LENGTH_SHORT).show();
            }
        });

        builder.show();
    }

    public void realertMiedo(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirm dialog demo !");
        builder.setMessage("You are about to delete all records of database. Do you really want to proceed ?");
        builder.setCancelable(false);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "You've choosen to delete all records", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getBaseContext(), LectorQR.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "You've changed your mind to delete all records", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        builder.show();
    }


}

