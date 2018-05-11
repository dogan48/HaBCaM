package com.habcam;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.GrayFilter;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.bytedeco.javacpp.avcodec;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.FFmpegFrameRecorder;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.FrameRecorder.Exception;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.bytedeco.javacv.OpenCVFrameGrabber;

public class Recorder0 implements Runnable{

    private static FrameGrabber grabber0 = null;
    private static FFmpegFrameRecorder recorder0 = null;
    private static FFmpegFrameRecorder converter0 = null;
    private static FFmpegFrameRecorder sender0 = null;

    private int CAPTUREWIDTH = 640;
    private int CAPTUREHRIGHT = 480;
    private int FRAME_RATE = 20;
    private static final int GOP_LENGTH_IN_FRAMES = 60;// Ýnternetten ne iþe yarýyor bak
    private volatile boolean runnable = true;
    private JPanel canvas = null;
	private int webcamNo;
	private String videoRecordAddr;
	private JLabel webcamLabel;
	private Date time;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH.mm.ss");
	private String recordName;
	private Boolean isStartedNewRecord = false;
	private  BufferedImage buff;
	private Frame capturedFrame = null;
	private Java2DFrameConverter paintConverter = new Java2DFrameConverter();

	
	/*public Recorder0(int webcamNo, String videoRecordAddr, JPanel webcamCanvas){
		if(webcamNo!=4) {
			setWebcamNo(webcamNo);
			grabber0 = new OpenCVFrameGrabber(webcamNo);
		}
		else {
			final JFileChooser fchooser = new JFileChooser(".mp4");
			final File videoFile = new File(".mp4");
			fchooser.setSelectedFile(videoFile);
	        int retvalue = fchooser.showOpenDialog(new JLabel("Vide File"));
	        if (retvalue == JFileChooser.APPROVE_OPTION)
	        {
				grabber0 = new FFmpegFrameGrabber(fchooser.getSelectedFile());
				grabber0.setFormat("mp4");
	         }
		}
	
		//grabber0.setImageWidth(CAPTUREWIDTH);
		//grabber0.setImageHeight(CAPTUREHRIGHT);
		grabber0.setAudioOption("crf", "2");
	    grabber0.setAudioBitrate(192000);
	    grabber0.setSampleRate(44100);
	    grabber0.setAudioChannels(2);
	    grabber0.setAudioCodec(avcodec.AV_CODEC_ID_AAC);
       
	    grabber0.setFrameRate(FRAME_RATE);
        setvideoRecordAddr(videoRecordAddr);
		time = new Date();
		setRecordName(time);
		setWebcamCanvas(webcamCanvas);

	}*/
	public Recorder0(int webcamNo, String videoRecordAddr, JPanel webcamCanvas, int fps){
		if(webcamNo!=4) {
			setWebcamNo(webcamNo);
			grabber0 = new OpenCVFrameGrabber(webcamNo);
		}
		else {
			/*final JFileChooser fchooser = new JFileChooser(".avi");
			final File videoFile = new File(".avi");
			fchooser.setSelectedFile(videoFile);
	        int retvalue = fchooser.showOpenDialog(new JLabel("Select Avi Vide File"));
	        if (retvalue == JFileChooser.APPROVE_OPTION)
	        {
				grabber0 = new OpenCVFrameGrabber(fchooser.getSelectedFile().getAbsolutePath());
				grabber0.setFormat("avi");
	         }*/
		}
	
		/*
		grabber0.setImageWidth(CAPTUREWIDTH);
		grabber0.setImageHeight(CAPTUREHRIGHT);
		grabber0.setAudioOption("crf", "0");
	    grabber0.setAudioBitrate(192000);
	    grabber0.setSampleRate(44100);
	    grabber0.setAudioChannels(2);
	    grabber0.setAudioCodec(avcodec.AV_CODEC_ID_AAC);*/
		
        grabber0.setFrameRate(fps);
		setvideoRecordAddr(videoRecordAddr);
		time = new Date();
		setRecordName(time);
		setWebcamCanvas(webcamCanvas);

	}
	/*public void setTargetIp(String ip) {
		this.targetIp = ip;
	}
	
	public String getTargetIp() {
		return this.targetIp;
	}*/
	public void setFPS(int fps) {
		this.FRAME_RATE = fps;
	}
	
	public int getFPS() {
		return FRAME_RATE;
	}
	
	public void setWidth(int width) {
		this.CAPTUREWIDTH = width;
	}
	
	public int getWidth() {
		return CAPTUREWIDTH;
	}
	
