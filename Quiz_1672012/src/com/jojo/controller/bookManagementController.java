/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jojo.controller;

import com.jojo.entity.Book;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author 1672012
 */
public class bookManagementController implements Initializable {

    @FXML
    private TextField txtName;
    @FXML
    private TextField txtCapacity;
    @FXML
    private TextField txtId;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    @FXML
    private TableColumn<Book, Integer> col1;
    @FXML
    private TableColumn<Book, String> col2;
    @FXML
    private TableColumn<Book, Integer> col3;
    private ObservableList<Book> bookList;
    private com.jojo.dao.BookDaoImpl bookDaoImpl;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       }    

    @FXML
    private void btnAddOnAction(ActionEvent event) {
    }

    @FXML
    private void btnUpdateOnAction(ActionEvent event) {
    }

    @FXML
    private void btnDeleteOnAction(ActionEvent event) {
    }

    @FXML
    private void tableOnMouseClicked(MouseEvent event) {
    }
    
}
