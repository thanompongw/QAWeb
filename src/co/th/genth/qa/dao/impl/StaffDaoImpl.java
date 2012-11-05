/******************************************************
 * Program History
 * 
 * Project Name	            :  QA Voice File System
 * Client Name				:  
 * Package Name             :  co.th.genth.qa.dao.impl
 * Program ID 	            :  StaffDaoImpl.java
 * Program Description	    :  
 * Environment	 	        :  
 * Author					:  thanompongw
 * Version					:  1.0
 * Creation Date            :  26/10/2012
 *
 * Modification History	    :
 * Version	   Date		   Person Name		Chng Req No		Remarks
 *
 * Copyright(C) 2011-Generali Life Insurance (Thailand) Co.,Ltd. All Rights Reserved.             
 ********************************************************/
package co.th.genth.qa.dao.impl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.th.genth.qa.common.util.ErrorUtil;
import co.th.genth.qa.dao.StaffDao;
import co.th.genth.qa.domain.Staff;
import co.th.genth.qa.exception.CommonException;

/**
 * @author Thanompong.W
 *
 */
@Service("staffDao")
@Transactional(rollbackFor = CommonException.class)
public class StaffDaoImpl implements StaffDao {
	
	private static Logger logger = Logger.getLogger(StaffDaoImpl.class);
	
	@Autowired
	private JdbcTemplate qaJdbcTemplate;

	/**
	 * Default Constructor of StaffDaoImpl.java
	 */
	public StaffDaoImpl() {
	}
    
    private static final class StaffRowMapper implements RowMapper<Staff> {

		/* (non-Javadoc)
         * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
         */
        @Override
        public Staff mapRow(ResultSet rs, int i) throws SQLException {
	        Staff staff = new  Staff();
	        
	        staff.setStaffCode(rs.getString("STAFF_CODE"));
	        staff.setStaffName(rs.getString("STAFF_NAME"));
	        staff.setSectionCode(rs.getString("SECTION_CODE"));
	        staff.setTaskRatio(rs.getInt("TASK_RATIO"));
	        staff.setStatusCode(rs.getString("ACTIVE_FLAG"));
	        staff.setCreatedDate(rs.getTimestamp("CREATED_DT"));
	        staff.setCreatedBy(rs.getString("CREATED_BY"));
	        staff.setUpdatedDate(rs.getTimestamp("UPDATED_DT"));
	        staff.setUpdatedBy(rs.getString("UPDATED_BY"));
	        
	        return staff;
        }
    	
    }

	/* (non-Javadoc)
     * @see co.th.genth.ccs.dao.StaffDao#findById(java.lang.String)
     */
    @Override
    public Staff findById(String staffCode) throws CommonException {
    	
    	StringBuffer sql = new StringBuffer();
    	
    	sql.append("SELECT STAFF_CODE, ");
		sql.append("       STAFF_NAME, ");
		sql.append("       SECTION_CODE, ");
		sql.append("       ACTIVE_FLAG, ");
		sql.append("       TASK_RATIO, ");
		sql.append("       CREATED_DT, ");
		sql.append("       CREATED_BY, ");
		sql.append("       UPDATED_DT, ");
		sql.append("       UPDATED_BY ");
		sql.append("FROM TB_M_STAFF ");
		sql.append("WHERE STAFF_CODE = ? ");
		
		Staff staff = null;
		
		try {
		
			staff = qaJdbcTemplate.queryForObject(sql.toString(), 
			                                    new String[] { staffCode }, 
			                                    new StaffRowMapper());

		} catch (EmptyResultDataAccessException ex) {
			return null;
		} catch (Exception ex) {
    		logger.error(ex.getMessage(), ex);
    		throw ErrorUtil.generateError("MSTD0006AERR", ex.getMessage());
		}
		
		return staff;
    }
	
