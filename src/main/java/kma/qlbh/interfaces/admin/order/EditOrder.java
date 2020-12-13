package kma.qlbh.interfaces.admin.order;

import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import kma.qlbh.dao.EmployeeDao;
import kma.qlbh.dao.OrderDao;
import kma.qlbh.dao.OrderItemDao;
import kma.qlbh.dao.TableDao;
import kma.qlbh.models.Employee;
import kma.qlbh.models.Order;
import kma.qlbh.models.OrderItem;
import kma.qlbh.models.Table;
import kma.qlbh.utils.ErrorPopup;
import kma.qlbh.utils.OrderStatus;
import kma.qlbh.utils.OrderType;
import kma.qlbh.utils.TableStatus;

/**
 *
 * @author MSI
 */
public class EditOrder extends javax.swing.JFrame {

    DefaultComboBoxModel<Employee> emComboBoxModel = new DefaultComboBoxModel<>();
    DefaultComboBoxModel<Table> tbComboBoxModel = new DefaultComboBoxModel<>();
    EmployeeDao employeeDao = new EmployeeDao();
    TableDao tableDao = new TableDao();
    OrderDao orderDao = new OrderDao();
    OrderItemDao orderItemDao = new OrderItemDao();
    DecimalFormat formatter = new DecimalFormat("###,###,###");
    ArrayList<OrderItem> orderItems;
    int id = 1;
    Order o;

    public EditOrder() {
        initComponents();
        setLocationRelativeTo(null);
        cboEmployee.setModel(emComboBoxModel);
        cboTable.setModel(tbComboBoxModel);
        initData();
        renderOrderItem();

    }

    private void initData() {
        try {
            orderItems = orderItemDao.getByIdOrder(id);
            o = orderDao.get(id);
            o.setTotalAmount(caculateTotalAmount());
            tbComboBoxModel.removeAllElements();
            emComboBoxModel.removeAllElements();
            cboType.removeAllItems();
            for (Table table : tableDao.getAll()) {
                if (table.getStatus() == TableStatus.FREE || table.getId() == o.getIdTable()) {
                    tbComboBoxModel.addElement(table);
                }
            }
            for (Employee employee : employeeDao.getAll()) {
                if (employee.getPermissionId() <= 2 || employee.getId() == o.getIdEmployee()) {
                    emComboBoxModel.addElement(employee);
                }
            }
            for (OrderType ot : OrderType.values()) {
                cboType.addItem(ot.getName());
            }

            tbComboBoxModel.setSelectedItem(o.getTable());
            emComboBoxModel.setSelectedItem(o.getEmployee());
            spnDiscount.setValue(o.getDiscount());
            cboType.setSelectedItem(o.getType().getName());
            lbDiscount.setText(o.getDiscount() + "");
            updateAmount();
        } catch (Exception e) {
            this.dispose();
            ErrorPopup.show(e);
        }
    }

    public void renderOrderItem() {
        pnlOrderItem.removeAll();
        for (OrderItem orderItem : orderItems) {
            pnlOrderItem.add(new OrderItemPane(orderItem, this));
        }
        pnlOrderItem.updateUI();
    }

    public void updateAmount() {
        o.setTotalAmount(caculateTotalAmount());
        lbDiscount.setText(o.getDiscount() + "");
        lbPaidAmount.setText(formatter.format(o.getPaidAmount()));
        lbFinalAmount.setText(formatter.format(o.getFinalAmount()));
        lbTotalAmount.setText(formatter.format(o.getTotalAmount()));
    }

