package org.school.backend.adapters.datasources.repository;

import org.school.backend.adapters.schema.elastic.StudentLogEls;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ElasticSearchRepository extends ElasticsearchRepository<StudentLogEls, String>  {

}