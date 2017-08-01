/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import edu.mum.dao.DaoLayer;
import edu.mum.dao.DaoLayerImpl;
import edu.mum.domain.Asset;
import edu.mum.domain.Beneficiary;
import edu.mum.domain.Project;
import edu.mum.domain.Location;
import edu.mum.domain.Status;
import edu.mum.domain.Task;
import edu.mum.domain.Volunteer;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.List;
import org.junit.Test;

/**
 *
 * @author gilberto
 */
public class Test_Register {//...............................Uncomment @Test to run tests

    DaoLayer dao = new DaoLayerImpl();
    
    
    @Test
    public void createProject() {
        Project project = new Project(  "Project 1", "Project 1 used for testing persistence", 
                                        new Location("Fairfield", "Iowa", "1000 N 4TH Street"), 
                                        new GregorianCalendar(2017, (9-1), 01).getTime(), new GregorianCalendar(2018, (2-1), 01).getTime());

        project.addBeneficiary(new Beneficiary(project, "Ahmed Jak", "Beneficiary 1", new Location("Fairfield", "Iowa", "1000 N 4TH Street")));
        project.addBeneficiary(new Beneficiary(project, "John Masa", "Beneficiary 2", new Location("Fairfield", "Iowa", "1000 N 4TH Street")));
        dao.saveRecord(project);
    }
    
//    @Test
    public void addTask() {
        Project project = dao.loadEntityObject(Project.class, 1);
        
        Task task1 = new Task("Task 1", project, Status.STARTED, new GregorianCalendar(2017, (9-1), 01).getTime(), new GregorianCalendar(2018, (2-1), 01).getTime());
        Task task2 = new Task("Task 2", project, Status.STARTED, new GregorianCalendar(2017, (5-1), 21).getTime(), new GregorianCalendar(2017, (12-1), 01).getTime());
        Task task3 = new Task("Task 3", project, Status.STARTED, new GregorianCalendar(2017, (11-1), 03).getTime(), new GregorianCalendar(2018, (1-1), 01).getTime());
        
        //Add a resource to a task
        task1.addResource(new Asset(task1, "Category 1", 3, "Asset 1", "The first asset added"));
        
        //Offer service on task
        task1.addResource(new Volunteer(task1, Arrays.asList("Skill 1", "Skill 2"), 10, "Gilbert Ndenzi", "Will partipate in service 1"));
        dao.saveRecord(task1);
        dao.saveRecord(task2);
        dao.saveRecord(task3);
    }

//    @Test
    public void removeTask() {
        dao.deleteRecord(Task.class, "taskid", 3);
    }

//    @Test
    public void updateTask() {
        dao.updateRecord(Task.class, new String[]{"taskname"}, new Object[]{"Task 1 updated"}, "taskid", 2);
    }

//    @Test
    public void listProjectAndBeneficiaries() {        
        List<Project> list = (List<Project>)dao.fetchRecord(Project.class, new String[]{"r"}, "JOIN FETCH r.beneficiaries b", new String[]{}, new Object[]{});
        list.stream().forEach((list1) -> {
            list1.getBeneficiaries().forEach((x) -> System.out.println(x));
        });        
    }

//    @Test
    public void listProjectTasks() {
        List<Project> list = (List<Project>)dao.fetchRecord(Project.class, new String[]{}, "JOIN FETCH r.tasks b", new String[]{}, new Object[]{});
        list.stream().map((list1) -> {
            System.out.println(list1);
            return list1;
        }).forEach((list1) -> {
            list1.getTasks().forEach((x) -> System.out.println(x));
        });
    }

//    @Test
    public void listProjectByStatus() {
        List<Project> list = (List<Project>)dao.fetchRecord(Task.class, new String[]{"r.project"}, "WHERE r.status=:status", 
                new String[]{"status"}, new Object[]{Status.STARTED});
        
        list.stream().forEach((list1) -> System.out.println(list1));   
    }

//    @Test
    public void lookForProjectRequiringResource() {
        //Search by volunteer resource
        List<Project> list = (List<Project>)dao.fetchRecord(Volunteer.class, new String[]{"r.task.project"}, "WHERE r.resourcename=:resourcename", 
                new String[]{"resourcename"}, new Object[]{"Gilbert Ndenzi"});        
        list.stream().forEach((list1) -> System.out.println(list1)); 
        
        //Search by Asset resource
        List<Project> list2 = (List<Project>)dao.fetchRecord(Asset.class, new String[]{"r.task.project"}, "WHERE r.resourcename=:resourcename", 
                new String[]{"resourcename"}, new Object[]{"Asset 1"});        
        list2.stream().forEach((list1) -> System.out.println(list1)); 
    }

//    @Test
    public void searchForProjectsByKeywordAndLocation() {
        //Search project by keyword and location
        List<Project> list = (List<Project>)dao.fetchRecord(Project.class, new String[]{}, "WHERE r.projectname LIKE :projectname "
                                + "AND r.location.city=:city", 
                new String[]{"city", "projectname"}, new Object[]{"Fairfield", "%Proje%"});        
        list.stream().forEach((list1) -> System.out.println(list1)); 
    }

    /**
     * List projects and tasks where a volunteer has offered services, order by
     * date of task
     */
    @Test
    public void listProjectsAndTasks() {
        //Search by volunteer resource
        List<Project> list = (List<Project>)dao.fetchRecord(Task.class, new String[]{"r.project"}, "JOIN FETCH r.project.tasks b "
                                            + "WHERE TYPE(r.resource)=Volunteer AND SIZE(r.resource) > 0 ORDER BY b.startdate ASC", 
                new String[]{}, new Object[]{});        
        list.stream().map((list1) -> {
            System.out.println(list1);
            return list1;
        }).forEach((list1) -> {
            list1.getTasks().forEach((x) -> System.out.println(x));
        }); 
        
    }
}
