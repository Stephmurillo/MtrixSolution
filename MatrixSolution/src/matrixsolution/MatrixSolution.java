package matrixsolution;

import collection.Casilla;
import collection.List;
import collection.Node;
import collection.SparseMatrix;
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
*
* (c) 2021
* @author Yoselin Rojas, Cinthya Murillo, Sebastián Cabezas
* @version 1.0.0 2021-10-24
*
* -----------------------------------------------
* EIF207 Estructuras de Datos
* 2do ciclo 2021
* Proyecto 1
*
* 207700499 Rojas Fuentes, Yoselin - Grupo 04
* 305260682 Murillo Hidalgo, Cinthya - Grupo 05
* 402260762 Cabezas Madrigal, Sebastián  - Grupo 4
* -----------------------------------------------
 * @param <T>
*
*
*/

public class MatrixSolution {

    private final static String XML_FILE_A = "A.xml";
    private final static String XML_FILE_B = "B.xml";
    private final static String ROW_ATTRIBUTE = "row";
    private final static String COLUMN_ATTRIBUTE = "column";
    private final static String VALUE_ATTRIBUTE = "value";
    
    private static List<Casilla> ListA = null;
    private static List<Casilla> ListB = null;

    public static void main(String[] args) {
        System.out.println("_____MATRIZ A_____");
        ListA = readAttributesXml(XML_FILE_A);
        System.out.println();
        System.out.println("_____MATRIZ B_____");
        ListB = readAttributesXml(XML_FILE_B);

        int canFilas = 10;
        SparseMatrix<Casilla> matrix = new SparseMatrix(canFilas);
       // Node tempNode = fillInfo(0, 0, 3, 3);
       // display(tempNode);
        
        for(int i = 0; i < canFilas; i++){
            matrix.addFila(getListRow(ListA,i));
        }
        // l1.add(tempNode.getInfo());

         System.out.println(matrix.toString());
    }

    private static List<Casilla> readAttributesXml(String path) {
        List<Casilla> tempList = new List();
        File file = readXmlFile(path);
        Document document = null;

        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            document = documentBuilder.parse(file);
        } catch (Exception e) {
            System.err.println(String.format("Error while creating a DocumentBuilder: %s", e.getMessage()));
        }

        NodeList entryNodeList = document.getElementsByTagName("entry");

        if (entryNodeList != null && entryNodeList.getLength() > 0) {
            for (int i = 0; i < entryNodeList.getLength(); i++) {
                Element eRow = (Element) entryNodeList.item(i);
                int row = Integer.parseInt(eRow.getElementsByTagName(ROW_ATTRIBUTE).item(0).getTextContent());

                Element eColumn = (Element) entryNodeList.item(i);
                int column = Integer.parseInt(eColumn.getElementsByTagName(COLUMN_ATTRIBUTE).item(0).getTextContent());

                Element eValue = (Element) entryNodeList.item(i);
                int value = Integer.parseInt(eValue.getElementsByTagName(VALUE_ATTRIBUTE).item(0).getTextContent());

                System.out.println("-- row -- column -- value");
                System.out.println(String.format("-- %s -- %s -- %s", row, column, value));
                Casilla objCasilla = new Casilla(row, column, value);
                tempList.add(objCasilla);
            }
        }
        
        return tempList;
    }

    private static File readXmlFile(String path) {
        File file = null;

        try {
            file = new File(path);
        } catch (Exception e) {
            System.err.println(String.format("Error while reading the XML file: %s", e.getMessage()));
            return null;
        }

        return file;
    }

    private static Node<Integer> fillInfo(int i, int j, int m, int n) {
        if (i > n - 1 || j > m - 1) {
            return null;
        }
        // create a new node for current i and j
        // and recursively allocate its down and
        // right pointers
        Node temp = new Node();
        temp.setInfo(getListInfo(ListA, i, j));
        temp.setRight(fillInfo(i, j + 1, m, n));
        temp.setDown(fillInfo(i + 1, j, m, n));
        return temp;
    }

    private static void display(Node head) {
        // pointer to move right
        Node Rp;

        // pointer to move down
        Node Dp = head;

        // loop till node->down is not NULL
        while (Dp != null) {
            Rp = Dp;

            // loop till node->right is not NULL
            while (Rp != null) {
                System.out.print(Rp.getInfo() + " ");
                Rp = Rp.getRight();
            }
            System.out.println();
            Dp = Dp.getDown();
        }
    }
    
    private static int getListInfo(List<Casilla> list, int row, int column) {
        List<Casilla> matchesRows = new List();
        
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getFila() == row) {
                matchesRows.add(list.get(i));
            }
        }
        
        for (int i = 0; i < matchesRows.size(); i++) {
            if (matchesRows.get(i).getColumna() == column) {
                return matchesRows.get(i).valor;
            }
        }
        return 0;
    }
    
    private static List<Casilla> getListRow(List<Casilla> list, int row) {
        List<Casilla> matchesRows = new List();
        
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getFila() == row) {
                matchesRows.add(list.get(i));
            }
        }
        
       return matchesRows;
    }
}
