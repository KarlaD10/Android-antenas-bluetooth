package com.example.bluetooth;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    UUID uuid = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    BluetoothSocket bluetoothSocket;
    BluetoothAdapter bluetoothAdapter;
    BluetoothDevice hc06;
    ConnectedThread MyConexionBT;

    String edovalvula1 = "b", edovalvula2 = "d", edovalvula3 = "f", edovalvula4 = "h",
           edovalvula5 = "j", edovalvula6 = "l", edovalvula7 = "n", edovalvula8 = "p",
           edovalvula9 = "r", edovalvula10 = "t", edovalvula11 = "v", edovalvula12 = "x",
           edovalvula13 = "z";

    String mensaje = "", id = "", edo = "";
    int s;

    Button btnConectar, btnDesconectar,
           btn_valvula1, btn_valvula2, btn_valvula3, btn_valvula4,
           btn_valvula5, btn_valvula6, btn_valvula7, btn_valvula8,
           btn_valvula9, btn_valvula10, btn_valvula11, btn_valvula12,
           btn_valvula13;

    TextView txt_valvula1, txt_valvula2, txt_valvula3, txt_valvula4,
             txt_valvula5, txt_valvula6, txt_valvula7, txt_valvula8,
             txt_valvula9, txt_valvula10, txt_valvula11, txt_valvula12,
             txt_valvula13;

    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnConectar = findViewById(R.id.btnConectar);
        btnDesconectar = findViewById(R.id.btnDesconectar);

        btn_valvula1  = findViewById(R.id.btn_valvula1);
        btn_valvula2  = findViewById(R.id.btn_valvula2);
        btn_valvula3  = findViewById(R.id.btn_valvula3);
        btn_valvula4  = findViewById(R.id.btn_valvula4);
        btn_valvula5  = findViewById(R.id.btn_valvula5);
        btn_valvula6  = findViewById(R.id.btn_valvula6);
        btn_valvula7  = findViewById(R.id.btn_valvula7);
        btn_valvula8  = findViewById(R.id.btn_valvula8);
        btn_valvula9  = findViewById(R.id.btn_valvula9);
        btn_valvula10 = findViewById(R.id.btn_valvula10);
        btn_valvula11 = findViewById(R.id.btn_valvula11);
        btn_valvula12 = findViewById(R.id.btn_valvula12);
        btn_valvula13 = findViewById(R.id.btn_valvula13);

        txt_valvula1  = findViewById(R.id.txt_valvula1);
        txt_valvula2  = findViewById(R.id.txt_valvula2);
        txt_valvula3  = findViewById(R.id.txt_valvula3);
        txt_valvula4  = findViewById(R.id.txt_valvula4);
        txt_valvula5  = findViewById(R.id.txt_valvula5);
        txt_valvula6  = findViewById(R.id.txt_valvula6);
        txt_valvula7  = findViewById(R.id.txt_valvula7);
        txt_valvula8  = findViewById(R.id.txt_valvula8);
        txt_valvula9  = findViewById(R.id.txt_valvula9);
        txt_valvula10 = findViewById(R.id.txt_valvula10);
        txt_valvula11 = findViewById(R.id.txt_valvula11);
        txt_valvula12 = findViewById(R.id.txt_valvula12);
        txt_valvula13 = findViewById(R.id.txt_valvula13);

        //Adaptador
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        //Direccion MAC del dispositivo Bluetooth
        hc06 = bluetoothAdapter.getRemoteDevice("44:44:10:07:19:ED");

        //Obtener el socket con el identificador universal.
        try {
            bluetoothSocket = hc06.createRfcommSocketToServiceRecord(uuid);
        } catch (IOException e) {
            e.printStackTrace();
        }

        btnConectar.setOnClickListener(v ->{
            try {
                bluetoothSocket.connect();
                Toast.makeText(this, "Conexion exitosa", Toast.LENGTH_SHORT).show();

                MyConexionBT = new ConnectedThread(bluetoothSocket);
                MyConexionBT.start();//Inicio del hilo

            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        btnDesconectar.setOnClickListener(v ->{
            try {
                bluetoothSocket.close();
                Toast.makeText(this, "Desconexion", Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        btn_valvula1.setOnClickListener(v -> onClick(btn_valvula1));
        btn_valvula2.setOnClickListener(v -> onClick(btn_valvula2));
        btn_valvula3.setOnClickListener(v -> onClick(btn_valvula3));
        btn_valvula4.setOnClickListener(v -> onClick(btn_valvula4));
        btn_valvula5.setOnClickListener(v -> onClick(btn_valvula5));
        btn_valvula6.setOnClickListener(v -> onClick(btn_valvula6));
        btn_valvula7.setOnClickListener(v -> onClick(btn_valvula7));
        btn_valvula8.setOnClickListener(v -> onClick(btn_valvula8));
        btn_valvula9.setOnClickListener(v -> onClick(btn_valvula9));
        btn_valvula10.setOnClickListener(v -> onClick(btn_valvula10));
        btn_valvula11.setOnClickListener(v -> onClick(btn_valvula11));
        btn_valvula12.setOnClickListener(v -> onClick(btn_valvula12));
        btn_valvula13.setOnClickListener(v -> onClick(btn_valvula13));

    }

    @SuppressLint("NonConstantResourceId")
    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn_valvula1:
                if (edovalvula1.equals("b")){
                    MyConexionBT.write("a");
                    edovalvula1 = "a";
                }else{
                    MyConexionBT.write("b");
                    edovalvula1 = "b";
                }
                break;

            case R.id.btn_valvula2:
                if (edovalvula2.matches("d")){
                    //MyConexionBT.write("c");
                    edovalvula2 = "c";
                }else{
                    //MyConexionBT.write("d");
                    edovalvula2 = "d";
                }
                break;

            case R.id.btn_valvula3:
                if (edovalvula3.matches("f")){
                    //MyConexionBT.write("e");
                    edovalvula3 = "e";
                }else{
                   // MyConexionBT.write("f");
                    edovalvula3 = "f";
                }
                break;

            case R.id.btn_valvula4:
                if (edovalvula4.matches("h")){
                    //MyConexionBT.write("g");
                    edovalvula4 = "g";
                }else{
                    //MyConexionBT.write("h");
                    edovalvula4 = "h";
                }
                break;

            case R.id.btn_valvula5:
                if (edovalvula5.matches("j")){
                    //MyConexionBT.write("i");
                    edovalvula5 = "i";
                }else{
                    //MyConexionBT.write("j");
                    edovalvula5 = "j";
                }
                break;

            case R.id.btn_valvula6:
                if (edovalvula6.matches("l")){
                    //MyConexionBT.write("k");
                    edovalvula6 = "k";
                }else{
                    //MyConexionBT.write("l");
                    edovalvula6 = "l";
                }
                break;

            case R.id.btn_valvula7:
                if (edovalvula7.matches("n")){
                    //MyConexionBT.write("m");
                    edovalvula7 = "m";
                }else{
                    //MyConexionBT.write("n");
                    edovalvula7 = "n";
                }
                break;

            case R.id.btn_valvula8:
                if (edovalvula8.matches("p")){
                    //MyConexionBT.write("o");
                    edovalvula8 = "o";
                }else{
                    //MyConexionBT.write("p");
                    edovalvula8 = "p";
                }
                break;

            case R.id.btn_valvula9:
                if (edovalvula9.matches("r")){
                    //MyConexionBT.write("q");
                    edovalvula9 = "q";
                }else{
                    //MyConexionBT.write("r");
                    edovalvula9 = "r";
                }
                break;

            case R.id.btn_valvula10:
                if (edovalvula10.matches("t")){
                    //MyConexionBT.write("s");
                    edovalvula10 = "s";
                }else{
                    //MyConexionBT.write("t");
                    edovalvula10 = "t";
                }
                break;

            case R.id.btn_valvula11:
                if (edovalvula11.matches("v")){
                    //MyConexionBT.write("u");
                    edovalvula11 = "u";
                }else{
                    //MyConexionBT.write("v");
                    edovalvula11 = "v";
                }
                break;

            case R.id.btn_valvula12:
                if (edovalvula12.matches("x")){
                    //MyConexionBT.write("w");
                    edovalvula12 = "w";
                }else{
                    //MyConexionBT.write("x");
                    edovalvula12 = "x";
                }
                break;

            case R.id.btn_valvula13:
                if (edovalvula13.matches("z")){
                    //MyConexionBT.write("y");
                    edovalvula13 = "y";
                }else{
                    //MyConexionBT.write("z");
                    edovalvula13 = "z";
                }
                break;
        }
    }
    public void verificarEstado(String valvula){
        switch (valvula){
            case "a":
                edovalvula1 = "b";
                break;
            case "b":
                edovalvula1 = "a";
                break;
            case "c":
                edovalvula1 = "d";
                break;
            case "d":
                edovalvula1 = "c";
                break;
            case "e":
                edovalvula1 = "f";
                break;
            case "f":
                edovalvula1 = "e";
                break;
            case "g":
                edovalvula1 = "h";
                break;
            case "h":
                edovalvula1 = "g";
                break;
            case "i":
                edovalvula1 = "j";
                break;
            case "j":
                edovalvula1 = "i";
                break;
            case "k":
                edovalvula1 = "l";
                break;
            case "l":
                edovalvula1 = "k";
                break;
            case "m":
                edovalvula1 = "n";
                break;
            case "n":
                edovalvula1 = "m";
                break;
            case "o":
                edovalvula1 = "p";
                break;
            case "p":
                edovalvula1 = "o";
                break;
            case "q":
                edovalvula1 = "r";
                break;
            case "r":
                edovalvula1 = "q";
                break;
            case "s":
                edovalvula1 = "t";
                break;
            case "t":
                edovalvula1 = "s";
                break;
            case "u":
                edovalvula1 = "v";
                break;
            case "v":
                edovalvula1 = "u";
                break;
            case "w":
                edovalvula1 = "x";
                break;
            case "x":
                edovalvula1 = "w";
                break;
            case "y":
                edovalvula1 = "z";
                break;
            case "z":
                edovalvula1 = "y";
                break;

        }
    }

   private class ConnectedThread extends Thread{

        private OutputStream outputStream;
        private InputStream inputStream;

        public ConnectedThread(BluetoothSocket socket){
            try {
                outputStream = socket.getOutputStream();
                inputStream = socket.getInputStream();

            } catch (IOException ignored) { }

        }

        @SuppressLint("SetTextI18n")
        public void run() {

            while (true){
                    try {
                        do {
                            //Lectura del caracter que llega
                            s = inputStream.read();
                            //Convertir de int a caracter y acumular el mensaje.
                            mensaje = mensaje + (char)s;
                            //System.out.println("Mensaje: "+mensaje);
                        }while (s<125);

                        //Convertir el mensaje a tipo Json
                        JSONObject jsonObject = new JSONObject(mensaje);
                        mensaje = "";

                        id = jsonObject.getString("id"); //Obtener id
                        edo = jsonObject.getString("do"); //Obtener estado del id

                        System.out.println("id: "+id);
                        System.out.println("do: "+edo);

                        runOnUiThread(() -> {
                            switch (id) {
                                case "1":
                                    txt_valvula1.setText(id + "-" + edo);
                                    /*if (edo.equals("1")){
                                        edovalvula1 = "a";
                                    }else{
                                        edovalvula1 = "b";
                                    }*/
                                    break;
                                case "2":
                                    txt_valvula2.setText(id+"-"+edo);
                                    /*if (edo.equals("1")){
                                        edovalvula1 = "c";
                                    }else{
                                        edovalvula1 = "d";
                                    }*/
                                    break;
                                case "3":
                                    txt_valvula3.setText(id+"-"+edo);
                                    /*if (edo.equals("1")){
                                        edovalvula1 = "e";
                                    }else{
                                        edovalvula1 = "f";
                                    }*/
                                    break;
                                case "4":
                                    txt_valvula4.setText(id+"-"+edo);
                                    /*if (edo.equals("1")){
                                        edovalvula1 = "g";
                                    }else{
                                        edovalvula1 = "h";
                                    }*/
                                    break;
                                case "5":
                                    txt_valvula5.setText(id+"-"+edo);
                                    /*if (edo.equals("1")){
                                        edovalvula1 = "i";
                                    }else{
                                        edovalvula1 = "j";
                                    }*/
                                    break;
                                case "6":
                                    txt_valvula6.setText(id+"-"+edo);
                                    /*if (edo.equals("1")){
                                        edovalvula1 = "k";
                                    }else{
                                        edovalvula1 = "l";
                                    }*/
                                    break;
                                case "7":
                                    txt_valvula7.setText(id+"-"+edo);
                                    /*if (edo.equals("1")){
                                        edovalvula1 = "m";
                                    }else{
                                        edovalvula1 = "n";
                                    }*/
                                    break;
                                case "8":
                                    txt_valvula8.setText(id+"-"+edo);
                                    /*if (edo.equals("1")){
                                        edovalvula1 = "o";
                                    }else{
                                        edovalvula1 = "p";
                                    }*/
                                    break;
                                case "9":
                                    txt_valvula9.setText(id+"-"+edo);
                                    /*if (edo.equals("1")){
                                        edovalvula1 = "q";
                                    }else{
                                        edovalvula1 = "r";
                                    }*/
                                    break;
                                case "10":
                                    txt_valvula10.setText(id+"-"+edo);
                                    /*if (edo.equals("1")){
                                        edovalvula1 = "s";
                                    }else{
                                        edovalvula1 = "t";
                                    }*/
                                    break;
                                case "11":
                                    txt_valvula11.setText(id+"-"+edo);
                                    /*if (edo.equals("1")){
                                        edovalvula1 = "u";
                                    }else{
                                        edovalvula1 = "v";
                                    }*/
                                    break;
                                case "12":
                                    txt_valvula12.setText(id+"-"+edo);
                                    /*if (edo.equals("1")){
                                        edovalvula1 = "w";
                                    }else{
                                        edovalvula1 = "x";
                                    }*/
                                    break;
                                case "13":
                                    txt_valvula13.setText(id+"-"+edo);
                                    /*if (edo.equals("1")){
                                        edovalvula1 = "y";
                                    }else{
                                        edovalvula1 = "z";
                                    }*/
                                    break;
                            }
                        });
                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                        mensaje = "";
                    }
            }

        }

        public void write(String input){
            if (input!=null){
                try {
                    outputStream.write(input.getBytes());
                } catch (IOException e) {
                    Toast.makeText(MainActivity.this, "No es posible enviar datos", Toast.LENGTH_LONG).show();
                    verificarEstado(input);
                }
            }
        }


    }
}

