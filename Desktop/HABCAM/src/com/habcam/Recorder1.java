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

public class Recorder1 implements Runnable{

    private static FrameGrabber grabber1 = null;
    private static FFmpegFrameRecorder recorder1 = null;
    private static FFmpegFrameRecorder converter1 = null;
    private static FFmpegFrameRecorder sender1 = null;
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

	
	public Recorder1(int webcamNo, String videoRecordAddr, JPanel webcamCanvas){
		if(webcamNo!=4) {
			setWebcamNo(webcamNo);
			grabber1 = new OpenCVFrameGrabber(webcamNo);
		}
		else {
			grabber1 = new FFmpegFrameGrabber("desktop");
			grabber1.setFormat("gdigrab");
		}
	
		//grabber1.setImageWidth(CAPTUREWIDTH);
		//grabber1.setImageHeight(CAPTUREHRIGHT);
        grabber1.setFrameRate(FRAME_RATE);
		setvideoRecordAddr(videoRecordAddr);
		time = new Date();
		setRecordName(time);
		setWebcamCanvas(webcamCanvas);

	}
	public Recorder1(int webcamNo, String videoRecordAddr, JPanel webcamCanvas, int fps){
		if(webcamNo!=4) {
			setWebcamNo(webcamNo);
			grabber1 = new OpenCVFrameGrabber(webcamNo);
		}
		else {
			grabber1 = new FFmpegFrameGrabber("desktop");
			grabber1.setFormat("gdigrab");
		}
	
		//grabber1.setImageWidth(CAPTUREWIDTH);
		//grabber1.setImageHeight(CAPTUREHRIGHT);
        grabber1.setFrameRate(fps);
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
		sender1 = new FFmpegFrameRecorder("http://127.0.0.1:8083/live", CAPTUREWIDTH, CAPTUREHRIGHT);
		// sender1 options //
		sender1.setVideoCodec(avcodec.AV_CODEC_ID_MPEG1VIDEO);
		sender1.setVideoOption("tune", "zerolatency");
		sender1.setVideoOption("preset", "ultrafast");
	    sender1.setVideoOption("video_size", CAPTUREWIDTH + "x" + CAPTUREHRIGHT);
	    sender1.setFrameRate(grabber1.getFrameRate());
	    sender1.setGopSize(GOP_LENGTH_IN_FRAMES);
		sender1.setFormat("mpegts");
		
	}
	
	private void setRecorderConfiguration() {
		recorder1 = new FFmpegFrameRecorder(videoRecordAddr+recordName+CAPTUREWIDTH+"x"+CAPTUREHRIGHT + ".mp4", 
				CAPTUREWIDTH, CAPTUREHRIGHT);//Cam0-2018-04-25-17.58.05-640x480.mp4
	    // video options //
		recorder1.setInterleaved(true);
	    recorder1.setVideoOption("tune", "zerolatency");
	    recorder1.setVideoOption("preset", "ultrafast");
	    recorder1.setVideoOption("crf", "28");
	    recorder1.setVideoBitrate(grabber1.getVideoBitrate());
	    recorder1.setVideoCodec(avcodec.AV_CODEC_ID_H264);
	    recorder1.setVideoOption("video_size", CAPTUREWIDTH + "x" + CAPTUREHRIGHT);
	    recorder1.setFrameRate(grabber1.getFrameRate());
	    recorder1.setGopSize(GOP_LENGTH_IN_FRAMES);
	    recorder1.setFormat("mp4");
	    // audio options //
	    recorder1.setAudioOption("crf", "0");
	    recorder1.setAudioQuality(0);
	    recorder1.setAudioBitrate(192000);
	    recorder1.setSampleRate(44100);
	    recorder1.setAudioChannels(2);
	    recorder1.setAudioCodec(avcodec.AV_CODEC_ID_AAC);
	    
	    converter1 = new FFmpegFrameRecorder(videoRecordAddr + recordName + CAPTUREWIDTH / 2 + "x" + CAPTUREHRIGHT / 2 + ".mp4",
	    		CAPTUREWIDTH / 2, CAPTUREHRIGHT / 2);//Cam0-2018-04-25-17.58.05-320x240.mp4
	    // video options //
	    converter1.setInterleaved(true);
	    converter1.setVideoOption("tune", "zerolatency");
	    converter1.setVideoOption("preset", "ultrafast");
	    converter1.setVideoOption("crf", "28");
	    converter1.setVideoBitrate(grabber1.getVideoBitrate());
	    converter1.setVideoCodec(avcodec.AV_CODEC_ID_H264);
	    converter1.setVideoOption("video_size", CAPTUREWIDTH / 2 + "x" + CAPTUREHRIGHT / 2);
		converter1.setFrameRate(grabber1.getFrameRate());
		converter1.setGopSize(GOP_LENGTH_IN_FRAMES);
		converter1.setFormat(".mp4");
		// audio options //
		converter1.setAudioOption("crf", "0");
		converter1.setAudioQuality(0);
		converter1.setAudioBitrate(192000);
		converter1.setSampleRate(44100);
		converter1.setAudioChannels(2);
		converter1.setAudioCodec(avcodec.AV_CODEC_ID_AAC);
		
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
				grabber1.start();	
				converter1.start();		
				sender1.start();
				recorder1.start();	
	            long startTime = System.currentTimeMillis();
	            long frame = 0;
	            System.out.println("Ready!");
	            while(true) {
	            	try {
						capturedFrame = grabber1.grabFrame();
						break;
					} catch (org.bytedeco.javacv.FrameGrabber.Exception e) {
						// TODO Auto-generated catch block
						System.out.println("Camera is preparing...");
					}
	            	
	            }	
					while (runnable) {
						if((capturedFrame = grabber1.grabFrame()) == null) {
							stop();
						}
					    buff = paintConverter.getBufferedImage(capturedFrame, 1);
					    Graphics g = canvas.getGraphics();
					    g.drawImage(buff, 0, 0, canvas.getWidth(), canvas.getHeight(), 0, 0, buff.getWidth(), buff.getHeight(), null);
						recorder1.record(capturedFrame);
				        converter1.record(capturedFrame);
				        sender1.record(capturedFrame);
					    frame++;
					    long waitMillis = 1000 * frame / FRAME_RATE - (System.currentTimeMillis() - startTime);
					    while (waitMillis <= 0) {

								recorder1.record(capturedFrame);  // use same capturedFrame for fast processing.
								converter1.record(capturedFrame);
								sender1.record(capturedFrame);
					        frame++;
					        waitMillis = 1000 * frame / FRAME_RATE - (System.currentTimeMillis() - startTime);
					    }
						Thread.sleep(waitMillis);
					    time = new Date();
					    if(time.getMinutes() == 00 && time.getSeconds() == 00 && isStartedNewRecord == false) {
							isStartedNewRecord = true;
							recorder1.stop();
							converter1.stop();
							setRecordName(new Date());
							setRecorderConfiguration();
							recorder1.start();
							converter1.start();
					    	continue;
						}
						if(time.getMinutes() == 01 && time.getSeconds() == 00 && isStartedNewRecord == true) {
							isStartedNewRecord = false;
							}
						}
				} catch (org.bytedeco.javacv.FrameGrabber.Exception | Exception | InterruptedException e) {
					// TODO Auto-generated catch block
					System.out.println("B :"+e.getMessage());
					runnable = false;
					}		
	           // }
	}
	public void stop() {
		try {
			runnable = false;
			grabber1.stop();
			recorder1.stop();
			converter1.stop();
			sender1.stop();
			System.out.println("Stop there");
		} catch (Exception | org.bytedeco.javacv.FrameGrabber.Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
