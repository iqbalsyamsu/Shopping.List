package com.isiomas.shop.list.shoppinglist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddItem extends Activity implements OnClickListener {


    private EditText subjectEditText;
    private EditText descEditText;

    private SqlController dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle(R.string.title_activity_add_item);

        setContentView(R.layout.additem);

        subjectEditText = (EditText) findViewById(R.id.nameEditText);
        descEditText = (EditText) findViewById(R.id.descriptionEditText);

        Button addTodoBtn;
        addTodoBtn = (Button) findViewById(R.id.add_record);

        dbManager = new SqlController(this);
        dbManager.open();
        addTodoBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_record:

                final String name = subjectEditText.getText().toString();
                final String desc = descEditText.getText().toString();

                if (name.matches("")) {
                    Toast.makeText(getApplicationContext(), "Please enter item name", Toast.LENGTH_LONG).show();
                    return;
                }
                dbManager.insert(name, desc);

                Intent main = new Intent(AddItem.this, MainActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                startActivity(main);
                break;
        }
    }

}
