<%@page import="java.util.Map"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
&nbsp;共&nbsp;${pageInfo.total}&nbsp;条数据&nbsp;每&nbsp;${pageInfo.pageSize}&nbsp;页条数据&nbsp;				
<a href="${param.url}?page=${pageInfo.firstPage}${param.serach}">首页</a>
<c:if test="${pageInfo.hasPreviousPage}">
<a href="${param.url}?page=${pageInfo.prePage}${param.serach}">前一页</a>
</c:if>
<c:forEach items="${pageInfo.navigatepageNums}" var="nav">
<c:if test="${nav == pageInfo.pageNum}">${nav}</c:if>
<c:if test="${nav != pageInfo.pageNum}"><a href="${param.url}?page=${nav}${param.serach}">${nav}</a></c:if>
</c:forEach>
<c:if test="${pageInfo.hasNextPage}">
<a href="${param.url}?page=${pageInfo.nextPage}${param.serach}">下一页</a>
</c:if>
<a href="${param.url}?page=${pageInfo.lastPage}${param.serach}">尾页</a>
&nbsp;共&nbsp;${pageInfo.pages}&nbsp;页
