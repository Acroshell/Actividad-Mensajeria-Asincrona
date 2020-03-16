import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client implements Runnable {

	// inicializamos las variables puerto y mensaje
	private int puerto;
	private String mensaje;
	
	public Client (int puerto, String mensaje) {//constructor de Buffer_Cliente
		this.puerto = puerto;//instanciamos el atributo puerto
		this.mensaje = mensaje;//instanciamos el atributo mensaje
	}
		
	@Override
	public void run() {

		final String HOST = "127.0.0.1"; // host del servidor

		DataOutputStream out; 
		
		try {
			Socket sck = new Socket(HOST, puerto);//se instancia socket con argumentos HOSt y puerto
			
			out = new DataOutputStream(sck.getOutputStream());//comunicacion del servidor al cliente
			
			out.writeUTF(mensaje);//se envia el mensaje por protocolo utf
			
			out.close();// se cierra la comunicacion
			
			sck.close();//se cierra el socket
			
		} catch (IOException e) {
			Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, e);
		}

	}

}
