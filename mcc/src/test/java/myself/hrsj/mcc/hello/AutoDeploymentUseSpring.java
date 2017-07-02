package myself.hrsj.mcc.hello;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.ProcessDefinition;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class AutoDeploymentUseSpring {

    @Autowired
    RepositoryService repositoryService;

    @Test
    public void testAutoDeployment() {
        long count = repositoryService.createProcessDefinitionQuery().count();
        assertEquals(1, count);
        
        List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery().list();
        for (ProcessDefinition processDefinition : list) {
            if ("SayHelloToLeave".equals(processDefinition.getKey())) {
                repositoryService.deleteDeployment(processDefinition.getDeploymentId(), true);
            }
        }
    }

}
