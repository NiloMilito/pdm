package br.edu.ifpb.view;

import java.util.Date;

import br.edu.ifpb.R;
import br.edu.ifpb.control.AmbienteDao;
import br.edu.ifpb.model.Ambiente;
import android.app.Activity;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class AddAmbienteActivity extends Activity{
	
	private AmbienteDao dao;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_screen_ccadastro);
		
		dao = new AmbienteDao(this);
		dao.open();
		
		Button saveButton = (Button) findViewById(R.id.btSave);
		Button saveCancel = (Button) findViewById(R.id.btCancel);
		EditText lat = (EditText) findViewById(R.id.editText1);
		EditText longi = (EditText)findViewById(R.id.editText2);
		EditText nom = (EditText)findViewById(R.id.editText3);
		EditText r = (EditText)findViewById(R.id.seekBar1);
		EditText p = (EditText) findViewById(R.id.spinner_ringtone);
		
		final String latitude = lat.getEditableText().toString();
		final String longitude = longi.getEditableText().toString();
		final String nome = nom.getEditableText().toString();
		final String raio = r.getEditableText().toString();
		final String perfil = p.getEditableText().toString();
		
		saveButton.setOnClickListener(new OnClickListener() {
		
		public void onClick(View v) {
			Ambiente ambiente = new Ambiente();
			Location location = new Location("");
			location.setLatitude(Double.valueOf(latitude));
			location.setLongitude(Double.valueOf(longitude));
			ambiente.setLocation(location);
			ambiente.setNome(nome);
			ambiente.setDate(new Date());
			ambiente.setRaio(Double.valueOf(raio));
			ambiente.setPerfil(perfil);
			dao.create(ambiente);	
			finish();
		}
		});
		
		saveCancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dao.close();
				finish();
			}
		});
	}
	
	@Override
	protected void onResume() {
		this.dao.open();
		super.onResume();
		
	}
	
	@Override
	protected void onPause() {
		this.dao.close();
		super.onPause();
	}
	
	

}
