package edu.cmu.qatar.cs214.hw.hw5;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class MyEmitter implements Emitter {

	private File f;
	private FileOutputStream foutStream;
	
	public MyEmitter(File f)
	{
		this.f = f;
	}
	
	@Override
	public void close() throws IOException
	{
		// TODO Auto-generated method stub
		foutStream.close();
	}

	@Override
	public void emit(String key, String value) throws IOException 
	{
		// TODO Auto-generated method stub
		if (! f.exists()) 
		{
			f.createNewFile();
		}
		foutStream = new FileOutputStream(f);
		foutStream.write((key+" "+value+"\n").getBytes());
		foutStream.close();
	}

}
