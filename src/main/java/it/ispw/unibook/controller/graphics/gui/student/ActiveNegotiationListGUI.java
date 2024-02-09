package it.ispw.unibook.controller.graphics.gui.student;

import it.ispw.unibook.bean.SellableBookBean;
import it.ispw.unibook.bean.SellableBooksListBean;
import it.ispw.unibook.exceptions.login.SessionException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ActiveNegotiationListGUI extends ManageSellableBookGUI implements Initializable {

    @FXML
    private VBox sellableBooksList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            SellableBooksListBean bean = new SellableBooksListBean();
            super.retrieveSellableBooksBySessionActiveNegotiation(bean);
            List<SellableBookBean> sellableBooks = bean.getList();

            for (SellableBookBean s : sellableBooks) {
                String text = s.getCode() + " - " + s;
                Label label = new Label(text);
                label.getStyleClass().add("sellable-book");
                sellableBooksList.getChildren().add(label);
            }
        } catch (SessionException e) {
            throw new RuntimeException(e);
        }
    }
}
