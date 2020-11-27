package kma.qlbh.interfaces.admin;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.YES_OPTION;
import javax.swing.table.DefaultTableModel;
import kma.qlbh.dao.EmployeeDao;
import kma.qlbh.models.Employee;
import kma.qlbh.utils.IconManager;

/**
 *
 * @author Tran Duc Cuong<clonebmn2itt@gmail.com>
 */
public class TableManager extends javax.swing.JPanel {

    /**
     * Creates new form EmployeeManager
     */
    DefaultTableModel model = new DefaultTableModel();
    EmployeeDao employeeDao = new EmployeeDao();

    public TableManager() {
        initComponents();
        IconManager im = new IconManager();
        btnAdd.setIcon(im.getIcon("add_25px.png"));
        btnEdit.setIcon(im.getIcon("edit_25px.png"));
        btnDelete.setIcon(im.getIcon("delete_25px.png"));
        model.addColumn("ID");
        model.addColumn("Họ và tên");
        model.addColumn("Tên tài khoản");
        model.addColumn("Mật khẩu");
        model.addColumn("Số điện thoại");
        model.addColumn("Ngày vào làm");
        model.addColumn("Chức vụ");
        tblTables.setModel(model);
        renderTable();
    }

    public void renderTable() {
//        int numRows = model.getRowCount();
//        for (int i = 0; i < numRows; i++) {
//            model.removeRow(i);
//        }
        model.setNumRows(0);
        try {
            ArrayList<Employee> employees = employeeDao.getAll();
            for (Employee employee : employees) {
                model.addRow(new Object[]{
                    employee.getId(), employee.getName(), employee.getUsername(), employee.getPassword(),
                    employee.getPhoneNumber(), employee.getStartDate().toString(), employee.getPermissionName()
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Có lỗi xảy ra!", "Lỗi", ERROR_MESSAGE);
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
//        EmployeeAdd pnl = new EmployeeAdd(this);
//        pnl.setVisible(true);
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed

        int selectedRow = tblTables.getSelectedRow();
        try {
            if (selectedRow < 0) {
                throw new Exception("Chọn nhân viên cần edit");
            } else {
                int id = (int) tblTables.getValueAt(selectedRow, 0);
//                EmployeeEdit pnl = new EmployeeEdit(this, id);
//                pnl.setVisible(true);
            }
        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e.getMessage(), "Lỗi", ERROR_MESSAGE);
        }

    }//GEN-LAST:event_btnEditActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        int selectedRows[] = tblTables.getSelectedRows();
        try {
            if (JOptionPane.showConfirmDialog(null, "Xác nhận xóa hàng loạt?", "Xóa nhân viên", ERROR_MESSAGE) != YES_OPTION) {
                return;
            }
            for (int i = 0; i < selectedRows.length; i++) {
                int selectedRow = selectedRows[i];
                int id = (int) tblTables.getValueAt(selectedRow, 0);
                employeeDao.deleteById(id);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage(), "Lỗi", ERROR_MESSAGE);
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
