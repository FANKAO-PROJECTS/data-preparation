package org.data.preparation.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.data.preparation.config.DynamicDataSourceConfig;
import org.data.preparation.dao.InsertScriptGenerator;
import org.data.preparation.util.ColumnInfoUtil;
import org.data.preparation.util.SqlParserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class TestDataPreparationService {
    @Autowired
    private ColumnInfoUtil columnInfoUtil;

    @Autowired
    private InsertScriptGenerator insertScriptGenerator;
    
    @Autowired
    private DynamicDataSourceConfig dynamicDataSourceConfig;

    public List<String> prepareTestData(String query, String url, String username, String password, String driverClassName) {
        try {
            List<String> tables = SqlParserUtil.retrieveTables(query).stream().toList();
            JdbcTemplate jdbcTemplate = dynamicDataSourceConfig.createJdbcTemplate(url, username, password, driverClassName);
            Map<String, List<String>> tableColumns = columnInfoUtil.getColumns(jdbcTemplate, tables);
            List<Map<String, Object>> data = jdbcTemplate.queryForList(query);

            List<String> insertScripts = new ArrayList<>();

            for (String table : tables) {
                List<String> columns = tableColumns.get(table);
                String script = insertScriptGenerator.generateInsertScript(table, columns, data);
                insertScripts.add(script);
            }
            return insertScripts;
        } catch (Exception e) {
            throw new RuntimeException("Error preparing test data", e);
        }
    }
}
