/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jojo.controller;

import com.jojo.entity.Book;
import com.jojo.entity.Peminjaman;
import com.jojo.entity.User;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import sun.security.util.Pem;

/**
 * FXML Controller class
 *
 * @author 1672012
 */
public class MainController implements Initializable {

    @FXML
    private Menu menu;
    @FXML
    private MenuItem menuUser;
    @FXML
    private MenuItem menuLab;
    @FXML
    private MenuItem close;
    @FXML
    private TextArea txtTask;
    @FXML
    private TextField txtId;
    @FXML
    private DatePicker datePicker;
    @FXML
    private ComboBox<Book> cmbBook;
    @FXML
    private Button btnReset;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnSave;
    @FXML
    private TableView<Peminjaman> tableView;
    @FXML
    private TableColumn<Peminjaman, Integer> col1;
    @FXML
    private TableColumn<Peminjaman, User> col2;
    @FXML
    private TableColumn<Peminjaman, Book> col3;
    @FXML
    private TableColumn<Peminjaman, String> col4;
    @FXML
    private TableColumn<Peminjaman, Date> col5;
    private User thisUser;

    private ObservableList<Peminjaman> maintenanceList;
    private ObservableList<Book> bookList;
    private com.jojo.dao.PeminjamanDaoImpl peminjamanDaoImpl;
    private com.jojo.dao.BookDaoImpl bookDaoImpl;
    @FXML
    private TextField txtUser;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        peminjamanDaoImpl = new com.jojo.dao.PeminjamanDaoImpl();
        bookDaoImpl = new com.jojo.dao.BookDaoImpl();
        maintenanceList = FXCollections.observableArrayList();
        bookList = FXCollections.observableArrayList();

        try {
            bookList.addAll(bookDaoImpl.fetchAllList());
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);

        }
        tableView.setItems(maintenanceList);
        cmbBook.setItems(bookList);

        col1.setCellValueFactory(data -> new SimpleObjectProperty(data.getValue().getId()));
        col2.setCellValueFactory(data -> new SimpleObjectProperty(data.getValue().getUser()));
        col3.setCellValueFactory(data -> new SimpleObjectProperty(data.getValue().getBook()));
        col4.setCellValueFactory(data -> new SimpleObjectProperty(data.getValue().getTask()));
        col5.setCellValueFactory(data -> new SimpleObjectProperty(data.getValue().getDate()));
        
    }

    @FXML
    private void btnAddOnAction(ActionEvent event) {
        if (txtTask.getText().trim().isEmpty() || datePicker.getValue() == null || cmbBook.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Data belum lengkap!!");
            alert.showAndWait();
        } else {
            Peminjaman peminjaman = new Peminjaman();
            peminjaman.setUser(thisUser);
            peminjaman.setDate(Date.valueOf(datePicker.getValue()));
            peminjaman.setBook(cmbBook.getValue());
            peminjaman.setTask(txtTask.getText());
            try {
                int result = peminjamanDaoImpl.addData(peminjaman);
                if (result == 1) {
                    txtTask.clear();
                    maintenanceList.clear();
                    maintenanceList.addAll(peminjamanDaoImpl.fetchList(thisUser));
                }
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void btnUpdateOnAction(ActionEvent event) {
        String id = txtId.getText();
        Book book = cmbBook.getValue();
        String task = txtTask.getText();
        Date date = Date.valueOf(datePicker.getValue());

        Peminjaman peminjaman = tableView.getSelectionModel().getSelectedItem();
        int selected = tableView.getSelectionModel().getSelectedIndex();
        peminjaman.setId(Integer.parseInt(id));
        peminjaman.setBook(book);
        peminjaman.setTask(task);
        peminjaman.setDate(date);
        try {
            int result = peminjamanDaoImpl.updateData(peminjaman);
            if (result == 1) {
                maintenanceList.remove(selected);
                maintenanceList.clear();
                maintenanceList.addAll(peminjamanDaoImpl.fetchList(thisUser));
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnDeleteOnAction(ActionEvent event) {
       
    }

    @FXML
    private void tableOnMouseClicked(MouseEvent event) {
        Peminjaman selectedItems = tableView.getSelectionModel().getSelectedItem();
        txtId.setText(String.valueOf(selectedItems.getId()));
        txtTask.setText(selectedItems.getTask());
        cmbBook.setValue(selectedItems.getBook());
        btnUpdate.setDisable(false);
        btnDelete.setDisable(false);
    }

}
