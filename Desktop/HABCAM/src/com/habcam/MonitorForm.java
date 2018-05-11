package com.habcam;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Image;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.border.BevelBorder;

public class MonitorForm extends JFrame {

	private static final long serialVersionUID = 1L;
	public MonitorForm() {
		
		 setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		 setLocationRelativeTo(null);
	     setVisible(true);
	     setResizable(false);
	     setSize(1000,618);
	     setLocation(100,25);
		
		panel2 = new JPanel();
		panel2.setBackground(new Color(44, 62, 80));
		getContentPane().add(panel2, BorderLayout.CENTER);
		panel2.setLayout(null);
		
		//ImageIcon addCamIcon = new ImageIcon(MonitorForm.class.getResource("/com/media/addCam.png"));
		cam1Canvas.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		cam1Canvas.setBackground(new Color(44, 62, 80));
		cam2Canvas.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		cam2Canvas.setBackground(new Color(44, 62, 80));
		cam3Canvas.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		cam3Canvas.setBackground(new Color(44, 62, 80));
		cam4Canvas.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		cam4Canvas.setBackground(new Color(44, 62, 80));
		
		cam1Canvas.setBounds(33, 55, 320, 240);
		cam2Canvas.setBounds(483, 55, 320, 240);
		cam3Canvas.setBounds(33, 315, 320, 240);
		cam4Canvas.setBounds(483, 315, 320, 240);
		
		cam1Canvas.setLayout(null);
		cam2Canvas.setLayout(null);
		cam3Canvas.setLayout(null);
		cam4Canvas.setLayout(null);
		
		panel2.add(cam1Canvas);
		panel2.add(cam2Canvas);
		panel2.add(cam3Canvas);
		panel2.add(cam4Canvas);
		
		
		Image takePictureImage = new ImageIcon(MonitorForm.class.getResource("/com/media/take picture.png")).getImage();
		takePictureImage = takePictureImage.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);
		
