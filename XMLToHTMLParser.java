import java.io.File;
import java.io.FileWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLToHTMLParser {
    public static void main(String[] args) {
        try {
            // Load the XML file
            File inputFile = new File("details.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);

            // Normalize the XML structure
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName("details");
            String[][] tableData = new String[nodeList.getLength()][4];

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    // Create table headers
                    Element bookingElement = (Element) nodeList.item(i);
                    String name = bookingElement.getElementsByTagName("fullName").item(0).getTextContent();
                    String year = bookingElement.getElementsByTagName("year").item(0).getTextContent();
                    String branch = bookingElement.getElementsByTagName("branch").item(0).getTextContent();
                    String college = bookingElement.getElementsByTagName("college").item(0).getTextContent();
                    // String classType = bookingElement.getElementsByTagName("classofseat").item(0).getTextContent();
                    tableData[i][0] = name;
                    tableData[i][1] = year;
                    tableData[i][2] = branch;
                    tableData[i][3] = college;
                    // +tableData[i][4] = classType;
                }
            }
            generateHTMLTable(tableData,"table.html");
            System.out.println("HTML table generated successfully.");
            

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void generateHTMLTable(String[][] tableData, String outputFilePath) 
    {
       try 
        {
            
            FileWriter writer = new FileWriter(outputFilePath);

            writer.write("<html><body>");
            writer.write("<table border=\"1\">");

            // Generate table header
            writer.write("<tr>");
            writer.write("<th>UserName</th>");
            writer.write("<th>Year</th>");
            writer.write("<th>Branch</th>");
            writer.write("<th>College</th>");
            // writer.write("<th>classType</th>");
            writer.write("</tr>");

            
            // Generate table rows
            for (String[] row : tableData) 
            {
                writer.write("<tr>");
                for (String cell : row) 
                {
                    writer.write("<td>" + cell + "</td>");
                }
                writer.write("</tr>");
            }

            writer.write("</table>");
            writer.write("</body></html>");

            writer.close();
        } catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
}
