package com.habcam;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.GrayFilter;
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

public class Recorder3 implements Runnable{

    private static FrameGrabber grabber3 = null;
    private static FFmpegFrameRecorder recorder3 = null;
    private static FFmpegFrameRecorder converter3 = null;
    private static FFmpegFrameRecorder sender3 = null;
    private int CAPTUREWIDTH = 640;
    private int CAPTUREHRIGHT = 480;
    private int FRAME_RATE = 20;
    private static final int GOP_LENGTH_IN_FRAMES = 60;
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

	
	public Recorder3(int webcamNo, String videoRecordAddr, JPanel webcamCanvas){
		if(webcamNo!=4) {
			setWebcamNo(webcamNo);
			grabber3 = new OpenCVFrameGrabber(webcamNo);
		}
		else {
			grabber3 = new FFmpegFrameGrabber("desktop");
			grabber3.setFormat("gdigrab");
		}
	
		//grabber3.setImageWidth(CAPTUREWIDTH);
		//grabber3.setImageHeight(CAPTUREHRIGHT);
        grabber3.setFrameRate(FRAME_RATE);
		setvideoRecordAddr(videoRecordAddr);
		time = new Date();
		setRecordName(time);
		setWebcamCanvas(webcamCanvas);

	}
	public Recorder3(int webcamNo, String videoRecordAddr, JPanel webcamCanvas, int fps){
		if(webcamNo!=4) {
			setWebcamNo(webcamNo);
			grabber3 = new OpenCVFrameGrabber(webcamNo);
		}
		else {
			grabber3 = new FFmpegFrameGrabber("desktop");
			grabber3.setFormat("gdigrab");
		}
	
		//grabber3.setImageWidth(CAPTUREWIDTH);
		//grabber3.setImageHeight(CAPTUREHRIGHT);
        grabber3.setFrameRate(fps);
		setvideoRecordAddr(videoRecordAddr);
		time = new Date();
		setRecordName(time);
		setWebcamCanvas(webcamCanvas);

	}/*
	public void setTargetIp(String ip) {
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
		sender3 = new FFmpegFrameRecorder("http://127.0.0.1:8087/live", CAPTUREWIDTH, CAPTUREHRIGHT);
		// sender3 options //
		sender3.setVideoCodec(avcodec.AV_CODEC_ID_MPEG1VIDEO);
		sender3.setVideoOption("tune", "zerolatency");
		sender3.setVideoOption("preset", "ultrafast");
	    sender3.setVideoOption("video_size", CAPTUREWIDTH + "x" + CAPTUREHRIGHT);
	    sender3.setFrameRate(grabber3.getFrameRate());
	    sender3.setGopSize(GOP_LENGTH_IN_FRAMES);
		sender3.setFormat("mpegts");
		
	}
	
	private void setRecorderConfiguration() {
		recorder3 = new FFmpegFrameRecorder(videoRecordAddr+recordName+CAPTUREWIDTH+"x"+CAPTUREHRIGHT + ".mp4", 
				CAPTUREWIDTH, CAPTUREHRIGHT);//Cam0-2018-04-25-17.58.05-640x480.mp4
	    // video options //
		recorder3.setInterleaved(true);
	    recorder3.setVideoOption("tune", "zerolatency");
	    recorder3.setVideoOption("preset", "ultrafast");
	    recorder3.setVideoOption("crf", "28");
	    recorder3.setVideoBitrate(grabber3.getVideoBitrate());
	    recorder3.setVideoCodec(avcodec.AV_CODEC_ID_H264);
	    recorder3.setVideoOption("video_size", CAPTUREWIDTH + "x" + CAPTUREHRIGHT);
	    recorder3.setFrameRate(grabber3.getFrameRate());
	    recorder3.setGopSize(GOP_LENGTH_IN_FRAMES);
	    recorder3.setFormat("mp4");
	    // audio options //
	    recorder3.setAudioOption("crf", "0");
	    recorder3.setAudioQuality(0);
	    recorder3.setAudioBitrate(192000);
	    recorder3.setSampleRate(44100);
	    recorder3.setAudioChannels(2);
	    recorder3.setAudioCodec(avcodec.AV_CODEC_ID_AAC);
	    
	    converter3 = new FFmpegFrameRecorder(videoRecordAddr + recordName + CAPTUREWIDTH / 2 + "x" + CAPTUREHRIGHT / 2 + ".mp4",
	    		CAPTUREWIDTH / 2, CAPTUREHRIGHT / 2);//Cam0-2018-04-25-17.58.05-320x240.mp4
	    // video options //
	    converter3.setInterleaved(true);
	    converter3.setVideoOption("tune", "zerolatency");
	    converter3.setVideoOption("preset", "ultrafast");
	    converter3.setVideoOption("crf", "28");
	    converter3.setVideoBitrate(grabber3.getVideoBitrate());
	    converter3.setVideoCodec(avcodec.AV_CODEC_ID_H264);
	    converter3.setVideoOption("video_size", CAPTUREWIDTH / 2 + "x" + CAPTUREHRIGHT / 2);
		converter3.setFrameRate(grabber3.getFrameRate());
		converter3.setGopSize(GOP_LENGTH_IN_FRAMES);
		converter3.setFormat(".mp4");
		// audio options //
		converter3.setAudioOption("crf", "0");
		converter3.setAudioQuality(0);
		converter3.setAudioBitrate(192000);
		converter3.setSampleRate(44100);
		converter3.setAudioChannels(2);
		converter3.setAudioCodec(avcodec.AV_CODEC_ID_AAC);
		
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
				grabber3.start();	
				converter3.start();		
				sender3.start();
				recorder3.start();	
	            long startTime = System.currentTimeMillis();
	            long frame = 0;
	            System.out.println("Ready!");
	            while(true) {
	            	try {
						capturedFrame = grabber3.grabFrame();
						break;
					} catch (org.bytedeco.javacv.FrameGrabber.Exception e) {
						// TODO Auto-generated catch block
						System.out.println("Camera is preparing...");
					}
	            	
	            }	
					while (runnable) {
						if((capturedFrame = grabber3.grabFrame()) == null) {
							stop();
						}
					    buff = paintConverter.getBufferedImage(capturedFrame, 1);
					    Graphics g = canvas.getGraphics();
					    g.drawImage(buff, 0, 0, canvas.getWidth(), canvas.getHeight(), 0, 0, buff.getWidth(), buff.getHeight(), null);
						recorder3.record(capturedFrame);
				        converter3.record(capturedFrame);
				        sender3.record(capturedFrame);
					    frame++;
					    long waitMillis = 1000 * frame / FRAME_RATE - (System.currentTimeMillis() - startTime);
					    while (waitMillis <= 0) {

								recorder3.record(capturedFrame);  // use same capturedFrame for fast processing.
								converter3.record(capturedFrame);
								sender3.record(capturedFrame);
					        frame++;
					        waitMillis = 1000 * frame / FRAME_RATE - (System.currentTimeMillis() - startTime);
					    }
						Thread.sleep(waitMillis);
					    time = new Date();
					    if(time.getMinutes() == 00 && time.getSeconds() == 00 && isStartedNewRecord == false) {
							isStartedNewRecord = true;
							recorder3.stop();
							converter3.stop();
							setRecordName(new Date());
							setRecorderConfiguration();
							recorder3.start();
							converter3.start();
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
			grabber3.stop();
			recorder3.stop();
			converter3.stop();
			sender3.stop();
			System.out.println("Stop there");
		} catch (Exception | org.bytedeco.javacv.FrameGrabber.Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
