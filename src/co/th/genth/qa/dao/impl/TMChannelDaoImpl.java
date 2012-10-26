/******************************************************
 * Program History
 * 
 * Project Name	            :  QA Voice File System
 * Client Name				:  
 * Package Name             :  co.th.genth.ccs.dao.impl
 * Program ID 	            :  TMChannelDaoImpl.java
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
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.th.genth.qa.common.util.ErrorUtil;
import co.th.genth.qa.dao.TMChannelDao;
import co.th.genth.qa.domain.TMChannel;
import co.th.genth.qa.exception.CommonException;

/**
 * @author Thanompong.W
 *
 */
@Service("tmChannelDao")
@Transactional(rollbackFor = CommonException.class)
public class TMChannelDaoImpl implements TMChannelDao {

	private static Logger logger = Logger.getLogger(TMChannelDaoImpl.class);
	
	private JdbcTemplate ccsJdbcTemplate;
	                                                
	/**
	 * Default Constructor of TMChannelDaoImpl.java
	 */
	public TMChannelDaoImpl() {
	}
    
    private static final class TMChannelRowMapper implements RowMapper<TMChannel> {

		/* (non-Javadoc)
         * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
         */
        @Override
        public TMChannel mapRow(ResultSet rs, int i) throws SQLException {
        	
        	TMChannel tmChannel = new TMChannel();
        	
        	tmChannel.setTmChannelId(rs.getInt("TM_CHANNEL_ID"));
        	tmChannel.setTmChannelCode(rs.getString("TM_CHANNEL_CODE"));
        	tmChannel.setTmChannelDesc(rs.getString("TM_CHENNEL_DESC"));
        	tmChannel.setActiveFlag(rs.getString("ACTIVE_FLAG"));
        	tmChannel.setCreatedBy(rs.getString("CREATED_BY"));
        	tmChannel.setCreatedDate(rs.getTimestamp("CREATED_DT"));
        	tmChannel.setUpdatedBy(rs.getString("UPDATED_BY"));
        	tmChannel.setUpdatedDate(rs.getTimestamp("UPDATED_DT"));
        	
	        return tmChannel;
        }
    	
    }
	
	/* (non-Javadoc)
	 * @see co.th.genth.ccs.dao.TMChannelDao#findById(java.lang.Integer)
	 */
	@Override
	public TMChannel findById(Integer tmChannelId) throws CommonException {
		final StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT TM_CHANNEL_ID, ");
		sql.append("       TM_CHANNEL_CODE, ");
		sql.append("       TM_CHENNEL_DESC, ");
		sql.append("       ACTIVE_FLAG, ");
		sql.append("       CREATED_DT, ");
		sql.append("       CREATED_BY, ");
		sql.append("       UPDATED_DT, ");
		sql.append("       UPDATED_BY ");
		sql.append("FROM TB_M_TM_CHANNEL ");
		sql.append("WHERE TM_CHANNEL_ID = ?");
		
		TMChannel tmChannel = null;
		
		try {
			
			tmChannel = ccsJdbcTemplate.queryForObject(sql.toString(), 
			                                        new Integer[] { tmChannelId },
			                                        new TMChannelRowMapper());
		
		} catch (EmptyResultDataAccessException ex) {
			return null;
		} catch (Exception ex) {
    		logger.error(ex.getMessage(), ex);
    		throw ErrorUtil.generateError("MSTD0006AERR", ex.getMessage());
		}
		
		return tmChannel;
	}
	
	/* (non-Javadoc)
	 * @see co.th.genth.ccs.dao.TMChannelDao#search(co.th.genth.ccs.domain.TMChannel)
	 */
	@Override
	public List<TMChannel> search(TMChannel searchObj) throws CommonException {
		final StringBuilder sql = new StringBuilder();

		sql.append("SELECT TM_CHANNEL_ID, ");
		sql.append("       TM_CHANNEL_CODE, ");
		sql.append("       TM_CHENNEL_DESC, ");
		sql.append("       ACTIVE_FLAG, ");
		sql.append("       CREATED_DT, ");
		sql.append("       CREATED_BY, ");
		sql.append("       UPDATED_DT, ");
		sql.append("       UPDATED_BY ");
		sql.append("FROM TB_M_TM_CHANNEL ");
		sql.append("WHERE 1 = 1 ");
		
		List<String> args = new ArrayList<String>();
		
		if (searchObj.getTmChannelCode() != null 
				|| searchObj.getTmChannelCode().length() > 0) {

			sql.append("AND TM_CHANNEL_CODE LIKE ? ");
			args.add(searchObj.getTmChannelCode() + "%");
		} else if (searchObj.getTmChannelDesc() != null 
				|| searchObj.getTmChannelDesc().length() > 0) {

			sql.append("AND TM_CHENNEL_DESC LIKE ? ");
			args.add(searchObj.getTmChannelDesc() + "%");
		}
		
		List<TMChannel> tmChannels = null;
		
		try {
			
			tmChannels = ccsJdbcTemplate.query(sql.toString(),
				                            args.toArray(new String[args.size()]), 
			                                new TMChannelRowMapper());
		
		} catch (EmptyResultDataAccessException ex) {
			return null;
		} catch (Exception ex) {
    		logger.error(ex.getMessage(), ex);
    		throw ErrorUtil.generateError("MSTD0006AERR", ex.getMessage());
		}
		
		return tmChannels;
	}
	
