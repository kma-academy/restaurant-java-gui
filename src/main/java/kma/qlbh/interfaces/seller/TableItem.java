package kma.qlbh.interfaces.seller;

import java.awt.Color;
import java.awt.Dimension;
import kma.qlbh.models.Table;
import kma.qlbh.utils.TableStatus;

/**
 *
 * @author Tran Duc Cuong<clonebmn2itt@gmail.com>
 */
public class TableItem extends javax.swing.JPanel {

    Table table;

    public TableItem(Table table) {
        this.table = table;
        initComponents();
        lbName.setText(table.getName());
        setPreferredSize(new Dimension(100, 100));
        switch (table.getStatus()) {
            case FREE:
                setBackground(new Color(0, 195, 0));
                break;
            case SERVING:
                setBackground(new Color(255, 51, 0));
                break;
            case RESERVING:
                setBackground(new Color(255, 255, 0));
                break;
        }
    }

    public void changeStatus(TableStatus ts) {
        switch (ts) {
            case FREE:
                setBackground(new Color(0, 195, 0));
                break;
            case SERVING:
                setBackground(new Color(255, 51, 0));
                break;
            case RESERVING:
                setBackground(new Color(255, 255, 0));
                break;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbIcon = new javax.swing.JLabel();
        lbName = new javax.swing.JLabel();

        setBackground(new java.awt.Color(0, 195, 0));
        setMaximumSize(new java.awt.Dimension(100, 100));
        setMinimumSize(new java.awt.Dimension(100, 100));
        setPreferredSize(new java.awt.Dimension(100, 100));

        lbIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/table_75px.png"))); // NOI18N

        lbName.setText("Bàn 10");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(lbName)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addComponent(lbIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lbIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbName)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lbIcon;
    private javax.swing.JLabel lbName;
    // End of variables declaration//GEN-END:variables
}
