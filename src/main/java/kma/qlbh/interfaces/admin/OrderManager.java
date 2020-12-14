package kma.qlbh.interfaces.admin;

import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.YES_OPTION;
import javax.swing.table.DefaultTableModel;
import kma.qlbh.dao.EmployeeDao;
import kma.qlbh.dao.OrderDao;
import kma.qlbh.dao.TableDao;
import kma.qlbh.interfaces.admin.order.AddOrder;
import kma.qlbh.models.Order;
import kma.qlbh.utils.ErrorPopup;
import kma.qlbh.utils.IconManager;

/**
 *
 * @author Tran Duc Cuong<clonebmn2itt@gmail.com>
 */
public class OrderManager extends javax.swing.JPanel {

    /**
     * Creates new form EmployeeManager
     */
    DefaultTableModel model = new DefaultTableModel();
    TableDao tableDao = new TableDao();
    OrderDao orderDao = new OrderDao();
    EmployeeDao employeeDao = new EmployeeDao();

    public OrderManager() {
        initComponents();
        IconManager im = new IconManager();
        btnAdd.setIcon(im.getIcon("add_25px.png"));
        btnEdit.setIcon(im.getIcon("edit_25px.png"));
        btnDelete.setIcon(im.getIcon("delete_25px.png"));
        model.addColumn("ID");
        model.addColumn("Người lập");
        model.addColumn("Bàn");
        model.addColumn("Loại");
        model.addColumn("Trạng thái");
        model.addColumn("Ngày lập HD");
        model.addColumn("Ngày thanh toán");
        model.addColumn("Đã Thanh Toán");
        tblTables.setModel(model);
        renderTable();
    }

    public void renderTable() {
        model.setNumRows(0);
        try {
            for (Order order : orderDao.getAll()) {
                model.addRow(new Object[]{
                    order.getId(), employeeDao.get(order.getIdEmployee()).getName(), tableDao.get(order.getIdTable()).getName(),
                    order.getType().getName(), order.getStatus().getName(), order.getOrderDate(), order.getPayDate(),
                    String.format("%d/%d", order.getPaidAmount(), order.getTotalAmount())
                });
            }
        } catch (Exception e) {
            ErrorPopup.show(e);
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

        jScrollPane1 = new javax.swing.JScrollPane();
        tblTables = new javax.swing.JTable();
        btnAdd = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 0, 204));

        tblTables.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblTables);

        btnAdd.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnAdd.setText("Thêm");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnEdit.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnEdit.setText("Sửa");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnDelete.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnDelete.setText("Xóa");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 824, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 668, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addComponent(btnAdd)
                .addGap(49, 49, 49)
                .addComponent(btnEdit)
                .addGap(50, 50, 50)
                .addComponent(btnDelete)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        AddOrder pnl = new AddOrder(this);
        pnl.setVisible(true);
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed

        int selectedRow = tblTables.getSelectedRow();
        try {
            if (selectedRow < 0) {
                throw new Exception("Chọn nhân viên cần edit");
            } else {
                int id = (int) tblTables.getValueAt(selectedRow, 0);

            }
        } catch (Exception e) {
            ErrorPopup.show(e);
        }

    }//GEN-LAST:event_btnEditActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        int selectedRows[] = tblTables.getSelectedRows();
        try {
            if (JOptionPane.showConfirmDialog(null, "Xác nhận xóa hàng loạt?", "Xóa đơn hàng", ERROR_MESSAGE) != YES_OPTION) {
                return;
            }
            for (int i = 0; i < selectedRows.length; i++) {
                int selectedRow = selectedRows[i];
                int id = (int) tblTables.getValueAt(selectedRow, 0);
                orderDao.deleteById(id);
            }
        } catch (Exception e) {
            ErrorPopup.show(e);
        }
        renderTable();
    }//GEN-LAST:event_btnDeleteActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblTables;
    // End of variables declaration//GEN-END:variables
}
