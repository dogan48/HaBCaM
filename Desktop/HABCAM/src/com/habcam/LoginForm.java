package com.habcam;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.*;
import java.awt.Window.Type;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Color;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;

public class LoginForm extends JFrame{

	public LoginForm() {
		getContentPane().setBackground(new java.awt.Color(44, 62, 80));
		initComponents();
        this.setLocationRelativeTo(null);
        setVisible(true);
		setResizable(false);
		setSize(395,295);
		setLocation(450,250);
		
		
	}
	 private void initComponents() {
		 panel1 = new javax.swing.JPanel();
		 labelHeader = new javax.swing.JLabel();
		 
		 setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		 //setUndecorated(true);
		 panel1.setBackground(new java.awt.Color(22, 44, 66));
		 labelHeader.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
		 labelHeader.setForeground(new java.awt.Color(255, 255, 255));
		 labelHeader.setText("Habcam - Login");
		 
		 javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(panel1);
		 jPanel1Layout.setHorizontalGroup(
		 	jPanel1Layout.createParallelGroup(Alignment.TRAILING)
		 		.addGroup(Alignment.LEADING, jPanel1Layout.createSequentialGroup()
		 			.addContainerGap()
		 			.addComponent(labelHeader)
		 			.addContainerGap(247, Short.MAX_VALUE))
		 );
		 jPanel1Layout.setVerticalGroup(
		 	jPanel1Layout.createParallelGroup(Alignment.LEADING)
		 		.addGroup(Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
		 			.addGap(4)
		 			.addComponent(labelHeader, GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
		 			.addContainerGap())
		 );
		 panel1.setLayout(jPanel1Layout);
	        panel2 = new javax.swing.JPanel();
	        labelUsername = new javax.swing.JLabel();
	        labelUsername.setHorizontalAlignment(SwingConstants.RIGHT);
	        labelPassword = new javax.swing.JLabel();
	        labelPassword.setHorizontalAlignment(SwingConstants.RIGHT);
	        txtUser = new javax.swing.JTextField(15);
	        txtPass = new javax.swing.JPasswordField(15);
	        btnLogin = new javax.swing.JButton();
	        btnCancel = new javax.swing.JButton();
	        
	        
	        panel2.setBackground(new java.awt.Color(44, 62, 80));
	        
	        labelUsername.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
	        labelUsername.setForeground(new java.awt.Color(236, 240, 241));
	        labelUsername.setText("Username:");
	        
	        labelPassword.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
	        labelPassword.setForeground(new java.awt.Color(236, 240, 241));
	        labelPassword.setText("Password:");
	        
	        txtUser.setBackground(new java.awt.Color(108, 122, 137));
	        txtUser.setFont(new Font("Tahoma", Font.BOLD, 16)); // NOI18N
	        txtUser.setForeground(new java.awt.Color(228, 241, 254));
	        
	        txtPass.setBackground(new java.awt.Color(108, 122, 137));
	        txtPass.setFont(new Font("Tahoma", Font.BOLD, 16)); // NOI18N
	        txtPass.setForeground(new java.awt.Color(228, 241, 254));
	        
	        btnLogin.setBackground(new java.awt.Color(34, 167, 240));
	        btnLogin.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
	        btnLogin.setForeground(new java.awt.Color(255, 255, 255));
	        btnLogin.setText("Login");
	        btnLogin.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {
					String username = txtUser.getText();
					String password = txtPass.getText();
					if(username.equals("") && password.equals("")) {
						MonitorForm securityMonitor = new MonitorForm();
						securityMonitor.setVisible(true);
						dispose();
					} else {
						JOptionPane.showMessageDialog(null,"Wrong Password or Username");
						txtUser.setText("");
						txtPass.setText("");
						txtUser.requestFocus();
					}
			
				}
			});
	        	        
