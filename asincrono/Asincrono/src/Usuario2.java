import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Toolkit;

public class Usuario2 extends JFrame implements Observer{

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
					Usuario2 frame = new Usuario2();
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
	public Usuario2() {
		setResizable(false);
		setTitle("Chat - Usuario 2");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Server sck = new Server(6999);
		sck.addObserver(this);
		Thread t = new Thread(sck);
		t.start();
		
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
				String message = "Usuario2: " + textField.getText() + "\n";
				textArea.append(message);
				//conexi�n para env�� de mensajes a usuario 1
				Client c = new Client(5999, message);
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
				
				String message = "Usuario2: " + textField.getText() + "\n";
				textArea.append(message);
				//conexi�n para env�� de mensajes a usuario 1
				Client c = new Client(5999, message);
				Thread t = new Thread(c);//hilo encargado del proceso
				t.start();
				textField.setText("");
			}
		});
		btnNewButton.setBounds(335, 223, 89, 27);
		contentPane.add(btnNewButton);
		
	}
	//M�todo que implementa la clase Observable, que pasa el argumento mensaje
	@Override
	public void update(Observable o, Object arg) {
		
		textArea.append((String) arg);
		
	}
}
