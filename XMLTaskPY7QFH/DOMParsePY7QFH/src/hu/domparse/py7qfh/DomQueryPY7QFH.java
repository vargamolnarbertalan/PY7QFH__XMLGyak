package hu.domparse.py7qfh;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import org.xml.sax.SAXException;

public class DomQueryPY7QFH {

  public static void main(String[] args) {
    try {
      DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

      Document document = documentBuilder.parse("XMLPY7QFH.xml");

      document.getDocumentElement().normalize();

      XPath xPath = XPathFactory.newInstance().newXPath();
      //Lekerdezesek
      String expression = "";
      //expression = "/gepjarmu/tulajdonos";
      //expression = "/gepjarmu/gyartas[@gyartasID='10B']";
      //expression = "/gepjarmu/motor[last()]";
      //expression = "/gepjarmu/karosszeria[szin='fehér']";
      expression = "/gepjarmu/gyartas[gyartasi_ev>2008]";
      NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(document, XPathConstants.NODESET);

      for (int i = 0; i < nodeList.getLength(); i++) {
        Node node = nodeList.item(i);
        System.out.println("\nAktuális elem: " + node.getNodeName());

        if (node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals("szemelygepjarmu")) {
          Element element = (Element) node;

          System.out.println("Rendszám: " + element.getAttribute("rendszam"));
          System.out.println("Tulaj jogsi: " + element.getElementsByTagName("tulaj_jogsi").item(0).getTextContent());
          System.out.println("Alváz száma: " + element.getElementsByTagName("alvaz_szama").item(0).getTextContent());
          System.out.println("Motor száma: " + element.getElementsByTagName("motor_szama").item(0).getTextContent());
          System.out.println("Gyártási szám: " + element.getElementsByTagName("gyartas_sorszam").item(0).getTextContent());
        }

        if (node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals("tulajdonos")) {
          Element element = (Element) node;

          System.out.println("Jogositvány száma: " + element.getAttribute("jogositvany_szama"));
          System.out.println("Keresztnév: " + element.getElementsByTagName("keresztnev").item(0).getTextContent());
          System.out.println("Vezetéknév: " + element.getElementsByTagName("vezeteknev").item(0).getTextContent());
        }

        if (node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals("motor")) {
          Element element = (Element) node;

          System.out.println("Motor száma: " + element.getAttribute("motorszam"));
          System.out.println("Üzemanyag: " + element.getElementsByTagName("uzemanyag").item(0).getTextContent());
          System.out.println("Lóerő: " + element.getElementsByTagName("loero").item(0).getTextContent());
          System.out.println("Nyomaték: " + element.getElementsByTagName("nyomatek").item(0).getTextContent());
        }

        if (node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals("karosszeria")) {
          Element element = (Element) node;

          System.out.println("Alvázszám: " + element.getAttribute("alvazszam"));
          System.out.println("Felület: " + element.getElementsByTagName("felulet").item(0).getTextContent());
          System.out.println("Matrica: " + element.getElementsByTagName("matrica").item(0).getTextContent());
          System.out.println("Elsődleges szin: " + element.getElementsByTagName("szin").item(0).getTextContent());
        }
        if (node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals("gyartas")) {
          Element element = (Element) node;

          System.out.println("ID: " + element.getAttribute("gyartasID"));
          System.out.println("Márka: " + element.getElementsByTagName("marka").item(0).getTextContent());
          System.out.println("Modell: " + element.getElementsByTagName("modell").item(0).getTextContent());
          System.out.println("Gyártási év: " + element.getElementsByTagName("gyartasi_ev").item(0).getTextContent());
        }
      }

    } catch (ParserConfigurationException e) {
      e.printStackTrace();
    } catch (SAXException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (XPathExpressionException e) {
      e.printStackTrace();
    }
  }
}