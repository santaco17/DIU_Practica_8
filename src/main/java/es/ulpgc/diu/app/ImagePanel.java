/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ulpgc.diu.app;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

/**
 *
 * @author santa
 */
public class ImagePanel extends JPanel {
    private BufferedImage picture;
    private BufferedImage currentPicture;
    private String currentPictureName;
    public ImagePanel() {
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        if(currentPicture != null){
            g.drawImage(currentPicture, 0, 0, null);
        }
    }

    void reloadCanvas(File fileSelected) throws MalformedURLException, IOException {
        Graphics g = this.getGraphics();  
        this.paintComponent(g);
    }

    private void reloadCanvasWithCurrentPicture() {
        Graphics g = this.getGraphics();  
        this.paintComponent(g);
    }

    public void setPicture(File fileSelected) throws IOException {
        picture = ImageIO.read(fileSelected);
        currentPicture = picture;
        this.currentPictureName = fileSelected.getName();
        this.repaint();
    }
    
    public BufferedImage getCurrentPicture(){
        return this.currentPicture;
    }

    public void thresholdPicture(int factor) throws IOException{
        Mat tresholdedMat = tresholdingPicture(BufferedImageToMat(this.picture), factor);
        this.currentPicture = BufferedImageToMat(tresholdedMat);
        reloadCanvasWithCurrentPicture();
    }
    
    public static Mat BufferedImageToMat(BufferedImage image) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", byteArrayOutputStream);
        byteArrayOutputStream.flush();
        return Imgcodecs.imdecode(new MatOfByte(byteArrayOutputStream.toByteArray()), Imgcodecs.IMREAD_UNCHANGED);
    }
    
    public static BufferedImage BufferedImageToMat(Mat matrix)throws IOException {
        MatOfByte mob=new MatOfByte();
        Imgcodecs.imencode(".jpg", matrix, mob);
        return ImageIO.read(new ByteArrayInputStream(mob.toArray()));
    }
    
    private Mat tresholdingPicture(Mat imagen_original, Integer umbral) {
        // crear dos im??genes en niveles de gris con el mismo
        // tama??o que la original
        Mat imagenGris = new Mat(imagen_original.rows(),
        imagen_original.cols(),
        CvType.CV_8U);
        Mat imagenUmbralizada = new Mat(imagen_original.rows(),
        imagen_original.cols(),
        CvType.CV_8U);
        // convierte a niveles de grises la imagen original
        Imgproc.cvtColor(imagen_original,
        imagenGris,
        Imgproc.COLOR_BGR2GRAY);
        // umbraliza la imagen:
        // - p??xeles con nivel de gris > umbral se ponen a 1
        // - p??xeles con nivel de gris <= umbra se ponen a 0
        Imgproc.threshold(imagenGris,
        imagenUmbralizada,
        umbral,
        255,
        Imgproc.THRESH_BINARY);
        // se devuelve la imagen umbralizada
        return imagenUmbralizada;
    }
    
    public String getCurrentPictureName() {
        return currentPictureName.substring(0, currentPictureName.lastIndexOf("."));
    }

    
    public String getCurrentPictureFormat(){
        return currentPictureName.substring(currentPictureName.lastIndexOf("."));
    }
}
