package com.hackathon.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.hackathon.model.Questions;

public class UploadDAO{
	private DataSource dataSource = null;
	private NamedParameterJdbcTemplate namedParamJdbcTemplate = null;

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(final DataSource dataSource) {
		this.dataSource = dataSource;
		if(namedParamJdbcTemplate == null) {
			namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		}
	}

	public NamedParameterJdbcTemplate getNamedParamJdbcTemplate() {
		return namedParamJdbcTemplate;
	}
	public boolean saveFileDataInDB(List<Questions> questionList){
        String sql = "insert into gr7_questions (gq_id, gq_question, gq_marks, gq_level, gq_op1, gq_op1_ans, gq_op2, gq_op2_ans, gq_op3, gq_op3_ans, gq_op4, gq_op4_ans,  gq_tech) "
                + " VALUES (:gq_id, :gq_question, :gq_marks, :gq_level, :gq_op1, :gq_op1_ans, :gq_op2, :gq_op2_ans, :gq_op3, :gq_op3_ans, :gq_op4, :gq_op4_ans,  :gq_tech)";
        try {
            List<Map<String, String>> batchUpdateParams = new ArrayList<Map<String, String>>();
 
            for(Questions vo : questionList){
                Map<String, String> paramMap = new HashMap<String, String>();
                paramMap.put("gq_id", String.valueOf(vo.getId()));
                paramMap.put("gq_question", vo.getQuestion());
                paramMap.put("gq_marks", String.valueOf(vo.getMarks()));
                paramMap.put("gq_level", vo.getLevel());
                paramMap.put("gq_op1", vo.getOp1());
                paramMap.put("gq_op1_ans", vo.getOp1Ans());
                paramMap.put("gq_op2", vo.getOp2());
                paramMap.put("gq_op2_ans", vo.getOp2Ans());
                paramMap.put("gq_op3", vo.getOp3());
                paramMap.put("gq_op3_ans", vo.getOp3Ans());
                paramMap.put("gq_op4", vo.getOp4());
                paramMap.put("gq_op4_ans", vo.getOp4Ans());
                paramMap.put("gq_tech", vo.getTech());
     
                
                batchUpdateParams.add(paramMap);
            }
 
            getNamedParamJdbcTemplate().batchUpdate(sql, batchUpdateParams.toArray(new Map[batchUpdateParams.size()] ));
 
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
 
        return true;
 
    }
	  
}
