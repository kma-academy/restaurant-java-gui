package kma.qlbh.interfaces.admin.food;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import kma.qlbh.dao.FoodCategoryDao;
import kma.qlbh.dao.FoodItemDao;
import kma.qlbh.interfaces.admin.FoodItemManager;
import kma.qlbh.models.FoodCategory;
import kma.qlbh.models.FoodItem;
import kma.qlbh.utils.ErrorPopup;
import kma.qlbh.utils.ImageManager;
import kma.qlbh.utils.StringToSlug;

/**
 * @createAt Dec 9, 2020
 * @author Tran Duc Cuong<clonebmn2itt@gmail.com>
 */
public class EditFoodItem extends javax.swing.JFrame {
    
    FoodItemManager foodItemManager;
    FoodItem foodItem;
    FoodCategoryDao foodCategoryDao = new FoodCategoryDao();
    FoodItemDao foodItemDao = new FoodItemDao();
    DefaultComboBoxModel<FoodCategory> comboBoxModel = new DefaultComboBoxModel<>();
    ImageManager im = new ImageManager();
    JFileChooser filechooser;
    
    public EditFoodItem(FoodItemManager fim, int id) {
        initComponents();
        setLocationRelativeTo(null);
        lbTitle.setText("Sửa món ăn - " + id);
        this.foodItemManager = fim;
        cboCategory.setModel(comboBoxModel);
        try {
            foodItem = foodItemDao.get(id);
            if (foodItem == null) {
                throw new Exception("Món không tồn tại");
            }
            for (FoodCategory foodCategory : foodCategoryDao.getAll()) {
                comboBoxModel.addElement(foodCategory);
            }
            txtName.setText(foodItem.getName());
            txtDescription.setText(foodItem.getDescription());
            txtUrlImage.setText(foodItem.getUrlImage());
            txtUnitName.setText(foodItem.getUnitName());
            txtUnitPrice.setText(foodItem.getUnitPrice() + "");
            cboCategory.setSelectedItem(foodCategoryDao.get(foodItem.getIdCategory()));
        } catch (Exception ex) {
            this.dispose();
            ErrorPopup.show(ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        lbTitle = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        txtDescription = new javax.swing.JTextField();
        txtUrlImage = new javax.swing.JTextField();
        btnChooseImage = new javax.swing.JButton();
        txtUnitName = new javax.swing.JTextField();
        txtUnitPrice = new javax.swing.JTextField();
        cboCategory = new javax.swing.JComboBox<>();
        btnSave = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        lbTitle.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbTitle.setText("Sửa món ăn - 01");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Nhập thông tin"));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jLabel2.setText("Tên món:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(jLabel2, gridBagConstraints);

        jLabel3.setText("Mô tả:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(jLabel3, gridBagConstraints);

        jLabel4.setText("Hình ảnh:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(jLabel4, gridBagConstraints);

        jLabel5.setText("Tên đơn vị:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(jLabel5, gridBagConstraints);

        jLabel6.setText("Giá:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(jLabel6, gridBagConstraints);

        jLabel7.setText("Loại món:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(jLabel7, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(txtName, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(txtDescription, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 0);
        jPanel1.add(txtUrlImage, gridBagConstraints);

        btnChooseImage.setText("Chọn Ảnh");
        btnChooseImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChooseImageActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.01;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 5);
        jPanel1.add(btnChooseImage, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(txtUnitName, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(txtUnitPrice, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(cboCategory, gridBagConstraints);

        btnSave.setText("Cập Nhật");
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
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 475, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addComponent(btnSave)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCancel)
                .addGap(70, 70, 70))
            .addGroup(layout.createSequentialGroup()
                .addGap(192, 192, 192)
                .addComponent(lbTitle)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbTitle)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSave)
                    .addComponent(btnCancel))
                .addGap(19, 19, 19))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCancelActionPerformed
    
    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        try {
            if (txtName.getText().isEmpty() || txtUnitName.getText().isEmpty() || txtUnitPrice.getText().isEmpty() || cboCategory.getSelectedItem() == null) {
                throw new Exception("Vui lòng điền đầy đủ thông tin");
            }
            FoodCategory selectCategory = (FoodCategory) cboCategory.getSelectedItem();
            foodItem.setName(txtName.getText());
            foodItem.setDescription(txtDescription.getText());
            foodItem.setUnitName(txtUnitName.getText());
            foodItem.setUnitPrice(Integer.parseInt(txtUnitPrice.getText()));
            foodItem.setUrlImage(txtUrlImage.getText());
            foodItem.setDescription(txtDescription.getText());
            foodItem.setIdCategory(selectCategory.getId());
            foodItemDao.update(foodItem);
            foodItemManager.renderTable();
            this.dispose();
            JOptionPane.showMessageDialog(null, "Sửa món thành công!");
        } catch (Exception e) {
            this.dispose();
            ErrorPopup.show(e);
        }
    }//GEN-LAST:event_btnSaveActionPerformed
    
    private void btnChooseImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChooseImageActionPerformed
        if (filechooser == null) {
            filechooser = new JFileChooser();
            filechooser.setDialogTitle("Choose Your File");
            filechooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            filechooser.setDragEnabled(true);
            filechooser.setCurrentDirectory(new File("D:\\"));
            filechooser.setDialogType(JFileChooser.OPEN_DIALOG);
            FileFilter imageFilter = new FileNameExtensionFilter("Hình ảnh(jpg, png, gif,...)", ImageIO.getReaderFileSuffixes());
            filechooser.setFileFilter(imageFilter);
        }
        
        int otp = filechooser.showOpenDialog(this);
        switch (otp) {
            case JFileChooser.APPROVE_OPTION:
                File file = filechooser.getSelectedFile();
                BufferedImage bi;
                try {
                    bi = ImageIO.read(file);
                    String name = StringToSlug.convert(txtName.getText());
                    String pth = im.saveImage(bi, name);
                    txtUrlImage.setText(pth);
                } catch (Exception e) {
                    ErrorPopup.show(e);
                }
                break;
            case JFileChooser.CANCEL_OPTION:
                txtUrlImage.setText("");
                break;
            
        }
    }//GEN-LAST:event_btnChooseImageActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnChooseImage;
    private javax.swing.JButton btnSave;
    private javax.swing.JComboBox<FoodCategory> cboCategory;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbTitle;
    private javax.swing.JTextField txtDescription;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtUnitName;
    private javax.swing.JTextField txtUnitPrice;
    private javax.swing.JTextField txtUrlImage;
    // End of variables declaration//GEN-END:variables
}
