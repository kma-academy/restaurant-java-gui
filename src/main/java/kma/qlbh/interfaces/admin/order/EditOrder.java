package kma.qlbh.interfaces.admin.order;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import kma.qlbh.dao.EmployeeDao;
import kma.qlbh.dao.FoodCategoryDao;
import kma.qlbh.dao.FoodItemDao;
import kma.qlbh.dao.OrderDao;
import kma.qlbh.dao.OrderItemDao;
import kma.qlbh.dao.TableDao;
import kma.qlbh.models.Employee;
import kma.qlbh.models.FoodCategory;
import kma.qlbh.models.FoodItem;
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
    FoodCategoryDao foodCategoryDao = new FoodCategoryDao();
    FoodItemDao foodItemDao = new FoodItemDao();
    DecimalFormat formatter = new DecimalFormat("###,###,###");
    ArrayList<OrderItem> orderItems;
    int idOrder = 1;
    Order o;

    public EditOrder(int idOrder) {
        initComponents();
        this.idOrder = idOrder;
        setLocationRelativeTo(null);
        cboEmployee.setModel(emComboBoxModel);
        cboTable.setModel(tbComboBoxModel);
        initData();
        renderFoodCategory();
        //In danh sách loại 1
        renderFoodItem(1);
        renderOrderItem();

    }

    //Khởi tạo dữ liệu cho các combobox
    private void initData() {
        try {
            //Lấy danh sách món đã đặt của order
            orderItems = orderItemDao.getByIdOrder(idOrder);
            o = orderDao.get(idOrder);
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
        } catch (Exception e) {
            this.dispose();
            ErrorPopup.show(e);
        }
    }

    //Hiển thị các loại món
    private void renderFoodCategory() {
        try {
            pnlFoodCategory.removeAll();
            for (FoodCategory foodCategory : foodCategoryDao.getAll()) {
                FoodCategoryPane pnl = new FoodCategoryPane(foodCategory);
                pnlFoodCategory.add(pnl);
                pnl.addMouseListener(new java.awt.event.MouseAdapter() {
                    @Override
                    //Chọn loại món
                    public void mousePressed(java.awt.event.MouseEvent evt) {
                        FoodCategory f = pnl.getFoodCategory();
                        if (f != null && f.getId() > 0) {
                            renderFoodItem(f.getId());
                        }
                    }
                });
            }
            pnlFoodCategory.updateUI();
        } catch (SQLException ex) {
            ErrorPopup.show(ex);
        }
    }

    // In danh sách món thuộc loại đã chọn
    private void renderFoodItem(int idCategory) {
        try {
            pnlFoodItem.removeAll();

            for (FoodItem foodItem : foodItemDao.getByIdCategory(idCategory)) {
                FoodItemPane pane = new FoodItemPane(foodItem);
                pnlFoodItem.add(pane);
                EditOrder parrent = this;
                pane.addMouseListener(new java.awt.event.MouseAdapter() {
                    @Override
                    public void mousePressed(java.awt.event.MouseEvent evt) {
                        //Sự kiện chọn món
                        System.out.println("Thêm món: " + pane.getFoodItem());
                        new AddOrderItem(parrent, pane.getFoodItem()).setVisible(true);
                    }
                });
            }
            pnlFoodItem.updateUI();

        } catch (Exception ex) {
            ErrorPopup.show(ex);
        }
    }

    //Hiển thị các món đã đặt
    private void renderOrderItem() {
        pnlOrderItem.removeAll();
        for (OrderItem orderItem : orderItems) {
            pnlOrderItem.add(new OrderItemPane(orderItem, this));
//            System.out.println(orderItem);
        }
        updateAmount();
        pnlOrderItem.updateUI();
    }

    //Thêm món vào danh sách
    public void addOrderItem(OrderItem newItem) {
        //Kiểm tra rỗng
        if (newItem == null) {
            return;
        }
        newItem.setIdOrder(idOrder);
        for (OrderItem orderItem : orderItems) {
            //Nếu đã tồn tại món trong danh sách
            if (newItem.equals(orderItem)) {
                //Cập nhật số lượng
                orderItem.setQuantity(orderItem.getQuantity() + newItem.getQuantity());
                //Cập nhật giá
                orderItem.setFoodPrice(newItem.getFoodPrice());
                //Hiển thị lại món
//                renderOrderItem();
                return;
            }
        }
        //Nếu chưa tồn tại
        orderItems.add(newItem);
        renderOrderItem();
    }

    //Cập nhật tổng tiền
    public void updateAmount() {
        o.setTotalAmount(caculateTotalAmount());
        lbDiscount.setText(o.getDiscount() + "");
        lbPaidAmount.setText(formatter.format(o.getPaidAmount()));
        lbFinalAmount.setText(formatter.format(o.getFinalAmount()));
        lbTotalAmount.setText(formatter.format(o.getTotalAmount()));
    }

    //Tính tiền
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
        pnlRoot = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
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
        jPanel3 = new javax.swing.JPanel();
        btnUpdate = new javax.swing.JButton();
        btnCancelOrder = new javax.swing.JButton();
        btnShipManager = new javax.swing.JButton();
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
        jPanel7 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        pnlOrderItem = new javax.swing.JPanel();
        pnlCenter = new javax.swing.JPanel();
        pnlFoodCategory = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        pnlFoodItem = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAutoRequestFocus(false);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Cập nhật hóa đơn");

        pnlRoot.setPreferredSize(new java.awt.Dimension(1080, 600));
        pnlRoot.setLayout(new java.awt.BorderLayout());

        jPanel6.setPreferredSize(new java.awt.Dimension(250, 601));
        jPanel6.setLayout(new java.awt.BorderLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Chỉnh sửa thông tin"));
        jPanel1.setPreferredSize(new java.awt.Dimension(250, 200));
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

        jPanel6.add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Thao tác"));
        jPanel3.setPreferredSize(new java.awt.Dimension(250, 175));
        jPanel3.setLayout(new java.awt.GridBagLayout());

        btnUpdate.setText("Cập Nhật");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
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
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(btnCancelOrder, gridBagConstraints);

        btnShipManager.setText("Quản lý ship");
        btnShipManager.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnShipManagerActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(btnShipManager, gridBagConstraints);

        btnPaid.setText("Thanh Toán");
        btnPaid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPaidActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
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
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(btnClose, gridBagConstraints);

        jPanel6.add(jPanel3, java.awt.BorderLayout.PAGE_END);

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

        jPanel6.add(jPanel4, java.awt.BorderLayout.CENTER);

        pnlRoot.add(jPanel6, java.awt.BorderLayout.LINE_START);

        jPanel7.setBackground(new java.awt.Color(102, 102, 0));
        jPanel7.setPreferredSize(new java.awt.Dimension(525, 600));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Danh sách món đã đặt"));
        jPanel2.setPreferredSize(new java.awt.Dimension(525, 800));

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setOpaque(false);
        jScrollPane1.setPreferredSize(new java.awt.Dimension(525, 5002));

        pnlOrderItem.setMaximumSize(new java.awt.Dimension(515, 5000));
        pnlOrderItem.setPreferredSize(new java.awt.Dimension(515, 5000));
        jScrollPane1.setViewportView(pnlOrderItem);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 515, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 571, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 529, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
        );

        pnlRoot.add(jPanel7, java.awt.BorderLayout.LINE_END);

        pnlCenter.setPreferredSize(new java.awt.Dimension(500, 600));
        pnlCenter.setLayout(new java.awt.BorderLayout());

        pnlFoodCategory.setPreferredSize(new java.awt.Dimension(150, 600));
        pnlFoodCategory.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 0, 5));

        jPanel9.setBackground(new java.awt.Color(153, 153, 255));
        jPanel9.setPreferredSize(new java.awt.Dimension(150, 50));

        jLabel11.setBackground(new java.awt.Color(102, 51, 0));
        jLabel11.setText("Trà sữa");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(jLabel11)
                .addContainerGap(64, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel11)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        pnlFoodCategory.add(jPanel9);

        jPanel10.setBackground(new java.awt.Color(51, 255, 255));
        jPanel10.setPreferredSize(new java.awt.Dimension(150, 50));

        jLabel12.setText("Bánh");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addComponent(jLabel12)
                .addContainerGap(68, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel12)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        pnlFoodCategory.add(jPanel10);

        pnlCenter.add(pnlFoodCategory, java.awt.BorderLayout.LINE_START);

        jPanel11.setOpaque(false);
        jPanel11.setPreferredSize(new java.awt.Dimension(625, 600));

        jScrollPane2.setBackground(new java.awt.Color(153, 153, 0));
        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane2.setOpaque(false);
        jScrollPane2.setPreferredSize(new java.awt.Dimension(625, 600));

        pnlFoodItem.setMaximumSize(new java.awt.Dimension(615, 10000));
        pnlFoodItem.setMinimumSize(new java.awt.Dimension(615, 1000));
        pnlFoodItem.setName(""); // NOI18N
        pnlFoodItem.setPreferredSize(new java.awt.Dimension(615, 5000));
        pnlFoodItem.setRequestFocusEnabled(false);
        pnlFoodItem.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jPanel5.setBackground(new java.awt.Color(102, 51, 0));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel5.setToolTipText("");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 75, Short.MAX_VALUE)
        );

        pnlFoodItem.add(jPanel5);

        jPanel12.setBackground(new java.awt.Color(102, 51, 0));
        jPanel12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel12.setToolTipText("");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 75, Short.MAX_VALUE)
        );

        pnlFoodItem.add(jPanel12);

        jPanel13.setBackground(new java.awt.Color(102, 0, 0));
        jPanel13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel13.setToolTipText("");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 75, Short.MAX_VALUE)
        );

        pnlFoodItem.add(jPanel13);

        jScrollPane2.setViewportView(pnlFoodItem);

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pnlCenter.add(jPanel11, java.awt.BorderLayout.CENTER);

        pnlRoot.add(pnlCenter, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlRoot, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(716, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap(716, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlRoot, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE))
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

    private void btnShipManagerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnShipManagerActionPerformed

    }//GEN-LAST:event_btnShipManagerActionPerformed

    public static void main(String args[]) {
        try {
            javax.swing.UIManager.setLookAndFeel("com.formdev.flatlaf.FlatLightLaf");
        } catch (Exception ex) {
            System.err.println("Failed to initialize LaF");
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EditOrder(1).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelOrder;
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnPaid;
    private javax.swing.JButton btnShipManager;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<Employee> cboEmployee;
    private javax.swing.JComboBox<Table> cboTable;
    private javax.swing.JComboBox<String> cboType;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
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
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbDiscount;
    private javax.swing.JLabel lbFinalAmount;
    private javax.swing.JLabel lbPaidAmount;
    private javax.swing.JLabel lbTotalAmount;
    private javax.swing.JPanel pnlCenter;
    private javax.swing.JPanel pnlFoodCategory;
    private javax.swing.JPanel pnlFoodItem;
    private javax.swing.JPanel pnlOrderItem;
    private javax.swing.JPanel pnlRoot;
    private javax.swing.JSpinner spnDiscount;
    // End of variables declaration//GEN-END:variables
}
