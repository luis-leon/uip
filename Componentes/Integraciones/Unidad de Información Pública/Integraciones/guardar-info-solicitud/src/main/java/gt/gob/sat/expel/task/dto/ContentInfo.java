/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.gob.sat.expel.task.dto;

/**
 *
 * @author amolopezo
 */
public class ContentInfo {
    private String mimeType;
    private String mimeTypeName;
    private Integer sizeinBytes;
    private String encoding;

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public String getMimeTypeName() {
        return mimeTypeName;
    }

    public void setMimeTypeName(String mimeTypeName) {
        this.mimeTypeName = mimeTypeName;
    }

    public Integer getSizeinBytes() {
        return sizeinBytes;
    }

    public void setSizeinBytes(Integer sizeinBytes) {
        this.sizeinBytes = sizeinBytes;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }
    
    
}
