/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.gob.sat.expel.task.dto;

import java.util.ArrayList;

/**
 *
 * @author amolopezo
 */
public class SharedLink {
    private String id;
    private String expiresAt;
    private String nodeId;
    private String name;
    private String title;
    private String description;
    private String modifiedAt;
    private UserInfo modifiedByUser;
    private UserInfo sharedByUser ;
    private ContentInfo content;
    ArrayList <String> allowableOperations = new ArrayList<>();
    ArrayList <String> allowableOperationsOnTarget = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(String expiresAt) {
        this.expiresAt = expiresAt;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(String modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public UserInfo getModifiedByUser() {
        return modifiedByUser;
    }

    public void setModifiedByUser(UserInfo modifiedByUser) {
        this.modifiedByUser = modifiedByUser;
    }

    public UserInfo getSharedByUser() {
        return sharedByUser;
    }

    public void setSharedByUser(UserInfo sharedByUser) {
        this.sharedByUser = sharedByUser;
    }

    public ContentInfo getContent() {
        return content;
    }

    public void setContent(ContentInfo content) {
        this.content = content;
    }

    public ArrayList<String> getAllowableOperations() {
        return allowableOperations;
    }

    public void setAllowableOperations(ArrayList<String> allowableOperations) {
        this.allowableOperations = allowableOperations;
    }

    public ArrayList<String> getAllowableOperationsOnTarget() {
        return allowableOperationsOnTarget;
    }

    public void setAllowableOperationsOnTarget(ArrayList<String> allowableOperationsOnTarget) {
        this.allowableOperationsOnTarget = allowableOperationsOnTarget;
    }
    
    
}
