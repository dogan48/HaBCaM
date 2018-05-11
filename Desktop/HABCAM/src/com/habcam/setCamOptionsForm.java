package com.habcam;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.JPanel;

public class setCamOptionsForm extends JFrame {

	private MonitorForm monitorForm;
	private int recorderNo;
	
	public setCamOptionsForm() {
		
		
		initComponents();
		
	}
	public setCamOptionsForm(MonitorForm monitor, int recorderNo) {
		initComponents();
		monitorForm = monitor;
		this.recorderNo = recorderNo;
		
	}
	
	
	 private void initComponents() {
		 setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		 setBackground(new java.awt.Color(44, 62, 80));
		 getContentPane().setBackground(new java.awt.Color(44,62,80));
		 //setUndecorated(true);
		 setLocationRelativeTo(null);
	     setVisible(true);
	     setResizable(false);
	     setSize(395,295);
	     setLocation(450,250);
		 panel2 = new javax.swing.JPanel();
		 btnSet = new javax.swing.JButton();
		 btnCancel = new javax.swing.JButton();
		 cbCamNo = new JComboBox();
		 setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
	        
	        
	        panel2.setBackground(new java.awt.Color(44, 62, 80));
	        
	        btnSet.setBackground(new java.awt.Color(34, 167, 240));
	        btnSet.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
	        btnSet.setForeground(new java.awt.Color(255, 255, 255));
	        btnSet.setText("Set");
	        btnSet.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {
					int width = Integer.parseInt(txtWidth.getText());
					int height = Integer.parseInt(txtHeight.getText());
					setRecorderOptions(cbCamNo.getSelectedIndex(),width,height,(Integer)spinnerFPS.getValue());
					monitorForm.setVisible(true);
					dispose();
			
				
				}
			});
	        
	        btnCancel.setBackground(new java.awt.Color(242, 38, 19));
	        btnCancel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
	        btnCancel.setForeground(new java.awt.Color(255, 255, 255));
	        btnCancel.setText("Cancel");
	        btnCancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {
					
					labelCloseMouseClicked(null);
			
				
				}
			});
	        
	        lblFps = new JLabel();
	        lblFps.setHorizontalAlignment(SwingConstants.RIGHT);
	        lblFps.setText("FPS:");
	        lblFps.setForeground(new Color(236, 240, 241));
	        lblFps.setFont(new Font("Tahoma", Font.PLAIN, 18));
	        
	        lblCamNo = new JLabel();
	        lblCamNo.setHorizontalAlignment(SwingConstants.RIGHT);
	        lblCamNo.setText("Cam No:");
	        lblCamNo.setForeground(new Color(236, 240, 241));
	        lblCamNo.setFont(new Font("Tahoma", Font.PLAIN, 18));
	        
	        lblWidth = new JLabel();
	        lblWidth.setHorizontalAlignment(SwingConstants.RIGHT);
	        lblWidth.setText("Width:");
	        lblWidth.setForeground(new Color(236, 240, 241));
	        lblWidth.setFont(new Font("Tahoma", Font.PLAIN, 18));
	        
	        lblHeight = new JLabel();
	        lblHeight.setHorizontalAlignment(SwingConstants.RIGHT);
	        lblHeight.setText("Height:");
	        lblHeight.setForeground(new Color(236, 240, 241));
	        lblHeight.setFont(new Font("Tahoma", Font.PLAIN, 18));
	        
	       
	        cbCamNo.setModel(new DefaultComboBoxModel(new String[] {"Cam 0", "Cam 1", "Cam 2", "Cam 3"}));
	        
	        txtWidth = new JTextField();
	        txtWidth.setText("640");
	        txtWidth.setForeground(new Color(228, 241, 254));
	        txtWidth.setFont(new Font("Tahoma", Font.BOLD, 16));
	        txtWidth.setBackground(new Color(108, 122, 137));
	        txtWidth.addKeyListener(new KeyAdapter() {
	        	public void keyTyped(KeyEvent e) {
	        		char c = e.getKeyChar();
	        		if(!((c >= '0') && (c <= '9')|| (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
	        			getToolkit().beep();
	        	        e.consume();
	        		}
	        	}
			});
	        
	        txtHeight = new JTextField();
	        txtHeight.setText("480");
	        txtHeight.setForeground(new Color(228, 241, 254));
	        txtHeight.setFont(new Font("Tahoma", Font.BOLD, 16));
	        txtHeight.setBackground(new Color(108, 122, 137));
	        txtHeight.addKeyListener(new KeyAdapter() {
	        	public void keyTyped(KeyEvent e) {
	        		char c = e.getKeyChar();
	        		if(!((c >= '0') && (c <= '9')|| (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
	        			getToolkit().beep();
	        	        e.consume();
	        		}
	        	}
			});
	        spinnerFPS = new JSpinner();
	        spinnerFPS.setModel(new SpinnerNumberModel(24, 20, 30, 1));
				
	        
	        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(panel2);
	        jPanel2Layout.setHorizontalGroup(
	        	jPanel2Layout.createParallelGroup(Alignment.LEADING)
	        		.addGroup(jPanel2Layout.createSequentialGroup()
	        			.addGap(31)
	        			.addGroup(jPanel2Layout.createParallelGroup(Alignment.LEADING)
	        				.addGroup(jPanel2Layout.createSequentialGroup()
	        					.addComponent(lblFps, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
	        					.addGap(25)
	        					.addComponent(spinnerFPS, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	        				.addGroup(jPanel2Layout.createSequentialGroup()
	        					.addGroup(jPanel2Layout.createParallelGroup(Alignment.TRAILING)
	        						.addComponent(lblCamNo, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
	        						.addComponent(lblWidth, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
	        						.addComponent(lblHeight, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE))
	        					.addGap(25)
	        					.addGroup(jPanel2Layout.createParallelGroup(Alignment.LEADING)
	        						.addComponent(cbCamNo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	        						.addGroup(jPanel2Layout.createParallelGroup(Alignment.LEADING, false)
	        							.addComponent(txtWidth, GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
	        							.addComponent(txtHeight, 0, 0, Short.MAX_VALUE))
	        						.addGroup(jPanel2Layout.createSequentialGroup()
	        							.addComponent(btnCancel, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
	        							.addGap(18)
	        							.addComponent(btnSet, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)))))
	        			.addContainerGap(66, Short.MAX_VALUE))
	        );
	        jPanel2Layout.setVerticalGroup(
	        	jPanel2Layout.createParallelGroup(Alignment.LEADING)
	        		.addGroup(jPanel2Layout.createSequentialGroup()
	        			.addGap(16)
	        			.addGroup(jPanel2Layout.createParallelGroup(Alignment.BASELINE)
	        				.addComponent(lblCamNo, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
	        				.addComponent(cbCamNo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	        			.addPreferredGap(ComponentPlacement.UNRELATED)
	        			.addGroup(jPanel2Layout.createParallelGroup(Alignment.BASELINE)
	        				.addComponent(lblWidth, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
	        				.addComponent(txtWidth, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
	        			.addPreferredGap(ComponentPlacement.UNRELATED)
	        			.addGroup(jPanel2Layout.createParallelGroup(Alignment.BASELINE)
	        				.addComponent(lblHeight, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
	        				.addComponent(txtHeight, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
	        			.addGap(18)
	        			.addGroup(jPanel2Layout.createParallelGroup(Alignment.BASELINE)
	        				.addComponent(lblFps, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
	        				.addComponent(spinnerFPS, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	        			.addGap(18)
	        			.addGroup(jPanel2Layout.createParallelGroup(Alignment.BASELINE)
	        				.addComponent(btnCancel, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
	        				.addComponent(btnSet, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
	        			.addContainerGap(58, Short.MAX_VALUE))
	        );
	        panel2.setLayout(jPanel2Layout);
	        
	        panel = new JPanel();
	        panel.setBackground(new Color(22, 44, 66));
	        
	        label = new JLabel();
	        label.setText("Habcam - Set Camera Options");
	        label.setForeground(Color.WHITE);
	        label.setFont(new Font("Tahoma", Font.BOLD, 24));
	        GroupLayout gl_panel = new GroupLayout(panel);
	        gl_panel.setHorizontalGroup(
	        	gl_panel.createParallelGroup(Alignment.LEADING)
	        		.addGap(0, 392, Short.MAX_VALUE)
	        		.addGroup(gl_panel.createSequentialGroup()
	        			.addContainerGap()
	        			.addComponent(label)
	        			.addContainerGap(195, Short.MAX_VALUE))
	        );
	        gl_panel.setVerticalGroup(
	        	gl_panel.createParallelGroup(Alignment.TRAILING)
	        		.addGap(0, 44, Short.MAX_VALUE)
	        		.addGroup(gl_panel.createSequentialGroup()
	        			.addGap(4)
	        			.addComponent(label, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	        			.addContainerGap())
	        );
	        panel.setLayout(gl_panel);

	        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
	        layout.setHorizontalGroup(
	        	layout.createParallelGroup(Alignment.LEADING)
	        		.addComponent(panel, GroupLayout.DEFAULT_SIZE, 412, Short.MAX_VALUE)
	        		.addGroup(layout.createSequentialGroup()
	        			.addComponent(panel2, GroupLayout.PREFERRED_SIZE, 402, GroupLayout.PREFERRED_SIZE)
	        			.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	        );
	        layout.setVerticalGroup(
	        	layout.createParallelGroup(Alignment.LEADING)
	        		.addGroup(layout.createSequentialGroup()
	        			.addComponent(panel, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
	        			.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	        			.addComponent(panel2, GroupLayout.PREFERRED_SIZE, 224, GroupLayout.PREFERRED_SIZE)
	        			.addGap(32))
	        );
	        getContentPane().setLayout(layout);
		 
	 }
	 private void setRecorderOptions(int camNo, int width, int height, int fps) {
		// TODO Auto-generated method stub
		 switch (recorderNo){
		 case 0:
			 monitorForm.webcamRecorder0 = new Recorder0(camNo,"C:/wamp64/www/habcam/dataset/Cam0/", monitorForm.cam1Canvas, fps);
			 monitorForm.webcamRecorder0.setWidth(width);
			 monitorForm.webcamRecorder0.setHeight(height);
			 monitorForm.webcamRecorder0.setFPS(fps);
			 monitorForm.isCam1setted = true;
			 monitorForm.btnCam1Options.setEnabled(false);
			 monitorForm.btnStartRecord.setEnabled(true);
			 
			 break;
		 case 1:
			 monitorForm.webcamRecorder1 = new Recorder1(camNo,"C:/wamp64/www/habcam/dataset/Cam1/", monitorForm.cam2Canvas, fps);
			 monitorForm.webcamRecorder1.setWidth(width);
			 monitorForm.webcamRecorder1.setHeight(height);
			 monitorForm.webcamRecorder1.setFPS(fps);
			 monitorForm.isCam2setted = true;
			 monitorForm.btnCam2Options.setEnabled(false);
			 monitorForm.btnStartRecord.setEnabled(true);
			 break;
		 case 2:
			 monitorForm.webcamRecorder2 = new Recorder2(camNo,"C:/wamp64/www/habcam/dataset/Cam2/", monitorForm.cam3Canvas, fps);
			 monitorForm.webcamRecorder2.setWidth(width);
			 monitorForm.webcamRecorder2.setHeight(height);
			 monitorForm.webcamRecorder2.setFPS(fps);
			 monitorForm.isCam3setted = true;
			 monitorForm.btnCam3Options.setEnabled(false);
			 monitorForm.btnStartRecord.setEnabled(true);
			 break;
		 case 3:
			 monitorForm.webcamRecorder3 = new Recorder3(camNo,"C:/wamp64/www/habcam/dataset/Cam3/", monitorForm.cam4Canvas, fps);
			 monitorForm.webcamRecorder3.setWidth(width);
			 monitorForm.webcamRecorder3.setHeight(height);
			 monitorForm.webcamRecorder3.setFPS(fps);
			 monitorForm.isCam4setted = true;
			 monitorForm.btnCam4Options.setEnabled(false);
			 monitorForm.btnStartRecord.setEnabled(true);
			 break;
		 }
	}
		private void labelCloseMouseClicked(MouseEvent evt) {
			// TODO Auto-generated method stub
			txtWidth.setText("");
			txtHeight.setText("");
			cbCamNo.setSelectedIndex(0);
			spinnerFPS.setValue(24);
			monitorForm.setVisible(true);
			dispose();

				
		 }
		
		 private javax.swing.JButton btnSet;
		 private javax.swing.JButton btnCancel;
		 private javax.swing.JPanel panel2;
		 private JLabel lblFps;
		 private JLabel lblCamNo;
		 private JLabel lblWidth;
		 private JLabel lblHeight;
		 private JTextField txtWidth;
		 private JTextField txtHeight;
		 private JSpinner spinnerFPS;
		 private JComboBox cbCamNo;
		 private JPanel panel;
		 private JLabel label;
}
