/*Project by Mamello Sekhu
 * */
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.awt.Color;

public class book{

	private JFrame frmBookKeepingApp;
	private JTextField txtBookName;
	private JTextField txtEdition;
	private JTextField txtPrice;
	private JTable dataTable;
	private JTextField txtBookID;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					book window = new book();
					window.frmBookKeepingApp.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public book() {
		initialize();
		connect();
		table_load();
	}
	Connection con;
	PreparedStatement ps;
	ResultSet rs;
	
	public void connect() {
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost/bookkeep","root","");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void table_load() {
		try {
			ps = con.prepareStatement("select * from book");
			rs = ps.executeQuery();
			dataTable.setModel(DbUtils.resultSetToTableModel(rs));
		}
		catch(Exception er) {
			er.printStackTrace();
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmBookKeepingApp = new JFrame();
		frmBookKeepingApp.setTitle("Book Keeping App");
		frmBookKeepingApp.setBounds(100, 100, 774, 428);
		frmBookKeepingApp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmBookKeepingApp.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Book Keeper");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblNewLabel.setBounds(255, 11, 156, 22);
		frmBookKeepingApp.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setForeground(Color.BLUE);
		panel.setBorder(new TitledBorder(null, "Registration", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 44, 368, 212);
		frmBookKeepingApp.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Book Name :");
		lblNewLabel_1.setForeground(Color.BLUE);
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(10, 33, 90, 14);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Edition         :");
		lblNewLabel_1_1.setForeground(Color.BLUE);
		lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNewLabel_1_1.setBounds(10, 75, 90, 14);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Price            :");
		lblNewLabel_1_2.setForeground(Color.BLUE);
		lblNewLabel_1_2.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNewLabel_1_2.setBounds(10, 112, 90, 14);
		panel.add(lblNewLabel_1_2);
		
		txtBookName = new JTextField();
		txtBookName.setFont(new Font("Times New Roman", Font.BOLD, 16));
		txtBookName.setBounds(150, 31, 174, 20);
		panel.add(txtBookName);
		txtBookName.setColumns(10);
		
		txtEdition = new JTextField();
		txtEdition.setFont(new Font("Times New Roman", Font.BOLD, 16));
		txtEdition.setColumns(10);
		txtEdition.setBounds(150, 72, 174, 20);
		panel.add(txtEdition);
		
		txtPrice = new JTextField();
		txtPrice.setFont(new Font("Times New Roman", Font.BOLD, 16));
		txtPrice.setColumns(10);
		txtPrice.setBounds(150, 110, 174, 20);
		panel.add(txtPrice);
		
		JButton btnSave = new JButton("Save");
		btnSave.setBounds(10, 267, 111, 35);
		frmBookKeepingApp.getContentPane().add(btnSave);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBounds(141, 267, 111, 35);
		frmBookKeepingApp.getContentPane().add(btnExit);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(400, 44, 330, 252);
		frmBookKeepingApp.getContentPane().add(scrollPane);
		
		dataTable = new JTable();
		scrollPane.setViewportView(dataTable);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtBookName.setText("");
				txtEdition.setText("");
				txtPrice.setText("");
			}
		});
		btnClear.setBounds(267, 267, 111, 35);
		frmBookKeepingApp.getContentPane().add(btnClear);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Search", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 313, 368, 48);
		frmBookKeepingApp.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		txtBookID = new JTextField();
		txtBookID.setFont(new Font("Times New Roman", Font.BOLD, 16));
		txtBookID.setColumns(10);
		txtBookID.setBounds(150, 17, 174, 20);
		panel_1.add(txtBookID);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Book ID  :");
		lblNewLabel_1_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNewLabel_1_1_1.setBounds(10, 20, 90, 14);
		panel_1.add(lblNewLabel_1_1_1);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setBounds(400, 326, 111, 35);
		frmBookKeepingApp.getContentPane().add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(543, 326, 111, 35);
		frmBookKeepingApp.getContentPane().add(btnDelete);
		
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Bookname = txtBookName.getText();
				String Edition = txtEdition.getText();
				String Price = txtPrice.getText();
				
				try {
					ps = con.prepareStatement("insert into book(name,edition,price)values(?,?,?)");
					ps.setString(1, Bookname);
					ps.setString(2, Edition);
					ps.setString(3,Price);
					ps.executeUpdate();
					JOptionPane.showMessageDialog(null,"Record Added Successfully");
					table_load();
					txtBookName.setText("");
					txtEdition.setText("");
					txtPrice.setText("");
					
					txtBookName.requestFocus();
				}
				catch(Exception ex) {
					ex.printStackTrace();
				}
			}
		}
				);
		txtBookID.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					String id = txtBookID.getText();
					ps = con.prepareStatement("select name,edition,price from book where id =?");
					ps.setString(1,id);
					ResultSet rs = ps.executeQuery();
					
					if(rs.next()==true) {
						String name = rs.getString(1);
						String edition = rs.getString(2);
						String price = rs.getString(3);
						
						txtBookName.setText(name);
						txtEdition.setText(edition);
						txtPrice.setText(price);
					}
					else {
						txtBookName.setText("");
						txtEdition.setText("");
						txtPrice.setText("");
					}
				}
				catch(Exception err) {
					err.printStackTrace();
				}
			}
		}
				);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Bookname = txtBookName.getText();
				String Edition = txtEdition.getText();
				String Price = txtPrice.getText();
				String bid = txtBookID.getText();
				try {
					ps = con.prepareStatement("update book set name=?,edition=?,price=? where id=?");
					ps.setString(1, Bookname);
					ps.setString(2, Edition);
					ps.setString(3,Price);
					ps.setString(4,bid);
					ps.executeUpdate();
					JOptionPane.showMessageDialog(null,"Record Updated Successfully");
					table_load();
					txtBookName.setText("");
					txtEdition.setText("");
					txtPrice.setText("");
					
					txtBookName.requestFocus();
				}
				catch(Exception ex) {
					ex.printStackTrace();
				}
			}
		}
				);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String bid = txtBookID.getText();
				try {
					ps = con.prepareStatement("delete from book where id=?");
					ps.setString(1,bid);
					ps.executeUpdate();
					JOptionPane.showMessageDialog(null,"Record Deleted Successfully");
					table_load();
					txtBookName.setText("");
					txtEdition.setText("");
					txtPrice.setText("");
					
					txtBookName.requestFocus();
				}
				catch(Exception ex) {
					ex.printStackTrace();
				}
			}
		}
				);
	}
}