	public void setHeight(int height) {
		this.CAPTUREHRIGHT = height;
	}
	
	public int getHeight() {
		return CAPTUREHRIGHT;
	}
	
	public void setWebcamCanvas(JPanel canvas) {
		this.canvas = canvas;
	}
	
	public JPanel getWebcamCanvas() {
		return canvas;
	}
	
	public void setWebcamNo(int webcamNo) {
		this.webcamNo = webcamNo;
	}
	
	public int getWebcamNo() {
		return webcamNo;	
	}
	
	public void setvideoRecordAddr(String videoRecordAddr) {
		this.videoRecordAddr = videoRecordAddr;
	}
	
	public String getvideoRecordAddr() {
		return videoRecordAddr;	
	}

	public void setWebcamLabel(JLabel webcamLabel) {
		this.webcamLabel = webcamLabel;
	}
	
	public JLabel getWebcamLabel() {
		return webcamLabel;
	}
	
	public void setRecordName(Date time) {
		recordName = "Cam" + webcamNo + "-" + sdf.format(time).toString() + "-";
	}
	
	public String getRecordName() {
		return recordName;
	}
	
	public BufferedImage getSnapshot() {
		return buff;
	}
	
	private void setSenderConfiguration() {
		sender0 = new FFmpegFrameRecorder("http://127.0.0.1:8081/live", CAPTUREWIDTH, CAPTUREHRIGHT);
		// sender0 options //
		sender0.setVideoCodec(avcodec.AV_CODEC_ID_MPEG1VIDEO);
		sender0.setVideoOption("tune", "zerolatency");
		sender0.setVideoOption("preset", "ultrafast");
	    sender0.setVideoOption("video_size", CAPTUREWIDTH + "x" + CAPTUREHRIGHT);
	    sender0.setFrameRate(grabber0.getFrameRate());
	    sender0.setGopSize(GOP_LENGTH_IN_FRAMES);
		sender0.setFormat("mpegts");
		
	}
	
	private void setRecorderConfiguration() {
		recorder0 = new FFmpegFrameRecorder(videoRecordAddr+recordName+CAPTUREWIDTH+"x"+CAPTUREHRIGHT + ".mp4", 
				CAPTUREWIDTH, CAPTUREHRIGHT);//Cam0-2018-04-25-17.58.05-640x480.mp4
	    // video options //
		recorder0.setInterleaved(true);
	    recorder0.setVideoOption("tune", "zerolatency");
	    recorder0.setVideoOption("preset", "ultrafast");
	    recorder0.setVideoOption("crf", "28");
	    recorder0.setVideoBitrate(grabber0.getVideoBitrate());
	    recorder0.setVideoCodec(avcodec.AV_CODEC_ID_H264);
	    recorder0.setVideoOption("video_size", CAPTUREWIDTH + "x" + CAPTUREHRIGHT);
	    recorder0.setFrameRate(grabber0.getFrameRate());
	    recorder0.setGopSize(GOP_LENGTH_IN_FRAMES);
	    recorder0.setFormat("mp4");
	    // audio options //
	    recorder0.setAudioOption("crf", "0");
	    recorder0.setAudioQuality(0);
	    recorder0.setAudioBitrate(192000);
	    recorder0.setSampleRate(44100);
	    recorder0.setAudioChannels(2);
	    recorder0.setAudioCodec(avcodec.AV_CODEC_ID_AAC);
	    
	    converter0 = new FFmpegFrameRecorder(videoRecordAddr + recordName + CAPTUREWIDTH / 2 + "x" + CAPTUREHRIGHT / 2 + ".mp4",
	    		CAPTUREWIDTH / 2, CAPTUREHRIGHT / 2);//Cam0-2018-04-25-17.58.05-320x240.mp4
	    // video options //
	    converter0.setInterleaved(true);
	    converter0.setVideoOption("tune", "zerolatency");
	    converter0.setVideoOption("preset", "ultrafast");
	    converter0.setVideoOption("crf", "28");
	    converter0.setVideoBitrate(grabber0.getVideoBitrate());
	    converter0.setVideoCodec(avcodec.AV_CODEC_ID_H264);
	    converter0.setVideoOption("video_size", CAPTUREWIDTH / 2 + "x" + CAPTUREHRIGHT / 2);
		converter0.setFrameRate(grabber0.getFrameRate());
		converter0.setGopSize(GOP_LENGTH_IN_FRAMES);
		converter0.setFormat(".mp4");
		// audio options //
		converter0.setAudioOption("crf", "0");
		converter0.setAudioQuality(0);
		converter0.setAudioBitrate(192000);
		converter0.setSampleRate(44100);
		converter0.setAudioChannels(2);
		converter0.setAudioCodec(avcodec.AV_CODEC_ID_AAC);
		
	}
	
