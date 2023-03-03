package Asturum_Regnum;
	
	import java.awt.EventQueue;
	import java.sql.*;
	import javax.swing.JFrame;
	import javax.swing.JLabel;
	import javax.swing.JOptionPane;
	import javax.swing.SwingConstants;
	import java.awt.Font;
	import javax.swing.JPanel;
	import javax.swing.border.TitledBorder;
	import javax.swing.table.TableModel;
	import javax.swing.JTextField;
	import javax.swing.JButton;
	import javax.swing.JTable;
	import javax.swing.JScrollPane;
	import java.awt.event.ActionListener;
	import java.awt.event.KeyAdapter;
	import java.awt.event.KeyEvent;
	import java.awt.event.ActionEvent;

	import net.proteanit.sql.DbUtils;

	public class Rex {

		private JFrame frame;
		private JTextField textNomen;
		private JTextField textNatio;
		private JTextField textMors;
		private JTextField textTempus;
		private JTextField textUxor;
		private JTextField textPrae;
		private JTextField textSuc;
		private JTable table;
		private JTextField textId;

		
		public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						Rex window = new Rex();
						window.frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}

	
		public Rex() {
			initialize();
			connect();
			table_load();
		}
		
		Connection conn;
		PreparedStatement pt;
		ResultSet rs;

		public void connect() {
			
			final String CONTROLLER = "com.mysql.cj.jdbc.Driver";
			final String URL = "jdbc:mysql://localhost:3306/asturum";
			final String USERNAME = "root";
			final String PASSWORD = "123456789";
			
			try {
				Class.forName(CONTROLLER);
				conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			} catch (ClassNotFoundException e) {
			
			}  catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		public void table_load() {
			
			try {
				pt = conn.prepareStatement("SELECT * FROM rex");
				rs = pt.executeQuery();
				table.setModel(DbUtils.resultSetToTableModel(rs));
			} catch (Exception e) {
			
			}
			
		}
		

		private void initialize() {
			frame = new JFrame();
			frame.setBounds(100, 100, 923, 531);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.getContentPane().setLayout(null);
			
			JLabel lblNewLabel = new JLabel("Asturum Regnum\r\n");
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setBounds(297, 10, 295, 42);
			frame.getContentPane().add(lblNewLabel);
			
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "Datos del Monarca", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel.setBounds(35, 98, 295, 265);
			frame.getContentPane().add(panel);
			panel.setLayout(null);
			
			JLabel lblNewLabel_1 = new JLabel("Nomen");
			lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblNewLabel_1.setBounds(34, 35, 97, 18);
			panel.add(lblNewLabel_1);
			
			JLabel lblNewLabel_2 = new JLabel("Natio");
			lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblNewLabel_2.setBounds(34, 63, 97, 18);
			panel.add(lblNewLabel_2);
			
			JLabel lblNewLabel_3 = new JLabel("Mors");
			lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblNewLabel_3.setBounds(34, 93, 97, 18);
			panel.add(lblNewLabel_3);
			
			JLabel lblNewLabel_4 = new JLabel("Tempus");
			lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblNewLabel_4.setBounds(34, 121, 97, 18);
			panel.add(lblNewLabel_4);
			
			JLabel lblNewLabel_5 = new JLabel("Uxor");
			lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblNewLabel_5.setBounds(34, 149, 97, 18);
			panel.add(lblNewLabel_5);
			
			JLabel lblNewLabel_6 = new JLabel("Praedecessor");
			lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblNewLabel_6.setBounds(34, 177, 145, 18);
			panel.add(lblNewLabel_6);
			
			JLabel lblNewLabel_7 = new JLabel("Sucessor");
			lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblNewLabel_7.setBounds(34, 205, 97, 18);
			panel.add(lblNewLabel_7);
			
			textNomen = new JTextField();
			textNomen.setBounds(156, 37, 96, 19);
			panel.add(textNomen);
			textNomen.setColumns(10);
			
			textNatio = new JTextField();
			textNatio.setBounds(156, 65, 96, 19);
			panel.add(textNatio);
			textNatio.setColumns(10);
			
			textMors = new JTextField();
			textMors.setBounds(156, 95, 96, 19);
			panel.add(textMors);
			textMors.setColumns(10);
			
			textTempus = new JTextField();
			textTempus.setBounds(156, 123, 96, 19);
			panel.add(textTempus);
			textTempus.setColumns(10);
			
			textUxor = new JTextField();
			textUxor.setBounds(156, 151, 96, 19);
			panel.add(textUxor);
			textUxor.setColumns(10);
			
			textPrae = new JTextField();
			textPrae.setBounds(156, 179, 96, 19);
			panel.add(textPrae);
			textPrae.setColumns(10);
			
			textSuc = new JTextField();
			textSuc.setBounds(156, 205, 96, 19);
			panel.add(textSuc);
			textSuc.setColumns(10);
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(383, 97, 480, 324);
			frame.getContentPane().add(scrollPane);
			
			table = new JTable();
			scrollPane.setViewportView(table);
			
			JButton btnNewButton = new JButton("Save");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					String nomen, natio, mors, tempus, uxor, prae, suc;
					
					nomen = textNomen.getText();
					natio = textNatio.getText();
					mors = textMors.getText();
					tempus = textTempus.getText();
					uxor = textUxor.getText();
					prae = textPrae.getText();
					suc = textSuc.getText();
					
					try {
						pt = conn.prepareStatement("INSERT into rex(Nomen, Natio, Mors, Tempus, Uxor, Praedecessor, Sucessor) values (?, ?, ?, ?, ?, ?, ?)");
						pt.setString(1, nomen);
						pt.setString(2, natio);
						pt.setString(3, mors);
						pt.setString(4, tempus);
						pt.setString(5, uxor);
						pt.setString(6, prae);
						pt.setString(7, suc);
						pt.executeUpdate();
						JOptionPane.showMessageDialog(null, "Rex inserted");
						table_load();
						textNomen.setText("");
						textNatio.setText("");
						textMors.setText("");
						textTempus.setText("");
						textUxor.setText("");
						textPrae.setText("");
						textSuc.setText("");
						textNomen.requestFocus();
						
					} catch (Exception e2) {

					}
				}
			});
			btnNewButton.setBounds(45, 373, 85, 42);
			frame.getContentPane().add(btnNewButton);
			
			JButton btnNewButton_2 = new JButton("Clear");
			btnNewButton_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					
						textNomen.setText("");
						textNatio.setText("");
						textMors.setText("");
						textTempus.setText("");
						textUxor.setText("");
						textPrae.setText("");
						textSuc.setText("");
						textNomen.requestFocus();
						
				}		
			});
			btnNewButton_2.setBounds(235, 373, 85, 42);
			frame.getContentPane().add(btnNewButton_2);
			
			JButton btnNewButton_1 = new JButton("Exit");
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
				}
			});
			btnNewButton_1.setBounds(140, 373, 85, 42);
			frame.getContentPane().add(btnNewButton_1);
			
			JPanel panel_1 = new JPanel();
			panel_1.setBorder(new TitledBorder(null, "Search", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel_1.setBounds(35, 425, 295, 42);
			frame.getContentPane().add(panel_1);
			panel_1.setLayout(null);
			
			JLabel lblNewLabel_8 = new JLabel("King ID");
			lblNewLabel_8.setBounds(58, 10, 45, 22);
			panel_1.add(lblNewLabel_8);
			
			textId = new JTextField();
			textId.addKeyListener(new KeyAdapter() {
				
				public void keyReleased(KeyEvent e) {
					
					try {
						
						String Id = textId.getText();
						
						pt = conn.prepareStatement("SELECT Nomen, Natio, Mors, Tempus, Uxor, Praedecessor, Sucessor FROM rex WHERE Id = ?");
						pt.setString(1, Id);
						ResultSet rs = pt.executeQuery();
						
						if(rs.next() == true) {
							
							String Nomen = rs.getString(1);
							String Natio = rs.getString(2);
							String Mors = rs.getString(3);
							String Tempus = rs.getString(4);
							String Uxor = rs.getString(5);
							String Praedecessor = rs.getString(6);
							String Sucessor = rs.getString(7);
							
							
							textNomen.setText(Nomen);
							textNatio.setText(Natio);
							textMors.setText(Mors);
							textTempus.setText(Tempus);
							textUxor.setText(Uxor);
							textPrae.setText(Praedecessor);
							textSuc.setText(Sucessor);
							
							
						} else {
							textNomen.setText("");
							textNatio.setText("");
							textMors.setText("");
							textTempus.setText("");
							textUxor.setText("");
							textPrae.setText("");
							textSuc.setText("");
							
						}
							
						
						
					} catch (Exception e2) {

					}
				}
			});
			textId.setBounds(113, 13, 96, 19);
			panel_1.add(textId);
			textId.setColumns(10);
			
			JButton btnNewButton_3 = new JButton("Update");
			btnNewButton_3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					String id, nomen, natio, mors, tempus, uxor, prae, suc;
					
					id = textId.getText();
					nomen = textNomen.getText();
					natio = textNatio.getText();
					mors = textMors.getText();
					tempus = textTempus.getText();
					uxor = textUxor.getText();
					prae = textPrae.getText();
					suc = textSuc.getText();
					
					try {
						pt = conn.prepareStatement("UPDATE rex SET Nomen = ?, Natio = ?, Mors = ?, Tempus = ?, Uxor = ?, Praedecessor = ?, Sucessor = ? WHERE Id = ?");
						pt.setString(1, id);
						pt.setString(2, nomen);
						pt.setString(3, natio);
						pt.setString(4, mors);
						pt.setString(5, tempus);
						pt.setString(6, uxor);
						pt.setString(7, prae);
						pt.setString(8, suc);
						pt.executeUpdate();
						JOptionPane.showMessageDialog(null, "Regnum updated!");
						table_load();
						textNomen.setText("");
						textNatio.setText("");
						textMors.setText("");
						textTempus.setText("");
						textUxor.setText("");
						textPrae.setText("");
						textSuc.setText("");
						textNomen.requestFocus();
						
					} catch (Exception e2) {

					}
					
					
				}
			});
			btnNewButton_3.setBounds(467, 431, 125, 21);
			frame.getContentPane().add(btnNewButton_3);
			
			JButton btnNewButton_4 = new JButton("Delete");
			btnNewButton_4.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					
					String id;
					
					id = textId.getText();

					
					try {
						pt = conn.prepareStatement("DELETE from rex WHERE Id = ?");
						pt.setString(1, id);

						pt.executeUpdate();
						JOptionPane.showMessageDialog(null, "Damnatio memoriae!");
						table_load();
						textNomen.setText("");
						textNatio.setText("");
						textMors.setText("");
						textTempus.setText("");
						textUxor.setText("");
						textPrae.setText("");
						textSuc.setText("");
						textNomen.requestFocus();
						
					} catch (Exception e2) {

					}
					
				}
			});
			btnNewButton_4.setBounds(647, 431, 125, 21);
			frame.getContentPane().add(btnNewButton_4);
		}
}
