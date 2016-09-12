package com.changwen.activiti.hellworld;

import java.util.List;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;

public class HelloWorld {
	/** 
	 * 第一步，我们要操作流程，必须获取流程引擎实例；
	 * 获取默认的流程引擎实例 会自动读取activiti.cfg.xml文件  
	 */ 
	 ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
	
	/** 
	 * 第二步，我们需要把前面我们绘制的流程定义图
	 * 部署流程定义 
	 */  
	@Test  
	public void deploy(){  
	    // 获取部署对象  
	    Deployment deployment=processEngine.getRepositoryService() // 部署Service  
	                 .createDeployment()  // 创建部署  
	                 .addClasspathResource("diagrams/helloWorld.bpmn")  // 加载资源文件  
	                 .addClasspathResource("diagrams/helloWorld.png")   // 加载资源文件  
	                 .name("HelloWorld流程")  // 流程名称  
	                 .deploy(); // 部署  
	    System.out.println("流程部署ID:"+deployment.getId());  
	    System.out.println("流程部署Name:"+deployment.getName());  
	}
	
    /** 
     * 第三步：我们要启动流程实例
     * 启动流程实例 
     */  
    @Test  
    public void start(){  
        // 启动并获取流程实例  
        ProcessInstance processInstance=processEngine.getRuntimeService() // 运行时流程实例Service  
            .startProcessInstanceByKey("helloworld"); // 流程定义表的KEY字段值，process id的名字  
        System.out.println("流程实例ID:-->"+processInstance.getId());  
        System.out.println("流程定义ID:-->"+processInstance.getProcessDefinitionId());  
    }  
    
    /**
     * 第四步：启动流程后，我们流程会走到helloWorld节点
     * 查看任务
     */
    @Test
    public void findTask(){
        // 查询并且返回任务即可
        List<Task> taskList=processEngine.getTaskService() // 任务相关Service
                .createTaskQuery()  // 创建任务查询
                .taskAssignee("李四") // 指定某个人
                .list(); 
        
        if (taskList != null && taskList.size()>0) {
            for(Task task:taskList){
                System.out.println("任务ID:"+task.getId());
                System.out.println("任务名称："+task.getName());
                System.out.println("任务创建时间："+task.getCreateTime());
                System.out.println("任务委派人："+task.getAssignee());
                System.out.println("流程实例ID:"+task.getProcessInstanceId());
            }
		}

    }
    
   

    /**
     *  第五步：我们来完成helloWorld节点任务，让流程走完；
     * 完成任务
     */
    @Test
    public void completeTask(){
        processEngine.getTaskService() // 任务相关Service
                .complete("104"); // 指定要完成的任务ID
    }
    
}
