package kma.qlbh.interfaces.admin.food;

import javax.swing.JOptionPane;
import kma.qlbh.dao.FoodCategoryDao;
import kma.qlbh.interfaces.admin.FoodCategoryManager;
import kma.qlbh.models.FoodCategory;

/**
 * @createAt Nov 28, 2020
 * @author Tran Duc Cuong<clonebmn2itt@gmail.com>
 */
public class EditFoodCategory extends javax.swing.JFrame {

    FoodCategoryDao foodCategoryDao = new FoodCategoryDao();
    FoodCategoryManager foodCategoryManager;
    FoodCategory fc;
    int id;

    public EditFoodCategory(FoodCategoryManager foodCategoryManager, int id) {
        this.foodCategoryManager = foodCategoryManager;
        this.id = id;
        try {
            fc = foodCategoryDao.get(id);
            if (fc == null) {
                throw new Exception("Loại món không tồn tại!");
            }
            initComponents();
            setLocationRelativeTo(null);
            lbTitle.setText("Sửa loại món - " + fc.getId());
            txtName.setText(fc.getName());

        } catch (Exception e) {
            this.dispose();
            JOptionPane.showMessageDialog(null, e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbTitle = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        btnSave = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        lbTitle.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbTitle.setText("Sửa loại món - 01");

        jLabel2.setText("Tên Loại:");

        btnSave.setText("Sửa");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(txtName))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(btnSave)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCancel)))
                .addGap(24, 24, 24))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(93, Short.MAX_VALUE)
                .addComponent(lbTitle)
                .addGap(89, 89, 89))
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
                    .addComponent(btnSave)
                    .addComponent(btnCancel))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        try {
            String foodCategoryName = txtName.getText();
            if (foodCategoryName.isEmpty()) {
                throw new Exception("Điền tên bàn");
            }
            FoodCategory temp = foodCategoryDao.findByName(foodCategoryName);
            if (temp != null && temp.getId() != fc.getId()) {
                throw new Exception("Tên bàn đã được sử dụng");
            }
            fc.setName(foodCategoryName);
            foodCategoryDao.update(fc);
            this.dispose();
            JOptionPane.showMessageDialog(null, "Sửa thành công");
            foodCategoryManager.renderTable();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnSave;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel lbTitle;
    private javax.swing.JTextField txtName;
    // End of variables declaration//GEN-END:variables
}
