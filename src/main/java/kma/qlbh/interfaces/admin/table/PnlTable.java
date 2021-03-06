/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kma.qlbh.interfaces.admin.table;

import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;

/**
 *
 * @author MSI
 */
public class PnlTable extends javax.swing.JPanel {

    /**
     * Creates new form PnlTable
     */
    private String id;
    private boolean check = true;
//    public PnlTable() {
//        initComponents();
//    }
    public PnlTable(int chair,String id,String urlImage,String urlImageChange){
        initComponents();
        labName.setText("Bàn số : "+id+" ("+chair+")");
        this.id=id;
        this.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                if(check){
                    ImageIcon icon = new ImageIcon(urlImage);
                    Image img = icon.getImage();
                    Image newImg = img.getScaledInstance(80, 80,Image.SCALE_SMOOTH);
                    icon= new ImageIcon(newImg);
                    labIcon.setIcon(icon);
                    check=!check;
                }
                else{
                    ImageIcon icon = new ImageIcon(urlImageChange);
                    Image img = icon.getImage();
                    Image newImg = img.getScaledInstance(80, 80,Image.SCALE_SMOOTH);
                    icon= new ImageIcon(newImg);
                    labIcon.setIcon(icon);
                    check=!check;
                }
                
            }
        });
    }
    public String getId(){
        return this.id;
    }
    public boolean getCheck(){
        return this.check;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labIcon = new javax.swing.JLabel();
        labName = new javax.swing.JLabel();

        setBackground(new java.awt.Color(153, 204, 255));

        labIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        labName.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        labName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labIcon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(labName, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(labIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labName, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel labIcon;
    private javax.swing.JLabel labName;
    // End of variables declaration//GEN-END:variables
}
