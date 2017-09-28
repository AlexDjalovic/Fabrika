package main;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import domen.IVratiTipZapolenog;
import domen.OrganizacionaJedinica;
import domen.Radnik;
import domen.Rukovodilac;
import domen.Tipzaposlenog;
import kontroler.Kontroler;
import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.border.TitledBorder;

public class FactoyMetodzapolesni extends JFrame implements IVratiTipZapolenog {

	private JPanel contentPane;
	private JTextField tfIme;
	private JTextField tfPrezime;
	private JTextField tfJmbg;
	private JComboBox cb;
	private JComboBox cbIdOrg;
	private int id;
	private JTable table;
	DefaultTableModel dtm =new DefaultTableModel();
	private int idZaposlenog;
	protected String imeZaposlenog, prezimeZaposlenog,jmbgZaposlenog,pozicijaZaposlenog;
	protected int idOrg;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FactoyMetodzapolesni frame = new FactoyMetodzapolesni();
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
	public FactoyMetodzapolesni() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 846, 742);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.inactiveCaption);
		contentPane.setBorder(new TitledBorder(null, "PODACI O ZAPOSLENIMA", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		 cb = new JComboBox();
		cb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				
			
			}
		});
		cb.setBounds(298, 91, 182, 20);
		contentPane.add(cb);
		
		
		tfIme = new JTextField();
		tfIme.setBounds(298, 146, 182, 20);
		contentPane.add(tfIme);
		tfIme.setColumns(10);
		
		tfPrezime = new JTextField();
		tfPrezime.setBounds(298, 200, 184, 20);
		contentPane.add(tfPrezime);
		tfPrezime.setColumns(10);
		
		tfJmbg = new JTextField();
		tfJmbg.setBounds(298, 256, 182, 20);
		contentPane.add(tfJmbg);
		tfJmbg.setColumns(10);
		
		JButton btnDodaj = new JButton("Dodaj");
		btnDodaj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				IVratiTipZapolenog();	
				popuniCelije();
			}
		});
		btnDodaj.setBounds(567, 90, 182, 23);
		contentPane.add(btnDodaj);
		
		cbIdOrg = new JComboBox();
		cbIdOrg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			//Kontroler.getInstanca().vratiIdOrgJed();
			
				for(OrganizacionaJedinica orgje: Kontroler.getInstanca().vratiOrganizacioneJedinice()){
					if(cbIdOrg.getSelectedItem().equals(orgje.getNaziv())){
						id=orgje.getIdOrganizacioneJedinice();
						
					}
				
					
				}
			}
		});
		cbIdOrg.setBounds(298, 43, 182, 20);
		contentPane.add(cbIdOrg);
		
		JLabel lblOrganizacionaJedinica = new JLabel("organizaciona jedinica");
		lblOrganizacionaJedinica.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblOrganizacionaJedinica.setBounds(33, 45, 174, 16);
		contentPane.add(lblOrganizacionaJedinica);
		
		JLabel lblTipZaposlenog = new JLabel("POZICIJA");
		lblTipZaposlenog.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTipZaposlenog.setBounds(33, 93, 139, 16);
		contentPane.add(lblTipZaposlenog);
		
		JLabel lblImeZaposlenog = new JLabel("ime zaposlenog");
		lblImeZaposlenog.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblImeZaposlenog.setBounds(33, 148, 139, 16);
		contentPane.add(lblImeZaposlenog);
		
		JLabel lblPrezimeZaposlenog = new JLabel("prezime zaposlenog");
		lblPrezimeZaposlenog.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblPrezimeZaposlenog.setBounds(33, 202, 139, 16);
		contentPane.add(lblPrezimeZaposlenog);
		
		JLabel lblJmbgZaposlenog = new JLabel("jmbg zaposlenog");
		lblJmbgZaposlenog.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblJmbgZaposlenog.setBounds(33, 258, 139, 16);
		contentPane.add(lblJmbgZaposlenog);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 321, 792, 361);
		contentPane.add(scrollPane);
		
		table = new JTable(dtm);
		scrollPane.setViewportView(table);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int red=table.getSelectedRow();
				idZaposlenog=(int) table.getModel().getValueAt(red, 0);
				imeZaposlenog=(String) table.getModel().getValueAt(red, 1);
				prezimeZaposlenog=(String) table.getModel().getValueAt(red, 2);
				jmbgZaposlenog= (String) table.getModel().getValueAt(red, 3);
				idOrg=Integer.valueOf( table.getModel().getValueAt(red, 4).toString());
				pozicijaZaposlenog= (String) table.getModel().getValueAt(red, 5);
				tfIme.setText(imeZaposlenog);
				tfJmbg.setText(jmbgZaposlenog);
				tfPrezime.setText(prezimeZaposlenog);
				cb.setSelectedItem(pozicijaZaposlenog);
				for(domen.OrganizacionaJedinica oj:Kontroler.getInstanca().vratiOrganizacioneJedinice()){
					if(oj.getIdOrganizacioneJedinice()==idOrg){
						cbIdOrg.setSelectedItem(oj.getNaziv());
					}
				}
			
				
			}
		});
		
		JButton btnPromeni = new JButton("promeni\r\n");
		btnPromeni.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String ime=tfIme.getText();
				String prezime=tfPrezime.getText();
				String jmbg=tfJmbg.getText();
				String org=cbIdOrg.getSelectedItem().toString();
				String pozicija=cb.getSelectedItem().toString();
			/*	int idOrgJed=0;
				for(OrganizacionaJedinica orgje: Kontroler.getInstanca().vratiOrganizacioneJedinice()){
					if(org.equals(orgje.getNaziv())){
						idOrgJed=orgje.getIdOrganizacioneJedinice();
						
					}*/
				Kontroler.getInstanca().promeniPodatkeOZaposlenom(ime,prezime,jmbg,id,pozicija,idZaposlenog);
				JOptionPane.showMessageDialog(null, "promenjeni podaci ");
				popuniCelije();
					
				
			}
		});
		btnPromeni.setBounds(567, 145, 182, 23);
		contentPane.add(btnPromeni);
		
		JButton btnObrisi = new JButton("obrisi");
		btnObrisi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Kontroler.getInstanca().obrisiZaposlenog(idZaposlenog);
				JOptionPane.showMessageDialog(null, "obrisani podaci");
				popuniCelije();
			}
		});
		btnObrisi.setBounds(567, 199, 182, 23);
		contentPane.add(btnObrisi);
		ucitajVrednosti();
		Object []kolone=new Object[6];
		kolone[0]="id zaposlenog";
		kolone[1]="ime ";
		kolone[2]="prezime ";
		kolone[3]="jmbg";
		kolone[4]="organizaciona jedinica";
		kolone [5]="pozicija";
		dtm.addColumn(kolone[0]);
		dtm.addColumn(kolone[1]);
		dtm.addColumn(kolone[2]);
		dtm.addColumn(kolone[3]);
		dtm.addColumn(kolone[4]);
		dtm.addColumn(kolone[5]);
		popuniCelije();
		
	}

	private void ucitajVrednosti() {
		// TODO Auto-generated method stub
		cb.addItem(Tipzaposlenog.Rukovodilac);
		cb.addItem(Tipzaposlenog.Zaposleni);
		for(OrganizacionaJedinica orgje: Kontroler.getInstanca().vratiOrganizacioneJedinice()){
			
			cbIdOrg.addItem(orgje.getNaziv());
			
		}
		
	}

	@Override
	public void IVratiTipZapolenog() {
		// TODO Auto-generated method stub
		
	Kontroler.getInstanca().proslediZaposlenog(cb.getSelectedItem().toString(),tfIme.getText(),tfPrezime.getText(),tfJmbg.getText(),id);
		
	}
	private void popuniCelije() {
		dtm.setRowCount(0);
		Object []celije=new Object[6];
		for(domen.Zaposleni z:Kontroler.getInstanca().vratiZaposlene()){
		
			celije[0]=z.getId();
			celije[1]=z.getIme();
			celije[2]=z.getPrezime();
			celije[3]=z.getJmbg();
			celije[4]=z.getId_org_jed();
			celije[5]=z.getPozicija();
			dtm.addRow(celije);
		}
	}
	
	
}
