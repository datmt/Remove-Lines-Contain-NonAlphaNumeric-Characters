package sample;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.controlsfx.control.Notifications;

import java.util.regex.Pattern;

public class Controller {

    @FXML
    CheckBox onlyCharactersCB;

    @FXML
    CheckBox spaceIsNotAllowedCB;
    @FXML
    TextField allowedCharsTF;
    @FXML
    TextArea sourceStringTA;
    @FXML
    TextArea resultStringTA;



    @FXML
    public void intitialize()
    {

    }




    public void filterString()
    {
        StringBuilder resultString = new StringBuilder();
        if (hasAlphaNumeric(allowedCharsTF.getText()))
        {
            Notifications.create().title("Not allowed!")
            .text("Allowed characters must NOT contain letters or numbers")
            .showError();
            return;
        }

        for (String x : sourceStringTA.getText().trim().split("\\n"))
        {
            if (hasSpecialChar(x))
                System.out.println("has special char");
            else
                resultString.append(x).append(System.getProperty("line.separator"));
        }

        //append the result to result TA
        resultStringTA.setText(resultString.toString());
    }

    private boolean hasSpecialChar(String s)
    {
        StringBuilder patternString = new StringBuilder();
        if (onlyCharactersCB.isSelected())
        {
            if (spaceIsNotAllowedCB.isSelected())
                patternString.append("^a-zA-Z");
            else
                patternString.append("^a-zA-Z\\s");
        } else
        {
            if (spaceIsNotAllowedCB.isSelected())
                patternString.append("^a-zA-Z0-9");
            else
                patternString.append("^a-zA-Z0-9\\s");
        }

        String[] allowedChars = allowedCharsTF.getText().trim().split("\\s");

        System.out.println(allowedChars.length);
        if (allowedChars.length > 0)
        {
            for (String a : allowedChars)
            {
                if (a == null || a.equals(""))
                    continue;
                patternString.append("\\\\").append(a.trim());
            }
        }


        Pattern p = Pattern.compile("[" + patternString.toString() + "]");
        return p.matcher(s).find();

    }

    private boolean hasAlphaNumeric(String s)
    {
        Pattern p = Pattern.compile("[a-zA-Z0-9]");
        return p.matcher(s).find();
    }


}
