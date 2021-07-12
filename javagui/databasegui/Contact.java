    package databasegui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.*;

import form.DefaultTableModel;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.date;
import java.util.*;
import java.text.*;
import java.util.concurrent.TimeUnit;

import models.Contacts;
import java.awt.Font;
import javax.swing.Box;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class Contact {

	private JFrame frame;
	private JTextField txtJudul;
	private JLabel lblClock;
	private JLabel lblDateP;
	private JLabel txtHariK;
	private JLabel txtKeterlambatan;
	private JLabel lblBiaya;
	
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://127.0.0.1/test?autoReconnect=true&useSSL=false";
    static final String USER = "root";
    static final String PASS = "";
    
    static Connection conn;
    static Statement stmt;
    static ResultSet rs;
    private JTable tabelData;
//    private DefaultTableModel model;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Contact window = new Contact();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void clock()
	{
		DateFormat formatTanggal = new SimpleDateFormat("yyyy-MM-dd");
		Date tanggal = new Date();
		
		DateFormat formatJam = new SimpleDateFormat("HH:mm:ss");
		Date jam = new Date();
		
		
		
		String clock = (formatJam.format(jam));
		String date =(formatTanggal.format(tanggal));
		
		lblClock.setText(clock);
		lblDateP.setText(date);
	}
	/**
	 * Create the application.
	 */
	public Contact() {
		initialize();
		clock();
	}
	
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				show();
			}
			
		});
        
		frame.setBounds(100, 100, 567, 546);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblKontakBaru = new JLabel("Persewaan Buku XYZ");
		lblKontakBaru.setBounds(161, 11, 237, 16);
		lblKontakBaru.setFont(new Font("Tahoma", Font.BOLD, 18));
		frame.getContentPane().add(lblKontakBaru);
		
		JLabel lblNamaLengkap = new JLabel("Tanggal :");
		lblNamaLengkap.setBounds(51, 63, 105, 15);
		frame.getContentPane().add(lblNamaLengkap);
		
		JLabel lblTelphp = new JLabel("Judul Buku");
		lblTelphp.setBounds(27, 119, 70, 15);
		frame.getContentPane().add(lblTelphp);
		
		txtJudul = new JTextField();
		txtJudul.setBounds(119, 116, 152, 19);
		frame.getContentPane().add(txtJudul);
		txtJudul.setColumns(10);
		
		JLabel notif = new JLabel("");
		notif.setBounds(27, 242, 262, 20);
		frame.getContentPane().add(notif);
		JLabel txtID = new JLabel("");
		txtID.setBounds(27, 37, 70, 15);
		txtID.setVisible(false);
		frame.getContentPane().add(txtID);

		JButton btnSimpan = new JButton("Simpan");
		btnSimpan.setBackground(new Color(135, 206, 235));
		btnSimpan.setBounds(27, 218, 98, 25);
		btnSimpan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String dt = new SimpleDateFormat("yyyy.MM.dd").format(new Date()); 
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		        Calendar c = Calendar.getInstance();
		        c.add(Calendar.DATE, 7);  
		        dt = sdf.format(c.getTime());  
		        
				String judul = txtJudul.getText().toString().trim();
				String tanggal_pinjam = lblDateP.getText().toString().trim();
				String tanggal_harus_kembali = dt;
				
				
				insert(judul,tanggal_pinjam,tanggal_harus_kembali);//method digunakan untuk insert data
				notif.setText("Input Data Berhasil!");
				show();
				txtJudul.setText("");
			}
		});
		frame.getContentPane().add(btnSimpan);
		JButton btnEdit = new JButton("Edit");
		btnEdit.setBackground(new Color(135, 206, 235));
		btnEdit.setBounds(293, 218, 105, 25);

		
		JButton btnKembalikanBuku = new JButton("Kembalikan Buku");
		btnKembalikanBuku.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Harimaks = txtHariK.getText().toString().trim();			
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Date tanggal = null;
				try {
					tanggal = (Date) dateFormat.parse(Harimaks);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				Date tanggalAkhir = new Date();
				long bedaWaktu = (tanggalAkhir.getTime()-tanggal.getTime());
				long total = TimeUnit.MILLISECONDS.toDays(bedaWaktu);
				String totalk = Long.toString(total);
				txtKeterlambatan.setText(totalk);
				
				int totalke = Integer.parseInt(totalk);
				
				if(totalke > 0) {
					String totalket = String.valueOf((totalke * 2000) + 5000);
					lblBiaya.setText(totalket);
				}
				else {
					lblBiaya.setText("5000");
				}
				
				
			}
		});
		btnKembalikanBuku.setBackground(new Color(135, 206, 235));
		btnKembalikanBuku.setBounds(134, 218, 154, 25);
		frame.getContentPane().add(btnKembalikanBuku);		
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(27, 283, 502, 199);
		frame.getContentPane().add(scrollPane);
		JButton btnHapus = new JButton("Hapus");
		btnHapus.setBackground(new Color(135, 206, 235));
		btnHapus.setBounds(408, 218, 117, 25);
		
		tabelData = new JTable();
		tabelData.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = tabelData.getSelectedRow();
				if(row!=-1)
				{
				txtHariK.setText(tabelData.getValueAt(row,3).toString());
				txtJudul.setText(tabelData.getValueAt(row,1).toString());
				txtID.setText(tabelData.getValueAt(row, 0).toString());
				
				}

			}
		});
		
		scrollPane.setViewportView(tabelData);
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String judul = txtJudul.getText().toString().trim();
				String id = txtID.getText().toString().trim();
				
				update(id,judul);
				notif.setText("Edit Data Berhasil!");
				show();
				txtJudul.setText("");
				
			
			}
		});
		frame.getContentPane().add(btnEdit);
		
		btnHapus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = txtID.getText();
				delete(id);
				show();
				txtJudul.setText("");
			
			}
		});
		frame.getContentPane().add(btnHapus);
		
		lblClock = new JLabel();
		lblClock.setBounds(401, 62, 85, 15);
		frame.getContentPane().add(lblClock);
		
		lblDateP = new JLabel("New label");
		lblDateP.setBounds(119, 63, 137, 14);
		frame.getContentPane().add(lblDateP);
		
		JLabel lblNewLabel = new JLabel("Jam :");
		lblNewLabel.setBounds(349, 62, 49, 14);
		frame.getContentPane().add(lblNewLabel);
		
		Box horizontalBox = Box.createHorizontalBox();
		horizontalBox.setBorder(new LineBorder(new Color(0, 0, 0)));
		horizontalBox.setBounds(37, 56, 442, 31);
		frame.getContentPane().add(horizontalBox);
		
		JLabel lblNewLabel_1 = new JLabel("Biaya :");
		lblNewLabel_1.setBounds(349, 119, 49, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		lblBiaya = new JLabel("");
		lblBiaya.setBounds(400, 153, 70, 25);
		frame.getContentPane().add(lblBiaya);
		
		txtKeterlambatan = new JLabel("New label");
		txtKeterlambatan.setBounds(27, 180, 98, 14);
		frame.getContentPane().add(txtKeterlambatan);
		txtKeterlambatan.setVisible(false);
		
		txtHariK = new JLabel("New label");
		txtHariK.setBounds(27, 158, 98, 14);
		frame.getContentPane().add(txtHariK);
		txtHariK.setVisible(false);
		

		
		
		
	}
	
	protected int parseInt(String text) {
		// TODO Auto-generated method stub
		return 0;
	}

	public void insert(String judul,String tanggal_pinjam,String tanggal_harus_kembali)
	{
		try {
            Class.forName(JDBC_DRIVER);
            
   
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            
 
            stmt = conn.createStatement();
            
            String sql = "INSERT INTO sewabuku (judul,tanggal_pinjam,tanggal_harus_kembali) VALUES (?,?,?)";
            
            PreparedStatement pms = conn.prepareStatement(sql);
            pms.setString(1, judul);
            pms.setString(2, tanggal_pinjam);
            pms.setString(3, tanggal_harus_kembali);
            
            pms.execute();

            
           
            
            stmt.close();
            conn.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	public void update(String id,String judul)
	{
		try {
            Class.forName(JDBC_DRIVER);
            
   
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            
 
            stmt = conn.createStatement();
            
            String sql = "UPDATE sewabuku SET judul=? WHERE id=?";
            
            PreparedStatement pms = conn.prepareStatement(sql);
            pms.setString(1, judul);
            pms.setString(2, id);
            
            pms.execute();

            stmt.close();
            conn.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	public void delete(String id)
	{
		try {
            Class.forName(JDBC_DRIVER);
            
   
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            
 
            stmt = conn.createStatement();
            
            String sql = "DELETE FROM sewabuku WHERE id=?";
            
            PreparedStatement pms = conn.prepareStatement(sql);
            pms.setString(1, id);
            
            pms.execute();

            stmt.close();
            conn.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	
	public void show()
	{

		try {
			Class.forName(JDBC_DRIVER);
            
			   
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("ID");
            model.addColumn("Judul Buku");
            model.addColumn("Tanggal Pinjam");
            model.addColumn("Tanggal Harus Kembali");
            model.addColumn("Tanggal Kembali");
            model.addColumn("Denda");
            model.addColumn("Biaya Sewa");
 
            stmt = conn.createStatement();
            String sql = "SELECT * FROM sewabuku";
            int i = 1;
            rs = stmt.executeQuery(sql);
            while(rs.next()) {
            	model.addRow(new Object[] {
            		rs.getString("id"),
            		rs.getString("judul"),
            		rs.getString("tanggal_pinjam"),
            		rs.getString("tanggal_harus_kembali"),
            		rs.getString("tanggal_kembali"),
            		rs.getString("denda"),
            		rs.getString("biaya_sewa")
            	});
            	i++;
            }
            rs.close();
            conn.close();
            stmt.close();
            
            tabelData.setModel(model);
            tabelData.setAutoResizeMode(0);
            //modifikasi lebar kolom (optional)
            tabelData.getColumnModel().getColumn(0).setPreferredWidth(30);
            tabelData.getColumnModel().getColumn(1).setPreferredWidth(100);
            tabelData.getColumnModel().getColumn(2).setPreferredWidth(200);
            tabelData.getColumnModel().getColumn(3).setPreferredWidth(200);
            tabelData.getColumnModel().getColumn(4).setPreferredWidth(200);
            tabelData.getColumnModel().getColumn(5).setPreferredWidth(100);
            tabelData.getColumnModel().getColumn(6).setPreferredWidth(100);
           
		}
		catch(Exception e) {
			e.printStackTrace();
		}
        
	}
	
	public void update()
	{
		
	}
	
	public void delete()
	{
		
	}
}

// array [0]{'nama','alamat','telp'}
//array[1] {'nama','alamat','telp'}


    

