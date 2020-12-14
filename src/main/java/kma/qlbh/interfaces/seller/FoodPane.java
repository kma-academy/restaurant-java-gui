package kma.qlbh.interfaces.seller;

import java.awt.Dimension;
import java.text.DecimalFormat;
import kma.qlbh.models.FoodItem;
import kma.qlbh.utils.ImageManager;

/**
 *
 * @author Tran Duc Cuong<clonebmn2itt@gmail.com>
 */
public class FoodPane extends javax.swing.JPanel {

    DecimalFormat decimalFormat = new DecimalFormat("###,###,###");

    public FoodPane() {
        initComponents();
        Dimension size = new Dimension(200, 200);
        setPreferredSize(size);
    }

    public FoodPane(FoodItem foodItem) {
        initComponents();
        Dimension size = new Dimension(200, 200);
        setPreferredSize(size);
        setMaximumSize(size);
        setMinimumSize(size);
        lbName.setText(foodItem.getName());
        lbCount.setText("10");
        lbPrice.setText(decimalFormat.format(foodItem.getUnitPrice()));
        if (foodItem.getUrlImage() != null) {
            lbImage.setIcon(new ImageManager().getImage(foodItem.getUrlImage()));
        } else {
            lbImage.setIcon(null);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbPrice = new javax.swing.JLabel();
        lbName = new javax.swing.JLabel();
        lbCount = new javax.swing.JLabel();
        lbImage = new javax.swing.JLabel();

        setMaximumSize(new java.awt.Dimension(200, 200));
        setMinimumSize(new java.awt.Dimension(200, 200));
        setLayout(null);

        lbPrice.setBackground(new java.awt.Color(255, 255, 255));
        lbPrice.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lbPrice.setText("123,000");
        lbPrice.setOpaque(true);
        add(lbPrice);
        lbPrice.setBounds(150, 0, 50, 20);

        lbName.setBackground(new java.awt.Color(255, 255, 255));
        lbName.setText("Trà sữa trân châu đen");
        lbName.setOpaque(true);
        add(lbName);
        lbName.setBounds(0, 180, 200, 20);

        lbCount.setBackground(new java.awt.Color(255, 255, 255));
        lbCount.setText("10");
        lbCount.setOpaque(true);
        add(lbCount);
        lbCount.setBounds(0, 0, 20, 20);

        lbImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/mi-fu-xao-xa-xiu.png"))); // NOI18N
        add(lbImage);
        lbImage.setBounds(0, 0, 200, 200);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lbCount;
    private javax.swing.JLabel lbImage;
    private javax.swing.JLabel lbName;
    private javax.swing.JLabel lbPrice;
    // End of variables declaration//GEN-END:variables
}
