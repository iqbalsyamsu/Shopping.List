package com.isiomas.shop.list.shoppinglist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import android.widget.Toast;

public class AddItem extends Activity implements OnClickListener {


    private EditText nameAddText;
    private EditText descAddText;
    private EditText qtyAddText;
    private EditText valAddText;

    private SqlController dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle(R.string.title_activity_add_item);

        setContentView(R.layout.additem);

        nameAddText = (EditText) findViewById(R.id.nameEditTextAdd);
        descAddText = (EditText) findViewById(R.id.descriptionEditTextAdd);
        qtyAddText = (EditText) findViewById(R.id.quantityEditTextAdd);
        valAddText = (EditText) findViewById(R.id.valueEditTextAdd);


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

                final String name = nameAddText.getText().toString().replace(" ", "");  //http://stackoverflow.com/questions/5960706/removing-space-from-edit-text-string
                final String desc = descAddText.getText().toString().replace(" ", "");
                final String qty = qtyAddText.getText().toString();
                final String val = valAddText.getText().toString();
                //final Integer qty = Integer.parseInt(qtyAddText.getText().toString());
                //final Integer val = Integer.parseInt( valAddText.getText().toString() );

                //if (name.matches("")) {
                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(getApplicationContext(), "Please enter item name", Toast.LENGTH_LONG).show();
                    return;
                }
                dbManager.insert(name, desc, qty, val);

                Intent main = new Intent(AddItem.this, MainActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                startActivity(main);
                break;
        }
    }

}
