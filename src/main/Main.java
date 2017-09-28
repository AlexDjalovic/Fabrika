package main;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;

public class Main extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
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
	public Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 648, 340);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 240));
		contentPane.setForeground(Color.GREEN);
		contentPane.setBorder(new TitledBorder(null, "UVODNI PROZOR", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnPreduzeca = new JButton("Preduzece");
		btnPreduzeca.setBounds(38, 54, 125, 23);
		contentPane.add(btnPreduzeca);
		
		JButton btnOrgJed = new JButton("Organizaciona Jedinica");
		btnOrgJed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OrgJednica org=new OrgJednica();
				org.setVisible(true);
				org.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
		});
		btnOrgJed.setBounds(206, 54, 163, 23);
		contentPane.add(btnOrgJed);
		
		JButton btnTipRadnog = new JButton("Tip Radnog Mesta");
		btnTipRadnog.setBounds(411, 54, 155, 23);
		contentPane.add(btnTipRadnog);
		
		JButton btnZaposleni = new JButton("Zaposleni");
		btnZaposleni.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FactoyMetodzapolesni fmz=new FactoyMetodzapolesni();
				fmz.setVisible(true);
				fmz.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
		});
		btnZaposleni.setBounds(38, 106, 125, 23);
		contentPane.add(btnZaposleni);
		
		JButton btnArtikal = new JButton("Artikal");
		btnArtikal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
			Artikal a=new Artikal();
			a.setVisible(true);
			}
		});
		btnArtikal.setBounds(206, 106, 163, 23);
		contentPane.add(btnArtikal);
		
		JButton btnTipArtikla = new JButton("TipArtikla");
		btnTipArtikla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TipArtikla ta=new TipArtikla();
				ta.setVisible(true);
				ta.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
		});
		btnTipArtikla.setBounds(411, 106, 155, 23);
		contentPane.add(btnTipArtikla);
		
		JButton btnUcinak = new JButton("Ucinak");
		btnUcinak.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				UcinakForma uf=new UcinakForma();
				uf.setVisible(true);
				uf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			
			}
		});
		btnUcinak.setBounds(38, 172, 125, 23);
		contentPane.add(btnUcinak);
		
		JButton btnRadnomestoorganizacjed = new JButton("radnoMesto_OrganizacJed");
		btnRadnomestoorganizacjed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormaRadnoMestoOrgJedinica frmoj=new FormaRadnoMestoOrgJedinica();
				frmoj.setVisible(true);
				frmoj.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
		});
		btnRadnomestoorganizacjed.setBounds(212, 172, 157, 23);
		contentPane.add(btnRadnomestoorganizacjed);
	}
}
