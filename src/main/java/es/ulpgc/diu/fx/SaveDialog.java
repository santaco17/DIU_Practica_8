package es.ulpgc.diu.fx;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JDialog;
import javax.swing.JInternalFrame;

public class SaveDialog extends JDialog {

    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton saveButton;
    private javax.swing.JButton cancelButton;
    private static ArrayList<JInternalFrame> openedFrames;
    
    public SaveDialog(ArrayList<JInternalFrame> allFrames){
        openedFrames = allFrames;
        initComponents();
        addActionListeners();
    }
    
    private void initComponents(){
        jPanel1 = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        saveButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();

        setAvailableFrames(this.openedFrames);
        this.jLabel2.setText("Seleccione la ventana que desea guardar");

        this.jPanel2.setLayout(new java.awt.GridLayout(1, 2, 100, 50));

        this.saveButton.setLabel("Guardar");
        this.jPanel2.add(this.saveButton);

        this.cancelButton.setLabel("Cancelar");
        this.jPanel2.add(this.cancelButton);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(this.jPanel1);
        this.jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(this.jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                    .addComponent(this.jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(this.jComboBox1, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(this.jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(this.jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(this.jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40))
        );

        javax.swing.GroupLayout saveDialogLayout = new javax.swing.GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(saveDialogLayout);
        saveDialogLayout.setHorizontalGroup(
            saveDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(this.jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        saveDialogLayout.setVerticalGroup(
            saveDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(saveDialogLayout.createSequentialGroup()
                .addComponent(this.jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        
        
        this.pack();
    }

    private void addActionListeners() {
        this.saveButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                var selectedFrame = jComboBox1.getSelectedItem().toString();
                //Guardar si no es null
                getInstance(selectedFrame).dispose();
                dispose();
            }
        });
        this.cancelButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dispose();
            }
        });
    }

    private void setAvailableFrames(ArrayList<JInternalFrame> allFrames) {
        var model = new javax.swing.DefaultComboBoxModel<>(getCurrentFramesTitles(allFrames));
        this.jComboBox1.setModel(model);
    }
    
    private String[] getCurrentFramesTitles(ArrayList<JInternalFrame> allFrames){
        String[] titles = new String[allFrames.size()]; 
        for (int i = 0; i < titles.length; i++) {
            titles[i] = allFrames.get(i).getTitle();
        }
        return titles;
    }
    
    private static JInternalFrame getInstance(String title){
        for (JInternalFrame openedFrame : openedFrames) {
            if(openedFrame.getTitle().equals(title)){
                return openedFrame;
            }
        }
        return null;
    }
    
    private static void saveCurrentImage(int jFileChooserOptionSelected, File outputFile) throws IOException {
        /*if(jFileChooserOptionSelected == JFileChooser.APPROVE_OPTION){
            String fileToSave = this.fileChooser.getCurrentDirectory() + "\\" + outputFile.getName();
            ImageIO.write(this.imagePanel1.getCurrentPicture(), "jpg", new File(fileToSave));
        }*/
    }
    // this.desktop.getAllFrames()[0].getTitle() + " - Factor: " + thresholdFactor;
    
            /*File outputFile = new File(this.imagePanel1.getCurrentPictureName() + "_modified" + this.imagePanel1.getCurrentPictureFormat());
        this.fileChooser.setSelectedFile(outputFile);
        int jFileChooserOptionSelected = this.fileChooser.showSaveDialog(this);
        try {
            saveCurrentImage(jFileChooserOptionSelected, outputFile);
        } catch (IOException ex) {
            Logger.getLogger(Practica8.class.getName()).log(Level.SEVERE, null, ex);
        }*/
}
