package gt.gob.sat.expel.task.dto;

import gt.gob.sat.expel.task.dto.Data;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
/**
 *
 * @author alfdeleon
 */
public class DatosApi{
    
    private Long size;
    private Long total;
    private Long start;
    public List<Data> data = new ArrayList<>();

    public DatosApi(){
    }

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Long getStart() {
        return start;
    }

    public void setStart(Long start) {
        this.start = start;
    }


}

