package com.example.juan.marcadorbaloncesto;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int marcadorLocal=0;
    int marcadorVisitante=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        masUnoLocal();
        masDosLocal();
        masTresLocal();
        menosUnoLocal();
        masUnoVisitante();
        masDosVisitante();
        masTresVisitante();
        menosUnoVisitante();
        reset();
        finalizar();
    }

    private void masUnoLocal() {
        Button button = (Button) findViewById(R.id.masUnoLocal);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                actualizarMarcadorLocal(1);
            }
        });
    }

    private void masDosLocal() {
        Button button = (Button) findViewById(R.id.masDosLocal);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                actualizarMarcadorLocal(2);
            }
        });
    }

    private void masTresLocal() {
        Button button = (Button) findViewById(R.id.masTresLocal);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                actualizarMarcadorLocal(3);
            }
        });
    }

    private void menosUnoLocal() {
        Button button = (Button) findViewById(R.id.menosUnoLocal);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (marcadorLocal<=0){
                    Context context = getApplicationContext();
                    CharSequence text = "El marcador no puede ser menor a 0";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }else{
                    actualizarMarcadorLocal(-1);
                }

            }
        });
    }


    private void masUnoVisitante() {
        Button button = (Button) findViewById(R.id.masUnoVisitante);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                actualizarMarcadorVisitante(1);
            }
        });
    }

    private void masDosVisitante() {
        Button button = (Button) findViewById(R.id.masDosVisitante);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                actualizarMarcadorVisitante(2);
            }
        });
    }

    private void masTresVisitante() {
        Button button = (Button) findViewById(R.id.masTresVisitante);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                actualizarMarcadorVisitante(3);
            }
        });
    }

    private void menosUnoVisitante() {
        Button button = (Button) findViewById(R.id.menosUnoVisitante);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (marcadorVisitante<=0){
                    Context context = getApplicationContext();
                    CharSequence text = "El marcador no puede ser menor a 0";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }else{
                    actualizarMarcadorVisitante(-1);
                }
            }
        });
    }


    private void finalizar(){
        Button button = (Button) findViewById(R.id.end);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dialogoConfirmacionFinalizar();
            }

        });
    }

    private void dialogoConfirmacionFinalizar() {
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        Context context = getApplicationContext();
                        CharSequence text = comprobarGanador();
                        int duration = Toast.LENGTH_LONG;

                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();

                        resetearMarcadores();
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:

                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("¿Estás seguro?, al finalizar el partido se obtiene el resultado del mism0 y se reinician los marcadores ").setPositiveButton("Si", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();
    }

    private String comprobarGanador(){
        if(marcadorLocal>marcadorVisitante){
            return "El ganador es el equipo local";
        }else if(marcadorLocal<marcadorVisitante){
            return "El ganador es el equipo visitante";
        }else{
            return "El partido ha terminado empate";
        }

    }

    private void reset() {
        Button button = (Button) findViewById(R.id.reset);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dialogoConfirmacionReset();
            }

        });
    }

    private void dialogoConfirmacionReset() {
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        resetearMarcadores();
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:

                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("¿Estás seguro?, si reinicia el marcador se perderá el resultado actual").setPositiveButton("Si", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();
    }

    private void actualizarMarcadorLocal(int puntos){
            marcadorLocal = marcadorLocal + puntos;
            final TextView textoMarcador = (TextView) findViewById(R.id.puntosLocal);
            textoMarcador.setText(""+marcadorLocal);

        };

    private void actualizarMarcadorVisitante(int puntos){
        marcadorVisitante= marcadorVisitante + puntos;
        final TextView textoMarcador2 = (TextView) findViewById(R.id.puntosVisitante);
        textoMarcador2.setText(""+marcadorVisitante);

    };

    private void resetearMarcadores(){
        marcadorLocal=0;
        marcadorVisitante=0;
        actualizarMarcadorLocal(0);
        actualizarMarcadorVisitante(0);
    }



    }

