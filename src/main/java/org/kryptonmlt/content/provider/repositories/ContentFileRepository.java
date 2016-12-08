package org.kryptonmlt.content.provider.repositories;

import java.io.File;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.kryptonmlt.content.provider.pojos.Content;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author kurt
 */
@Component
public class ContentFileRepository {

    private final Map<String, List<Content>> contents = new HashMap<>();

    private final SimpleDateFormat parser = new SimpleDateFormat("dd/MM/yyyy zzz");

    private final String RESOURCE_FILE = "contents.xml";

    public ContentFileRepository() throws Exception {
        File fXmlFile = new File(RESOURCE_FILE);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(fXmlFile);

        //optional, but recommended
        //read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
        doc.getDocumentElement().normalize();
        NodeList nList = doc.getElementsByTagName("content");

        for (int temp = 0; temp < nList.getLength(); temp++) {

            Node nNode = nList.item(temp);

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                Element eElement = (Element) nNode;

                //eElement.getAttribute("id");
                String type = eElement.getElementsByTagName("type").item(0).getTextContent();
                Date date = parser.parse(eElement.getElementsByTagName("date").item(0).getTextContent());
                if (contents.get(type) == null) {
                    contents.put(type, new ArrayList<Content>());
                }
                contents.get(type).add(new Content(eElement.getElementsByTagName("title").item(0).getTextContent(), date.getTime(),
                        nodeToString(eElement.getElementsByTagName("html").item(0), "html")));

            }
        }
        for (String type : contents.keySet()) {
            Collections.sort(contents.get(type));
        }
    }

    public static String nodeToString(Node node, String outerRemoval) throws TransformerException {
        Transformer t = TransformerFactory.newInstance().newTransformer();
        t.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        t.setOutputProperty(OutputKeys.INDENT, "yes");
        StringWriter sw = new StringWriter();
        t.transform(new DOMSource(node), new StreamResult(sw));
        String temp = sw.toString();
        temp = temp.replace("<" + outerRemoval + ">", "");
        temp = temp.replace("</" + outerRemoval + ">", "");
        return temp.trim();
    }

    public List<Content> getContents(String type) {
        if (contents.get(type) == null) {
            return new ArrayList<>();
        }
        return contents.get(type);
    }

    public Set<String> getTypes() {
        return contents.keySet();
    }

}
