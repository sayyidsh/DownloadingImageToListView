package pro.nanosystems.downloadingimagetolistview;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import pro.nanosystems.downloadingimagetolistview.model.ListData;

public class MainActivity extends AppCompatActivity {
    /*
    * 1- create Model Calss ListData.
    * 2- Create Download Image Class extends AsyncTask
    * 3- Add <uses-permission android:name="android.permission.INTERNET"/> to AndroidManifest.xml.
    * 4- Add <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/> to AndroidManifest.xml.
    * 5- add new layoutResource for row data.
    * 6- add List View in your main Layout.
    * 7- check if connected status in MainActivity class.
    * 8- create class extends BaseAdapter
    * 9- setAdapter to list view.
    * */
    private ListView listView;
    private ArrayList<ListData> listData;
    private ImageAdapter listAdapter;
    private ConnectivityManager connectivityManager;
    private NetworkInfo networkInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        networkInfo = connectivityManager.getActiveNetworkInfo();

        listView = findViewById(R.id.list);
        if (networkInfo != null && networkInfo.isConnected()) {
            listData = getListData();
            listAdapter = new ImageAdapter(this, listData);
            listView.setAdapter(listAdapter);
        }
        else{
            Toast.makeText(this, "Please, Connect to Internet.", Toast.LENGTH_SHORT).show();
        }


    }

    private ArrayList<ListData> getListData() {
        ArrayList<ListData> list = new ArrayList<>();
        String[] images = getResources().getStringArray(R.array.images_array);
        String[] headLines = getResources().getStringArray(R.array.headline_array);
        for (int i = 0; i < images.length; i++) {
            ListData listItems = new ListData(images[i], headLines[i], "Notaila", "Jan 12, 2018, 11:30");
            list.add(listItems);
        }
        return list;

    }


}
