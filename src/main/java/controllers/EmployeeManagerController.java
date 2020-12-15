package controllers;

import views.admin.EmployeeManagerView;

/**
 * createAt Dec 15, 2020
 *
 * @author Đỗ Tuấn Anh <daclip26@gmail.com>
 */
public class EmployeeManagerController {
    
    private EmployeeManagerView view;
    
    public EmployeeManagerController(EmployeeManagerView view) {
        this.view = view;
        view.setVisible(true);
    }
    
    public EmployeeManagerView getView() {
        return view;
    }
    
    public void setView(EmployeeManagerView view) {
        this.view = view;
    }
    
}
