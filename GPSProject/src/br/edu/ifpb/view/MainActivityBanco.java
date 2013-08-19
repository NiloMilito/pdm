package br.edu.ifpb.view;

import java.util.List;

import android.R;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import br.edu.ifpb.control.AmbienteDao;
import br.edu.ifpb.model.Ambiente;

public class MainActivityBanco extends ListActivity {
	private AmbienteDao dao;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_item);
		this.dao = new AmbienteDao(this);
		this.dao.open();
	}
	
	@Override
	protected void onResume() {
		this.dao.open();
		super.onResume();
		
		List<Ambiente> ambientes = this.dao.getAll();
		
		ArrayAdapter<Ambiente> adapter = new ArrayAdapter<Ambiente>(this,
				android.R.layout.simple_list_item_1 , ambientes);
		setListAdapter(adapter);
	}
	
	@Override
	protected void onPause() {
		this.dao.close();
		super.onPause();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.layout.activity_list_item, menu);
		return true;
		
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == R.id.addToDictionary) {
			Intent intent = new Intent(this, AddAmbienteActivity.class);
			startActivity(intent);
			}
		return super.onOptionsItemSelected(item);
	}
	
}
