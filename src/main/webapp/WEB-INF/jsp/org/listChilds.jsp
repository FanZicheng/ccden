<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/css/admin/basic.css" type="text/css"/>
<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/css/admin/index.css" type="text/css"/>
<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/css/admin/table1.css" type="text/css"/>
<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/css/icon.css" type="text/css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/main.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/admin/jquery.admintable.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/public/jquery.confirmdialog.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/public/jquery.static.js"></script>
<script type="text/javascript">
$(function(){
	$("#admintable").admintable({showCheckbox:false});
	$("a[oper='delete']").confirmdialog();
	$("#orgTypes").change(function(){
		var href = window.location.href;
		window.location.href = $.formatHref(href)+"?tid="+$(this).val();
	});
});
</script>
</head>
<body>
<div class="main_content_container">
	<table id="admintable" width="850" cellpadding="0" cellspacing="0">
	<thead>
	 <tr>
	    <td colspan="6" class="title">
		   <jsp:include page="nav.jsp"/>
	    </td>
	  </tr>
	   <tr>
	    <td colspan="6" class="title">
	   	 查询组织:<span class="em">[${parent.name}]</span>
	   	<span>
	   		<select id="orgTypes" name="orgTypes">
	   			<option value="">筛选组织类型</option>
	   			<c:forEach var="ot" items="${orgTypes}">
	   			<c:if test="${tid eq ot.cid }">
	   				<option value="${ot.cid }" selected="selected">${ot.cname }</option>
	   			</c:if>
	   			<c:if test="${tid != ot.cid }">
	   				<option value="${ot.cid }">${ot.cname }</option>
	   			</c:if>
	   			</c:forEach>
	   		</select>
	   	</span>
	    </td>
	  </tr>
	  <tr>
	    <td width="20%" align="center"><strong>名称</strong></td>
	    <td width="10%" align="center"><strong>类型</strong></td>
	    <td width="10%" align="center"><strong>管理类型</strong></td>
	    <td width="20%" align="center"><strong>电话</strong></td>
	    <td width="30%" align="center"><strong>操作</strong></td>
	  </tr>
	</thead>
	<tbody>
		<c:forEach var="data" items="${pagehelper.list}">
			 <tr>
			    <td align="center"><a href="<%=request.getContextPath() %>/admin/org/list/${parent.id }/${data.id }" class="color_red">${data.name }</a></td>
			    <td class="jianju10">${data.tname }</td>
			    <td class="jianju10">
			    <c:if test="${data.managerType eq 0 }">默认</c:if>
			    <c:if test="${data.managerType eq 1 }">所有部门</c:if>
			    <c:if test="${data.managerType eq 2 }">自定义</c:if>
			    <c:if test="${data.managerType eq -1 }">无</c:if>
			    </td>
			    <td align="center">${data.phone }</td>
			    <td align="center">
			    	<c:if test="${not fn:contains(existOrgTypes,data.tid)}">
			    	<a href="<%=request.getContextPath() %>/admin/org/list/${parent.id }/delete/${data.id }" class="color_red" oper="delete">删除</a>
			    	<a href="<%=request.getContextPath() %>/admin/org/list/${parent.id }/update/${data.id }" class="color_red">修改</a>
			    	</c:if>
			    	<a href="<%=request.getContextPath() %>/admin/org/persons/${data.id}" class="color_red">查询人员</a>
			    	<c:if test="${data.managerType eq 2 }">
			    	<a href="<%=request.getContextPath() %>/admin/org/setRule/${data.id }" class="color_red">设置规则</a>
			    	</c:if>
			    </td>
			  </tr>
		</c:forEach>
	  </tbody>
	  <tfoot>
	  <tr><td colspan="6">
  		<jsp:include page="/jsp/pager/pager.jsp">
			<jsp:param value="${pageContext.request.contextPath}${url}" name="url"/>
			<jsp:param value="&tid=${tid}" name="serach"/>
	    </jsp:include>  	
	  </td></tr>
	  </tfoot>
	</table>
</div>
</body>
</html>