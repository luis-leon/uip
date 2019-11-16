/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.gob.sat.expel.task.delegate;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

/**
 *
 * @author amolopezo
 */
public class FechaTask 
        implements JavaDelegate
{
    public void execute(DelegateExecution execution){
        Calendar now = Calendar.getInstance();
        SimpleDateFormat folioFormat = new SimpleDateFormat("dd-MM-yyyy");
        
        String fecha = folioFormat.format(now.getTime());
        execution.setVariable("fecha", fecha);
    }
}
    