		btnStartRecord = new JButton();
		btnStartRecord.setEnabled(false);
		btnStartRecord.setText("Start Record");
		btnStartRecord.setForeground(Color.WHITE);
		btnStartRecord.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnStartRecord.setBackground(new Color(34, 167, 240));
		btnStartRecord.setBounds(854, 544, 130, 38);
		btnStartRecord.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				startStopRecord();
			}
		});
		panel2.add(btnStartRecord);
		
		Image changeOptions = new ImageIcon(MonitorForm.class.getResource("/com/media/addCam.png")).getImage();
		changeOptions = changeOptions.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);
		
		btnCam1Options = new JButton("");
		btnCam1Options.setBorderPainted(false);
		btnCam1Options.setBackground(new Color(44, 62, 80));
		btnCam1Options.setBounds(363, 55, 42, 42);
		btnCam1Options.setIcon(new ImageIcon(changeOptions));
		btnCam1Options.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				setCamOptionsForm setcamoptionsform = new setCamOptionsForm(thisForm, 0);
            	setcamoptionsform.setVisible(true);
            	dispose();
		
		        }
			
		});
		panel2.add(btnCam1Options);
		
		btnCam2Options = new JButton("");
		btnCam2Options.setBorderPainted(false);
		btnCam2Options.setBackground(new Color(44, 62, 80));
		btnCam2Options.setBounds(813, 55, 42, 42);
		btnCam2Options.setIcon(new ImageIcon(changeOptions));
		btnCam2Options.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
            	setCamOptionsForm setcamoptionsform = new setCamOptionsForm(thisForm, 1);
            	setcamoptionsform.setVisible(true);
            	dispose();
            }
        });
		panel2.add(btnCam2Options);
		
		btnCam3Options = new JButton("");
		btnCam3Options.setBorderPainted(false);
		btnCam3Options.setBackground(new Color(44, 62, 80));
		btnCam3Options.setBounds(363, 315, 42, 42);
		btnCam3Options.setIcon(new ImageIcon(changeOptions));
		btnCam3Options.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
            	setCamOptionsForm setcamoptionsform = new setCamOptionsForm(thisForm, 2);
            	setcamoptionsform.setVisible(true);
            	dispose();
            }
        });
		panel2.add(btnCam3Options);
		
		btnCam4Options = new JButton("");
		btnCam4Options.setBorderPainted(false);
		btnCam4Options.setBackground(new Color(44, 62, 80));
		btnCam4Options.setBounds(813, 315, 42, 42);
		btnCam4Options.setIcon(new ImageIcon(changeOptions));
		btnCam4Options.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
            	setCamOptionsForm setcamoptionsform = new setCamOptionsForm(thisForm, 3);
            	setcamoptionsform.setVisible(true);
            	dispose();
            }
        });
		panel2.add(btnCam4Options);
		
				
				btnTakePicture3 = new JButton("");
				btnTakePicture3.setEnabled(false);
				btnTakePicture3.setBorderPainted(false);
				btnTakePicture3.setIcon(new ImageIcon(takePictureImage));
				btnTakePicture3.setBackground(new Color(44, 62, 80));
				btnTakePicture3.setBounds(363, 368, 42, 42);
				btnTakePicture3.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent ae) {
						saveImage( webcamRecorder2.getSnapshot());
				
				        }
					
				});
				panel2.add(btnTakePicture3);
				
				btnTakePicture1 = new JButton("");
				btnTakePicture1.setEnabled(false);
				btnTakePicture1.setBackground(new Color(44, 62, 80));
				btnTakePicture1.setIcon(new ImageIcon(takePictureImage));
				btnTakePicture1.setBounds(363, 108, 42, 42);
				btnTakePicture1.setBorderPainted(false);
				btnTakePicture1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent ae) {
						saveImage( webcamRecorder0.getSnapshot());
				
				        }
					
				});
				
				panel2.add(btnTakePicture1);
				
				
				btnTakePicture2 = new JButton("");
				btnTakePicture2.setEnabled(false);
				btnTakePicture2.setBorderPainted(false);
				btnTakePicture2.setIcon(new ImageIcon(takePictureImage));
				btnTakePicture2.setBackground(new Color(44, 62, 80));
				btnTakePicture2.setBounds(813, 108, 42, 42);
				btnTakePicture2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent ae) {
						saveImage( webcamRecorder1.getSnapshot());
				
				        }
					
				});
				panel2.add(btnTakePicture2);
				
				btnTakePicture4 = new JButton("");
				btnTakePicture4.setEnabled(false);
				btnTakePicture4.setBorderPainted(false);
				btnTakePicture4.setIcon(new ImageIcon(takePictureImage));
				btnTakePicture4.setBackground(new Color(44, 62, 80));
				btnTakePicture4.setBounds(813, 368, 42, 42);
				btnTakePicture4.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent ae) {
						saveImage( webcamRecorder3.getSnapshot());
				
				        }
					
				});
				panel2.add(btnTakePicture4);
				
				JPanel panel = new JPanel();
				panel.setBackground(new Color(22, 44, 66));
				panel.setBounds(0, 0, 990, 44);
				panel2.add(panel);
				
				JLabel lblHabcamMonitoring = new JLabel();
				lblHabcamMonitoring.setText("Habcam - Monitoring");
				lblHabcamMonitoring.setForeground(Color.WHITE);
				lblHabcamMonitoring.setFont(new Font("Tahoma", Font.BOLD, 24));
				GroupLayout gl_panel = new GroupLayout(panel);
				gl_panel.setHorizontalGroup(
					gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGap(0, 392, Short.MAX_VALUE)
						.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblHabcamMonitoring)
							.addContainerGap(247, Short.MAX_VALUE))
				);
				gl_panel.setVerticalGroup(
					gl_panel.createParallelGroup(Alignment.LEADING)
						.addGap(0, 44, Short.MAX_VALUE)
						.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
							.addGap(4)
							.addComponent(lblHabcamMonitoring, GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
							.addContainerGap())
				);
				panel.setLayout(gl_panel);
		
		ImageIcon image = new ImageIcon(this.getClass().getResource("/com/media/webcam.png"));//ImageIO.read(this.getClass().getResource("/com/media/webcam.png"));
		cam1Canvas.getGraphics().drawImage(image.getImage(), 0, 0, 480, 360, null);
		cam2Canvas.getGraphics().drawImage(image.getImage(), 0, 0, 480, 360, null);
		cam3Canvas.getGraphics().drawImage(image.getImage(), 0, 0, 480, 360, null);
		cam4Canvas.getGraphics().drawImage(image.getImage(), 0, 0, 480, 360, null);
		repaint();


		 Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
		        public void run() {
		        
		        	stopAllProcess();
		            System.out.println("In shutdown hook");
		            
		        }
		    }, "Shutdown-thread"));

		
	}
	protected void saveImage(BufferedImage image) {
		final JFileChooser fchooser = new JFileChooser(".");
		final File saveFile = new File("saveSnapshot.png");
		fchooser.setSelectedFile(saveFile);
        int retvalue = fchooser.showSaveDialog(new JLabel("Kaydet"));
        if (retvalue == JFileChooser.APPROVE_OPTION)
        {
        	try {
				ImageIO.write(image, "PNG", fchooser.getSelectedFile());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
         }
		
	}

	protected void stopAllProcess() {
		if(webcamRecorder0 != null) {
			webcamRecorder0.stop();	
			webcamThread0.interrupt();
			if(p0 != null)	p0.destroy();
			webSocket0.interrupt();
		}
    	if(webcamRecorder1 != null) {
    		webcamRecorder1.stop();
    		webcamThread1.interrupt();
        	if(p1 != null)	p1.destroy();
    		webSocket1.interrupt();
    	}
    	if(webcamRecorder2 != null) {
    		webcamRecorder2.stop();
    		webcamThread2.interrupt();
        	if(p2 != null)	p2.destroy();
    		webSocket2.interrupt();
    	}
    	if(webcamRecorder3 != null) {
    		webcamRecorder3.stop();
    		webcamThread3.interrupt();
        	if(p3 != null)	p3.destroy();
    		webSocket3.interrupt();
    	}
    	
	}
	
	protected void startStopRecord() {
		if(!isStarted) {
			btnStartRecord.setText("Stop Record");
			btnStartRecord.setBackground(new java.awt.Color(242, 38, 19));
			
			if(isCam1setted) {
				pb0 = new ProcessBuilder("node", "./src/com/habcam/websocket-relay.js" ,"live","8081","8082");
				pb0.redirectOutput(ProcessBuilder.Redirect.INHERIT);
				pb0.redirectError(ProcessBuilder.Redirect.INHERIT);
				
				webSocket0 = new Thread(new Runnable() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						try {
							p0 = pb0.start();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
				});
				webSocket0.start();
				webcamThread0 = new Thread(webcamRecorder0);
				webcamThread0.start();
				btnTakePicture1.setEnabled(true);
				isStarted = true;
			} 
			if(isCam2setted) {
				pb1 = new ProcessBuilder("node", "./src/com/habcam/websocket-relay.js" ,"live","8083","8084");
				pb1.redirectOutput(ProcessBuilder.Redirect.INHERIT);
				pb1.redirectError(ProcessBuilder.Redirect.INHERIT);
				
				webSocket1 = new Thread(new Runnable() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						try {
							p1 = pb1.start();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
				});
				webSocket1.start();
				webcamThread1 = new Thread(webcamRecorder1);
				webcamThread1.start();
				btnTakePicture2.setEnabled(true);
				isStarted = true;
			}		
			if(isCam3setted) {
				pb2 = new ProcessBuilder("node", "./src/com/habcam/websocket-relay.js" ,"live","8085","8086");
				pb2.redirectOutput(ProcessBuilder.Redirect.INHERIT);
				pb2.redirectError(ProcessBuilder.Redirect.INHERIT);
				
				webSocket2 = new Thread(new Runnable() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						try {
							p2 = pb2.start();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
				});
				webSocket2.start();
				webcamThread2 = new Thread(webcamRecorder2);
				webcamThread2.start();
				btnTakePicture3.setEnabled(true);
				isStarted = true;
			}		
			if(isCam4setted) {
				pb3 = new ProcessBuilder("node", "./src/com/habcam/websocket-relay.js" ,"live","8087","8088");
				pb3.redirectOutput(ProcessBuilder.Redirect.INHERIT);
				pb3.redirectError(ProcessBuilder.Redirect.INHERIT);
				
				webSocket3 = new Thread(new Runnable() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						try {
							p3 = pb3.start();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
				});
				webSocket3.start();
				webcamThread3 = new Thread(webcamRecorder3);
				webcamThread3.start();
				btnTakePicture4.setEnabled(true);
				isStarted = true;
			}
		
		}
		else 
		{
			btnStartRecord.setText("Start Record");
			btnStartRecord.setBackground(new java.awt.Color(34, 167, 240));

			btnTakePicture1.setEnabled(false);
			btnTakePicture2.setEnabled(false);
			btnTakePicture3.setEnabled(false);
			btnTakePicture4.setEnabled(false);
			
			repaint();
			isStarted = false;
			stopAllProcess();
			
		}
	}
	
	public static JPanel cam1Canvas = new JPanel();
	public static JPanel cam2Canvas = new JPanel();
	public static JPanel cam3Canvas = new JPanel();
	public static JPanel cam4Canvas = new JPanel();
	JPanel panel2;
	
	JButton btnStartRecord;
	
	JButton btnCam1Options;
	JButton btnCam2Options;
	JButton btnCam3Options;
	JButton btnCam4Options;
	
	JButton btnTakePicture1;
	JButton btnTakePicture2;
	JButton btnTakePicture3;
	JButton btnTakePicture4;
	
	boolean isStarted = false;
	
	boolean isCam1setted = false;
	boolean isCam2setted = false;
	boolean isCam3setted = false;
	boolean isCam4setted = false;
	
	MonitorForm thisForm = this;
	
	Thread webSocket0;
	Thread webSocket1;
	Thread webSocket2;
	Thread webSocket3;
	
	Thread webcamThread0;
	Thread webcamThread1;
	Thread webcamThread2;
	Thread webcamThread3;
	
	Process p0;
	Process p1;
	Process p2;
	Process p3;
	
	ProcessBuilder pb0;
	ProcessBuilder pb1;
	ProcessBuilder pb2;
	ProcessBuilder pb3;
	
	Recorder0 webcamRecorder0;
	Recorder1 webcamRecorder1;
	Recorder2 webcamRecorder2;
	Recorder3 webcamRecorder3;
}
