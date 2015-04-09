package com.isiomas.shop.list.shoppinglist;


import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ListView;
import android.database.Cursor;
import android.support.v4.widget.SimpleCursorAdapter;

public class MainActivity extends ActionBarActivity {
	private SqlController dbcon;
	private ListView listView;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //sendiri
		

		dbcon = new SqlController(this);
		dbcon.open();
		//listView = (ListView) findViewById(R.id.list_view);
		//listView.setEmptyView(findViewById(R.id.empty));
		// Attach The Data From DataBase Into ListView Using Crusor Adapter
		Cursor cursor = dbcon.fetch();
		String[] from = new String[] { DataBaseHelper.SHOPPING_ID, DataBaseHelper.SHOPPING_NAME, DataBaseHelper.SHOPPING_DESCRIPTION,
		
		 };
		int[] to = new int[] { R.id.id, R.id.title, R.id.desc };

		SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
															  R.layout.activity_view_record, cursor, from, to);

		adapter.notifyDataSetChanged();
		listView.setAdapter(adapter);
		
		
        Button button = (Button)findViewById(R.id.ngad_button);
        button.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        // kerja
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
}
