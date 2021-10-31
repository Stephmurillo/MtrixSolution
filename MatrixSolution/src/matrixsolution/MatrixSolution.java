package matrixsolution;

import collection.BoxItem;
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
* @author Yoselin Rojas, Cinthya Murillo
* @version 1.0.0 2021-10-24
*
* -----------------------------------------------
* EIF207 Estructuras de Datos
* 2do ciclo 2021
* Proyecto 1
*
* 207700499 Rojas Fuentes, Yoselin - Grupo 04
* 305260682 Murillo Hidalgo, Cinthya - Grupo 05
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
    
    private static SparseMatrix<BoxItem> SparseMatrixA = null;
    private static SparseMatrix<BoxItem> SparseMatrixAEqual = null;
    private static SparseMatrix<BoxItem> SparseMatrixB = null;

    public static void main(String[] args) {
        //Reading XML files 
        System.out.println("_____MATRIZ A_____");
        SparseMatrixA = readAttributesXml(XML_FILE_A);
        SparseMatrixAEqual = readAttributesXml(XML_FILE_A);
        System.out.println();
        System.out.println("_____MATRIZ B_____");
        SparseMatrixB = readAttributesXml(XML_FILE_B);
        System.out.println(SparseMatrixA.toString());
        System.out.println();

        //Calcula la transpuesta de una matriz
        System.out.println("TransposeMatrix{ \n" + SparseMatrixA.transpose().toString());
        
        //Evalua si dos matrices son iguales
        System.out.println("Equals?{ \n" + SparseMatrixA.Equals(SparseMatrixAEqual));
        System.out.println("Equals?{ \n" + SparseMatrixA.Equals(SparseMatrixB));
        
        SparseMatrix<BoxItem> matrix = new SparseMatrix<BoxItem>(10, 5);
        System.out.println("\n\n" + matrix.toString());
        
        BoxItem val = new BoxItem(0, 0 ,1);
        matrix.setT(0, 0, val);
        matrix.setT(1, 1, val);
        matrix.setT(2, 2, val);
        matrix.setT(2, 2, val);
        matrix.setT(3, 3, val);
        matrix.setT(4, 4, val);
        System.out.println(matrix.toString());
        
        //Get the value of a specific position
        System.out.println("\nPosition[1, 1]: " + matrix.getT(1, 1));
        System.out.println("Position[3, 7]: " + matrix.getT(3, 7));
        System.out.println("Position[4, 4]: " + matrix.getT(4, 4));
        
        //Create transpose matrix
        System.out.println("\nTransposeMatrix{ \n" + matrix.transpose().toString());
        
        //Create splice matrix
        System.out.println("SpliceMatrix [f1=[1] | f2=[6] | c1=[2] | c2=[3]{\n"+ 
                matrix.splice(1, 6, 2, 3).toString());
        
        //They are equals?
        System.out.println("Equals? [Matrix1 & SparseMatrixA]{ \n" + matrix.Equals(SparseMatrixA));
        System.out.println("Equals? [Matrix & Matrix]{ \n" + matrix.Equals(matrix));
        
        SparseMatrix<BoxItem> matrixA = new SparseMatrix<BoxItem>(2, 2);
        matrixA.setT(0, 0, val);
        matrixA.setT(0, 1, val);
        matrixA.setT(1, 0, val);
        matrixA.setT(1, 1, val);
        System.out.println("\nMatrixA: \n" + matrixA.toString());
        
        SparseMatrix<BoxItem> matrixB = new SparseMatrix<BoxItem>(2, 2);
        matrixB.setT(0, 0, val);
        matrixB.setT(0, 1, val);
        matrixB.setT(1, 0, val);
        matrixB.setT(1, 1, val);
        System.out.println("\nMatrixB: \n" + matrixB.toString());
        
        //Sum matrices
        matrixA.add(matrixB);
        System.out.println("\nMatrixA + MatrixB: \n" + matrixA.add(matrixB).toString());
    }

    
    private static SparseMatrix<BoxItem> readAttributesXml(String path) {
        BoxItem zero = new BoxItem(0, 0 ,0);
        SparseMatrix<BoxItem> loadMatrix = new SparseMatrix<>(5, 5, zero); // crea una matriz donde se van a almacenar los datos del archivo
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
        int row = 0;
        int column  = 0;
        int value = 0;
        BoxItem objBox = null;
        if (entryNodeList != null && entryNodeList.getLength() > 0) {
            for (int i = 0; i < entryNodeList.getLength(); i++) {
                Element eRow = (Element) entryNodeList.item(i);
                row = Integer.parseInt(eRow.getElementsByTagName(ROW_ATTRIBUTE).item(0).getTextContent());//extrae el valor de fila

                Element eColumn = (Element) entryNodeList.item(i);
                column = Integer.parseInt(eColumn.getElementsByTagName(COLUMN_ATTRIBUTE).item(0).getTextContent());//extrae el valor de columna

                Element eValue = (Element) entryNodeList.item(i);
                value = Integer.parseInt(eValue.getElementsByTagName(VALUE_ATTRIBUTE).item(0).getTextContent());//extrae el valor del objeto

               objBox = new BoxItem(row, column, value);
               loadMatrix.setT(row, column, objBox);//como la matriz esta conformada por 0, cambia los valores segun los elementos que lee del archivo
            }
        }
        System.out.println("--all was read--");
        return loadMatrix;
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
}
