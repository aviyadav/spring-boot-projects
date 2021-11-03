package org.sample.springbatch;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.sample.springbatch.model.ExamResult;
import org.springframework.batch.item.database.ItemPreparedStatementSetter;

public class ExamResultItemPreparedStatementSetter implements ItemPreparedStatementSetter<ExamResult> {

    @Override
    public void setValues(ExamResult result, PreparedStatement ps) throws SQLException {
        ps.setString(1, result.getStudentName());
        ps.setDate(2, new java.sql.Date(result.getDob().toDate().getTime()));
        ps.setDouble(3, result.getPercentage());
    }

}
