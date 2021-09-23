package com.example.springboot.cruddemo.dao;

import com.example.springboot.cruddemo.entity.Employee;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class EmployeeDAOHibernateImpl  implements EmployeeDAO{

    private EntityManager entityManager;

    @Autowired
    public EmployeeDAOHibernateImpl(EntityManager theentityManager) {
        entityManager = theentityManager;
    }

    @Override
    @Transactional
    public List<Employee> findAll() {

        Session current = entityManager.unwrap(Session.class);

        Query<Employee> query = current.createQuery("from Employee", Employee.class);


        return query.getResultList();
    }

    @Override
    public Employee findById(int theId) {


        Session currentSession = entityManager.unwrap(Session.class);




        return currentSession.get(Employee.class, theId);
    }


    @Override
    public void save(Employee theEmployee) {


        Session currentSession = entityManager.unwrap(Session.class);


        currentSession.saveOrUpdate(theEmployee);
    }


    @Override
    public void deleteById(int theId) {


        Session currentSession = entityManager.unwrap(Session.class);

        Query<Employee> theQuery =
                currentSession.createQuery(
                        "delete from Employee where id=:employeeId");
        theQuery.setParameter("employeeId", theId);

        theQuery.executeUpdate();
    }


}
