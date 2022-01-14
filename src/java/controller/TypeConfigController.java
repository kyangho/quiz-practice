/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;

import model.Setting;
import model.Type;

/**
 *
 * @author ducky
 */
@XmlRootElement(name = "types")
@XmlAccessorType(XmlAccessType.FIELD)

class Types {

    @XmlElement(name = "type")
    private ArrayList<Type> types;

    public Types() {
        types = new ArrayList<Type>();
    }

    public ArrayList<Type> getTypes() {
        return types;
    }

    public void setTypes(ArrayList<Type> types) {
        this.types = types;
    }

}

public class TypeConfigController {

    private static final String FILENAME = "src\\java\\config\\type_config.xml";

    public static ArrayList<Type> getTypesList() {
        ArrayList<Type> typesList = new ArrayList<>();
        try {

            File file = new File(FILENAME);
            JAXBContext jaxbContext = JAXBContext.newInstance(Types.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            Types types = (Types) unmarshaller.unmarshal(file);
            
            for(Type t : types.getTypes()){
                typesList.add(t);
            }
            
        } catch (JAXBException e) {
            return null;
        }
        return typesList;
    }

    public static void updateTypesList(ArrayList<Type> typesList) {

        try {

            File file = new File(FILENAME);
            JAXBContext jaxbContext = JAXBContext.newInstance(Types.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            Types types = new Types();
            types.setTypes(typesList);
            // output pretty printed
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(types, file);

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
//    public static void main(String[] args) {
//        for (Type t : getTypesList()) {
//            System.out.println(t.getName());
//        }
//        Types types = new Types();
//        Type type = new Type(1, "Role");
//        
//        types.getTypes().add(type);
//        updateTypesList(types.getTypes());
//    }
}
