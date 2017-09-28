package main;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import domen.TipArtikla;
import kontroler.Kontroler;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Artikal extends JFrame {

	private JPanel contentPane;
	private JTextField tfIme;
	private JTextField tfBarkod;
	private JTextField tfSifra;
	private JComboBox cbTipArtikla;
	private String tip;
	private JLabel lblIme;
	private JLabel lblBarkod;
	private int id = 0;
	private JButton btnBrisi;
	private ArrayList<TipArtikla> al = new ArrayList<>();
	private ArrayList<domen.Artikal> alArtikal = new ArrayList<>();
	private JTable table;
	private DefaultTableModel dtm = new DefaultTableModel();
	private String idArtikla, naziv, sifra, idtip,barkod;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Artikal frame = new Artikal();
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
	public Artikal() {
		setTitle("FORMA ZA ARTIKAL");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 598, 393);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		tfIme = new JTextField();
		tfIme.setBounds(139, 46, 194, 20);
		contentPane.add(tfIme);
		tfIme.setColumns(10);

		tfBarkod = new JTextField();
		tfBarkod.setBounds(139, 77, 194, 20);
		contentPane.add(tfBarkod);
		tfBarkod.setColumns(10);

		cbTipArtikla = new JComboBox();
		cbTipArtikla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tip = cbTipArtikla.getSelectedItem().toString();

				for (TipArtikla ta : al) {
					if (ta.getNazivTipaArtikla().equalsIgnoreCase(tip)) {

						id = ta.getId();
						System.out.println("Id je " + id);
					}
				}

			}
		});
		cbTipArtikla.setBounds(139, 123, 194, 20);
		contentPane.add(cbTipArtikla);

		JButton btnDodaj = new JButton("Dodaj Artikal");
		btnDodaj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ime = tfIme.getText();
				String barkod = tfBarkod.getText();
				String sifra = tfSifra.getText();
				boolean provera = false;
				for (domen.Artikal a : alArtikal) {

					if (a.getSifra().equals(tfSifra.getText().toString())) {
						provera = true;
						break;
						

					}
				}
				if (provera == false) {
					Kontroler.getInstanca().upisiArtikal(ime, barkod, sifra, id);
					srediFormu();

					JOptionPane.showMessageDialog(null, "Uspesno dodati podaci!!");
				}else{
					
					JOptionPane.showMessageDialog(null, "Postoji sifra u bazi!!");
				}
			}
		});
		btnDodaj.setBounds(41, 307, 125, 23);
		contentPane.add(btnDodaj);

		tfSifra = new JTextField();
		tfSifra.setBounds(139, 11, 194, 20);
		contentPane.add(tfSifra);
		tfSifra.setColumns(10);

		JLabel lblNewLabel = new JLabel("Sifra");
		lblNewLabel.setBounds(31, 14, 86, 14);
		contentPane.add(lblNewLabel);

		lblIme = new JLabel("Ime");
		lblIme.setBounds(31, 49, 86, 14);
		contentPane.add(lblIme);

		lblBarkod = new JLabel("BarKod");
		lblBarkod.setBounds(31, 80, 86, 14);
		contentPane.add(lblBarkod);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(31, 186, 521, 105);
		contentPane.add(scrollPane);

		table = new JTable(dtm);
		scrollPane.setViewportView(table);
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int red = table.getSelectedRow();// selektovani red u tabeli
				idArtikla = (table.getModel().getValueAt(red, 0).toString());
				naziv = (table.getModel().getValueAt(red, 1).toString());
				barkod = (table.getModel().getValueAt(red, 2).toString());
				sifra=(table.getModel().getValueAt(red, 4).toString());
				// System.out.println("Kliknuto "+tableClID);
				tfIme.setText(naziv);
				tfBarkod.setText(barkod);
				tfSifra.setText(sifra);

			}
		});

		
		JButton btnizmeni = new JButton("Izmeni");
		btnizmeni.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String ime=tfIme.getText().toString();
				String barkod=tfBarkod.getText().toString();
				String sifra=tfSifra.getText().toString();
				Kontroler.getInstanca().promeniPodatke(ime,barkod,sifra,id,idArtikla);
				srediFormu();
				JOptionPane.showMessageDialog(null, "Uspesno izmenjeni podaci!!");
						
			}
		});
		btnizmeni.setBounds(228, 307, 125, 23);
		contentPane.add(btnizmeni);
		
		btnBrisi = new JButton("Brisi");
		btnBrisi.setBounds(427, 307, 125, 23);
		contentPane.add(btnBrisi);
		Object[] kolone = new Object[6];
		// {"IdArtikla","Naziv","Sifra","BarKod","IdTip"}
		kolone[0] = "IdArtikla";
		kolone[1] = "Naziv";
		kolone[2] = "BarKod";
		kolone[3] = "IdTip";
		kolone[4] = "Sifra";
		//kolone[5] = "Brisi";
		dtm.addColumn(kolone[0]);
		dtm.addColumn(kolone[1]);
		dtm.addColumn(kolone[2]);
		dtm.addColumn(kolone[3]);
		dtm.addColumn(kolone[4]);
		//dtm.addColumn(kolone[5]);

		vratiTipArtikla();
		srediFormu();
	}

	private void srediFormu() {
		// TODO Auto-generated method stub

		dtm.setRowCount(0);
		Object[] celije = new Object[6];// ovo su polja u koja smestamo podatke
										// na odredjnui poziciju
		alArtikal = Kontroler.getInstanca().vratiArtikal();

		for (domen.Artikal ta : alArtikal) {
			celije[0] = ta.getId();
			celije[1] = ta.getNaziv();
			celije[2] = ta.getBarkod();
			celije[3] = ta.getIdtip();
			celije[4] = ta.getSifra();
			//celije[5]=btnBrisi;
			dtm.addRow(celije);
		}

	}

	private void vratiTipArtikla() {
		// TODO Auto-generated method stub
		cbTipArtikla.addItem("Izaberi tip");
		al = Kontroler.getInstanca().vratitipArtikla();
		for (domen.TipArtikla tp : al) {

			cbTipArtikla.addItem(tp.getNazivTipaArtikla());

		}

	}
}
