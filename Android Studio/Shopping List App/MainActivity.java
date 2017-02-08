package com.example.sydney.shoppinglist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Button addButt;
    private ListView listView;
    private ArrayList<String> items = new ArrayList<String>();
    private  ArrayAdapter<String> adapter;
    private EditText addedItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                items);

        addButt = (Button)findViewById(R.id.addButton);
        listView = (ListView)findViewById(R.id.ShoppingList);
        listView.setAdapter(adapter);
        addedItem = ((EditText)findViewById(R.id.listPrompt));

        addedItem.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                addedItem.setText(null);
            }
        });

        addButt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                items.add(((EditText)findViewById(R.id.listPrompt)).getText().toString());
                adapter.notifyDataSetChanged();
                addedItem.setText(null);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> a, View v, int position, long id) {
                Toast.makeText(MainActivity.this, "Removed", Toast.LENGTH_LONG).show();
                items.remove(position);
                adapter.notifyDataSetChanged();
                return true;
            }
        });
    }
}
