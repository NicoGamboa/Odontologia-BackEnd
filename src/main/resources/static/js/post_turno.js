window.addEventListener('load', function () {

   const formulario = document.querySelector('#add_new_turno');

   formulario.addEventListener('submit', function (event) {

       event.preventDefault();
       const formData = {

           odontologo_id: document.querySelector('#odontologo_id').value,

           paciente_id: document.querySelector('#paciente_id').value,

           fecha: document.querySelector('#fecha').value,



       };

       const url = '/turnos';

       const settings = {

           method: 'POST',

           headers: {

               'Content-Type': 'application/json',

           },

           body: JSON.stringify(formData)

       }



        fetch(url, settings)
        .then(function(response){
                        if(response.status==200){let successAlert = '<div class="alert alert-success alert-dismissible">' +

                                                                             '<button type="button" class="close" ' +

                                                                             'data-dismiss="alert">&times;</button>' +

                                                                             '<strong></strong> Turno agregado  </div>'



                                                                         document.querySelector('#response').innerHTML = successAlert;

                                                                         document.querySelector('#response').style.display = "block";

                                                                         resetUploadForm();
                         }else if(response.status==403){

                                           let errorAlert = '<div class="alert alert-danger alert-dismissible">' +

                                                                                       '<button type="button" class="close"' +

                                                                                       'data-dismiss="alert">&times;</button>' +

                                                                                       '<strong> Error no tienes permiso para realizar esta accion</strong> </div>'



                                                                        document.querySelector('#response').innerHTML = errorAlert;

                                                                        document.querySelector('#response').style.display = "block";

                                                                       resetUploadForm();

                                           }else{
                                           let errorAlert = '<div class="alert alert-danger alert-dismissible">' +

                                                                                                        '<button type="button" class="close"' +

                                                                                                        'data-dismiss="alert">&times;</button>' +

                                                                                                        '<strong> Error intente nuevamente</strong> </div>'



                                                                                         document.querySelector('#response').innerHTML = errorAlert;

                                                                                         document.querySelector('#response').style.display = "block";

                                                                                        resetUploadForm();}
                                          return response.json();
                                          })
                                          });





              function resetUploadForm(){

                  document.querySelector('#odontologo_id').value = " ";

                  document.querySelector('#paciente_id').value = " ";

                  document.querySelector('#fecha').value = " ";



              }
           })