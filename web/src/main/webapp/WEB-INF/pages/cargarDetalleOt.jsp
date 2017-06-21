<%@ include file="/common/taglibs.jsp" %>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<head>
    <title><fmt:message key="signup.title"/></title>
    <jsp:include page="../../common/headTag.jsp" />
</head>
<script type="text/javascript">

    $(document).ready(function (){

/*        $("#detalleDialog").on("show.bs.modal", function () {

           $(this).find('input').val("");
            $(this).find('select').val("");


        });*/

        $("#btnAgregar").click (function () {

            $("#detalleDialog").find('input').val("");
            $("#detalleDialog").find('select').val("");

            $("#detalleDialog").modal(function (){
                return "show";

            });

        });


        $(document).on("click",".btnEliminar",function () {


            $.ajax({
                type: "POST",
                url: "deleteItem",
                data: { itm : $(this).data("itm")
                },
                cache: false,
                statusCode: {
                    202: function (e) {
                        Error(e.responseText, "Error");
                    }
                }
            }).done(function (result) {

                $("#detalleOtTable tbody").empty();

                mostrarTable(result);


            });



        });



    });

</script>
<div class="col-sm-12" style="padding-bottom: 20px;">
<div class="row">
    <div class="col-sm-12">
        <h2><fmt:message key="menu.detalleOt.cargarDetalleOt"/> ${ot.nrot}</h2>
        <p><fmt:message key="detalleOt.cargarDetalleOt.message"/></p>
        <br/>
    </div>
    <div class="col-sm-6">
        <button class="btn btn-primary btn-sm" id="btnAgregar" name="continuar" data-toggle="modal">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.detalle.agregar"/>
        </button>
    </div>
    <div class="col-sm-6 text-right">
        <a href="guardarOT" class="btn btn-success btn-sm">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.detalle.GuardarOt"/>
        </a>
    </div>
</div>
</div>
<div class="col-sm-12">

    <table id="detalleOtTable" class="table table-condensed table-striped table-hover">
        <thead>
        <tr>
<%--            <th>Nro° Ot</th>--%>
            <th>Descripción</th>
            <th>Marca</th>
            <th>Modelo</th>
            <th>Item</th>
            <th>Nro Certificado</th>
            <th>Laboratorio Terc.</th>
            <th>Rango</th>
            <th>S/N</th>
            <th>Fecha Calibración</th>
            <th>Fecha Ingreso</th>
            <th>Fecha Egreso</th>
            <th>Fecha Egreso certificado</th>
            <th>#</th>
        </tr>
        </thead>
        <tbody>
        <c:choose>
            <c:when test="${not empty ot.detalleOtEntityList}" >

        <c:forEach items="${ot.detalleOtEntityList}" var="detalleOtEntityList">
        <tr>
<%--            <td>${detalleOtEntityList.nrot}</td>--%>
<%--            <td>${detalleOtEntityList.cliente.razonSocial}</td>--%>
            <td>${detalleOtEntityList.descripcion}</td>
            <td>${detalleOtEntityList.marca}</td>
            <td>${detalleOtEntityList.modelo}</td>
            <td>${detalleOtEntityList.item}</td>
            <td>${detalleOtEntityList.nrcertif}</td>
            <td>${detalleOtEntityList.labterc}</td>
            <td>${detalleOtEntityList.rango}</td>
            <td>${detalleOtEntityList.sn}</td>
            <td><fmt:formatDate pattern="dd/MM/yyyy" value="${detalleOtEntityList.fCal}" /></td>
            <td><fmt:formatDate pattern="dd/MM/yyyy" value="${detalleOtEntityList.fIn}" /></td>
            <td><fmt:formatDate pattern="dd/MM/yyyy" value="${detalleOtEntityList.fEginst}" /></td>
            <td><fmt:formatDate pattern="dd/MM/yyyy" value="${detalleOtEntityList.fEgcert}" /></td>
            <td><a data-itm="${detalleOtEntityList.itm}" class="btnEliminar">Eliminar</a></td>
        </tr>
        </c:forEach>
        </c:when>
            <c:otherwise>
                <tr><td colspan="13">Sin Instrumentos agregados</td></tr>
            </c:otherwise>
        </c:choose>

        </tbody>
    </table>
</div>




<jsp:include page="detalleOtForm.jsp" />