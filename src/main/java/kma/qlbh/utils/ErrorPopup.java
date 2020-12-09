/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kma.qlbh.utils;

import javax.swing.JOptionPane;

/**
 *
 * @author MSI
 */
public class ErrorPopup {

    public static void show(Exception e) {
        JOptionPane.showMessageDialog(null, e.getMessage(), "Có lỗi xảy ra", JOptionPane.ERROR_MESSAGE);
    }
}
