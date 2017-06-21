<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<div class="modal fade" id="detalleDialog" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">Cargar items</h4>
            </div>
            <div class="modal-body">
                <form action="#" id="detalleOtForm">
                    <div class="row form-group">
                        <div class="col-sm-12">
                            <label for="cliente">Cliente</label>
                            <select id="cliente" name="cliente.clienteId" class="form-control input-sm input-sm" required>
                                <option value="">Seleccionar</option>
                                <c:forEach items="${clientes}" var="cliente">
                                    <option value="${cliente.clienteId}">${cliente.razonSocial}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="row form-group">
                        <div class="col-sm-6">
                            <div class="has-feedback">
                            <label for="descripcion">Descripcion</label>
                            <input class="form-control input-sm" id="descripcion" name="descripcion" required/>
                            <div class="help-block with-errors"></div>
                            </div>
                        </div>
                        <div class="col-sm-6">
                            <div class="has-feedback">
                                <label for="descripcion">Elemento</label>
                                <input class="form-control input-sm" id="elemento" name="elemento" required/>
                                <div class="help-block with-errors"></div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-6">
                            <div class=" form-group has-feedback">
                            <label for="fCal">Fecha de Calibración</label>
                            <input class="form-control input-sm" id="fCal" name="fCal" required/>
                            <div class="help-block with-errors"></div>
                            </div>
                        </div>
                        <div class="col-sm-6">
                            <div class="form-group has-feedback">
                            <label for="fEgcert">Fecha egreso Certificado</label>
                            <input class="form-control input-sm" id="fEgcert" name="fEgcert" required/>
                            <div class="help-block with-errors"></div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-6">
                            <div class="form-group has-feedback">
                            <label for="fEginst">Fecha egreso Instrumento</label>
                            <input class="form-control input-sm" id="fEginst" name="fEginst" required/>
                            <div class="help-block with-errors"></div>
                            </div>
                        </div>
                        <div class="col-sm-6">
                            <div class="form-group has-feedback">
                            <label for="fIn">Fecha ingreso</label>
                            <input class="form-control input-sm" id="fIn" name="fIn" required/>
                            <div class="help-block with-errors"></div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-6">
                            <div class="form-group has-feedback">
                            <label for="fEgcert">Nro Item</label>
                            <input class="form-control input-sm" id="item" type="number" name="item" required/>
                            <div class="help-block with-errors"></div>
                            </div>
                        </div>
                        <div class="col-sm-6">
                            <div class="form-group has-feedback">
                            <label for="nrcertif">Nro de Certificado</label>
                            <input class="form-control input-sm" id="nrcertif" name="nrcertif" type="number" required/>
                            <div class="help-block with-errors"></div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-12">
                            <div class="form-group has-feedback">
                            <label for="fEgcert">Laboratario Tercializado</label>
                            <input class="form-control input-sm" id="labterc" name="labterc" required/>
                            <div class="help-block with-errors"></div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-12">
                            <div class="form-group has-feedback">
                            <label for="marca">Marca</label>
                            <input class="form-control input-sm" id="marca" name="marca" required/>
                            <div class="help-block with-errors"></div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-12 has-feedback">
                            <label for="marca">Modelo</label>
                            <input class="form-control input-sm" id="modelo" name="modelo" required/>
                            <div class="help-block with-errors"></div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-6">
                            <div class="form-group has-feedback">
                            <label for="marca">Rango</label>
                            <input class="form-control input-sm" id="rango" name="rango" required/>
                            <div class="help-block with-errors"></div>
                            </div>
                        </div>
                        <div class="col-sm-6">
                            <div class="form-group has-feedback">
                            <label for="marca">SN</label>
                            <input class="form-control input-sm" id="sn" name="sn" required/>
                            <div class="help-block with-errors"></div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" id="btnGuardar" class="btn btn-primary btn-sm">Guardar Cambios</button>
                <button type="button" class="btn btn-default btn-sm" data-dismiss="modal">Cerrar</button>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">

    $(document).ready(function () {


        $("#fCal,#fEgcert,#fEginst,#fIn").datepicker({format: "dd/mm/yyyy", weekStart: "0", language: 'es'});



        $("#btnGuardar").click(function () {

            var frm = $('#detalleOtForm');
            var data = (frm.serializeArray());

            emptyValues (data);
            $.ajax({
                type: "POST",
                url: "addDetalle",
                data: emptyValues (data),
                cache: false,
                statusCode: {
                    202: function (e) {
                        Error(e.responseText, "Error");
                    }
                }
            }).done(function (result) {

                $("#detalleOtTable tbody").empty();


                mostrarTable(result);


                $("#detalleDialog").modal("hide");


            });


        });


    });


    function mostrarTable(result){



        $("#detalleOtTable tbody").append(function (){

            var tr;

            if( result.detalleOtEntityList.length == 0) return "<tr><td colspan='13'>Sin Instrumentos agregados</td></tr>";

            for(var i = 0 ; result.detalleOtEntityList.length > i ; i++){

                tr += "<tr>" ;
       /*         tr += "<td>" + result.detalleOtEntityList[i].nrot + "</td>";*/
                /*                            tr += "<td>" + result.detalleOtEntityList[i].cliente.razonSocial + "</td>";*/
                tr += "<td>" + result.detalleOtEntityList[i].descripcion + "</td>";
                tr += "<td>" + result.detalleOtEntityList[i].marca + "</td>";
                tr += "<td>" + result.detalleOtEntityList[i].modelo + "</td>";
                tr += "<td>" + result.detalleOtEntityList[i].item + "</td>";
                tr += "<td>" + result.detalleOtEntityList[i].nrcertif + "</td>";
                tr += "<td>" + result.detalleOtEntityList[i].labterc + "</td>";
                tr += "<td>" + result.detalleOtEntityList[i].rango + "</td>";
                tr += "<td>" + result.detalleOtEntityList[i].sn + "</td>";
                tr += "<td>" + result.detalleOtEntityList[i].fCal + "</td>";
                tr += "<td>" + result.detalleOtEntityList[i].fIn + "</td>";
                tr += "<td>" + result.detalleOtEntityList[i].fEgcert + "</td>";
                tr += "<td>" + result.detalleOtEntityList[i].fEginst + "</td>";
                tr += "<td><a data-itm='" + result.detalleOtEntityList[i].itm + "' class='btnEliminar'>Eliminar</a></td>";

                tr += "</tr>"

            }
            return tr;
        });


    }

    function emptyValues (object){


        var copyObject = [];

        for(var i = 0 ; object.length > i ; i ++) {

            if(object[i].value != ""){
                copyObject.push(object[i])
            }

        }

        return copyObject;

    }

</script>

</html>
