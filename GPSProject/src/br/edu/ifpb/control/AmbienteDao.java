package br.edu.ifpb.control;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.edu.ifpb.banco.Banco;
import br.edu.ifpb.model.Ambiente;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;

public class AmbienteDao {
	private SQLiteDatabase database;
	private String[] columns = { Banco.COLUMN_ID , Banco.COLUMN_NOME, 
			Banco.COLUMN_DATE, Banco.COLUMN_LATITUDE, Banco.COLUMN_LONGITUDE};
	private Banco banco;
	
	public AmbienteDao(Context context){
		this.banco = new Banco(context);
	}
	
	public void open() throws SQLException {
		 database = banco.getWritableDatabase();
	}
	
	public void close() {
		 banco.close();
	}
	
	public Ambiente create(Ambiente ambiente) {
		 ContentValues values = new ContentValues();
		 
		 values.put(Banco.COLUMN_NOME, ambiente.toString());
		 
		 long insertId = database.insert(Banco.TABLE_AMBIENTE , null,values);
		 Cursor cursor = database.query(Banco.TABLE_AMBIENTE,columns , 
			Banco.COLUMN_ID + " = " + insertId , null,null, null, null);
		 
		 cursor.moveToFirst();
		 
		 Ambiente ambient = new Ambiente();
		 ambient.setId(cursor.getInt(0));
		 ambient.setNome(cursor.getString(1));
		 
		 Location location = new Location("");
		 location.setLatitude(cursor.getDouble(2));
		 location.setLongitude(cursor.getDouble(3));
		 ambient.setLocation(location);
		 
		 Date data = new Date();
		 data.setTime(cursor.getLong(4));
		 ambient.setDate(data);
		 cursor.close();
		 	return ambient;
		 }
	
	public void delete(Ambiente ambiente) {
		long id = ambiente.getId();
		database.delete(Banco.TABLE_AMBIENTE, Banco.COLUMN_ID + " = " + id, null);
	}
	
	public List <Ambiente> getAll() {
		 List <Ambiente> ambientes = new ArrayList <Ambiente>();
		 Cursor cursor = database.query(Banco.TABLE_AMBIENTE ,columns , null, null, null, null, null);
		 cursor.moveToFirst();
		 while (!cursor.isAfterLast()) {
		 Ambiente ambiente = new Ambiente();
		 
		 ambiente.setId(cursor.getInt(0));
		 ambiente.setNome(cursor.getString(1));
		 
		 Location location = new Location("");
		 location.setLatitude(cursor.getDouble(2));
		 location.setLongitude(cursor.getDouble(3));
		 ambiente.setLocation(location);
		 
		 Date data = new Date();
		 data.setTime(cursor.getLong(4));
		 ambiente.setDate(data);
		 ambientes.add(ambiente);
		 cursor.moveToNext();
		 }
		 cursor.close();
	 return ambientes;
	 
	}
	
}
