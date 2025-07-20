package org.school.backend.adapters.schema.elastic;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Document;
import java.time.LocalDateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.DateFormat;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "es_student_logs")
public class StudentLogEls {
    @Id
    @Field(type = FieldType.Integer)
    private Integer id;

    @Field(type = FieldType.Keyword)
    private String fullName;

    @Field(type = FieldType.Keyword)
    private String nickName;

    @Field(type = FieldType.Keyword)
    private String nik;

    @Field(type = FieldType.Keyword)
    private String gender;

    @Field(type = FieldType.Date, format = DateFormat.date_hour_minute_second, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dateBirth;

    @Field(type = FieldType.Keyword)
    private String birthOrder;

    @Field(type = FieldType.Keyword)
    private String tribe;

    @Field(type = FieldType.Keyword)
    private String address;

    @Field(type = FieldType.Keyword)
    private String height;

    @Field(type = FieldType.Keyword)
    private String weight;


    public StudentLogEls(
            String fullName,
            String nickName,
            String nik,
            String gender,
            LocalDateTime dateBirth,
            String birthOrder,
            String tribe,
            String address,
            String height,
            String weight
    ){
        this.fullName = fullName;
        this.nickName = nickName;
        this.nik = nik;
        this.gender = gender;
        this.dateBirth = dateBirth;
        this.birthOrder = birthOrder;
        this.tribe = tribe;
        this.address= address;
        this.height = height;
        this.weight = weight;
    }
}
