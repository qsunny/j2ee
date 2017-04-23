package com.aaron;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class Controller {
    //这里的Button对象有需要加@FXML注解，然后变量的名称为你刚才在FXML文件中声明的Button的id属性/
    @FXML
    private Button btnHello;

    //这里的handleButtonAction方法为我们在FXML文件中声明的onAction的处理函数
    @FXML
    protected void handleButtonAction(ActionEvent event) {
        btnHello.setText("Hello World, I am JavaFX!");
    }
}
