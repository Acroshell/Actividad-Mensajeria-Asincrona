import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server extends Observable implements Runnable{ // se pone observable porque es el que va a notificar
	
	private int puerto;
	
	public Server (int puerto) {//Constructor servidor que recibira como dato el numero del puerto
		this.puerto = puerto;//declaracion de atributo
	}

	@Override
	public void run() {
		
		ServerSocket servidor = null;//inicializacion de servidor
		Socket socket = null;//inicializacion de socket
		DataInputStream in;//libreria para recepcion y empaquetamiento de mensaje
					
		try {
			servidor = new ServerSocket(puerto);//objeto del servidor
			System.out.println("Server on");//Mensaje que dice si el servidor esta activo
			
			while (true) {//bucle del servidor en escucha constante
				socket = servidor.accept();//socket para decir que el cliente esta a la espera
				
				System.out.println("Cliente conectado");//nos dice cuando el cliente es decir usuario esta conectado
				in = new DataInputStream(socket.getInputStream());//recepcion y empaquetamiento del mensaje
				
				String mensaje = in.readUTF();//mensaje a la espera
				
				System.out.println(mensaje);//mostrar mensaje que se haya enviado
				
				//lo siguientes metodos son los que cambian , observan y notifican el mensaje
				this.setChanged();
				this.notifyObservers(mensaje);
				this.clearChanged();
				
				in.close();//se cierra la recepcion y empaquetamiento del mensaje
				socket.close();//se cierra el socket del cliente
				System.out.println("Cliente desconectado");//Se muestra el mensaje de que esta desconectado
				}
		} catch (IOException e) {
			Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, e);
		}

	


		
	}

}
