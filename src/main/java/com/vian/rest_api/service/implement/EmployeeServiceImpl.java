package com.vian.rest_api.service.implement;

import com.vian.rest_api.dto.EmployeeDto;
import com.vian.rest_api.entity.Employee;
import com.vian.rest_api.exception.ResourceNotFoundException;
import com.vian.rest_api.mapper.EmployeeMapper;
import com.vian.rest_api.repository.EmployeeRepository;
import com.vian.rest_api.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto){
        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId){
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() ->
            new ResourceNotFoundException("Employee with id: "+ employeeId + " is not found")
        );
        return EmployeeMapper.mapToEmployeeDto(employee);
    }

    @Override
    public List<EmployeeDto> getAllEmployee(){
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream().map((employee)->EmployeeMapper.mapToEmployeeDto(employee)).collect(Collectors.toList());
    }

    @Override
    public EmployeeDto updateEmployee(Long employeeId, EmployeeDto updatedEmployee){
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(()->
                new ResourceNotFoundException("Data not found")
        );
        employee.setFirstName(updatedEmployee.getFirstName());
        employee.setLastName(updatedEmployee.getLastName());
        employee.setEmail(updatedEmployee.getEmail());
        Employee updatedData = employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(updatedData);
    }

    @Override
    public void deleteEmployee(Long employeeId){
        employeeRepository.findById(employeeId).orElseThrow(()->
            new ResourceNotFoundException("Employee with id " + employeeId + " was not found")
        );
        employeeRepository.deleteById(employeeId);
    }
}