	/* (non-Javadoc)
	 * @see co.th.genth.ccs.dao.StaffDao#search(co.th.genth.ccs.domain.Staff)
	 */
	@Override
	public List<Staff> search(final Staff searchObj) throws CommonException {
		StringBuffer sql = new StringBuffer();
		
		sql.append("SELECT STAFF_CODE, ");
		sql.append("       STAFF_NAME, ");
		sql.append("       SECTION_CODE, ");
		sql.append("       ACTIVE_FLAG, ");
		sql.append("       TASK_RATIO, ");
		sql.append("       CREATED_DT, ");
		sql.append("       CREATED_BY, ");
		sql.append("       UPDATED_DT, ");
		sql.append("       UPDATED_BY ");
		sql.append("FROM TB_M_STAFF ");
		sql.append("WHERE 1 = 1 ");
		
		List<String> args = new ArrayList<String>();
		
		if (searchObj.getStaffCode() != null 
				|| searchObj.getStaffCode().length() > 0) {

			sql.append("AND STAFF_CODE LIKE ? ");
			args.add(searchObj.getStaffCode() + "%");
		} else if (searchObj.getStaffName() != null 
				|| searchObj.getStaffName().length() > 0) {

			sql.append("AND STAFF_NAME LIKE ? ");
			args.add(searchObj.getStaffName() + "%");
		} else if (searchObj.getSectionCode() != null 
				|| searchObj.getSectionCode().length() > 0) {

			sql.append("AND SECTION_CODE = ? ");
			args.add(searchObj.getSectionCode());
		}
		
		List<Staff> staffs = null;
		
		try {
		
			staffs = qaJdbcTemplate.query(sql.toString(), 
			                            args.toArray(new String[args.size()]), 
			                            new StaffRowMapper());

		} catch (EmptyResultDataAccessException ex) {
			return null;
		} catch (Exception ex) {
    		logger.error(ex.getMessage(), ex);
    		throw ErrorUtil.generateError("MSTD0006AERR", ex.getMessage());
		}
		
		return staffs;
	}
	
	/* (non-Javadoc)
	 * @see co.th.genth.ccs.dao.StaffDao#list()
	 */
	@Override
	public List<Staff> list() throws CommonException {
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT STAFF_CODE, ");
		sql.append("       STAFF_NAME, ");
		sql.append("       SECTION_CODE, ");
		sql.append("       ACTIVE_FLAG, ");
		sql.append("       TASK_RATIO, ");
		sql.append("       CREATED_DT, ");
		sql.append("       CREATED_BY, ");
		sql.append("       UPDATED_DT, ");
		sql.append("       UPDATED_BY ");
		sql.append("FROM TB_M_STAFF ");
		
		List<Staff> staffs = null;
		
		try {
		
			staffs = qaJdbcTemplate.query(sql.toString(), 
			                               new StaffRowMapper());

		} catch (EmptyResultDataAccessException ex) {
			return null;
		} catch (Exception ex) {
    		logger.error(ex.getMessage(), ex);
    		throw ErrorUtil.generateError("MSTD0006AERR", ex.getMessage());
		}
		
		return staffs;
	}
	
	/* (non-Javadoc)
	 * @see co.th.genth.ccs.dao.StaffDao#add(co.th.genth.ccs.domain.Staff)
	 */
	@Override
	public void add(final Staff staff) throws CommonException {
		final StringBuilder sql = new StringBuilder();
		
		sql.append("INSERT INTO TB_M_STAFF ");
		sql.append("           (STAFF_CODE, ");
		sql.append("           STAFF_NAME, ");
		sql.append("           SECTION_CODE, ");
		sql.append("           ACTIVE_FLAG, ");
		sql.append("           TASK_RATIO, ");
		sql.append("           CREATED_DT, ");
		sql.append("           CREATED_BY, ");
		sql.append("           UPDATED_DT, ");
		sql.append("           UPDATED_BY) ");
		sql.append("VALUES ");
		sql.append("           (?, ");
		sql.append("           ?, ");
		sql.append("           ?, ");
		sql.append("           ?, ");
		sql.append("           ?, ");
		sql.append("           ?, ");
		sql.append("           ?, ");
		sql.append("           ?, ");
		sql.append("           ?) ");
		
		try {
		
			PreparedStatementSetter pss = new PreparedStatementSetter() {
				
				public void setValues(PreparedStatement pstm) throws SQLException {
					int parameterIndex = 1;
					
					pstm.setString(parameterIndex++, staff.getStaffCode());
					pstm.setString(parameterIndex++, staff.getStaffName());
					pstm.setString(parameterIndex++, staff.getSectionCode());
					pstm.setString(parameterIndex++, staff.getStatusCode());
					pstm.setInt(parameterIndex++, staff.getTaskRatio());
					pstm.setTimestamp(parameterIndex++, staff.getCreatedDate());
					pstm.setString(parameterIndex++, staff.getCreatedBy());
					pstm.setTimestamp(parameterIndex++, staff.getUpdatedDate());
					pstm.setString(parameterIndex++, staff.getUpdatedBy());
					
				}
			};
	
			qaJdbcTemplate.update(sql.toString(), pss);

		} catch (Exception ex) {
    		logger.error(ex.getMessage(), ex);
    		throw ErrorUtil.generateError("MSTD0006AERR", ex.getMessage());
		}
		
	}
	
