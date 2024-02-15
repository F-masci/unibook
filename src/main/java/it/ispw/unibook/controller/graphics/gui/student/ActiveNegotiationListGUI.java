package it.ispw.unibook.controller.graphics.gui.student;

import it.ispw.unibook.bean.SellableBookBean;
import it.ispw.unibook.bean.SellableBooksListBean;
import it.ispw.unibook.exceptions.login.SessionException;
import it.ispw.unibook.utils.Printer;
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
            // Viene istanziato il bean che conterrà la lista dei libri in vendita
            SellableBooksListBean bean = new SellableBooksListBean();
            // Viene caricata la lista dei libri in vendita
            super.retrieveSellableBooksByActiveNegotiationOfSession(bean);
            // Viene estratta dal bean la lista dei libri in vendita
            List<SellableBookBean> sellableBooks = bean.getList();

            // Viene mostrata la lista libri in vendita
            for (SellableBookBean s : sellableBooks) {
                String text = s.getCode() + " - " + s + " - " + s.getEmailSeller();
                Label label = new Label(text);
                label.getStyleClass().add("sellable-book");
                sellableBooksList.getChildren().add(label);
            }
        } catch (SessionException e) {
            Printer.error(e);
            System.exit(-1);
        }
    }
}
