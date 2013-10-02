package beng.jumperB;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class ObjectDeserializer<T> {

	public ObjectDeserializer(){}
	
	@SuppressWarnings("unchecked")
	public void deserialize(T output, String pathname){
		Object object;
		try {
			FileInputStream fis = new FileInputStream(pathname);
			ObjectInputStream ois = new ObjectInputStream(fis);
			object = ois.readObject();
			if(!(object.getClass().equals(output.getClass()))){
				System.err.println("Object to deserialise of not of type " + output.getClass().toString());
				ois.close();
				return;
			}
			ois.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
			return;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		object = (T)object;
		return;
	}

}
