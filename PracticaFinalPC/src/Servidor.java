
import Main.Fichero;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class Servidor {

    public static void main(String[] args) {

           
            ObjectOutputStream objectOutput = new ObjectOutputStream(socket.getOutputStream());
    
            List<Fichero> listF = new ArrayList<>();
 
            listF.add(new Fichero(   ));
 
   
            for (Fichero f : listF) {
                objectOutput.writeObject(f);
		objectOutput.flush();
            }
 
            
}