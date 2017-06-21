<%@ include file="/common/taglibs.jsp" %>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>

<head>
    <title><fmt:message key="menu.detalleOT.consultarOts"/></title>
    <meta name="menu" content=""/>
    <jsp:include page="../../common/headTag.jsp"/>
</head>

<script type="text/javascript">
    $(document).ready(function () {

    });

</script>
<div class="col-sm-12">

    <h2>Items ${search.nrot ne null ?"Para la orden de trabajo ":""} ${search.nrot}</h2>

    <p><fmt:message key="items.mensaje.consulta"/></p>

    <form action="${ctx}/items/search" method="get">
        <div class="row">
            <div class="col-sm-2">
                <label>Nro OT</label>
                <input value="${search.nrot}" name="nrot" class="form-control input-sm"/>
            </div>
            <div class="col-sm-2">
                <label>Item</label>
                <input value="${search.item}" name="item" class="form-control input-sm"/>
            </div>
            <div class="col-sm-2">
                <label>Nro Certificado</label>
                <input value="${search.nrcertif}" name="nrcertif" class="form-control input-sm"/>
            </div>
            <div class="col-sm-2">
                <label>S/N</label>
                <input value="${search.sn}" name="sn" class="form-control input-sm"/>
            </div>
            <div class="col-sm-2">
                <label>&nbsp;</label>
                <br/>
                <button type="submit" class="btn btn-default btn-sm">Buscar</button>
            </div>
        </div>
    </form>

    <br/>


    <display:table name="items" cellspacing="0" cellpadding="0" requestURI=""
                   defaultsort="1" id="items" pagesize="25" class="table table-condensed table-striped table-hover"
                   export="true">

        <display:column property="nrot" escapeXml="true" sortable="true" titleKey="Nro OT"/>
        <display:column property="descripcion" escapeXml="true" sortable="true" titleKey="Descripción"/>
        <display:column property="marca" escapeXml="true" sortable="true" titleKey="Marca"/>
        <display:column property="modelo" escapeXml="true" sortable="true" titleKey="Modelo"/>
        <display:column property="item" escapeXml="true" sortable="true" titleKey="Item"/>
        <display:column property="nrcertif" escapeXml="true" sortable="true" titleKey="Nro Certificado"/>
        <display:column property="labterc" escapeXml="true" sortable="true" titleKey="Laboratorio Terc."/>
        <display:column property="rango" escapeXml="true" sortable="true" titleKey="Rango"/>
        <display:column property="sn" escapeXml="true" sortable="true" titleKey="S/N"/>


        <display:column escapeXml="true" sortable="true" titleKey="Fecha Calibración"><fmt:formatDate
                value="${items.fCal}" pattern="dd/MM/yyyy"/></display:column>
        <display:column escapeXml="true" sortable="true" titleKey="Fecha Ingreso"><fmt:formatDate value="${items.fIn}"
                                                                                                  pattern="dd/MM/yyyy"/></display:column>
        <display:column escapeXml="true" sortable="true" titleKey="Fecha Egreso"><fmt:formatDate
                value="${items.fEginst}" pattern="dd/MM/yyyy"/></display:column>
        <display:column escapeXml="true" sortable="true" titleKey="Fecha Egreso Cert."><fmt:formatDate
                value="${items.fEgcert}" pattern="dd/MM/yyyy"/></display:column>


        <%--        <display:setProperty name="paging.banner.item_name"><fmt:message key="ot.ot"/></display:setProperty>
                <display:setProperty name="paging.banner.items_name"><fmt:message key="ot.ots"/></display:setProperty>--%>
        <display:setProperty name="paging.banner.placement">none</display:setProperty>


        <display:setProperty name="export.excel.filename" value="User List.xls"/>
        <display:setProperty name="export.csv.filename" value="User List.csv"/>
        <display:setProperty name="export.pdf.filename" value="User List.pdf"/>


    </display:table>
</div>

