package com.reforgan.reforgan.domain;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Node {

    private String nodeId; // node id
    private String pid; // parent id
    private String text;
    //private Person person;
    private String href;

//    public Node(String nodeId, String pId, String text, Person person, String href) {
//        this.nodeId = nodeId;
//        this.pid = pId;
//        this.text = text;
//        this.person = person;
//        this.href = href;
//    }
}
