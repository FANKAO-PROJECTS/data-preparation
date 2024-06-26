package org.data.preparation.util;


import java.util.Set;

import net.sf.jsqlparser.util.TablesNamesFinder;
public class SqlParserUtil {
	public static Set<String> retrieveTables(String sql) throws Exception {
        return TablesNamesFinder.findTables(sql);
    }
}
