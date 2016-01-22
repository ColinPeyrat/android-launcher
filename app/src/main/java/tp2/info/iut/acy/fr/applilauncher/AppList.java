package tp2.info.iut.acy.fr.applilauncher;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by peyratc on 22/01/2016.
 */
public class AppList extends Activity {

    private ListView _activityList;


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

        // Création d'un SimpleAdapter qui met en correspondance les items présents dans la list avec ceux de la vue
        SimpleAdapter itemsAdapter = new SimpleAdapter(this.getBaseContext(), appItemList, R.layout.app_item,
                new String[] {"TextAppTitle", "TextAppSummary", "App_icon"}, new int[] {R.id.TextAppTitle,
                R.id.TextAppSummary, R.id.App_icon});

        _activityList.setAdapter(itemsAdapter);



    }

    // méthode privée permettant de remplir un HashMap
    private HashMap<String, String> fillHashMap(String Title, String summary, String icon){
        HashMap<String, String> item = new HashMap<String, String>();
        item.put("TextAppTitle", Title);
        item.put("TextAppSummary", summary);
        item.put("App_icon", icon);
        return item;
    }
}