    private int caculateTotalAmount() {
        int totalAmount = 0;
        for (OrderItem orderItem : orderItems) {
            totalAmount += orderItem.getAmount();
        }
        return totalAmount;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        spnDiscount = new javax.swing.JSpinner();
        cboEmployee = new javax.swing.JComboBox<>();
        cboTable = new javax.swing.JComboBox<>();
        cboType = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        pnlOrderItem = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        btnUpdate = new javax.swing.JButton();
        btnCancelOrder = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        btnPaid = new javax.swing.JButton();
        btnClose = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lbTotalAmount = new javax.swing.JLabel();
        lbFinalAmount = new javax.swing.JLabel();
        lbDiscount = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lbPaidAmount = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAutoRequestFocus(false);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Cập nhật hóa đơn");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Chỉnh sửa thông tin"));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jLabel2.setText("Nhân viên:");
        jLabel2.setToolTipText("");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(jLabel2, gridBagConstraints);

        jLabel3.setText("Bàn:");
        jLabel3.setToolTipText("");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(jLabel3, gridBagConstraints);

        jLabel4.setText("Loại hóa đơn:");
        jLabel4.setToolTipText("");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(jLabel4, gridBagConstraints);
        jPanel1.add(jLabel5, new java.awt.GridBagConstraints());

        jLabel6.setText("Giảm giá(%):");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(jLabel6, gridBagConstraints);

        spnDiscount.setModel(new javax.swing.SpinnerNumberModel(0, 0, 100, 1));
        spnDiscount.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spnDiscountStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(spnDiscount, gridBagConstraints);

        cboEmployee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboEmployeeActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(cboEmployee, gridBagConstraints);

        cboTable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTableActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(cboTable, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(cboType, gridBagConstraints);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Danh sách món"));
        jPanel2.setPreferredSize(new java.awt.Dimension(525, 800));

        pnlOrderItem.setMaximumSize(new java.awt.Dimension(500, 5000));
        pnlOrderItem.setPreferredSize(new java.awt.Dimension(500, 5000));
        jScrollPane1.setViewportView(pnlOrderItem);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 525, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 572, Short.MAX_VALUE)
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Thao tác"));
        jPanel3.setLayout(new java.awt.GridBagLayout());

        btnUpdate.setText("Cập Nhật");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(btnUpdate, gridBagConstraints);

        btnCancelOrder.setText("Hủy Hóa Đơn");
        btnCancelOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelOrderActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(btnCancelOrder, gridBagConstraints);

        jButton3.setText("Chọn món");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(jButton3, gridBagConstraints);

        btnPaid.setText("Thanh Toán");
        btnPaid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPaidActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(btnPaid, gridBagConstraints);

        btnClose.setText("Đóng");
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(btnClose, gridBagConstraints);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin hóa đơn"));
        jPanel4.setLayout(new java.awt.GridBagLayout());

        jLabel7.setText("Tổng:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel4.add(jLabel7, gridBagConstraints);

        jLabel8.setText("Đã Thanh Toán:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel4.add(jLabel8, gridBagConstraints);

        jLabel9.setText("Giảm Giá:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel4.add(jLabel9, gridBagConstraints);

        lbTotalAmount.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbTotalAmount.setForeground(new java.awt.Color(255, 0, 0));
        lbTotalAmount.setText("1000,000,000");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel4.add(lbTotalAmount, gridBagConstraints);

        lbFinalAmount.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbFinalAmount.setForeground(new java.awt.Color(255, 0, 0));
        lbFinalAmount.setText("1020,000,000");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel4.add(lbFinalAmount, gridBagConstraints);

        lbDiscount.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbDiscount.setForeground(new java.awt.Color(255, 0, 0));
        lbDiscount.setText("0");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel4.add(lbDiscount, gridBagConstraints);

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel13.setText("VND");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel4.add(jLabel13, gridBagConstraints);

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel14.setText("VND");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel4.add(jLabel14, gridBagConstraints);

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel15.setText("%");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel4.add(jLabel15, gridBagConstraints);

        jLabel10.setText("Phải Trả");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel4.add(jLabel10, gridBagConstraints);

        lbPaidAmount.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbPaidAmount.setForeground(new java.awt.Color(255, 0, 0));
        lbPaidAmount.setText("1020,000,000");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel4.add(lbPaidAmount, gridBagConstraints);

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel16.setText("VND");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel4.add(jLabel16, gridBagConstraints);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 535, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(340, 340, 340)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 595, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void spnDiscountStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spnDiscountStateChanged
        o.setDiscount((int) spnDiscount.getValue());
        updateAmount();
    }//GEN-LAST:event_spnDiscountStateChanged

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        try {
            if (o.getTable() == null) {
                throw new Exception("Hết bàn");
            }
            if (o.getDiscount() < 0 || o.getDiscount() > 100) {
                throw new Exception("Discount phải nằm trong khoảng 0-100");
            }
            if (o.getEmployee() == null) {
                throw new Exception("Chọn nhân viên");
            }
            if (o.getType() == OrderType.LOCAL) {
                o.getTable().setStatus(TableStatus.SERVING);
            } else {
                o.getTable().setStatus(TableStatus.FREE);
            }
            if (o.getFinalAmount() == o.getPaidAmount()) {
                o.setStatus(OrderStatus.PAID);
            }
            for (OrderItem orderItem : orderItems) {
                if (orderItem.getQuantity() <= 0) {
                    orderItemDao.delete(orderItem);
                } else {
                    orderItemDao.save(orderItem);
                }
            }
            orderDao.update(o);
            tableDao.update(o.getTable());
            this.dispose();
            JOptionPane.showMessageDialog(null, "Cập nhật hóa đơn thành công!");
        } catch (Exception e) {
            ErrorPopup.show(e);
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void cboEmployeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboEmployeeActionPerformed
        Employee employee = (Employee) emComboBoxModel.getSelectedItem();
        o.setEmployee(employee);
    }//GEN-LAST:event_cboEmployeeActionPerformed

    private void cboTableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTableActionPerformed
        try {

            Table nTable = (Table) tbComboBoxModel.getSelectedItem(),
                    cTable = o.getTable();
            if (nTable == null || (cTable != null && nTable.getId() == cTable.getId())) {
                return;
            }
            cTable.setStatus(TableStatus.FREE);
            nTable.setStatus(TableStatus.SERVING);
            o.setTable(nTable);
            tableDao.update(cTable);
            tableDao.update(nTable);
        } catch (Exception e) {
            ErrorPopup.show(e);
        }
    }//GEN-LAST:event_cboTableActionPerformed

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCloseActionPerformed

    private void btnCancelOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelOrderActionPerformed
        try {
            int value = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn hủy hóa đơn?", "Xác Nhận", JOptionPane.YES_NO_OPTION);
            if (value != JOptionPane.YES_OPTION) {
                return;
            }
            o.setStatus(OrderStatus.CANCEL);
            orderDao.update(o);
        } catch (Exception e) {
            ErrorPopup.show(e);
        }
    }//GEN-LAST:event_btnCancelOrderActionPerformed

    private void btnPaidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPaidActionPerformed

        try {
            String rawInput = JOptionPane.showInputDialog(null, "Nhập số tiền thanh toán!", o.getPaidAmount());
            if (rawInput == null) {
                return;
            }
            int paidAmount = Integer.parseInt(rawInput);
            if (o.getFinalAmount() > paidAmount) {
                JOptionPane.showMessageDialog(null, "Bạn còn phải thanh toán " + formatter.format(o.getFinalAmount() - paidAmount) + " VND");
            } else {
                JOptionPane.showMessageDialog(null, "Bạn đã thanh toán xong");
            }
            o.setPaidAmount(paidAmount);
            updateAmount();
        } catch (Exception e) {
            ErrorPopup.show(e);
        }
    }//GEN-LAST:event_btnPaidActionPerformed

    public static void main(String args[]) {
        try {
            javax.swing.UIManager.setLookAndFeel("com.formdev.flatlaf.FlatLightLaf");
        } catch (Exception ex) {
            System.err.println("Failed to initialize LaF");
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EditOrder().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelOrder;
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnPaid;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<Employee> cboEmployee;
    private javax.swing.JComboBox<Table> cboTable;
    private javax.swing.JComboBox<String> cboType;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbDiscount;
    private javax.swing.JLabel lbFinalAmount;
    private javax.swing.JLabel lbPaidAmount;
    private javax.swing.JLabel lbTotalAmount;
    private javax.swing.JPanel pnlOrderItem;
    private javax.swing.JSpinner spnDiscount;
    // End of variables declaration//GEN-END:variables
}
