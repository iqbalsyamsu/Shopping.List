package com.isiomas.shop.list.shoppinglist;


import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
//import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
//import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ListView;
import android.database.Cursor;
import android.support.v4.widget.SimpleCursorAdapter;

public class MainActivity extends ActionBarActivity {
    private SqlController dbCon;
	private ListView mainListView;
    private SimpleCursorAdapter adapter;

    //final String[] from = new String[] { DataBaseHelper.SHOPPING_ID, DataBaseHelper.SHOPPING_NAME,DataBaseHelper.SHOPPING_DESCRIPTION};
	//final int[] to = new int[] { R.id.shopID, R.id.shopNAME, R.id.shopDESC};

    final String[] from = new String[] { DataBaseHelper.SHOPPING_ID, DataBaseHelper.SHOPPING_NAME,DataBaseHelper.SHOPPING_DESCRIPTION, DataBaseHelper.SHOPPING_QUANTITY, DataBaseHelper.SHOPPING_VALUE, DataBaseHelper.SHOPPING_CREATED_AT, DataBaseHelper.SHOPPING_UPDATED_AT, DataBaseHelper.SHOPPING_CATEGORY, DataBaseHelper.SHOPPING_FAVOURITE, DataBaseHelper.SHOPPING_STATUS};
    final int[] to = new int[] { R.id.shopID, R.id.shopNAME, R.id.shopDESC, R.id.shopQTY, R.id.shopVAL, R.id.shopCREATED, R.id.shopUPDATED, R.id.shopCATEGORY, R.id.shopFAV, R.id.shopDONE};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showMainList();

        // OnCLickListiner For List Items
        mainListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long viewId) {
                TextView idTextView = (TextView) view.findViewById(R.id.shopID);
                TextView nameTextView = (TextView) view.findViewById(R.id.shopNAME);
                TextView descTextView = (TextView) view.findViewById(R.id.shopDESC);
                TextView qtyTextView = (TextView) view.findViewById(R.id.shopQTY);
                TextView valTextView = (TextView) view.findViewById(R.id.shopVAL);

                String id = idTextView.getText().toString();
                String name = nameTextView.getText().toString();
                String desc = descTextView.getText().toString();
                String qty = qtyTextView.getText().toString();
                String val = valTextView.getText().toString();

                Intent modify_intent = new Intent(getApplicationContext(), EditItem.class);
                modify_intent.putExtra("name", name);
                modify_intent.putExtra("desc", desc);
                modify_intent.putExtra("id", id);
                modify_intent.putExtra("qty", qty);
                modify_intent.putExtra("val", val);

                startActivity(modify_intent);
            }
        });

        Button button = (Button)findViewById(R.id.ngad_button);
        button.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        // it's by me
                        Toast.makeText(getApplicationContext(),"Done",Toast.LENGTH_LONG).show();
                        //Intent add_item = new Intent(MainActivity.this, AddItem.class);
                        //startActivity(add_item);
                    }
                }
        );

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            //Intent add_mem = new Intent(this, AddItem.class);
            //startActivity(add_mem);

            return true;
        }

        if (id == R.id.add_record) {
            Intent add_mem = new Intent(this, AddItem.class);
            startActivity(add_mem);
        }
        return super.onOptionsItemSelected(item);
    }

    public void showMainList() {
        //Toast.makeText(getApplicationContext(),"Poid",Toast.LENGTH_LONG).show();
        //sendiri

        dbCon = new SqlController(this);
        dbCon.open();

        //Cursor cursor = dbCon.fetch();
        Cursor cursor = dbCon.fetchAll();

        mainListView = (ListView) findViewById(R.id.listView);

        int jml = 0;
        jml = dbCon.countRows();
        // Log.d("SLApp", "Masuk ..");
        //Toast.makeText(getApplicationContext(), String.valueOf(jml)+" entries found.",Toast.LENGTH_LONG).show();

        adapter = new SimpleCursorAdapter(this, R.layout.fragment_list, cursor, from, to, 0);
        //adapter.setViewBinder();

        adapter.notifyDataSetChanged();
        mainListView.setAdapter(adapter);

    }
}
