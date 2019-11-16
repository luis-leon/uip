/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.gob.sat.expel.task.service;

/**
 *
 * @author amolopezo
 */

import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

public class FolioServiceTask 
    implements JavaDelegate
{

    public void execute(DelegateExecution execution){
        Calendar now = Calendar.getInstance();
        SimpleDateFormat folioFormat = new SimpleDateFormat("yyMMddHHmmss");
        
        String folio = folioFormat.format(now.getTime());
        execution.setVariable("folio", folio);
    }
}
