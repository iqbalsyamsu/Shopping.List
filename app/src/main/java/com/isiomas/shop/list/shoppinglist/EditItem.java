package com.isiomas.shop.list.shoppinglist;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class EditItem extends Activity implements View.OnClickListener {
    private EditText nameText;
    //private
    private EditText descText;
    private long _id;
    private SqlController dbController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);

        dbController = new SqlController(this);
        dbController.open();

        nameText=(EditText) findViewById(R.id.name_edittext);
        descText=(EditText) findViewById(R.id.descriptionEditText);

        Button updateBtn, deleteBtn;
        updateBtn = (Button) findViewById(R.id.btn_update);
        deleteBtn = (Button) findViewById(R.id.btn_delete);

        Intent intent = getIntent();

        String id = intent.getStringExtra("id");
        String name = intent.getStringExtra("name");
        String desc = intent.getStringExtra("desc");

        _id = Long.parseLong(id);

        nameText.setText(name);
        descText.setText(desc);

        updateBtn.setOnClickListener(this);
        deleteBtn.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_update:
                String name = nameText.getText().toString();
                String desc = descText.getText().toString();

                if (name.matches("")) {
                    Toast.makeText(getApplicationContext(), "Please enter item name", Toast.LENGTH_LONG).show();
                    return;
                }
                dbController.update(_id, name, desc);
                this.returnHome();
                break;

            case R.id.btn_delete:
                dbController.delete(_id);
                this.returnHome();
                break;
        }
    }

    public void returnHome() {
        Intent home_intent = new Intent(getApplicationContext(), MainActivity.class)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(home_intent);
    }
}
