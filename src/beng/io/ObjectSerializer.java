package beng.io;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class ObjectSerializer {
	private Object object;
	private String pathname;

	public ObjectSerializer(Object object, String pathname){
			super();
			this.object = object;
			this.pathname = pathname;
	}
	
	public void serialize(){
		try {
				FileOutputStream fos = new FileOutputStream(this.pathname);
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(this.object);
				oos.flush();
				oos.close();
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(1);
				return;
			}
		return;
	}

}
