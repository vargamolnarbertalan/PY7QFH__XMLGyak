package hu.domparse.py7qfh;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DomModifyPY7QFH {

    public static void main(String argv[]) throws ParserConfigurationException, SAXException, IOException, TransformerException {

        File inputFile = new File("XMLPY7QFH.xml");

        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

        Document doc = documentBuilder.parse(inputFile);
        doc.getDocumentElement().normalize();

        // második személygépjármű attribútumának módosítása
        Node jarmu = doc.getElementsByTagName("szemelygepjarmu").item(1);

        NamedNodeMap attr = jarmu.getAttributes();
        Node nodeAttr = attr.getNamedItem("rendszam");
        nodeAttr.setTextContent("CFN-213");
        System.out.println("szemelygepjarmu attributum modositva");

        // első tulajdonos attribútumának módosítása
        Node tulaj = doc.getElementsByTagName("tulajdonos").item(0);

        attr = tulaj.getAttributes();
        nodeAttr = attr.getNamedItem("jogositvany_szama");
        nodeAttr.setTextContent("FR346544");

        System.out.println("tulajdonos attributum modositva");

        // összes matrica beállitása van-ra

        NodeList nodes = doc.getElementsByTagName("karosszeria");

        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                NodeList childNodes = node.getChildNodes();

                for (int j = 0; j < childNodes.getLength(); j++) {
                    Node childNode = childNodes.item(j);
                    if (childNode.getNodeName().equals("matrica")) {
                        childNode.setTextContent("van");
                    }
                }
            }
        }
        System.out.println("matrica modositasok kesz");

        // összes benzines motor átállitása hibridre

        nodes = doc.getElementsByTagName("motor");

        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                NodeList childNodes = node.getChildNodes();

                for (int j = 0; j < childNodes.getLength(); j++) {
                    Node childNode = childNodes.item(j);
                    if (childNode.getNodeName().equals("uzemanyag") && childNode.getTextContent().equals("benzin")) {
                        childNode.setTextContent("hibrid");
                    }
                }
            }
        }
        System.out.println("benzin motorok atallitva hibridre");

        // gyartasi_ev element atnevezese evjaratra

        nodes = doc.getElementsByTagName("gyartasi_ev");
        for (int i = 0; i < nodes.getLength(); i++) {
            doc.renameNode(nodes.item(i), null, "evjarat");
        }
        System.out.println("gyartasi_ev atnevezeve evjaratra");

        // modositott xml dokumentum elmentese 
        writeXml(doc, new File("XMLPY7QFH2.xml"));

    }

    private static void writeXml(Document doc, File output) throws TransformerException {
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        DOMSource source = new DOMSource(doc);

        StreamResult file = new StreamResult(output);

        transformer.transform(source, file);
    }

}