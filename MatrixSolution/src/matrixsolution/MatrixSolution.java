package matrixsolution;

import collection.BoxItem;
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
    
    private static List<BoxItem> ListA = null;
    private static List<BoxItem> ListB = null;

    public static void main(String[] args) {
        //Reading XML files 
        System.out.println("_____MATRIZ A_____");
        ListA = readAttributesXml(XML_FILE_A);
        System.out.println();
        System.out.println("_____MATRIZ B_____");
        ListB = readAttributesXml(XML_FILE_B);
        System.out.println();

        //Test values for a matrix
        int canRows = 10, canColumns = 5;
        
        //Create and fill a sparse matrix with XML readed objects
        SparseMatrix<BoxItem> matrix = new SparseMatrix(canRows, canColumns);
        for(int i = 0; i < matrix.getNumRows(); i++){
            matrix.addRow(getListRow(ListA, i));
        }
        for(int i = 0; i < matrix.getNumColumns(); i++){
            matrix.addColumn(getListColumn(ListA, i));
        }
        System.out.println(matrix.toString());
        
        //Get the value of a position
        System.out.println("\nValor / Pos[1, 0]: " + matrix.getBoxItem(1, 0) + "\n");
        System.out.println("\nValor2 / Pos[1, 1]: " + matrix.getBoxItem(1, 1) + "\n");
        
        //Create and fill a sparse matrix with specific objects
        Integer zero = 0;
        SparseMatrix<BoxItem> matrixT = new SparseMatrix(canRows, canColumns, zero);
        System.out.println(matrixT.toString());
        
        //Create transpose matrixT
        System.out.println("TransposeMatrix{ \n" + matrixT.transpose().toString());
    }

    
    private static List<BoxItem> readAttributesXml(String path) {
        List<BoxItem> tempList = new List();
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

              //  System.out.println("-- row -- column -- value");
               // System.out.println(String.format("-- %s -- %s -- %s", row, column, value));
                BoxItem objCasilla = new BoxItem(row, column, value);
                tempList.add(objCasilla);
            }
        }
        System.out.println("--all was read--");
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
    
    private static List<BoxItem> getListRow(List<BoxItem> list, int row) {
        List<BoxItem> matchesRows = new List();
        Node<BoxItem> auxNode = list.getFirst();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getRow() == row) {
                matchesRows.add(auxNode.getInfo());
            }
            auxNode = auxNode.getRight();
        }
        
       return matchesRows;
    }


    private static List<BoxItem> getListColumn(List<BoxItem> list, int column) {
            List<BoxItem> matchesRows = new List();
            Node<BoxItem> auxNode = list.getFirst();
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getColumn() == column) {
                    matchesRows.add(auxNode.getInfo());
                }
                auxNode = auxNode.getRight();
            }

           return matchesRows;
    }
}
