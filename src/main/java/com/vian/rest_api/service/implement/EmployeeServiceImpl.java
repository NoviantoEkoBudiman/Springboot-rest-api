package com.vian.rest_api.service.implement;

import com.vian.rest_api.dto.EmployeeDto;
import com.vian.rest_api.entity.Employee;
import com.vian.rest_api.mapper.EmployeeMapper;
import com.vian.rest_api.repository.EmployeeRepository;
import com.vian.rest_api.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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
}
