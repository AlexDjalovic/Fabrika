package main;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import kontroler.Kontroler;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class OrgJednica extends JFrame {

	private JPanel contentPane;
	private JTextField tfNaziv;
	private JTextField tfAdresa;
	private JComboBox comboPreduzece;
	private int idPreduzeca;
	private JTable table;
	private DefaultTableModel dtm=new DefaultTableModel();
	private int id,idP;
	private String naziv,adresa;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrgJednica frame = new OrgJednica();
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
	public OrgJednica() {
		setTitle("ORGANIZACIONA JEDINICA");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 692, 599);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 245, 238));
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tfNaziv = new JTextField();
		tfNaziv.setBounds(275, 29, 151, 20);
		contentPane.add(tfNaziv);
		tfNaziv.setColumns(10);
		
		tfAdresa = new JTextField();
		tfAdresa.setBounds(275, 81, 151, 20);
		contentPane.add(tfAdresa);
		tfAdresa.setColumns(10);
		
		 comboPreduzece = new JComboBox();
		 comboPreduzece.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent arg0) {
		 		String nazivPreduzeca=comboPreduzece.getSelectedItem().toString();
		 		for(domen.Preduzece p:Kontroler.getInstanca().vratiPreduzeca()){
		 			if(p.getNaziv().equalsIgnoreCase(nazivPreduzeca)){
		 				idPreduzeca=p.getIdPreduzeca();
		 			}
		 		}
		 	}
		 });
		comboPreduzece.setBounds(275, 136, 151, 20);
		contentPane.add(comboPreduzece);
		
		JButton btndodaj = new JButton("Dodaj");
		btndodaj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nazivOrgjed=tfNaziv.getText().toString();
				String adresa=tfAdresa.getText().toString();
				Kontroler.getInstanca().upisiOrganizacionuJedinicu(nazivOrgjed,adresa,idPreduzeca);
				JOptionPane.showMessageDialog(null, "upisani podaci");
				tfNaziv.setText("");
				tfAdresa.setText("");
				popuniCelije();
			}
		});
		btndodaj.setBounds(41, 196, 151, 23);
		contentPane.add(btndodaj);
		
		JLabel lblNewLabel = new JLabel("Naziv");
		lblNewLabel.setBounds(49, 33, 56, 16);
		contentPane.add(lblNewLabel);
		
		JLabel lblAdresa = new JLabel("Adresa");
		lblAdresa.setBounds(49, 83, 56, 16);
		contentPane.add(lblAdresa);
		
		JLabel lblPreduzece = new JLabel("Preduzece");
		lblPreduzece.setBounds(49, 138, 83, 16);
		contentPane.add(lblPreduzece);
		
		JButton btnPromeni = new JButton("Promeni");
		btnPromeni.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String naziv=tfNaziv.getText().toString();
				String adresa=tfAdresa.getText().toString();
				Kontroler.getInstanca().promeniOrganizacionuJedinicu(naziv,adresa,idPreduzeca,id);
				popuniCelije();
				JOptionPane.showMessageDialog(null, "promenjeni podaci");
			}
		});
		btnPromeni.setBounds(275, 195, 151, 23);
		contentPane.add(btnPromeni);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 284, 650, 255);
		contentPane.add(scrollPane);
		
		table = new JTable(dtm);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int red=table.getSelectedRow();
				id=(int) table.getModel().getValueAt(red, 0);
				naziv=(String) table.getModel().getValueAt(red, 1);
				adresa=(String) table.getModel().getValueAt(red, 2);
				idP=(int) table.getModel().getValueAt(red, 3);
				tfNaziv.setText(naziv);
				tfAdresa.setText(adresa);
				for(domen.Preduzece p:Kontroler.getInstanca().vratiPreduzeca()){
					if(p.getIdPreduzeca()==idP){
						comboPreduzece.setSelectedItem(p.getNaziv());
					}
				}
				
			}
		});
		scrollPane.setViewportView(table);
		popuniCombo();
		Object []kolone=new Object[4];
		kolone[0]="idOrgJedinice";
		kolone[1]="naziv";
		kolone[2]="adresa";
		kolone[3]="idPreduzeca";
		dtm.addColumn(kolone[0]);
		dtm.addColumn(kolone[1]);
		dtm.addColumn(kolone[2]);
		dtm.addColumn(kolone[3]);
		popuniCelije();
	}

	private void popuniCelije() {
		dtm.setRowCount(0);
		Object []celije=new Object[4];
		for(domen.OrganizacionaJedinica oj:Kontroler.getInstanca().vratiOrganizacioneJedinice()){
			celije[0]=oj.getIdOrganizacioneJedinice();
			celije[1]=oj.getNaziv();
			celije[2]=oj.getAdresa();
			celije[3]=oj.getIdPreduzeca();
			dtm.addRow(celije);
		}
	}

	public void popuniCombo() {
		// TODO Auto-generated method stub
		for(domen.Preduzece p:Kontroler.getInstanca().vratiPreduzeca()){
			comboPreduzece.addItem(p.getNaziv());
		}
	}
}
