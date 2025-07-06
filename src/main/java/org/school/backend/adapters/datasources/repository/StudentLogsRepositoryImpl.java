package org.school.backend.adapters.datasources.repository;

import jakarta.transaction.Transactional;
import org.school.backend.adapters.configuration.ApplicationConfigProperties;
import org.school.backend.adapters.dto.StudentDetails;
import org.school.backend.adapters.dto.StudentLogs;
import org.school.backend.adapters.dto.StudentRequest;
import org.school.backend.adapters.schema.jpa.GradeJpa;
import org.school.backend.adapters.schema.jpa.ParentJpa;
import org.school.backend.adapters.schema.jpa.StudentLogJpa;
import org.school.backend.application.dto.GradeDto;
import org.school.backend.application.dto.ParentDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.school.backend.application.utils.DateTimeFormatterConfig.parseDate;

@Component
public class StudentLogsRepositoryImpl implements StudentLogsRepository{

    final ApplicationConfigProperties applicationConfigProperties;
    final JpaStudentLogsRepository jpaStudentLogsRepository;
    final JpaGradeRepository jpaGradeRepository;
    final JpaParentRepository jpaParentRepository;
    final  ElasticSearchRepository elasticSearchRepository;

    public StudentLogsRepositoryImpl(
            ApplicationConfigProperties applicationConfigProperties,
            final JpaStudentLogsRepository jpaStudentLogsRepository,
            final JpaGradeRepository jpaGradeRepository,
            final JpaParentRepository jpaParentRepository,
            ElasticSearchRepository elasticSearchRepository
    ){
        this.applicationConfigProperties =applicationConfigProperties;
        this.jpaStudentLogsRepository = jpaStudentLogsRepository;
        this.jpaGradeRepository = jpaGradeRepository;
        this.jpaParentRepository = jpaParentRepository;
        this.elasticSearchRepository = elasticSearchRepository;
    }

    @Override
    public List<StudentLogs> findAll(){
        List<StudentLogs> results = new ArrayList<>();

        switch (applicationConfigProperties.getDatabaseDefault().toLowerCase()){
            case "postgresql" -> jpaStudentLogsRepository.findAll()
                    .forEach(entity-> results.add(new StudentLogs(
                            entity.getId(),
                            entity.getFullName(),
                            entity.getNickName(),
                            entity.getNik(),
                            entity.getGender(),
                            entity.getDateBirth(),
                            entity.getBirthOrder()
                    )));
//            case "elasticsearch" -> elasticSearchRepository.findAll()
//                    .forEach(entity-> results.add(new StudentLogs(
//                            entity.getId(),
//                            entity.getFullName(),
//                            entity.getNickName(),
//                            entity.getNik(),
//                            entity.getGender(),
//                            entity.getDateBirth(),
//                            entity.getBirthOrder()
//                    )));
            default -> throw new IllegalArgumentException(applicationConfigProperties.getDatabaseDefault().toLowerCase());
        }
        return  results;
    }

    @Override
    public List<StudentLogs> findAll(int rpp,int page){
        List<StudentLogs> results = new ArrayList<>();

        switch (applicationConfigProperties.getDatabaseDefault().toLowerCase()){
            case "postgresql" -> jpaStudentLogsRepository.findAll()
                    .forEach(entity-> results.add(new StudentLogs(
                            entity.getId(),
                            entity.getFullName(),
                            entity.getNickName(),
                            entity.getNik(),
                            entity.getGender(),
                            entity.getDateBirth(),
                            entity.getBirthOrder()
                    )));
//            case "elasticsearch" -> elasticSearchRepository.findAll()
//                    .forEach(entity-> results.add(new StudentLogs(
//                            entity.getId(),
//                            entity.getFullName(),
//                            entity.getNickName(),
//                            entity.getNik(),
//                            entity.getGender(),
//                            entity.getDateBirth(),
//                            entity.getBirthOrder()
//                    )));
            default -> throw new IllegalArgumentException(applicationConfigProperties.getDatabaseDefault().toLowerCase());
        }
        return  results;
    }

    @Override
    public Optional<StudentDetails> findById(Object id) {
        StudentDetails studentDetails = null;
        GradeDto academicGrades;

        switch (applicationConfigProperties.getDatabaseDefault().toLowerCase()) {
            case "postgresql" -> {
                Optional<StudentLogJpa> resultJpa = jpaStudentLogsRepository.findById((Integer) id);
                if (resultJpa.isPresent()) {
                    StudentLogJpa studentJpa = resultJpa.get();
                    studentDetails = new StudentDetails();
                    studentDetails.fullName = studentJpa.getFullName();
                    studentDetails.nickName = studentJpa.getNickName();
                    studentDetails.nik = studentJpa.getNik();
                    studentDetails.gender = studentJpa.getGender();
                    studentDetails.dateBirth = studentJpa.getDateBirth();
                    studentDetails.birthOrder = studentJpa.getBirthOrder();
                    studentDetails.tribe = studentJpa.getTribe();
                    studentDetails.address = studentJpa.getAddress();
                    studentDetails.height = studentJpa.getHeight();
                    studentDetails.weight = studentJpa.getWeight();

                    academicGrades = jpaGradeRepository.findByStudentId(studentJpa.getId());
                    studentDetails.gradeClass = academicGrades;
                } else {
                    return Optional.empty();
                }
            }
            default -> throw new IllegalArgumentException("Unsupported database: " + applicationConfigProperties.getDatabaseDefault());
        }

        return Optional.of(studentDetails);
    }

    @Override
    @Transactional
    public void create(StudentRequest record){
        if (applicationConfigProperties.getDatabaseDefault().toLowerCase().equals("postgresql")) {
            StudentLogJpa studentData = new StudentLogJpa(
                    record.getFullName(),
                    record.getNickName(),
                    record.getNik(),
                    record.getGender(),
                    record.getDateBirth(),
                    record.getBirthOrder(),
                    record.getTribe(),
                    record.getAddress(),
                    record.getHeight(),
                    record.getWeight()
            );
            jpaStudentLogsRepository.save(studentData);

            GradeDto gradeDto = record.getGradeClass();
            GradeJpa gradeData = new GradeJpa(
                    gradeDto.gradeLevel(),
                    gradeDto.academicYear(),
                    studentData.getId()
            );
            jpaGradeRepository.save(gradeData);

            ParentDto parentDto = record.getParent();
            ParentJpa parentData = new ParentJpa(
                    parentDto.fatherName(),
                    parseDate(parentDto.fatherDateBirth()),
                    parentDto.fatherNik(),
                    parentDto.fatherEducation(),
                    parentDto.fatherJob(),
                    parentDto.fatherCitizen(),
                    parentDto.fatherIncome(),
                    parentDto.motherName(),
                    parseDate(parentDto.motherDateBirth()),
                    parentDto.motherNik(),
                    parentDto.motherEducation(),
                    parentDto.motherCitizen(),
                    parentDto.motherIncome(),
                    parentDto.phone(),
                    parentDto.fullAddress(),
                    parentDto.postalCode(),
                    studentData.getId()
            );
            jpaParentRepository.save(parentData);
        } else {
            throw new IllegalArgumentException("Unsupported database: " + applicationConfigProperties.getDatabaseDefault());
        }
    }


}