	/*
	protected int getAverageBrightness(BufferedImage img) {
		Raster r = img.getData();
		int total = 0;
		for (int y = 0; y < r.getHeight(); y++) {
			for (int x = 0; x < r.getWidth(); x++) {
				total += r.getSample(r.getMinX() + x, r.getMinY() + y, 0);
			}
		}
		return (int)(total / ((r.getWidth()/10)*(r.getHeight()/10)));
	}
	protected static BufferedImage imageToBufferedImage(Image img) {
		BufferedImage bi = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_RGB);
		Graphics2D g2 = bi.createGraphics();
		g2.drawImage(img, null, null);
		return bi;
	}
	public Boolean compare(BufferedImage img1, BufferedImage img2) {
		int comparex = 8;
		int comparey = 6;
		int factorA = 5;
		
		if(img1 == null )
			return false;
		img1 = imageToBufferedImage(GrayFilter.createDisabledImage(img1));
		img2 =  imageToBufferedImage(GrayFilter.createDisabledImage(img2));
		// how big are each section
		int blocksx = (int)(img1.getWidth() / comparex);
		int blocksy = (int)(img1.getHeight() / comparey);
		
		for (int y = 0; y < comparey; y++) {
			for (int x = 0; x < comparex; x++) {
				int b1 = getAverageBrightness(img1.getSubimage(x*blocksx, y*blocksy, blocksx - 1, blocksy - 1));
				int b2 = getAverageBrightness(img2.getSubimage(x*blocksx, y*blocksy, blocksx - 1, blocksy - 1));
				int diff = Math.abs(b1 - b2);
				if (diff > factorA)  
					return false;
			}
		}
		return true;
	}
	*/
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		//synchronized (this) {  
			runnable = true;
			setRecordName(new Date());
            setRecorderConfiguration();
            setSenderConfiguration();	
			try {
				grabber0.start();	
				converter0.start();		
				sender0.start();
				recorder0.start();	
	            long startTime = System.currentTimeMillis();
	            long frame = 0;
	            System.out.println("Ready!");
	            while(true) {
	            	try {
						capturedFrame = grabber0.grabFrame();
						break;
					} catch (org.bytedeco.javacv.FrameGrabber.Exception e) {
						// TODO Auto-generated catch block
						System.out.println("Camera is preparing...");
					}
	            	
	            }	
					while (runnable) {
						if((capturedFrame = grabber0.grabFrame()) == null) {
							stop();
						}
					    buff = paintConverter.getBufferedImage(capturedFrame, 1);
					    Graphics g = canvas.getGraphics();
					    g.drawImage(buff, 0, 0, canvas.getWidth(), canvas.getHeight(), 0, 0, buff.getWidth(), buff.getHeight(), null);
						recorder0.record(capturedFrame);
				        converter0.record(capturedFrame);
				        sender0.record(capturedFrame);
					    frame++;
					    long waitMillis = 1000 * frame / FRAME_RATE - (System.currentTimeMillis() - startTime);
					    while (waitMillis <= 0) {
							recorder0.record(capturedFrame);  // use same capturedFrame for fast processing.
							converter0.record(capturedFrame);
							sender0.record(capturedFrame);
					        frame++;
					        waitMillis = 1000 * frame / FRAME_RATE - (System.currentTimeMillis() - startTime);
					    }
						Thread.sleep(waitMillis);
					    time = new Date();
					    if(time.getMinutes() == 00 && time.getSeconds() == 00 && isStartedNewRecord == false) {
							isStartedNewRecord = true;
							recorder0.stop();
							converter0.stop();
							setRecordName(new Date());
							setRecorderConfiguration();
							recorder0.start();
							converter0.start();
					    	continue;
						}
						if(time.getMinutes() == 01 && time.getSeconds() == 00 && isStartedNewRecord == true) {
							isStartedNewRecord = false;
							}
						}
				} catch (org.bytedeco.javacv.FrameGrabber.Exception | Exception | InterruptedException e) {
					// TODO Auto-generated catch block
					System.out.println("A :"+e.getMessage());
					runnable = false;
					}		
	           // }
	}
	final public void stop() {
		try {
			runnable = false;
			grabber0.stop();
			recorder0.stop();
			converter0.stop();
			sender0.stop();
			System.out.println("Stop there");
		} catch (Exception | org.bytedeco.javacv.FrameGrabber.Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
