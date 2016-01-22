package tp2.info.iut.acy.fr.applilauncher;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class Calculette extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculette);

        // récupérer les liens vers les éléments de l'interface graphiques
        Button plus=(Button)findViewById(R.id.button_plus);
        final EditText champText_valeur1 = (EditText)findViewById(R.id.editText1);
        final EditText champText_valeur2 = (EditText)findViewById(R.id.editText2);
        final TextView champText_resultat=(TextView)findViewById(R.id.result);

        // créer un écouteur qui attend un clic sur le bouton plus
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //créer la variable resultat qui vaudra 0 si jamais le calcul échoue
                float resultat = 0;
                //si le bouton est cliqué, alors on calcul la somme des deux textField
                //et on l'affiche dans le textView "result"
                try {
                    //convertis les 2 valeurs des textField en int
                    float val1 = Float.parseFloat(champText_valeur1.getText().toString());
                    float val2 = Float.parseFloat(champText_valeur2.getText().toString());
                    Log.d("val1", String.valueOf(val1));
                    Log.d("val2", String.valueOf(val2));

                    //additionne les 2 valeurs
                    resultat = val1 + val2;

                    //recupère l'erreur quand l'utilsateur n'ecrit rien dans un des deux champs.
                } catch (NumberFormatException e){

                    Log.e("Calculette","L'utilisateur a pas remplis un textField, le goujat.");

                    //envoie une alerte à l'utilisateur
                    Toast.makeText(getApplicationContext(), "Tous les champs sont obligatoires.", Toast.LENGTH_SHORT).show();

                }
                //affiche le résultat
                champText_resultat.setText("Résultat : " + String.valueOf(resultat));
            }
        });
        //Inscrit dans les logs que tout s'est bien passé si il n'y a pas eu d'erreur
        Log.i("Calculette", "Tout s'est bien passé");
    }
}
