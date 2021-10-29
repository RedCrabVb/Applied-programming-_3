package ru.vivt;

import com.sun.javafx.image.IntPixelGetter;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.net.URL;
import java.util.*;

public class MainController implements Initializable {
    @FXML
    public TextField group, numberStudent, numberObject;
    @FXML
    public TextArea output;
    @FXML
    public Button fillOutput, load;
    DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
    TransformerFactory transformerFactory = TransformerFactory.newInstance();
    DocumentBuilder docBuilder;
    Transformer transformer;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            docBuilder = docFactory.newDocumentBuilder();
            transformer = transformerFactory.newTransformer();

            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
        } catch (ParserConfigurationException | TransformerConfigurationException e) {
            e.printStackTrace();
        }


        fillOutput.setOnAction(e -> {

            String group = this.group.getText();
            String numberStudent = this.numberStudent.getText();
            String numberObject = this.numberObject.getText();

            //root elements
            Document doc = docBuilder.newDocument();

            Element rootElement = doc.createElement("students");
            doc.appendChild(rootElement);


            for (int id = 1; id < Integer.parseInt(numberStudent); id++) {
                var ball = new Random().ints(2, 6).limit(Integer.parseInt(numberObject)).toArray();
                //student elements
                Element student = doc.createElement("student");
                rootElement.appendChild(student);

                //set attribute to student element
                Attr attr = doc.createAttribute("id");
                attr.setValue(String.valueOf(id));
                student.setAttributeNode(attr);

                //group elements
                Element groupElement = doc.createElement("group");
                groupElement.appendChild(doc.createTextNode(group));
                student.appendChild(groupElement);

                //ball elements
                Element ballElement = doc.createElement("ball");
                ballElement.appendChild(doc.createTextNode(ballToString(ball)));
                student.appendChild(ballElement);
            }


            DOMSource source = new DOMSource(doc);

            StreamResult result = new StreamResult(new StringWriter());
            try {
                transformer.transform(source, result);
            } catch (TransformerException ex) {
                ex.printStackTrace();
            }
            output.setText(result.getWriter().toString());
        });

        load.setOnAction(e -> {
            try {
                FileWriter fileWriter = new FileWriter("XMLFile.xml");
                fileWriter.write(output.getText());
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }

    private String ballToString(int[] ball) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < ball.length - 1; i++) {
            stringBuilder.append(String.format("%s ", ball[i]));
        }
        stringBuilder.append(String.format("%s", ball[ball.length - 1]));
        return stringBuilder.toString();
    }

}
