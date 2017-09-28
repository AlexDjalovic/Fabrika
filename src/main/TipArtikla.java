package main;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import kontroler.Kontroler;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.border.LineBorder;
import java.awt.Font;

public class TipArtikla extends JFrame {

	private JPanel contentPane;
	private JTextField tfNazivArtikla;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TipArtikla frame = new TipArtikla();
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
	public TipArtikla() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 224));
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tfNazivArtikla = new JTextField();
		tfNazivArtikla.setBounds(208, 36, 169, 20);
		contentPane.add(tfNazivArtikla);
		tfNazivArtikla.setColumns(10);
		
		JButton btnDodaj = new JButton("Dodaj");
		btnDodaj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String naziv=tfNazivArtikla.getText().toString();
				Kontroler.getInstanca().upisiNazivTipaArtikla(naziv);
				JOptionPane.showMessageDialog(null, "upisani podaci");
				tfNazivArtikla.setText("");
			}
		});
		btnDodaj.setBounds(208, 89, 169, 23);
		contentPane.add(btnDodaj);
		
		JLabel lblNazivArtikla = new JLabel("Naziv artikla");
		lblNazivArtikla.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNazivArtikla.setBounds(31, 40, 94, 16);
		contentPane.add(lblNazivArtikla);
	}
}