	        btnCancel.setBackground(new java.awt.Color(242, 38, 19));
	        btnCancel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
	        btnCancel.setForeground(new java.awt.Color(255, 255, 255));
	        btnCancel.setText("Cancel");
	        btnCancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {
					txtUser.setText("");
					txtPass.setText("");
					txtUser.requestFocus();
					
				}
			});
	        	        
	        
	        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(panel2);
	        jPanel2Layout.setHorizontalGroup(
	        	jPanel2Layout.createParallelGroup(Alignment.TRAILING)
	        		.addGroup(jPanel2Layout.createSequentialGroup()
	        			.addContainerGap(10, Short.MAX_VALUE)
	        			.addGroup(jPanel2Layout.createParallelGroup(Alignment.TRAILING)
	        				.addGroup(jPanel2Layout.createSequentialGroup()
	        					.addGroup(jPanel2Layout.createParallelGroup(Alignment.LEADING)
	        						.addComponent(labelPassword, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
	        						.addComponent(labelUsername, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE))
	        					.addPreferredGap(ComponentPlacement.UNRELATED)
	        					.addGroup(jPanel2Layout.createParallelGroup(Alignment.LEADING)
	        						.addComponent(txtUser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	        						.addComponent(txtPass)))
	        				.addGroup(jPanel2Layout.createSequentialGroup()
	        					.addComponent(btnCancel, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
	        					.addGap(18)
	        					.addComponent(btnLogin, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)))
	        			.addGap(189))
	        );
	        jPanel2Layout.setVerticalGroup(
	        	jPanel2Layout.createParallelGroup(Alignment.LEADING)
	        		.addGroup(jPanel2Layout.createSequentialGroup()
	        			.addGap(47)
	        			.addGroup(jPanel2Layout.createParallelGroup(Alignment.BASELINE)
	        				.addComponent(txtUser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	        				.addComponent(labelUsername))
	        			.addGap(18)
	        			.addGroup(jPanel2Layout.createParallelGroup(Alignment.BASELINE)
	        				.addComponent(txtPass, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	        				.addComponent(labelPassword))
	        			.addGap(42)
	        			.addGroup(jPanel2Layout.createParallelGroup(Alignment.BASELINE)
	        				.addComponent(btnLogin, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
	        				.addComponent(btnCancel, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
	        			.addContainerGap(69, Short.MAX_VALUE))
	        );
	        panel2.setLayout(jPanel2Layout);

	        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
	        layout.setHorizontalGroup(
	        	layout.createParallelGroup(Alignment.LEADING)
	        		.addGroup(layout.createSequentialGroup()
	        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
	        				.addGroup(layout.createSequentialGroup()
	        					.addContainerGap()
	        					.addComponent(panel2, GroupLayout.PREFERRED_SIZE, 415, GroupLayout.PREFERRED_SIZE))
	        				.addComponent(panel1, GroupLayout.PREFERRED_SIZE, 392, GroupLayout.PREFERRED_SIZE))
	        			.addContainerGap(33, Short.MAX_VALUE))
	        );
	        layout.setVerticalGroup(
	        	layout.createParallelGroup(Alignment.LEADING)
	        		.addGroup(layout.createSequentialGroup()
	        			.addComponent(panel1, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
	        			.addPreferredGap(ComponentPlacement.UNRELATED)
	        			.addComponent(panel2, GroupLayout.PREFERRED_SIZE, 270, GroupLayout.PREFERRED_SIZE)
	        			.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	        );
	        getContentPane().setLayout(layout);
		 
	 }
	
	 private javax.swing.JButton btnLogin;
	 private javax.swing.JButton btnCancel;
	 private javax.swing.JLabel labelHeader;
	 private javax.swing.JLabel labelUsername;
	 private javax.swing.JLabel labelPassword;
	 private javax.swing.JPanel panel1;
	 private javax.swing.JPanel panel2;
	 private javax.swing.JPasswordField txtPass;
	 private javax.swing.JTextField txtUser;
}
