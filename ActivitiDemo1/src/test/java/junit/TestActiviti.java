package junit;

import org.activiti.engine.ProcessEngine;  
import org.activiti.engine.ProcessEngineConfiguration;  
import org.junit.Test; 

public class TestActiviti {
	 /** 
     * 生成25张Activiti表 
     */  
    @Test  
    public void testCreateTable() {  
        // 引擎配置  
        ProcessEngineConfiguration pec=ProcessEngineConfiguration.createStandaloneProcessEngineConfiguration();  
        pec.setJdbcDriver("com.mysql.jdbc.Driver");
        //pec.setJdbcUrl("jdbc:mysql://localhost:3306/changwenDB"); 
        pec.setJdbcUrl("jdbc:mysql://localhost:3306/changwenDB");  
        pec.setJdbcUsername("root");  
        pec.setJdbcPassword("123456");  
 //         pec.setJdbcDriver("oracle.jdbc.OracleDriver");  
 //         pec.setJdbcUrl("jdbc:oracle:thin:@localhost:1522:orcl2");  
 //         pec.setJdbcUsername("activiti");  
 //         pec.setJdbcPassword("activiti");  
           
        /** 
         * false 不能自动创建表 
         * create-drop 先删除表再创建表 
         * true 自动创建和更新表   
         */  
        pec.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);  
           
        // 获取流程引擎对象  
        ProcessEngine processEngine=pec.buildProcessEngine();  
    }  
    
    /** 
     * 使用xml配置 简化 
     */  
    @Test  
    public void testCreateTableWithXml(){  
        // 引擎配置  
        ProcessEngineConfiguration pec=ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml");  
        // 获取流程引擎对象  
        ProcessEngine processEngine=pec.buildProcessEngine();  
    }
}
