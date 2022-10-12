window.addEventListener('load', function () {
      const url = '/pacientes';
      const settings = {
        method: 'GET'
      }

      fetch(url,settings)
      .then(function(respuesta){
      return respuesta.json();
      })
      .then(function(data){
       for(paciente of data){



                  var table = document.getElementById("pacienteTable");

                  var pacienteRow =table.insertRow();

                  let tr_id = 'tr_' + paciente.id;

                  pacienteRow.id = tr_id;


                  let deleteButton = '<button' +

                                            ' id=' + '\"' + 'btn_delete_' + paciente.id + '\"' +

                                            ' type="button"' +

                                            'class="btn btn-danger btn_delete" onclick="deleteBy('+paciente.id+')">' +

                                            '&times' +

                                            '</button>';


                  let updateButton = '<button' +

                                            ' id=' + '\"' + 'btn_id_' + paciente.id + '\"' +

                                            ' type="button" onclick="findBy('+paciente.id+')"' +

                                            ' class="btn btn-info btn_id">' +

                                            paciente.id +

                                            '</button>';


                  pacienteRow.innerHTML = '<td>' + updateButton + '</td>' +

                        '<td class=\"td_apellido\">' + paciente.apellido.toUpperCase() + '</td>' +

                        '<td class=\"td_nombre\">' + paciente.nombre.toUpperCase() + '</td>' +

                        '<td class=\"td_email\">' + paciente.email.toUpperCase() + '</td>' +

                        '<td class=\"td_dni\">' + paciente.dni + '</td>' +

                        '<td class=\"td_fecha\">' + paciente.fecha.toUpperCase() + '</td>' +

                        '<td class=\"td_calle\">' + paciente.domicilio.calle.toUpperCase() + '</td>' +

                        '<td class=\"td_numero\">' + paciente.domicilio.numero + '</td>' +

                        '<td class=\"td_localidad\">' + paciente.domicilio.localidad.toUpperCase() + '</td>' +

                        '<td class=\"td_provincia\">' +paciente.domicilio.provincia.toUpperCase() + '</td>' +

                          '<td>' + deleteButton + '</td>';



              };



          })

    })