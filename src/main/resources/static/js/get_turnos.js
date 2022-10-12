window.addEventListener('load', function () {
      const url = '/turnos';
      const settings = {
        method: 'GET'
      }

      fetch(url,settings)
      .then(function(respuesta){
      return respuesta.json();
      })
      .then(function(data){
       for(turno of data){



                  var table = document.getElementById("turnoTable");

                  var turnoRow =table.insertRow();

                  let tr_id = 'tr_' + turno.id;

                  turnoRow.id = tr_id;


                  let deleteButton = '<button' +

                                            ' id=' + '\"' + 'btn_delete_' + turno.id + '\"' +

                                            ' type="button"' +

                                            'class="btn btn-danger btn_delete" onclick="deleteBy('+turno.id+')">' +

                                            '&times' +

                                            '</button>';


                  let updateButton = '<button' +

                                            ' id=' + '\"' + 'btn_id_' + turno.id + '\"' +

                                            ' type="button" onclick="findBy('+turno.id+')"' +

                                            ' class="btn btn-info btn_id">' +

                                            turno.id +

                                            '</button>';


                  turnoRow.innerHTML = '<td>' + updateButton + '</td>' +

                        '<td class=\"td_odontologo_id\">' + turno.odontologo_id + '</td>' +

                        '<td class=\"td_paciente_id\">' + turno.paciente_id + '</td>' +

                        '<td class=\"td_fecha\">' + turno.fecha + '</td>' +

                          '<td>' + deleteButton + '</td>';



              };



          })

    })