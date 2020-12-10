package kma.qlbh.interfaces.admin.table;

import javax.swing.JOptionPane;
import kma.qlbh.dao.TableDao;
import kma.qlbh.interfaces.admin.TableManager;
import kma.qlbh.models.Table;
import kma.qlbh.utils.ErrorPopup;

/**
 * @createAt Nov 28, 2020
 * @author Tran Duc Cuong<clonebmn2itt@gmail.com>
 */
public class EditTable extends javax.swing.JFrame {

    TableDao tableDao = new TableDao();
    TableManager tableManager;
    Table t;
    int id;

    public EditTable(TableManager tableManager, int id) {
        this.tableManager = tableManager;
        this.id = id;
        try {
            t = tableDao.get(id);
            if (t == null) {
                throw new Exception("Bàn không tồn tại!");
            }
            initComponents();
            setLocationRelativeTo(null);
            lbTitle.setText("Sửa bàn số " + t.getId());
            txtName.setText(t.getName());

        } catch (Exception e) {
            this.dispose();
            ErrorPopup.show(e);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbTitle = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        btnAdd = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        lbTitle.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbTitle.setText("Sửa bàn số 01");

        jLabel2.setText("Tên bàn:");

        btnAdd.setText("Sửa");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnCancel.setText("Hủy");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAdd)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 117, Short.MAX_VALUE)
                        .addComponent(btnCancel))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtName)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(lbTitle)
                                .addGap(74, 74, 74)))))
                .addGap(24, 24, 24))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbTitle)
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdd)
                    .addComponent(btnCancel))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        try {
            String tableName = txtName.getText();
            if (tableName.isEmpty()) {
                throw new Exception("Điền tên bàn");
            }
            Table temp = tableDao.findByName(tableName);
            if (temp != null && temp.getId() != t.getId()) {
                throw new Exception("Tên bàn đã được sử dụng");
            }
            t.setName(tableName);
            tableDao.update(t);
            this.dispose();
            JOptionPane.showMessageDialog(null, "Sửa thành công");
            tableManager.renderTable();
        } catch (Exception e) {
            ErrorPopup.show(e);
        }
    }//GEN-LAST:event_btnAddActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnCancel;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel lbTitle;
    private javax.swing.JTextField txtName;
    // End of variables declaration//GEN-END:variables
}
