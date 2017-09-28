package main;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import kontroler.Kontroler;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.border.LineBorder;

public class FormaRadnoMestoOrgJedinica extends JFrame {

	private JPanel contentPane;
	JComboBox comboRADNOMesto,comboOrgJed;
	private JButton btnNewButton;
	private int idRM,idOJ;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormaRadnoMestoOrgJedinica frame = new FormaRadnoMestoOrgJedinica();
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
	public FormaRadnoMestoOrgJedinica() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 545, 353);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		panel.setBackground(new Color(255, 228, 196));
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		 comboRADNOMesto = new JComboBox();
		 comboRADNOMesto.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		String nazivRM=comboRADNOMesto.getSelectedItem().toString();
		 		for(domen.TipRandnogMesta trm:Kontroler.getInstanca().vratitipRadnogMesta()){
		 			if(trm.getNaziv().equalsIgnoreCase(nazivRM)){
		 				idRM=trm.getIdRadnogMesta();
		 			}
		 		}
		 	}
		 });
		comboRADNOMesto.setBounds(205, 85, 172, 22);
		panel.add(comboRADNOMesto);
		
		 comboOrgJed = new JComboBox();
		 comboOrgJed.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		String nazivOJ=comboOrgJed.getSelectedItem().toString();
		 		for(domen.OrganizacionaJedinica oj:Kontroler.getInstanca().vratiOrganizacioneJedinice()){
		 			if(oj.getNaziv().equalsIgnoreCase(nazivOJ)){
		 				idOJ=oj.getIdOrganizacioneJedinice();
		 			}
		 		}
		 	}
		 });
		comboOrgJed.setBounds(205, 153, 172, 22);
		panel.add(comboOrgJed);
		
		JLabel lblNewLabel = new JLabel("radno mesto");
		lblNewLabel.setBounds(27, 88, 131, 16);
		panel.add(lblNewLabel);
		
		JLabel lblOrganizacionaJedinica = new JLabel("organizaciona jedinica");
		lblOrganizacionaJedinica.setBounds(27, 156, 131, 16);
		panel.add(lblOrganizacionaJedinica);
		
		btnNewButton = new JButton("UNESI");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Kontroler.getInstanca().upisiVezuRMiOJ(idRM,idOJ);
				JOptionPane.showMessageDialog(null, "upisani podaci");
			}
		});
		btnNewButton.setBounds(205, 237, 172, 25);
		panel.add(btnNewButton);
		popuniCombo();
	}

	private void popuniCombo() {
		// TODO Auto-generated method stub
		for(domen.OrganizacionaJedinica oj:Kontroler.getInstanca().vratiOrganizacioneJedinice()){
			comboOrgJed.addItem(oj.getNaziv());
		}
		for(domen.TipRandnogMesta trm:Kontroler.getInstanca().vratitipRadnogMesta()){
			comboRADNOMesto.addItem(trm.getNaziv());
		}
	}
}
