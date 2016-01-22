package tp2.info.iut.acy.fr.applilauncher;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import android.content.ActivityNotFoundException;


public class AppliLauncher extends Activity implements OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_appli_launcher);

        // regle tous les onClickListener des bouton sur le onClick de this.
        Button launch=(Button)findViewById(R.id.btn_launch);
        launch.setOnClickListener(this);

        Button call=(Button)findViewById(R.id.call);
        call.setOnClickListener(this);

        Button web = (Button)findViewById(R.id.web);
        web.setOnClickListener(this);

        Button unknow=(Button)findViewById(R.id.unknow);
        unknow.setOnClickListener(this);

        Button launch_calculator = (Button)findViewById(R.id.launch_calculator);
        launch_calculator.setOnClickListener(this);


        // créer un écouteur qui attend un clic sur le bouton plus
//        launch.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.d(" bouton ", "appuye");
//                Toast.makeText(getApplicationContext(), "Démarrage", Toast.LENGTH_SHORT).show();
//            }
//        });

    }

    //switch selon l'id du bouton cliqué
    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btn_launch:
                // lancer l'application
                Log.d(" bouton appuyé ", "lancement");
                Toast.makeText(getApplicationContext(), "Démarrage", Toast.LENGTH_SHORT).show();
                break;

            // lorsque le bouton d'appel du site est cliqué
            case R.id.call:
                // lancer l'appel
                Log.d(" bouton appuyé ", "appel");
                Toast.makeText(getApplicationContext(), "Appel", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "06 85 21 45 06"));
                startActivity(intent);
                break;

            // lorsque le bouton de lancement du site est cliqué
            case R.id.web:
                Log.d(" bouton appuyé ", "web");
                Toast.makeText(getApplicationContext(), "Connexion", Toast.LENGTH_SHORT).show();
                // lance un intent pour se connecter à un site web fort sympatique
                Intent i = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://colin-peyrat.com"));
                startActivity(i);
                break;

            // lorsque le bouton de lancement de la calculette est cliqué
            case R.id.launch_calculator:
                Log.d(" bouton appuyé ", "calculette");
                Toast.makeText(getApplicationContext(), "Lancement de la calculette", Toast.LENGTH_SHORT).show();

                // permet de gérer l'exception si l'activité n'es pas trouvée
                try{
                    Intent intentCalc = new Intent(getBaseContext(),Calculette.class);
                    startActivity(intentCalc);
                } catch (ActivityNotFoundException e){
                    Log.e("exception","activité pas trouvé");
                    // avertis l'utilisateur par un toast si c'est le cas
                    Toast.makeText(getApplicationContext(), "Activité non trouvée", Toast.LENGTH_SHORT).show();
                }
                break;

            // si le bouton utilisé n'a pu être ide  ntifié
            default:
                Log.d(" bouton appuyé ", "inconnu");
                Toast.makeText(getApplicationContext(), "Bouton inconnu", Toast.LENGTH_SHORT).show();
                break;

        }
    }
}