	/* (non-Javadoc)
	 * @see co.th.genth.ccs.dao.StaffDao#edit(co.th.genth.ccs.domain.Staff)
	 */
	@Override
	public void edit(final Staff staff) throws CommonException {
		final StringBuilder sql = new StringBuilder();
		
		sql.append("UPDATE TB_M_STAFF ");
		sql.append("SET STAFF_NAME = ?, ");
		sql.append("    SECTION_CODE = ?, ");
		sql.append("    ACTIVE_FLAG = ?, ");
		sql.append("    TASK_RATIO = ?, ");
		sql.append("    UPDATED_DT = ?, ");
		sql.append("    UPDATED_BY = ? ");
		sql.append("WHERE STAFF_CODE = ?");
		try {
		
			PreparedStatementSetter pss = new PreparedStatementSetter() {
				
				public void setValues(PreparedStatement pstm) throws SQLException {
					int parameterIndex = 1;
					
					pstm.setString(parameterIndex++, staff.getStaffName());
					pstm.setString(parameterIndex++, staff.getSectionCode());
					pstm.setString(parameterIndex++, staff.getStatusCode());
					pstm.setInt(parameterIndex++, staff.getTaskRatio());
					pstm.setTimestamp(parameterIndex++, staff.getUpdatedDate());
					pstm.setString(parameterIndex++, staff.getUpdatedBy());
					pstm.setString(parameterIndex++, staff.getStaffCode());
					
				}
			};
	
			qaJdbcTemplate.update(sql.toString(), pss);

		} catch (Exception ex) {
    		logger.error(ex.getMessage(), ex);
    		throw ErrorUtil.generateError("MSTD0006AERR", ex.getMessage());
		}
		
	}
	
	/* (non-Javadoc)
	 * @see co.th.genth.ccs.dao.StaffDao#remove(co.th.genth.ccs.domain.Staff)
	 */
	@Override
	public void remove(final Staff staff) throws CommonException {
		final StringBuilder sql = new StringBuilder();
		
		sql.append("UPDATE TB_M_STAFF ");
		sql.append("SET ACTIVE_FLAG = ?, ");
		sql.append("    UPDATED_DT = ?, ");
		sql.append("    UPDATED_BY = ? ");
		sql.append("WHERE STAFF_CODE = ? ");
		
		try {
		
			PreparedStatementSetter pss = new PreparedStatementSetter() {
				
				public void setValues(PreparedStatement pstm) throws SQLException {
					int parameterIndex = 1;
					
					pstm.setString(parameterIndex++, staff.getStatusCode());
					pstm.setTimestamp(parameterIndex++, staff.getUpdatedDate());
					pstm.setString(parameterIndex++, staff.getUpdatedBy());
					pstm.setString(parameterIndex++, staff.getStaffCode());
					
				}
			};
	
			qaJdbcTemplate.update(sql.toString(), pss);

		} catch (Exception ex) {
    		logger.error(ex.getMessage(), ex);
    		throw ErrorUtil.generateError("MSTD0006AERR", ex.getMessage());
		}
		
	}

	/**
     * @param qaJdbcTemplate the qaJdbcTemplate to set
     */
    public void setQaJdbcTemplate(JdbcTemplate qaJdbcTemplate) {
    	this.qaJdbcTemplate = qaJdbcTemplate;
    }
	
}