	/* (non-Javadoc)
	 * @see co.th.genth.ccs.dao.TMChannelDao#list()
	 */
	@Override
	public List<TMChannel> list() throws CommonException {
		final StringBuilder sql = new StringBuilder();

		sql.append("SELECT TM_CHANNEL_ID, ");
		sql.append("       TM_CHANNEL_CODE, ");
		sql.append("       TM_CHENNEL_DESC, ");
		sql.append("       ACTIVE_FLAG, ");
		sql.append("       CREATED_DT, ");
		sql.append("       CREATED_BY, ");
		sql.append("       UPDATED_DT, ");
		sql.append("       UPDATED_BY ");
		sql.append("FROM TB_M_TM_CHANNEL ");
		
		List<TMChannel> tmChannels = null;
		
		try {
			
			tmChannels = ccsJdbcTemplate.query(sql.toString(),
			                                new TMChannelRowMapper());
		
		} catch (EmptyResultDataAccessException ex) {
			return null;
		} catch (Exception ex) {
    		logger.error(ex.getMessage(), ex);
    		throw ErrorUtil.generateError("MSTD0006AERR", ex.getMessage());
		}
		
		return tmChannels;
	}
	
	/* (non-Javadoc)
	 * @see co.th.genth.ccs.dao.TMChannelDao#add(co.th.genth.ccs.domain.TMChannel)
	 */
	@Override
	public void add(final TMChannel tmChannel) throws CommonException {

		final StringBuilder sql = new StringBuilder();
		
		sql.append("INSERT INTO TB_M_TM_CHANNEL ");
		sql.append("           (TM_CHANNEL_CODE, ");
		sql.append("           TM_CHENNEL_DESC, ");
		sql.append("           ACTIVE_FLAG, ");
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
		sql.append("           ?) ");
		
		try {
		
			PreparedStatementSetter pss = new PreparedStatementSetter() {
				
				public void setValues(PreparedStatement pstm) throws SQLException {
					int parameterIndex = 1;
					
					pstm.setString(parameterIndex++, tmChannel.getTmChannelCode());
					pstm.setString(parameterIndex++, tmChannel.getTmChannelDesc());
					pstm.setString(parameterIndex++, tmChannel.getActiveFlag());
					pstm.setTimestamp(parameterIndex++, tmChannel.getCreatedDate());
					pstm.setString(parameterIndex++, tmChannel.getCreatedBy());
					pstm.setTimestamp(parameterIndex++, tmChannel.getUpdatedDate());
					pstm.setString(parameterIndex++, tmChannel.getUpdatedBy());
					
				}
			};
	
			ccsJdbcTemplate.update(sql.toString(), pss);

		} catch (Exception ex) {
    		logger.error(ex.getMessage(), ex);
    		throw ErrorUtil.generateError("MSTD0006AERR", ex.getMessage());
		}		
	}
	
	/* (non-Javadoc)
	 * @see co.th.genth.ccs.dao.TMChannelDao#edit(co.th.genth.ccs.domain.TMChannel)
	 */
	@Override
	public void edit(final TMChannel tmChannel) throws CommonException {

		final StringBuilder sql = new StringBuilder();
		
		sql.append("UPDATE TB_M_TM_CHANNEL ");
		sql.append("SET TM_CHANNEL_CODE = ?, ");
		sql.append("    TM_CHENNEL_DESC = ?, ");
		sql.append("    UPDATED_DT = ?, ");
		sql.append("    UPDATED_BY = ? ");
		sql.append("WHERE TM_CHANNEL_ID = ?");
		
		try {
		
			PreparedStatementSetter pss = new PreparedStatementSetter() {
				
				public void setValues(PreparedStatement pstm) throws SQLException {
					int parameterIndex = 1;
					
					pstm.setString(parameterIndex++, tmChannel.getTmChannelCode());
					pstm.setString(parameterIndex++, tmChannel.getTmChannelDesc());
					pstm.setTimestamp(parameterIndex++, tmChannel.getUpdatedDate());
					pstm.setString(parameterIndex++, tmChannel.getUpdatedBy());
					pstm.setInt(parameterIndex++, tmChannel.getTmChannelId());
					
				}
			};
	
			ccsJdbcTemplate.update(sql.toString(), pss);

		} catch (Exception ex) {
    		logger.error(ex.getMessage(), ex);
    		throw ErrorUtil.generateError("MSTD0006AERR", ex.getMessage());
		}
		
	}
	
	/* (non-Javadoc)
	 * @see co.th.genth.ccs.dao.TMChannelDao#remove(co.th.genth.ccs.domain.TMChannel)
	 */
	@Override
	public void remove(final TMChannel tmChannel) throws CommonException {

		final StringBuilder sql = new StringBuilder();
		
		sql.append("UPDATE TB_M_TM_CHANNEL ");
		sql.append("SET ACTIVE_FLAG = ?, ");
		sql.append("    UPDATED_DT = ?, ");
		sql.append("    UPDATED_BY = ? ");
		sql.append("WHERE TM_CHANNEL_ID = ?");
		
		try {
		
			PreparedStatementSetter pss = new PreparedStatementSetter() {
				
				public void setValues(PreparedStatement pstm) throws SQLException {
					int parameterIndex = 1;
					
					pstm.setString(parameterIndex++, tmChannel.getActiveFlag());
					pstm.setTimestamp(parameterIndex++, tmChannel.getUpdatedDate());
					pstm.setString(parameterIndex++, tmChannel.getUpdatedBy());
					pstm.setInt(parameterIndex++, tmChannel.getTmChannelId());
					
				}
			};
	
			ccsJdbcTemplate.update(sql.toString(), pss);

		} catch (Exception ex) {
    		logger.error(ex.getMessage(), ex);
    		throw ErrorUtil.generateError("MSTD0006AERR", ex.getMessage());
		}
		
	}

	/**
     * @param ccsJdbcTemplate the ccsJdbcTemplate to set
     */
    public void setCcsJdbcTemplate(JdbcTemplate ccsJdbcTemplate) {
    	this.ccsJdbcTemplate = ccsJdbcTemplate;
    }
	
}
