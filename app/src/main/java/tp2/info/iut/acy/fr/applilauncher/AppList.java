package tp2.info.iut.acy.fr.applilauncher;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by peyratc on 22/01/2016.
 */
public class AppList extends Activity {

    private ListView _activityList;

    // variable statique pour différencier les réponses
    static int PRENDRE_PHOTO_FLAG = 1;
    static int PRENDRE_VIDEO_FLAG = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.list_layout);

        //bind avec la listView de la vue
        _activityList = (ListView)findViewById(R.id.ListView_app);

        //remplis la arraylist avec la méthode fillHashMap
        ArrayList<HashMap<String,String>> appItemList = new ArrayList<HashMap<String,String>>();
        appItemList.add(fillHashMap("Calculette", "Apprendre à compter", String.valueOf((R.drawable.ic_calculator))));
        appItemList.add(fillHashMap("Help", "Appeler Moman", String.valueOf((R.drawable.ic_phone))));
        appItemList.add(fillHashMap("Web", "Découvrir un site incroyable !", String.valueOf((R.drawable.ic_web))));
        appItemList.add(fillHashMap("Photo", "Prendre une photo", String.valueOf((R.drawable.ic_photo))));

        // Création d'un SimpleAdapter qui met en correspondance les items présents dans la list avec ceux de la vue
        SimpleAdapter itemsAdapter = new SimpleAdapter(this.getBaseContext(), appItemList, R.layout.app_item,
                new String[] {"TextAppTitle", "TextAppSummary", "App_icon"}, new int[] {R.id.TextAppTitle,
                R.id.TextAppSummary, R.id.App_icon});

        _activityList.setAdapter(itemsAdapter);

        // créer un listener OnClick pour tous les items de la ListView
        _activityList.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Debug la position de l'item cliqué
                Log.d("position",String.valueOf(position));


                // position contient la position de l'item dans la ListView en commencant à 0
                switch (position){
                    // Item "Caclulette" cliqué sur le ListView
                    case 0:
                        try{
                            Intent intentCalc = new Intent(getBaseContext(),Calculette.class);
                            startActivity(intentCalc);
                        } catch (ActivityNotFoundException e){
                            Log.e("exception","activité pas trouvé");
                            // avertis l'utilisateur par un toast si c'est le cas
                            Toast.makeText(getApplicationContext(), "Activité non trouvée", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    // Item "Help" cliqué sur le ListView

                    case 1:
                        // lancer l'appel
                        Log.d(" bouton appuyé ", "appel");
                        Toast.makeText(getApplicationContext(), "Appel", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "06 85 21 45 06"));
                        startActivity(intent);
                        break;

                    // Item "Web" cliqué sur le ListView
                    case 2:
                        Log.d(" bouton appuyé ", "web");
                        Toast.makeText(getApplicationContext(), "Connexion", Toast.LENGTH_SHORT).show();
                        // lance un intent pour se connecter à un site web fort sympatique
                        Intent i = new Intent(Intent.ACTION_VIEW,
                                Uri.parse("http://colin-peyrat.com"));
                        startActivity(i);
                        break;

                    // Item "Photo" cliqué sur le ListView
                    case 3:
                        Log.d(" bouton appuyé ", "photo");
                        Toast.makeText(getApplicationContext(), "Lancement de l'appareil photo", Toast.LENGTH_SHORT).show();
                        takeAPicture();
                        break;

                }

            }
        });



    }

    // méthode privée permettant de remplir un HashMap
    private HashMap<String, String> fillHashMap(String Title, String summary, String icon){
        HashMap<String, String> item = new HashMap<String, String>();
        item.put("TextAppTitle", Title);
        item.put("TextAppSummary", summary);
        item.put("App_icon", icon);
        return item;
    }

    // méthode qui prend une photo
    private void takeAPicture(){
        // on crée un Intent d'appel au lancement de la fonctionnalite photo de l appareil
        Intent photoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        // ajouter des information à l'Intent: ou enregistrer la photo
//        File lieuSauvegarde = new File(Environment.getExternalStorageDirectory(), "AppliLauncher.jpg");
//        Uri fichierDeSortie = Uri.fromFile(lieuSauvegarde);
//
//        // mise à jour du nom de fichier de sauvegarde
//        String filepath_last = fichierDeSortie.toString();
//
//        // on ajoute a l intent des informations sur le fichier d enregistrement de l image
//        photoIntent.putExtra(MediaStore.EXTRA_OUTPUT, fichierDeSortie);

        /* lancement de l intent... avec attente de reponse.... lorsque la reponse est disponible
        * la methode qui suit: 'onActivityResult est appelee automatiquement
        */
        startActivityForResult(photoIntent, PRENDRE_PHOTO_FLAG);
    }

    // Pour recuperer la photo ou la video selon le resultCode
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);

        // debug de la méthode
        Log.d("ActivityResult","Appelé");
        Log.d("Result code",String.valueOf(requestCode));

        // si le code résultat correspond au flag de la photo
        if(requestCode == PRENDRE_PHOTO_FLAG){

//            Toast.makeText(getApplicationContext(), "Photo prise", Toast.LENGTH_SHORT).show();
//            if (data == null) {
//                // vérifie l'existance et le contenu de l'Intent de retour data
//                Toast.makeText(getApplicationContext(), "Donnée impossible à recuperer", Toast.LENGTH_SHORT).show();
//            } else {
//                Toast.makeText(getApplicationContext(), "Donnée recuperé", Toast.LENGTH_SHORT).show();
//            }

//            if (resultCode == RESULT_OK) {
//                Toast.makeText(this, "Photo saved to:\n", Toast.LENGTH_LONG).show();
//            } else if (resultCode == RESULT_CANCELED) {
//                Toast.makeText(this, "Photo recording cancelled.",
//                        Toast.LENGTH_LONG).show();
//            } else {
//                Toast.makeText(this, "Failed to prendre photo",
//                        Toast.LENGTH_LONG).show();
//            }
            if (resultCode == RESULT_OK) {
                if(data != null){
                    Toast.makeText(this, "Video saved to:\n" +
                            data.getData(), Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "Fromage" +
                            data.getData(), Toast.LENGTH_LONG).show();
                }
            }


        } else if(requestCode == PRENDRE_VIDEO_FLAG){

            Toast.makeText(getApplicationContext(), "Vidéo prise", Toast.LENGTH_SHORT).show();

        }

    }

}
