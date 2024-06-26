package org.data.preparation.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class ColumnInfoUtil {

	public Map<String, List<String>> getColumns(JdbcTemplate jdbcTemplate, List<String> tables) {
        Map<String, List<String>> tableColumns = new HashMap<>();

        for (String table : tables) {
            List<String> columns = jdbcTemplate.queryForList(
                "SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = ?", String.class, table
            );
            tableColumns.put(table, columns);
        }

        return tableColumns;
    }
	

}
