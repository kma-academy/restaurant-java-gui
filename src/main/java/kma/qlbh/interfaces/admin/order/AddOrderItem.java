package kma.qlbh.interfaces.admin.order;

import java.text.DecimalFormat;
import kma.qlbh.dao.FoodItemDao;
import kma.qlbh.models.FoodItem;
import kma.qlbh.models.OrderItem;
import kma.qlbh.utils.ErrorPopup;

/**
 * createAt Dec 14, 2020
 *
 * @author Đỗ Tuấn Anh <daclip26@gmail.com>
 */
public class AddOrderItem extends javax.swing.JFrame {

    OrderItem orderItem = new OrderItem();
    FoodItemDao foodItemDao = new FoodItemDao();
    DecimalFormat formatter = new DecimalFormat("###,###,###");
    EditOrder parrent;

    public AddOrderItem(EditOrder parrent, FoodItem foodItem) {
        initComponents();
        setLocationRelativeTo(null);
        this.parrent = parrent;
        try {
            if (foodItem == null) {
                throw new Exception("Món bạn chọn không hợp lệ");
            }
            orderItem.setQuantity(1);
            orderItem.setFoodItem(foodItem);
            orderItem.setFoodPrice(foodItem.getUnitPrice());

            lbFoodName.setText(foodItem.getName());
            spnFoodPrice.setValue(foodItem.getUnitPrice());

            //Lấy danh sách topping
            if (foodItem.getIdCategory() == 2) {
                //Cần viết thêm thuộc tính requireTopping foodItem thay vì check tay
                //Nếu là trà sữa thì hiện topping
                for (FoodItem f : foodItemDao.getByIdCategory(4)) {
                    cboTopping.addItem(f);
                }
            } else {
                //Nếu không thì ẩn đi
                orderItem.setToppingItem(foodItemDao.get(1));//No Topping
                lbTopping.setVisible(false);
                cboTopping.setVisible(false);
            }

        } catch (Exception e) {
            ErrorPopup.show(e);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        lbFoodName = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btnMinus = new javax.swing.JButton();
        btnPlus = new javax.swing.JButton();
        VND = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cboTopping = new javax.swing.JComboBox<>();
        spnQuantity = new javax.swing.JSpinner();
        spnFoodPrice = new javax.swing.JSpinner();
        lbTopping = new javax.swing.JLabel();
        lbAmount = new javax.swing.JLabel();
        VND1 = new javax.swing.JLabel();
        btnAdd = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Hóa đơn 01 - Thêm món");

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Tên món:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 15);
        jPanel1.add(jLabel2, gridBagConstraints);

        lbFoodName.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbFoodName.setForeground(new java.awt.Color(102, 51, 255));
        lbFoodName.setText("Trà Sữa Trân Châu Đường Đen");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(lbFoodName, gridBagConstraints);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Tổng Tiền:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 15);
        jPanel1.add(jLabel3, gridBagConstraints);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("Số Lượng:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 15);
        jPanel1.add(jLabel5, gridBagConstraints);

        btnMinus.setText("-");
        btnMinus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMinusActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 0);
        jPanel1.add(btnMinus, gridBagConstraints);

        btnPlus.setText("+");
        btnPlus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPlusActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 5);
        jPanel1.add(btnPlus, gridBagConstraints);

        VND.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        VND.setText("VND");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(VND, gridBagConstraints);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("Giá Món:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 15);
        jPanel1.add(jLabel7, gridBagConstraints);

        cboTopping.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboToppingActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 0);
        jPanel1.add(cboTopping, gridBagConstraints);

        spnQuantity.setModel(new javax.swing.SpinnerNumberModel(1, 1, 99, 1));
        spnQuantity.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spnQuantityStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 0);
        jPanel1.add(spnQuantity, gridBagConstraints);

        spnFoodPrice.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1000));
        spnFoodPrice.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spnFoodPriceStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 0);
        jPanel1.add(spnFoodPrice, gridBagConstraints);

        lbTopping.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbTopping.setText("Topping:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 15);
        jPanel1.add(lbTopping, gridBagConstraints);

        lbAmount.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbAmount.setForeground(new java.awt.Color(255, 0, 0));
        lbAmount.setText("234,123,777");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(lbAmount, gridBagConstraints);

        VND1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        VND1.setText("VND");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(VND1, gridBagConstraints);

        btnAdd.setText("Thêm");
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(btnAdd)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCancel)
                .addGap(49, 49, 49))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 440, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancel)
                    .addComponent(btnAdd))
                .addGap(25, 25, 25))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void caculateAmount() {
        FoodItem topping = orderItem.getToppingItem();
        int amount = orderItem.getFoodPrice();
        if (topping != null) {
            amount += topping.getUnitPrice();
        }
        amount *= orderItem.getQuantity();
        lbAmount.setText(formatter.format(amount));
    }
    private void btnMinusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMinusActionPerformed
        orderItem.setQuantity(orderItem.getQuantity() - 1);
        spnQuantity.setValue(orderItem.getQuantity());
    }//GEN-LAST:event_btnMinusActionPerformed

    private void btnPlusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPlusActionPerformed
        orderItem.setQuantity(orderItem.getQuantity() + 1);
        spnQuantity.setValue(orderItem.getQuantity());
    }//GEN-LAST:event_btnPlusActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void spnFoodPriceStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spnFoodPriceStateChanged
        orderItem.setFoodPrice((int) spnFoodPrice.getValue());
        caculateAmount();
    }//GEN-LAST:event_spnFoodPriceStateChanged

    private void cboToppingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboToppingActionPerformed
        FoodItem topping = (FoodItem) cboTopping.getSelectedItem();
        orderItem.setToppingItem(topping);
        caculateAmount();
    }//GEN-LAST:event_cboToppingActionPerformed

    private void spnQuantityStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spnQuantityStateChanged
        orderItem.setQuantity((int) spnQuantity.getValue());
        caculateAmount();
    }//GEN-LAST:event_spnQuantityStateChanged

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        if (orderItem.getQuantity() == 0) {
            ErrorPopup.show(new Exception("Số lượng không hợp lệ"));
        }
        this.dispose();
        parrent.addOrderItem(orderItem);
    }//GEN-LAST:event_btnAddActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel VND;
    private javax.swing.JLabel VND1;
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnMinus;
    private javax.swing.JButton btnPlus;
    private javax.swing.JComboBox<FoodItem> cboTopping;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbAmount;
    private javax.swing.JLabel lbFoodName;
    private javax.swing.JLabel lbTopping;
    private javax.swing.JSpinner spnFoodPrice;
    private javax.swing.JSpinner spnQuantity;
    // End of variables declaration//GEN-END:variables

}
