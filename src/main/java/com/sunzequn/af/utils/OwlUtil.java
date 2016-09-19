package com.sunzequn.af.utils;

import com.sunzequn.af.common.Conf;
import org.apache.jena.rdf.model.*;

import java.io.FileInputStream;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by sloriac on 16-9-13.
 */
public class OwlUtil {

    private static final String ROOT_URI = "http://www.w3.org/2002/07/owl#Thing";
    private static final String SUB_OF = "http://www.w3.org/2000/01/rdf-schema#subClassOf";
    private String file;
    private Model model = ModelFactory.createDefaultModel();
    private Property subOf;

    public OwlUtil(String file) throws Exception {
        this.file = file;
        model.read(new FileInputStream(file), "", "NT");
        subOf = model.getProperty(SUB_OF);
    }

    public Resource getSuperClass(String uri) {
        Resource resource = model.getResource(uri);
        return resource == null ? null : resource.getPropertyResourceValue(subOf);
    }

    public Resource getSuperestClass(String uri) {
        Resource sub = getSuperClass(uri);
        if (sub.getURI().equals(ROOT_URI)) {
            return model.getResource(uri);
        } else {
            return getSuperestClass(sub.getURI());
        }
    }

    public Set<String> listDirectLeaves(String uri) {
        StmtIterator stmtIterator = model.listStatements();
        Set<String> res = new HashSet<>();
        while (stmtIterator.hasNext()) {
            Statement stmt = stmtIterator.nextStatement();
            Resource subject = stmt.getSubject();
            Property property = stmt.getPredicate();
            RDFNode object = stmt.getObject();
            if (property.getURI().equals(SUB_OF) && object.toString().equals(uri)) {
                res.add(subject.getURI());
            }
        }
        return res.size() == 0 ? null : res;
    }

    public Set<String> listTopClasses() {
        Set<String> leaves = listDirectLeaves(ROOT_URI);
        leaves.stream().filter(leaf -> !getSuperestClass(leaf).getURI().equals(leaf)).forEach(leaves::remove);
        return leaves;
    }

    public Set<String> listAllLeaves(String uri) {
        Set<String> leaves = listDirectLeaves(uri);
        if (leaves == null) {
            return null;
        }
        Set<String> temp = new HashSet<>();
        for (String leaf : leaves) {
            Set<String> ls = listAllLeaves(leaf);
            if (ls != null) {
                temp.addAll(ls);
            }
        }
        leaves.addAll(temp);
        return leaves.size() == 0 ? null : leaves;
    }

    public static void main(String[] args) throws Exception {
        OwlUtil owlUtil = new OwlUtil(Conf.DBPEDIA_ONTOLOGY);
        String uri = "http://dbpedia.org/ontology/Organisation";
//        System.out.println(owlUtil.getSuperestClass(uri).getURI());
//        System.out.println(owlUtil.getSuperestClass("http://dbpedia.org/ontology/EducationalInstitution"));
//        System.out.println(owlUtil.listDirectLeaves(ROOT_URI));
//        System.out.println(owlUtil.listTopClasses());
        System.out.println(owlUtil.listAllLeaves(uri).size());
    }
}
