package com.example.carlosrenato.searchy;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


public class MainActivity extends ActionBarActivity implements SearchView.OnQueryTextListener, SearchView.OnCloseListener {

    private ClientesDataSource ds;
    private ListView list;
    private SearchView filter;
    private TextView h1;

    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_main);

        filter = (SearchView) findViewById(R.id.searcher);
        filter.setIconifiedByDefault(false);
        filter.setOnQueryTextListener(this);
        filter.setOnCloseListener(this);
        h1 = (TextView) findViewById(R.id.titleActivity);
        h1.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_launcher, 0, 0, 0);
        ds = new ClientesDataSource(this);
        ds.open();
        showList("");
    }

    @Override
    protected void onResume() {
        ds.open();
        super.onResume();
    }

    @Override
    protected void onPause() {
        ds.close();
        super.onPause();
    }

    // SearchView
    @Override
    public boolean onClose () {
        return false;
    }

    @Override
    public boolean onQueryTextSubmit (String query){
        showList(query);
        return false;
    }

    @Override
    public boolean onQueryTextChange (String newText){
        showList(newText);
        return false;
    }

    private void showList(final String query) {
//        Cursor cursor = ds.filtering((query != null ? query.toString() : "@@@@"));
//        Cursor cursor = ds.filtering(query.toString());
//        if (cursor == null) {
            //
//        } else {
            // Specify the columns we want to display in the result
//            String[] from = ds.allColumns;
//
//            // Specify the Corresponding layout elements where we want the columns to go
//            int[] to = new int[]{
//                    R.id.tvItem,
//                    R.id.tvName,
//                    R.id.tvAddress,
//                    R.id.tvNumber};
            // Create a simple cursor adapter for the definitions and apply them to the ListView
            final List<Cliente> values = ds.getAll(query);

            ArrayAdapter<Cliente> adapter = new ArrayAdapter<Cliente>(this, android.R.layout.simple_list_item_1, values);
            list = (ListView) findViewById(R.id.list2);
            list.setAdapter(adapter);

        // Define the on-click listener for the list items
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get the cursor, positioned to the corresponding row in the result set
//                Cursor cursor = (Cursor) list.getItemAtPosition(position);
//                Cursor cursor = ds.filtering(query);
                Cursor cursor = ds.getAll();
                cursor.moveToPosition(position);


                // Get the state's capital from this row in the database.
                String item = cursor.getString(cursor.getColumnIndexOrThrow("ITEM"));
                String name = cursor.getString(cursor.getColumnIndexOrThrow("NOMBRE"));
                String address = cursor.getString(cursor.getColumnIndexOrThrow("DIRECCION"));
                String number = cursor.getString(cursor.getColumnIndexOrThrow("NUMERO"));
                int count = values.size();
                cursor.close();
//                //Get References to the TextViews
                TextView tvItem = (TextView) findViewById(R.id.tvItem);
                TextView tvName = (TextView) findViewById(R.id.tvName);
                TextView tvAddress = (TextView) findViewById(R.id.tvAddress);
                TextView tvNumber = (TextView) findViewById(R.id.tvNumber);
                TextView tvClientsCount = (TextView) findViewById(R.id.clientsCount);
//
//                // Update the parent class's TextView
                tvItem.setText(item);
                tvName.setText(name);
                tvAddress.setText(address);
                tvNumber.setText(number);
                tvClientsCount.setText("Mostrando "+ count + " clientes");
//                filter.setQuery("", true);
            }
        });

//            SimpleCursorAdapter customers = new SimpleCursorA dapter(this, R.layout.activity_main, cursor, from, to);
//            list.setAdapter(customers);
//        }
        }
    }


