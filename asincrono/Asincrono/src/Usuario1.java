import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JScrollPane;
import java.awt.Toolkit;

public class Usuario1 extends JFrame implements Observer { 
	private JPanel contentPane;
	private JTextField textField;
	private JTextArea textArea;
	private JScrollPane scrollPane;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Usuario1 frame = new Usuario1();//se declara frame para hacerlo visible y ejecutable
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Usuario1() {
		setResizable(false);
		setTitle("Chat - Usuario 1");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 300, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		//conexiondel  usuario 1 con el servidor y envio del mensaje
		Server socket = new Server(5999);
		socket.addObserver(this);
		Thread t = new Thread(socket);// este hilo es el que se encarga del proceso
		t.start(); // comienza el hilo a trabajar
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 414, 201);
		contentPane.add(scrollPane);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBounds(10, 11, 414, 201);

		scrollPane.setViewportView(textArea);
		
		textField = new JTextField();
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String message = "Usuario1: " + textField.getText() + "\n";
				textArea.append(message);
				//conexión para envúŒ de mensajes a usuario 2
				Client c = new Client(6999, message);
				Thread t = new Thread(c);//hilo encargado del proceso
				t.start();
				textField.setText("");
			}
		});
		textField.setBounds(10, 223, 322, 27);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Enviar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String message = "Usuario1: " + textField.getText() + "\n";
				textArea.append(message);
				//conexión para envúŒ de mensajes a usuario 2
				Client c = new Client(6999, message);
				Thread t = new Thread(c);//hilo encargado del proceso
				t.start();
				textField.setText("");
			}
		});
		btnNewButton.setBounds(335, 223, 89, 27);
		contentPane.add(btnNewButton);
		
	}
	//Método que implementa la clase Observable, que pasa el argumento mensaje
	@Override
	public void update(Observable o, Object arg) {
		
		textArea.append((String) arg);
		
	}
}
