package org.school.backend.adapters.schema.elastic;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "es_grade")
public class GradeEls {
    @Id
    @Field(type = FieldType.Integer)
    private Integer id;

    @Field(type = FieldType.Keyword)
    private String gradeLevel;

    @Field(type = FieldType.Keyword)
    private String academicYear;

    @Field(type = FieldType.Keyword)
    private Integer studentId;

    public GradeEls(
            String gradeLevel,
            String academicYear,
            Integer studentId
    ){
        this.academicYear =academicYear;
        this.gradeLevel = gradeLevel;
    }
}
