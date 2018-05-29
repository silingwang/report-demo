/**
 * Project Name:ysynet
 * File Name:Pager.java
 * Package Name:com.phxl.ysynet.common.entity
 * Date:2015年9月24日下午1:03:39
 * Copyright (c) 2015, PHXL All Rights Reserved.
 */

package org.spring.springboot.util;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 分页信息实体Bean <br> 
 * @date 2015年9月24日 下午1:03:49 <br>
 * @author 黄文君
 * @version 1.0
 * @since JDK 1.6
 */
public class Pager<E> implements Serializable {

	/**总记录数*/
	@JsonProperty("records")
	private Integer total = 0;

	/**总页数*/
	@JsonProperty("total")
	private Integer totalPage;

	/**每页记录数量（默认每页10条）*/
	@JsonIgnore
	private Integer pageSize = 10;

	/**当前页码（从1开始）*/
	@JsonIgnore
	private Integer pageNum = 1;

	/**查询结果集(列表)*/
	private List<E> rows;



	public Pager() {
		super();
	}





	public Integer getTotal() {
		return total;
	}

	public Pager<E> setTotal(Integer total) {
		this.total = total;
		return this;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public Pager<E> setPageSize(Integer pageSize) {
		if (pageSize == null || pageSize <= 0) {
			pageSize = 10;//每页记录数默认值
		}
		this.pageSize = pageSize;
		return this;
	}

	/**
	 * 设置每页记录数量（允许指定默认值）
	 * @author 黄文君
	 * @date 2017年2月8日 下午3:47:36
	 * @param    pageSize
	 * @param    defaultPageSize
	 * @return void
	 */
	public Pager<E> setPageSize(Integer pageSize, Integer defaultPageSize) {
		if (pageSize == null || pageSize <= 0) {
			pageSize = defaultPageSize == null ? 10 : defaultPageSize;//每页记录数默认值
		}
		this.pageSize = pageSize;
		return this;
	}

	public Integer getPageNum() {
		return pageNum;
	}

	public Pager<E> setPageNum(Integer pageNum) {
		if (pageNum == null || pageNum <= 0) {
			pageNum = 1;//页码默认值
		}
		this.pageNum = pageNum;
		return this;
	}



	public List<E> getRows() {
		return rows;
	}

	public Pager<E> setRows(List<E> rows) {
		this.rows = rows;
		return this;
	}


	public Integer getTotalPage() {
		if (totalPage == null) {
			if (total != null && pageSize != null) {
				totalPage = (this.total % this.pageSize == 0) ? this.total / this.pageSize : this.total / this.pageSize + 1;
			}
		}
		return totalPage;
	}

	public Pager<E> setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
		return this;
	}
}